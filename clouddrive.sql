drop table if exists users;
drop table if exists files;
drop table if exists folders;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE='InnoDB';

CREATE TABLE `files` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `filename` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  `userid` int NOT NULL,
  `folderpath` varchar(255) NOT NULL
) ENGINE='InnoDB';

CREATE TABLE `folders` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `userid` int NOT NULL,
  `foldername` varchar(50) NOT NULL,
  `parentpath` varchar(50) NULL
) ENGINE='InnoDB';