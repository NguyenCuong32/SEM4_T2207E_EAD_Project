--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN');

 


--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `enabled`, `password`, `username`) VALUES
(0x667e11427140426fbef4357729c2c510, b'1', '$2a$10$zXx.7eRQDzBJ7zdjqq42VutrH5AMlXUzSkChGqtuBezFmlIcvDDbS', 'admin'),
(0x7df8a4fb1bfd4bc699ea67fb7fe702cf, b'1', '$2a$10$USqgyJhVS8O1L8J1tgEBBe.1d7ky17Rka8gFiZbQJgGO7fizx/Xmm', 'vuxuandu'),
(0x9f9d05aaa9a041808bc576e9e494f411, b'1', '$2a$10$09v0c5yKeO.XVEks3rsKrutqvyzpipfv35wvsm/TQT24irSzOdVeK', 'user1'),
(0xb6a76f9c1f4b4affbf58e1cf8256dc7c, b'1', '$2a$10$WEsy.ZfXi3ufiCYSRSDR..AMS8nS/IbE/yPOURsf6RajiuXzsYqSW', 'user'),
(0xf1fc7c96e16f4950bab22c9e5039254f, b'1', '$2a$10$y5gvApSSHLVjlWKQBDhLCePoK5Bp66DAlb6I.vpzA0TmiRByyrAF6', 'user123');


--
-- Dumping data for table `accounts_roles`
--

INSERT INTO `accounts_roles` (`account_id`, `role_id`) VALUES
(0x667e11427140426fbef4357729c2c510, 1);


-- --------------------------------------------------------
