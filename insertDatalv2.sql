-- Dumping data for table `branchs`
--

INSERT INTO `branchs` (`id` ,`name` ,`address`,`phone`,`email`,`status`)
 VALUES
('MNV2092', 'FPT Aptech Ha noi','8 Ton that thuyet, My dinh, Nam tu liem', '0911 659 131', 'aptech.fpt@fe.edu.vn', 1),
('MNV2034', 'FPT Aptech Ho chi minh','391A Nam Ky Khoi Nghia,  Vo thi sau', '0911 789 450 ', 'aptech.fpt@fe.edu.vn', 1)
;

-- Dumping data for table `formulas`
--
INSERT INTO 
`formulas` (`id` ,`name_category` , `description`,`status`)
 VALUES
 ('CT001','formula 1', 'Amount = Price x (VAT tax +  bonus + Tax Ratio) / 100 ', 1),
 ('CT002','formula 2', 'Amount = Price x (VAT tax + 2 x bonus - Tax ratio ) / (100 x 2 ) ', 1)
 ;

 -- Dumping data for table `category`
--
INSERT INTO 
`category` (`id` ,`name_category`  ,`status`)
VALUES
('LDV01', 'Cleaning', 1),
('LDV02', 'Washing', 1),
('LDV03', 'Marketing', 1)
;

-- Dumping data for table `policies`
--
INSERT INTO 
`policies` (`id` , `name`, `time_start`, `time_ending` , `formula_id`,  `status`, `branchsid`, `vat_tax`,`tax_ratio`, `bonus` )
VALUES
('PLC01','Policies no 1', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'CT001',  1,'MNV2092', 10, 20, 50  ),
('PLC02','Policies no 2', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'CT002',  1,'MNV2092', 10, 23,60  ),
('PLC03','Policies no 3', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'CT001',  1,'MNV2034', 10, 20, 20  )
;


 -- Dumping data for table `services`
--
INSERT INTO 
`services` (`id` , `name`, `description`, `category`, `price`  ,`status` , policiesid  )
VALUES
('DV01','Cleaning No 1','cleaning service no 1','LDV01', 12.00 , 1 , 'PLC01'),
('DV02','Cleaning No 2','cleaning service no 2','LDV01', 24.00 , 1, 'PLC02'),
('DV03','Cleaning No 3','cleaning service no 3','LDV01', 32.00 , 1,'PLC03' ),
('DV04','Washing No 1','Washing service no 1','LDV02', 13.00 , 1 ,'PLC01'),
('DV05','Washing No 2','Washing service no 2','LDV02', 21.00 , 1, 'PLC02'),
('DV06','Marketing No 1','Marketing service no 1','LDV03', 24.00 , 1 ,'PLC03'),
('DV07','Marketing No 2','Marketing service no 2','LDV03', 34.00 , 1, 'PLC01')
;

 -- Dumping data for table `feepercent`
--
INSERT INTO 
`feepercent` ( `level`, `ratio`, `bonus`,	`servicesid` ,`status`)
VALUES
(1, 2.3, 2, 'DV01', 1 ),
(2, 3.3, 3, 'DV01', 1 ),
(3, 4.3, 4, 'DV01', 1 ),
(4, 5.3, 5, 'DV01', 1 ),
(5, 6.3, 6, 'DV01', 1 ),
(1, 2.3, 2, 'DV02', 1 ),
(2, 3.3, 3, 'DV02', 1 ),
(3, 4.3, 4, 'DV02', 1 ),
(4, 5.3, 5, 'DV02', 1 ),
(5, 6.3, 6, 'DV02', 1 ),
(1, 2.3, 2, 'DV03', 1 ),
(2, 3.3, 3, 'DV03', 1 ),
(3, 4.3, 4, 'DV03', 1 ),
(4, 5.3, 5, 'DV03', 1 ),
(5, 6.3, 6, 'DV03', 1 ),
(1, 2.3, 2, 'DV04', 1 ),
(2, 3.3, 3, 'DV04', 1 ),
(3, 4.3, 4, 'DV04', 1 ),
(4, 5.3, 5, 'DV04', 1 ),
(1, 2.3, 2, 'DV05', 1 ),
(2, 3.3, 3, 'DV05', 1 ),
(3, 4.3, 4, 'DV05', 1 ),
(1, 4.3, 2, 'DV06', 1 ),
(2, 5.3, 3, 'DV06', 1 ),
(3, 6.3, 4, 'DV06', 1 ) 
;


-- Dumping data for table  `events`
--
INSERT INTO 
 `events` (`id` , `name`,`desc`, `time_start`, `time_end` , `banner`,`status`,`eventcode`)
VALUES
('EV01','Event 01 ' , 'Beginner Event', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'img.png', 1, 'EVENTNEWBEGIN@!@#' ),
('EV02','Event 02 ' , 'Beginner Event', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'img.png', 1 , 'EVENTNEWBEGIN@!@#2'),
('EV03','Event 03 ' , 'Beginner Event', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'img.png', 1, 'EVENTNEWBEGIN@!@#3' )
;

-- Dumping data for table  `eventservice`
--
INSERT INTO 
 `eventservice` (`id` ,`eventid` ,	`serviceid`)
VALUES
('EVSV01', 'EV01', 'DV01' ),
('EVSV02', 'EV02', 'DV02' ),
('EVSV03', 'EV02', 'DV01' ),
('EVSV04', 'EV01', 'DV02' ),
('EVSV05', 'EV01', 'DV03' ),
('EVSV06', 'EV01', 'DV04' )
;


-- Dumping data for table  `eventbranchs`
--
INSERT INTO 
 `eventbranchs` (`id` , `name`, `eventid` ,	`branch_id`  )
VALUES
('EVBR01','EVINHN','EV01', 'MNV2092'),
('EVBR02','EVINHN','EV02', 'MNV2092'),
('EVBR03','EVINHN','EV03', 'MNV2092');