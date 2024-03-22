 
ALTER TABLE `bill` 
MODIFY `amount` decimal(12,6)  NULL DEFAULT 0;

ALTER TABLE policies
 DROP FOREIGN KEY policies_fk5;

ALTER TABLE policies
 DROP COLUMN servicesid;

ALTER TABLE services
 ADD policiesid  VARCHAR(255) ;

ALTER TABLE policies
 MODIFY COLUMN id varchar(255) ;


ALTER TABLE  services  ADD CONSTRAINT  servicespoli3_fk3  FOREIGN KEY (policiesid) REFERENCES  policies ( id );
