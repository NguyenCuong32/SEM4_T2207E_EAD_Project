-- Dumping data for table `branchs`
--

INSERT INTO `branchs` (`id` ,`name` ,`address`,`phone`,`email`,`status`)
 VALUES
(0x667e11427140426fbef4357729c2c510, 'FPT Aptech Ha noi','8 Ton that thuyet, My dinh, Nam tu liem', '0911 659 131', 'aptech.fpt@fe.edu.vn', 1),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 'FPT Aptech Ho chi minh','391A Nam Ky Khoi Nghia,  Vo thi sau', '0911 789 450 ', 'aptech.fpt@fe.edu.vn', 1)
;

-- Dumping data for table `formulas`
--
INSERT INTO 
`formulas` (`id` ,`name_category` , `description`,`status`)
 VALUES
 (0x667e11427140426fbef4357729c2c510,'formula 1', 'Amount = Price x (VAT tax +  bonus + Tax Ratio) / 100 ', 1),
 (0x7df8a4fb1bfd4bc699ea67fb7fe702cf,'formula 2', 'Amount = Price x (VAT tax + 2 x bonus - Tax ratio ) / (100 x 2 ) ', 1)
 ;

 -- Dumping data for table `category`
--
INSERT INTO 
`category` (`id` ,`name_category`  ,`status`)
VALUES
(0x667e11427140426fbef4357729c2c510, 'Cleaning', 1),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 'Washing', 1),
(0x9f9d05aaa9a041808bc576e9e494f411, 'Marketing', 1)
;
 


-- Dumping data for table `policies`
--
INSERT INTO 
`policies` (`id` , `name`, `time_start`, `time_ending` , `formula_id`, `branchsid`, `vat_tax`,`tax_ratio`, `bonus`,  `status` )
VALUES
(0x667e11427140426fbef4357729c2c510,'Policies no 1', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 0x667e11427140426fbef4357729c2c510,0x667e11427140426fbef4357729c2c510, 10, 20, 50 ,  1 ),
(0x9f9d05aaa9a041808bc576e9e494f411,'Policies no 2', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 0x667e11427140426fbef4357729c2c510,0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 10, 23,60 ,  1 ),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf,'Policies no 3', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 0x7df8a4fb1bfd4bc699ea67fb7fe702cf,0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 10, 20, 20 ,  1 )
;

-- Dumping data for table  `events`
--
INSERT INTO 
 `events` (`id` , `name`,`desc`, `time_start`, `time_end` , `banner`,`status`,`eventcode`)
VALUES
(0x667e11427140426fbef4357729c2c510,'Event Default ' , 'Event Default for every account', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'img.png', 1, 'EVENTNEWBEGIN@!@#' ),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf,'Event 02 ' , 'Beginner Event', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'img.png', 1 , 'EVENTNEWBEGIN@!@#2'),
(0x9f9d05aaa9a041808bc576e9e494f411,'Event 03 ' , 'Beginner Event', '2024-10-28 19:30:35', '2026-10-28 19:30:35', 'img.png', 1, 'EVENTNEWBEGIN@!@#3' )
;

 -- Dumping data for table `services`
--
INSERT INTO 
`services` (`id` , `name`, `description`, `price`, level1 ,level2,level3 ,level4 ,level5, `category`, policiesid  ,`status` )
VALUES
(0x667e11427140426fbef4357729c2c510,'Cleaning No 1','cleaning service no 1', 12.00,  3.5, 3,2,1, 0.5
,0x667e11427140426fbef4357729c2c510   ,0x667e11427140426fbef4357729c2c510 , 1 ),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf,'Cleaning No 2','cleaning service no 2', 24.00,  3.5, 3,2,1, 0.5, 0x7df8a4fb1bfd4bc699ea67fb7fe702cf ,   0x7df8a4fb1bfd4bc699ea67fb7fe702cf , 1),
(0x9f9d05aaa9a041808bc576e9e494f411,'Cleaning No 3','cleaning service no 3', 32.00, 3.5, 3,2,1, 0.5, 0x9f9d05aaa9a041808bc576e9e494f411  , 0x9f9d05aaa9a041808bc576e9e494f411, 1 )
;



-- Dumping data for table  `eventservice`
--
INSERT INTO 
 `eventservice` (`id` ,`eventid` ,	`serviceid`)
VALUES
(0x9f9d05aaa9a041808bc576e9e494f411, 0x667e11427140426fbef4357729c2c510 , 0x7df8a4fb1bfd4bc699ea67fb7fe702cf ),
(0x667e11427140426fbef4357729c2c510, 0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 0x9f9d05aaa9a041808bc576e9e494f411 ),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 0x9f9d05aaa9a041808bc576e9e494f411, 0x667e11427140426fbef4357729c2c510 )
;


-- Dumping data for table  `eventbranchs`
--
INSERT INTO 
 `eventbranchs` (`id` , `name`, `eventid` ,	`branch_id`  )
VALUES
(0x9f9d05aaa9a041808bc576e9e494f411,'EVINHN',0x9f9d05aaa9a041808bc576e9e494f411, 0x667e11427140426fbef4357729c2c510),
(0x667e11427140426fbef4357729c2c510,'EVINHN',0x667e11427140426fbef4357729c2c510, 0x7df8a4fb1bfd4bc699ea67fb7fe702cf),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf,'EVINHN', 0x7df8a4fb1bfd4bc699ea67fb7fe702cf , 0x7df8a4fb1bfd4bc699ea67fb7fe702cf);


-- Dumping data for table  `users`
--
INSERT INTO 
 `users` (`id` , `fullname`, `phone`, `email`, address, feedbackfee, gender, total_amount, parent_phone, accountsid, `status` )
VALUES
 (0x7df8a4fb1bfd4bc699ea67fb7fe702cf, 'vuxuandu', '090932189', 'vuxuandu@gmail.com', '8 ton that thuyet'  , 4 , 1, 50, null , 0x7df8a4fb1bfd4bc699ea67fb7fe702cf , 1  ),
 (0xb6a76f9c1f4b4affbf58e1cf8256dc7c, 'hieu', '09012189', 'hieu@gmail.com','8 ton that thuyet'  , 10 , 1, 50, '090932189' , 0xb6a76f9c1f4b4affbf58e1cf8256dc7c , 1  )
;




-- Dumping data for table  `bill`
--
INSERT INTO 
`bill`(`id`,`serviceid`, `customersid`,	`time_set_start`, `time_set_end`, `eventcode`  ,`status`,  `amount`)
VALUES
 (0x7df8a4fb1bfd4bc699ea67fb7fe702cf,0x7df8a4fb1bfd4bc699ea67fb7fe702cf,0xb6a76f9c1f4b4affbf58e1cf8256dc7c ,
'2024-10-28 19:30:35', '2026-10-28 19:30:35', NULL , 1, 0)

