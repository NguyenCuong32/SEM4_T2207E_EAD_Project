-- use script after insert data lv 2

alter table `events` CHANGE COLUMN `desc`   `description` text NULL DEFAULT 'No description';

alter table `eventbranchs` DROP COLUMN `name`;

