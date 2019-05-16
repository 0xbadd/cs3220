drop table if exists users;
drop table if exists files;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE='InnoDB';

INSERT INTO `users` (`username`, `email`, `password`)
VALUES ('albert', 'acervan5@calstatela.edu', '$2b$10$.E5GcFmGcPROIedn7e6Wm.fs3SCfZ/OCHDo1LfGCdrpTZXO631YoW');

CREATE TABLE `files` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  `userid` int NOT NULL
) ENGINE='InnoDB';