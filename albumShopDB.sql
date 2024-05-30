CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
);

CREATE TABLE `album` (
  `ID` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `artist` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `price` int NOT NULL
);

ALTER TABLE `user` ADD PRIMARY KEY (`ID`);
ALTER TABLE `user` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `album` ADD PRIMARY KEY (`ID`);
ALTER TABLE `album` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
ALTER TABLE `album` ADD CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`);

INSERT INTO `album`(`user_ID`, `artist`, `title`, `genre`, `price`) VALUES (1,'Architects','Holy Hell','Metalcore',2800);
INSERT INTO `album`(`user_ID`, `artist`, `title`, `genre`, `price`) VALUES (1,'AWS','Kint a Vízből','Metalcore',5000);
INSERT INTO `album`(`user_ID`, `artist`, `title`, `genre`, `price`) VALUES (2,'Thornhill','The Dark Pool','Metalcore',4500);
INSERT INTO `album`(`user_ID`, `artist`, `title`, `genre`, `price`) VALUES (4,'Alpha Wolf','HalfLiving Things','Metalcore',7500);