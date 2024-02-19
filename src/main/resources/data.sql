-- This file seed some data in my tables

-- Migrate data for table nrt_plex.user: ~1 rows (approximately)
TRUNCATE TABLE `user`;
INSERT INTO `user` (`id`, `email`, `name`, `password`, `user_type`) VALUES
	(1, '9939anshu@gmail.com', 'Anshu Kumar', '9939anshu@gmail.com', 'ADMIN');

-- Migrate Data for movie masters table
TRUNCATE TABLE `movie_masters`;
INSERT INTO `movie_masters` (`id`, `image`, `movie_details`, `movie_name`) VALUES
	(1, 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTCXgCV-ZNb3InBCTaLdED58dF6iZJxIvCOBurktiWxXrwGc8DB', 'THE MUMMY', 'THE MUMMY'),
	(2, 'https://m.media-amazon.com/images/M/MV5BMTYzODQzYjQtNTczNC00MzZhLTg1ZWYtZDUxYmQ3ZTY4NzA1XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg', 'WONDER WOMAN', 'WONDER WOMAN'),
	(3, 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSZeZdWD3S9rSzfwlSsnqBWERtgBHR4h_6kHb_fR_6J-BObyxfK', 'Alien: Covenant', 'Alien: Covenant'),
	(4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX2TBaWUUMpmbhcnr0zypXQltqtQmW9wED_Y8bYrynL98MM1Wq', 'Baywatch', 'Baywatch'),
	(5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXhEeDOpouHNg3A75Ngkgl-pQdWrr8ErxSuYCbb8-Tn7KcuD79', 'Pirates of the Caribbean', 'Pirates of the Caribbean'),
	(6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6NX1HzM5IkUhkwR1Yq7vkd9j5dqv0_Zaz5FCa2bzyJaUx9zOa', 'transformers 5', 'transformers 5'),
	(7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJ8wYlRSHxcAyi7TijH8FjeTLKcYsKi3qCzI8r_X0xKU8LkAn_', ' Planet of the Apes', ' Planet of the Apes'),
	(8, 'https://www.movienewsletters.net/photos/NZL_105934R1.jpg', 'Dark Tower', 'Dark Tower');