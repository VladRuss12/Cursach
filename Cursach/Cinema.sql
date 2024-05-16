DROP TABLE if exists users;
DROP TABLE if exists admins;
DROP TABLE if exists actors;
DROP TABLE if exists directors;
DROP TABLE if exists movies;
DROP TABLE if exists reviews;
DROP TABLE if exists persons;
DROP TABLE if exists abstractEntities;

CREATE TABLE `cinema`.`abstractEntities`
(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `cinema`.`persons`
(
    `id`          BIGINT NOT NULL,
    `name`        VARCHAR(45) NULL,
    `firstName`   VARCHAR(45) NULL,
    `lastName`    VARCHAR(45) NULL,
    `dateOfBirth` DATE NULL,
    `gender`      ENUM('MALE', 'FEMALE', 'LOVECRAFTIAN_HORROR') NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_persons_abstractEntities`
        FOREIGN KEY (`id`)
            REFERENCES `cinema`.`abstractEntities` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`users`
(
    `id`       BIGINT NOT NULL,
    `email`    VARCHAR(45) NULL,
    `password` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_users_persons`
        FOREIGN KEY (`id`)
            REFERENCES `cinema`.`persons` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`admins`
(
    `id`          BIGINT NOT NULL,
    `admin_level` INT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_admins_users`
        FOREIGN KEY (`id`)
            REFERENCES `cinema`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`actors`
(
    `id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_actors_users`
        FOREIGN KEY (`id`)
            REFERENCES `cinema`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`directors`
(
    `id`        BIGINT NOT NULL,
    `biography` TEXT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_directors_users`
        FOREIGN KEY (`id`)
            REFERENCES `cinema`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`movies`
(
    `id`           BIGINT NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(45) NULL,
    `description`  TEXT NULL,
    `release_year` INT NULL,
    `genre`        ENUM('ACTION', 'COMEDY', 'DRAMA', 'FANTASY', 'HORROR', 'MYSTERY', 'ROMANCE', 'THRILLER', 'WESTERN') NULL,
    `avgRating`    INT NULL,
    `director_id`  BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_movies_directors`
        FOREIGN KEY (`director_id`)
            REFERENCES `cinema`.`directors` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`reviews`
(
    `id`       BIGINT NOT NULL AUTO_INCREMENT,
    `comment`  TEXT NULL,
    `rating`   INT NULL,
    `user_id`  BIGINT NOT NULL,
    `movie_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_reviews_users`
        FOREIGN KEY (`user_id`)
            REFERENCES `cinema`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_reviews_movies`
        FOREIGN KEY (`movie_id`)
            REFERENCES `cinema`.`movies` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`actors`
(
    `movie_id` BIGINT NOT NULL,
    `actor_id` BIGINT NOT NULL,
    PRIMARY KEY (`movie_id`, `actor_id`),
    CONSTRAINT `fk_actors_movies`
        FOREIGN KEY (`movie_id`)
            REFERENCES `cinema`.`movies` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_actors_actors`
        FOREIGN KEY (`actor_id`)
            REFERENCES `cinema`.`actors` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- Заполняем таблицу abstractEntities
INSERT INTO `cinema`.`abstractEntities` () VALUES ();
SET @id1 = LAST_INSERT_ID();
INSERT INTO `cinema`.`abstractEntities` () VALUES ();
SET @id2 = LAST_INSERT_ID();
INSERT INTO `cinema`.`abstractEntities` () VALUES ();
SET @id3 = LAST_INSERT_ID();

-- Заполняем таблицу persons
INSERT INTO `cinema`.`persons` (`id`, `name`, `firstName`, `lastName`, `dateOfBirth`, `gender`) VALUES (@id1, 'John Doe', 'John', 'Doe', '1980-01-01', 'MALE');
INSERT INTO `cinema`.`persons` (`id`, `name`, `firstName`, `lastName`, `dateOfBirth`, `gender`) VALUES (@id2, 'Jane Doe', 'Jane', 'Doe', '1985-01-01', 'FEMALE');

-- Заполняем таблицу users
INSERT INTO `cinema`.`users` (`id`, `email`, `password`) VALUES (@id1, 'john.doe@example.com', 'password1');
INSERT INTO `cinema`.`users` (`id`, `email`, `password`) VALUES (@id2, 'jane.doe@example.com', 'password2');

-- Заполняем таблицу admins
INSERT INTO `cinema`.`admins` (`id`, `admin_level`) VALUES (@id1, 1);

-- Заполняем таблицу actors
INSERT INTO `cinema`.`actors` (`id`) VALUES (@id2);

-- Заполняем таблицу directors
INSERT INTO `cinema`.`directors` (`id`, `biography`) VALUES (@id1, 'A famous director.');

-- Заполняем таблицу movies
INSERT INTO `cinema`.`movies` (`title`, `description`, `release_year`, `genre`, `avgRating`, `director_id`) VALUES ('Movie 1', 'A great movie.', 2020, 'ACTION', 5, @id1);
SET @movie_id1 = LAST_INSERT_ID();
INSERT INTO `cinema`.`movies` (`title`, `description`, `release_year`, `genre`, `avgRating`, `director_id`) VALUES ('Movie 2', 'Another great movie.', 2021, 'COMEDY', 4, @id1);
SET @movie_id2 = LAST_INSERT_ID();

-- Заполняем таблицу reviews
INSERT INTO `cinema`.`reviews` (`comment`, `rating`, `user_id`, `movie_id`) VALUES ('Great movie!', 5, @id1, @movie_id1);
INSERT INTO `cinema`.`reviews` (`comment`, `rating`, `user_id`, `movie_id`) VALUES ('Not bad.', 3, @id2, @movie_id2);

-- Заполняем таблицу actors
INSERT INTO `cinema`.`actors` (`movie_id`, `actor_id`) VALUES (@movie_id1, @id2);
INSERT INTO `cinema`.`actors` (`movie_id`, `actor_id`) VALUES (@movie_id2, @id2);

