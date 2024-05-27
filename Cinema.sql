drop database if exists cinema;
create database cinema DEFAULT CHARACTER SET utf8;

use cinema;

DROP TABLE if exists directors;
DROP TABLE if exists movies;
DROP TABLE if exists reviews;
DROP TABLE if exists users;


CREATE TABLE `cinema`.`users`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `username`          VARCHAR(100)  NULL COMMENT '',
    `date_of_birth` DATE         NULL COMMENT '',
    `gender`        VARCHAR(20)  NULL COMMENT '',
    `email`         VARCHAR(100) NULL COMMENT '',
    `password`      VARCHAR(100) NULL COMMENT '',
    `user_role`     ENUM('ADMIN', 'MODERATOR', 'USER') NOT NULL COMMENT '',
    PRIMARY KEY (`id`) COMMENT ''
);

CREATE TABLE `cinema`.`directors`
(
    `id`            BIGINT      NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(100) NULL COMMENT '',
    `date_of_birth` DATE        NULL COMMENT '',
    `gender`        VARCHAR(20) NULL COMMENT '',
    `biography`     TEXT        NULL COMMENT '',
    PRIMARY KEY (`id`) COMMENT ''
);

CREATE TABLE `cinema`.`movies`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(100) NULL COMMENT '',
    `description`  TEXT         NULL COMMENT '',
    `release_year` INT          NULL COMMENT '',
    `genre`        VARCHAR(45)  NULL COMMENT '',
    `director_id`  BIGINT       NOT NULL COMMENT '',
    PRIMARY KEY (`id`) COMMENT '',
    CONSTRAINT `fk_movies_directors`
        FOREIGN KEY (`director_id`)
            REFERENCES `cinema`.`directors` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`reviews`
(
    `id`       BIGINT NOT NULL AUTO_INCREMENT,
    `comment`  TEXT   NULL COMMENT '',
    `rating`   INT    NULL COMMENT '',
    `user_id`  BIGINT NOT NULL COMMENT '',
    `movie_id` BIGINT NOT NULL COMMENT '',
    PRIMARY KEY (`id`) COMMENT '',
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

INSERT INTO `cinema`.`users` (`id`, `username`, `date_of_birth`, `gender`, `email`, `password`, `user_role`)
VALUES (1, 'John Doe', '1985-05-15', 'Male', 'john.doe@example.com', 'password1', 'USER'),
       (2, 'Jane Smith', '1990-11-20', 'Female', 'jane.smith@example.com', 'securepass', 'ADMIN'),
       (3, 'Michael Johnson', '1978-02-10', 'Male', 'michael.johnson@example.com', 'mypassword', 'USER'),
       (4, 'Emily Davis', '1992-06-05', 'Female', 'emily.davis@example.com', 'strongpass1', 'USER');

INSERT INTO `cinema`.`directors` (`id`, `name`, `date_of_birth`, `gender`, `biography`)
VALUES (1, 'Christopher Nolan', '1970-07-30', 'Male', 'Biography of Christopher Nolan'),
       (2, 'Quentin Tarantino', '1963-03-27', 'Male', 'Biography of Quentin Tarantino'),
       (3, 'Greta Gerwig', '1983-08-04', 'Female', 'Biography of Greta Gerwig'),
       (4, 'Bong Joon-ho', '1969-09-14', 'Male', 'Biography of Bong Joon-ho');

INSERT INTO `cinema`.`movies` (`id`, `title`, `description`, `release_year`, `genre`, `director_id`)
VALUES (1, 'Inception', 'Description of Inception', 2010, 'Sci-Fi', 1),
       (2, 'Pulp Fiction', 'Description of Pulp Fiction', 1994, 'Crime', 2),
       (3, 'Little Women', 'Description of Little Women', 2019, 'Drama', 3),
       (4, 'Parasite', 'Description of Parasite', 2019, 'Thriller', 4);
INSERT INTO `cinema`.`reviews` (`id`, `comment`, `rating`, `user_id`, `movie_id`)
VALUES (1, 'Great movie!', 10, 1, 1),
       (2, 'Another great movie!', 9, 1, 2),
       (3, 'Classic!', 9, 2, 2),
       (4, 'Beautifully made', 8, 3, 3),
       (5, 'Thrilling and thought-provoking', 9, 4, 4);

