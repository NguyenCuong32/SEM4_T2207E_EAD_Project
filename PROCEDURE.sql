CREATE PROCEDURE GetUserByUsername(IN username VARCHAR(255))
BEGIN
    SELECT username, password, status FROM users WHERE username = username;
END;

CREATE PROCEDURE GetAuthoritiesByUsername(IN username VARCHAR(255))
BEGIN
    SELECT u.username, r.name AS role 
    FROM users u 
    INNER JOIN user_roles ur ON u.id = ur.user_id 
    INNER JOIN roles r ON ur.role_id = r.id 
    WHERE u.username = username;
END;
