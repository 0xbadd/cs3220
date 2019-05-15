drop table if exists users;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE='InnoDB';

INSERT INTO `users` (`username`, `email`, `password`)
VALUES ('albert', ' Albert.Cervantes16@calstatela.edu ', 'abcd');