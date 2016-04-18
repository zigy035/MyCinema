CREATE TABLE `movie` (
  `id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `genre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `theatre` (
  `id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `row_number` INTEGER COLLATE utf8_unicode_ci NOT NULL,
  `column_number` INTEGER COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `movie_broadcast` (
  `id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `movie_id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `theatre_id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `broadcast_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mb_unique_key`(`movie_id`, `theatre_id`, `broadcast_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `ticket` (
  `id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `movie_broadcast_id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `auth_user_id` varchar(36) COLLATE utf8_unicode_ci,
  `seat_row` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `seat_column` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket_unique_key`(`movie_broadcast_id`, `seat_row`, `seat_column`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `authuser` (
  `id` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `access` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
