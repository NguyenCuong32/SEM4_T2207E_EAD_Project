CREATE PROCEDURE GetUserByUsername(IN usernametk VARCHAR(255))
BEGIN
    SELECT username, password, status FROM users WHERE username = usernametk;
END;

CREATE PROCEDURE GetAuthoritiesByUsername(IN username VARCHAR(255))
BEGIN
    SELECT u.username, r.name AS role 
    FROM users u 
    INNER JOIN user_roles ur ON u.id = ur.user_id 
    INNER JOIN roles r ON ur.role_id = r.id 
    WHERE u.username = username;
END;

--Phân trang
CREATE PROCEDURE GetPaginatedData(
    IN pageNumber INT,
    IN pageSize INT,
    IN tableName VARCHAR(255)
)
BEGIN
    DECLARE offsetValue INT DEFAULT 0;
    SET offsetValue = (pageNumber - 1) * pageSize;

    SET @query = CONCAT('SELECT * FROM ', tableName, ' LIMIT ', offsetValue, ',', pageSize);

    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END;

-- Example Call: CALL GetPaginatedData(1,5,'users')