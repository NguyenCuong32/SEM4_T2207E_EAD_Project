--check phone lay id
DROP PROCEDURE IF EXISTS CHECK_PHONEID;
	
CREATE PROCEDURE CHECK_PHONEID
(IN  parent_phone varchar(20), OUT userid BINARY(16) )
 
BEGIN 

 SELECT id into userid 
 From users  
 WHERE  phone =  parent_phone ; 
 
END;

-- Vidu
--
-- CALL CHECK_PHONEID('09012189', @userid);
-- SELECT @userid;


DROP PROCEDURE IF EXISTS  GET_LEVEL_AMOUNT;
 Drop TABLE if EXISTS tempbill;
DROP TABLE if EXISTS  templevel;
CREATE PROCEDURE GET_LEVEL_AMOUNT (
IN billid  binary(16) )
 
BEGIN 

-- gọi biến
DECLARE  userid  BINARY(16)  DEFAULT 0 ;
-- DECLARE  amountfee DECIMAL(12,6)   DEFAULT 0;
-- DECLARE  lv1 int   DEFAULT 0;
-- DECLARE  lv2 int   DEFAULT 0;
-- DECLARE  lv3 int   DEFAULT 0;
-- DECLARE  lv4 int   DEFAULT 0;
-- DECLARE  lv5 int   DEFAULT 0;
 DECLARE  flag int   DEFAULT 0;
DECLARE  phone1 VARCHAR(20)   DEFAULT 0;
DECLARE  phone2 VARCHAR(20)    DEFAULT 0;
DECLARE  phone3 VARCHAR(20)    DEFAULT 0;
DECLARE  phone4 VARCHAR(20)    DEFAULT 0;
-- DECLARE  amountlv1 DECIMAL(12,6)   DEFAULT 0;

--tạo bảng tạm tempbill để lấy được thông tin người dùng , tỷ giá level từ bill
CREATE TEMPORARY TABLE tempbill
(
 SELECT A.id, A.customersid , A.amount ,  B.fullname, B.phone, B.parent_phone , C.level1, C.level2, C.level3, C.level4, C.level5
 FROM `bill` A
 INNER JOIN `users` B 
 ON A.`customersid` = B.`id`
 INNER JOIN `services` C
 ON A.`serviceid`  =  C.id
 WHERE A.id = billid
);
-- nhập giá trị tương ứng từ bảng tạm 
SELECT customersid INTO userid  FROM tempbill   LIMIT 1;
 SELECT  amount INTO @amountfee  FROM tempbill   LIMIT 1;
 SELECT  level1 INTO @lv1  FROM tempbill   LIMIT 1;
  SELECT  level2 INTO @lv2  FROM tempbill   LIMIT 1;
   SELECT  level3 INTO @lv3  FROM tempbill   LIMIT 1;
    SELECT  level4 INTO @lv4  FROM tempbill   LIMIT 1;
     SELECT  level5 INTO @lv5  FROM tempbill   LIMIT 1;

--tạo bảng tạm templevel , dùng đệ quy để tìm level và    lấy được số đt người dùng  
CREATE TEMPORARY  TABLE  templevel
( id BINARY(16) ,
  fullname VARCHAR(255), 
   phone VARCHAR(20) ,
     parent_phone VARCHAR(20),
        levelnumber int );

INSERT INTO templevel ( id ,  fullname  ,  phone  ,  parent_phone  ,   levelnumber  )
WITH RECURSIVE  temp   AS(
  SELECT e.id, e.fullname, e.phone , e.parent_phone,  0 AS number
                From   users   e
                Where e.id =  userid
                Union All
                Select b.id, b.fullname, b.phone , b.parent_phone, number +  1
                From   users   b 
                JOIN temp c ON  b.phone = c.parent_phone
                Where number < 5
     )
SELECT id, fullname, phone , parent_phone,  number
From temp ;

-- tính tiền của user gốc bên bill
SET @amountlv1 =@amountfee*@lv1;
insert into amount_eachlv (level, amount, user_id,status, bill_id)
VALUE (1, @amountlv1 , userid, 1, billid);

 -- lấy đt của người dùng ứng vs level
SELECT phone into phone1  FROM templevel WHERE   levelnumber = 1  LIMIT 1;
SELECT phone into phone2  FROM templevel WHERE   levelnumber = 2  LIMIT 1;
SELECT phone into phone3  FROM templevel WHERE   levelnumber = 3 LIMIT 1 ;
SELECT phone into phone4  FROM templevel WHERE   levelnumber = 4  LIMIT 1;
 --
 -- tính tiền của user gốc bên bill
--
-- gọi procedure tìm user id theo số đt 
CALL CHECK_PHONEID(phone1, @userid1);
--đặt cờ để check đk, nếu user là null thì cờ  = 0
SET flag = IF(@userid1 IS NULL,0, 1);
-- tính lại tiền theo lv
SET @amountlv1 =@amountfee*@lv2;
-- nhập giá trị vào bảng  amount_eachlv theo level hiện tại với điều kiện là cờ phải  = 1
insert into amount_eachlv (level, amount, user_id,status, bill_id)
select   2, @amountlv1, @userid1, 1, billid 
FROM DUAL
where flag=1;

-- tương tự với các lv 3, 4 , 5
CALL CHECK_PHONEID(phone2, @userid2);
SET @amountlv1 =@amountfee*@lv3;
SET flag = IF(@userid2 IS NULL, 0, 1);

insert into amount_eachlv (level, amount, user_id,status, bill_id)
select   3, @amountlv1, @userid2, 1, billid 
FROM DUAL
where flag=1;

CALL CHECK_PHONEID(phone3, @userid3);
SET @amountlv1 =@amountfee*@lv4;
SET @userid3 = IF(@userid3 IS NULL, 0, 1);

insert into amount_eachlv (level, amount, user_id,status, bill_id)
select   4, @amountlv1, @userid3, 1, billid 
FROM DUAL
where flag=1;

CALL CHECK_PHONEID(phone4, @userid4);
SET @amountlv1 =@amountfee*@lv5;
SET @userid4 = IF(@userid4 IS NULL, 0, 1);

insert into amount_eachlv (level, amount, user_id,status, bill_id)
select   5, @amountlv1, @userid4, 1, billid 
FROM DUAL
where flag=1;

Drop TABLE if EXISTS tempbill;
DROP TABLE if EXISTS  templevel;
 
END  ; 

-- call ví dụ 
 CALL GET_LEVEL_AMOUNT(0x7df8a4fb1bfd4bc699ea67fb7fe702cf);
--  CALL GET_LEVEL(0x9f9d05aaa9a041808bc576e9e494f411);
--  CALL GET_LEVEL(0xb6a76f9c1f4b4affbf58e1cf8256dc7c);
 