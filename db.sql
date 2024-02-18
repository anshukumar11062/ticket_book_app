-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.44-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table nrt_plex.date_operation
CREATE TABLE IF NOT EXISTS `date_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `show_date` date DEFAULT NULL,
  `show_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.date_operation: ~6 rows (approximately)
INSERT INTO `date_operation` (`id`, `show_date`, `show_time`) VALUES
	(1, '2024-02-18', '09:00 am'),
	(2, '2024-02-18', '09:00 am'),
	(3, '2024-02-18', '09:00 am'),
	(4, '2024-02-18', '09:00 pm'),
	(5, '2024-02-18', '09:00 pm'),
	(6, '2024-02-18', '09:00 pm');

-- Dumping structure for table nrt_plex.movie_masters
CREATE TABLE IF NOT EXISTS `movie_masters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  `movie_details` varchar(1000) NOT NULL,
  `movie_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.movie_masters: ~8 rows (approximately)
INSERT INTO `movie_masters` (`id`, `image`, `movie_details`, `movie_name`) VALUES
	(1, 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTCXgCV-ZNb3InBCTaLdED58dF6iZJxIvCOBurktiWxXrwGc8DB', 'THE MUMMY', 'THE MUMMY'),
	(2, 'https://m.media-amazon.com/images/M/MV5BMTYzODQzYjQtNTczNC00MzZhLTg1ZWYtZDUxYmQ3ZTY4NzA1XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg', 'WONDER WOMAN', 'WONDER WOMAN'),
	(3, 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSZeZdWD3S9rSzfwlSsnqBWERtgBHR4h_6kHb_fR_6J-BObyxfK', 'Alien: Covenant', 'Alien: Covenant'),
	(4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX2TBaWUUMpmbhcnr0zypXQltqtQmW9wED_Y8bYrynL98MM1Wq', 'Baywatch', 'Baywatch'),
	(5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXhEeDOpouHNg3A75Ngkgl-pQdWrr8ErxSuYCbb8-Tn7KcuD79', 'Pirates of the Caribbean', 'Pirates of the Caribbean'),
	(6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6NX1HzM5IkUhkwR1Yq7vkd9j5dqv0_Zaz5FCa2bzyJaUx9zOa', 'transformers 5', 'transformers 5'),
	(7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ8wYlRSHxcAyi7TijH8FjeTLKcYsKi3qCzI8r_X0xKU8LkAn_', ' Planet of the Apes', ' Planet of the Apes'),
	(8, 'https://www.movienewsletters.net/photos/NZL_105934R1.jpg', 'Dark Tower', 'Dark Tower');

-- Dumping structure for table nrt_plex.order_history
CREATE TABLE IF NOT EXISTS `order_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_on_date` date DEFAULT NULL,
  `movie_name` varchar(255) DEFAULT NULL,
  `show_on_date` date DEFAULT NULL,
  `show_time` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `is_cancelled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.order_history: ~6 rows (approximately)
INSERT INTO `order_history` (`id`, `book_on_date`, `movie_name`, `show_on_date`, `show_time`, `total`, `user_id`, `is_cancelled`) VALUES
	(1, '2024-02-18', 'THE MUMMY', '2024-02-18', '09:00 am', 1000, 1, b'0'),
	(2, '2024-02-18', 'THE MUMMY', '2024-02-18', '09:00 am', 500, 1, b'1'),
	(3, '2024-02-18', 'THE MUMMY', '2024-02-18', '09:00 am', 1000, 1, b'0'),
	(4, '2024-02-18', 'THE MUMMY', '2024-02-19', '09:00 pm', 500, 1, b'0'),
	(5, '2024-02-18', 'THE MUMMY', '2024-02-19', '09:00 pm', 500, 1, b'0'),
	(6, '2024-02-18', 'THE MUMMY', '2024-02-19', '09:00 pm', 500, 1, b'0');

-- Dumping structure for table nrt_plex.order_history_price
CREATE TABLE IF NOT EXISTS `order_history_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_history_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.order_history_price: ~8 rows (approximately)
INSERT INTO `order_history_price` (`id`, `order_history_id`, `price`) VALUES
	(1, 1, 500),
	(2, 1, 500),
	(3, 2, 500),
	(4, 3, 500),
	(5, 3, 500),
	(6, 4, 500),
	(7, 5, 500),
	(8, 6, 500);

-- Dumping structure for table nrt_plex.order_history_seat
CREATE TABLE IF NOT EXISTS `order_history_seat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_history_id` bigint(20) DEFAULT NULL,
  `seat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.order_history_seat: ~8 rows (approximately)
INSERT INTO `order_history_seat` (`id`, `order_history_id`, `seat`) VALUES
	(1, 1, 'seat1'),
	(2, 1, 'seat2'),
	(3, 2, 'seat3'),
	(4, 3, 'seat3'),
	(5, 3, 'seat4'),
	(6, 4, 'seat1'),
	(7, 5, 'seat1'),
	(8, 6, 'seat20');

-- Dumping structure for table nrt_plex.seat
CREATE TABLE IF NOT EXISTS `seat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_operation_id` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `movie_name` varchar(255) DEFAULT NULL,
  `order_history_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.seat: ~6 rows (approximately)
INSERT INTO `seat` (`id`, `date_operation_id`, `total`, `user_id`, `movie_name`, `order_history_id`) VALUES
	(1, 1, 1000, 1, 'THE MUMMY', 1),
	(2, 2, 500, 1, 'THE MUMMY', 2),
	(3, 3, 1000, 1, 'THE MUMMY', 3),
	(4, 4, 500, 1, 'THE MUMMY', 4),
	(5, 5, 500, 1, 'THE MUMMY', 5),
	(6, 6, 500, 1, 'THE MUMMY', 6);

-- Dumping structure for table nrt_plex.seat_seat_no
CREATE TABLE IF NOT EXISTS `seat_seat_no` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seat_id` bigint(20) DEFAULT NULL,
  `seat_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.seat_seat_no: ~7 rows (approximately)
INSERT INTO `seat_seat_no` (`id`, `seat_id`, `seat_no`) VALUES
	(1, 1, 'seat1'),
	(2, 1, 'seat2'),
	(3, 2, 'seat3'),
	(4, 3, 'seat3'),
	(5, 3, 'seat4'),
	(6, 4, 'seat1'),
	(7, 5, 'seat1'),
	(8, 6, 'seat20');

-- Dumping structure for table nrt_plex.seat_seat_price
CREATE TABLE IF NOT EXISTS `seat_seat_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `seat_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.seat_seat_price: ~8 rows (approximately)
INSERT INTO `seat_seat_price` (`id`, `price`, `seat_id`) VALUES
	(1, 500, 1),
	(2, 500, 1),
	(3, 500, 2),
	(4, 500, 3),
	(5, 500, 3),
	(6, 500, 4),
	(7, 500, 5),
	(8, 500, 6);

-- Dumping structure for table nrt_plex.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('USER','ADMIN') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table nrt_plex.user: ~1 rows (approximately)
INSERT INTO `user` (`id`, `email`, `name`, `password`, `user_type`) VALUES
	(1, '9939anshu@gmail.com', 'Anshu Kumar', '9939anshu@gmail.com', 'ADMIN');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
