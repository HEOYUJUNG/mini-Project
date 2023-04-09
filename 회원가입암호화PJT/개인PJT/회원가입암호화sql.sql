CREATE DATABASE login;

USE login;

CREATE TABLE `users`(
	`userNo` INT PRIMARY KEY AUTO_INCREMENT,
	`id` VARCHAR(40) NOT NULL UNIQUE,
    `password` VARCHAR(40) NOT NULL
);

DESC `users`;

INSERT INTO `users` (id, password)
VALUES ("yudaeng", 1234);

SELECT * FROM `users`;

SELECT * FROM `users` WHERE id = "yudaeng" AND password = 1234;