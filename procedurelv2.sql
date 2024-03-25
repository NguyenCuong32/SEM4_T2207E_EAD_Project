-- Store CRUD

-- Store CRUD  branchs

-- insert branchs
DROP PROCEDURE IF EXISTS create_branchs;

DELIMITER $	
CREATE PROCEDURE create_branchs
(IN  branchsid varchar(255), branchsname varchar(255) ,
	branchsaddress varchar(255) , branchsphone varchar(255) ,
     branchsemail varchar(255) , branchsstatus tinyint(1) )
 
BEGIN 

 INSERT INTO `branchs` (`id` ,`name` ,`address`,`phone`,`email`,`status`)
 VALUES
( branchsid,branchsname,branchsaddress,branchsphone,branchsemail,branchsstatus);
 
 
END $
 
DELIMITER ; 	

-- update branch

DROP PROCEDURE IF EXISTS update_branchs;

DELIMITER $	
CREATE PROCEDURE update_branchs
(IN  branchsid varchar(255), branchsname varchar(255) ,
	branchsaddress varchar(255) , branchsphone varchar(255) ,
     branchsemail varchar(255) , branchsstatus tinyint(1) )
 
BEGIN 
 update `branchs` 
 set `name` = branchsname, `address` = branchsaddress , `phone` = branchsphone, 
     `email` = branchsemail,`status` = branchsstatus   
 where `id` = branchsid;
END $
 
DELIMITER ; 	

-- delete branch

DROP PROCEDURE IF EXISTS delete_branchs;

DELIMITER $	
CREATE PROCEDURE delete_branchs
(IN  branchsid varchar(255)  )
 
BEGIN 
 
 update `branchs` 
 set  `status` = 0  
 where `id` = branchsid;

END $
 
DELIMITER ; 




-- store sử dụng công thức cho bill
DROP PROCEDURE IF EXISTS bill_amount;

CREATE PROCEDURE bill_amount
( IN   billid BINARY(16)  )
 
BEGIN 

 DECLARE   list_price DECIMAL(12,6)  DEFAULT 0;
DECLARE  formulaid  BINARY(16)  DEFAULT 0 ;
DECLARE  currentprice DECIMAL(12,6)   DEFAULT 0;
DECLARE  vattax float  DEFAULT 0;
DECLARE  bonusratio float  DEFAULT 0;
DECLARE  taxratio float  DEFAULT 0;

--tạo bảng tạm cte
CREATE TEMPORARY TABLE CTE
(
 SELECT A.id, A.serviceid, A.amount ,  B.name, B.price, B.policiesid, C.vat_tax,C.tax_ratio, C.bonus, C.formula_id 
 FROM `bill` A
 INNER JOIN `services` B 
 ON A.`serviceid` = B.`id`
 INNER JOIN policies C 
 On B.policiesid = C.id
 WHERE A.id = billid
);

--gán biến từ bảng tạm CTE
--SELECT  formula_id , price ,vat_tax, bonus ,tax_ratio
--  INTO @formulaid ,@currentprice , @vattax , @bonusratio ,@taxratio
--	FROM CTE;
 SELECT formula_id INTO formulaid  FROM CTE   LIMIT 1;
 SELECT  price INTO currentprice  FROM CTE   LIMIT 1;
  SELECT vat_tax INTO vattax FROM CTE   LIMIT 1;
   SELECT bonus INTO bonusratio  FROM CTE   LIMIT 1;
    SELECT tax_ratio INTO taxratio  FROM CTE   LIMIT 1;
 
CASE  formulaid
    WHEN formulaid = 0x667e11427140426fbef4357729c2c510 THEN SET  list_price = currentprice*(vattax + 123 + bonusratio + taxratio)/100;  
    WHEN formulaid = 0x7df8a4fb1bfd4bc699ea67fb7fe702cf THEN SET  list_price = currentprice*(vattax + (2*bonusratio) - taxratio )/100/2;
    ELSE SET  list_price = 0;
END CASE;
 
 
 update bill 
 set  amount =   list_price  
 where id = billid;

 drop TABLE CTE;
SELECT list_price, formulaid ,currentprice , vattax , bonusratio ,taxratio ;
END ;


call bill_amount(0x7df8a4fb1bfd4bc699ea67fb7fe702cf);


