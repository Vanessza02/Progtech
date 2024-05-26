CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
);

CREATE TABLE `album` (
  `ID` int(11) NOT NULL,
  `artist` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `price` int NOT NULL
);

CREATE TABLE `cart` (
  `ID` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `album_id` int(11) NOT NULL,
  `total` int(11) NOT NULL
);

ALTER TABLE `user` ADD PRIMARY KEY (`ID`);
ALTER TABLE `user` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `album` ADD PRIMARY KEY (`ID`);
ALTER TABLE `album` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `cart` ADD PRIMARY KEY (`ID`);
ALTER TABLE `cart` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
ALTER TABLE `cart` ADD CONSTRAINT `FK_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`);
ALTER TABLE `cart` ADD CONSTRAINT `FK_cart_album` FOREIGN KEY (`album_id`) REFERENCES `album` (`ID`);