
-- câu lệnh cho mysql 
 
-- Store kiểm tra số điện thoại 

DROP PROCEDURE IF EXISTS CHECK_PHONE;

DELIMITER $	
CREATE PROCEDURE CHECK_PHONE
(IN  parent_phone varchar(20) )
 
BEGIN 

 SELECT * 
 From users  
 WHERE  phone =  parent_phone ; 
 
END $
 
DELIMITER ; 	



-- câu lệnh cho mysql 
 
-- Store kiểm tra event

DROP PROCEDURE IF EXISTS CHECK_EVENT;

DELIMITER $	
CREATE PROCEDURE CHECK_EVENT
(IN  giftcode varchar(255) )
 
BEGIN 

 SELECT * 
 From events  
 WHERE  eventcode =  giftcode ; 
 
END $

DELIMITER ; 
 


-- Store Đệ quy tìm level  cha con theo id


DROP PROCEDURE IF EXISTS  GET_LEVEL
 DELIMITER //
 
CREATE PROCEDURE GET_LEVEL (
IN  id varchar(255) )
 
BEGIN 

WITH RECURSIVE  temp   AS(
  SELECT e.id, e.fullname, e.phone , e.parent_phone,  0 AS number
                From   users   e
                Where e.id =  id
                Union All
                Select b.id, b.fullname, b.phone , b.parent_phone, number +  1
                From   users   b 
                JOIN temp c ON  b.phone = c.parent_phone
                Where number < 4
     )

SELECT * From temp;

END //
DELIMITER ; 