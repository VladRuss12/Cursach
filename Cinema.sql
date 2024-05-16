drop database if exists cinema;
create database cinema DEFAULT CHARACTER SET utf8;

use cinema;

CREATE TABLE `cinema`.`persons` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                    `name` VARCHAR(45) NULL,
                                    `surname` VARCHAR(45) NULL,
                                    `dateOfBirth` DATE NULL,
                                    `gender` ENUM('MALE', 'FEMALE', 'LOVECRAFTIAN_HORROR') NULL,
                                    PRIMARY KEY (`id`)
);

CREATE TABLE `cinema`.`users` (
                                  `id` BIGINT NOT NULL,
                                  `email` VARCHAR(45) NULL,
                                  `password` VARCHAR(45) NULL,
                                  PRIMARY KEY (`id`),
                                  CONSTRAINT `fk_users_persons`
                                      FOREIGN KEY (`id`)
                                          REFERENCES `cinema`.`persons` (`id`)
                                          ON DELETE NO ACTION
                                          ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`admins` (
                                   `id` BIGINT NOT NULL,
                                   `admin_level` INT NULL,
                                   PRIMARY KEY (`id`),
                                   CONSTRAINT `fk_admins_persons`
                                       FOREIGN KEY (`id`)
                                           REFERENCES `cinema`.`persons` (`id`)
                                           ON DELETE NO ACTION
                                           ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`actors` (
                                   `id` BIGINT NOT NULL,
                                   PRIMARY KEY (`id`),
                                   CONSTRAINT `fk_actors_persons`
                                       FOREIGN KEY (`id`)
                                           REFERENCES `cinema`.`persons` (`id`)
                                           ON DELETE NO ACTION
                                           ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`directors` (
                                      `id` BIGINT NOT NULL,
                                      `biography` VARCHAR(45) NULL,
                                      PRIMARY KEY (`id`),
                                      CONSTRAINT `fk_directors_persons`
                                          FOREIGN KEY (`id`)
                                              REFERENCES `cinema`.`persons` (`id`)
                                              ON DELETE NO ACTION
                                              ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`movies` (
                                   `id` BIGINT NOT NULL AUTO_INCREMENT,
                                   `title` VARCHAR(45) NULL,
                                   `description` VARCHAR(45) NULL,
                                   `release_year` INT NULL,
                                   `genre` ENUM('ACTION', 'ADVENTURE', 'COMEDY', 'DRAMA', 'FANTASY', 'HORROR', 'MYSTERY', 'ROMANCE', 'SCIFI', 'THRILLER', 'WESTERN') NULL,
                                   `avgRating` INT NULL,
                                   `director_id` BIGINT NOT NULL,
                                   PRIMARY KEY (`id`),
                                   CONSTRAINT `fk_movies_directors`
                                       FOREIGN KEY (`director_id`)
                                           REFERENCES `cinema`.`directors` (`id`)
                                           ON DELETE NO ACTION
                                           ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`movies_actors` (
                                          `movie_id` BIGINT NOT NULL,
                                          `actor_id` BIGINT NOT NULL,
                                          PRIMARY KEY (`movie_id`, `actor_id`),
                                          CONSTRAINT `fk_movies_actors_movies`
                                              FOREIGN KEY (`movie_id`)
                                                  REFERENCES `cinema`.`movies` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION,
                                          CONSTRAINT `fk_movies_actors_actors`
                                              FOREIGN KEY (`actor_id`)
                                                  REFERENCES `cinema`.`actors` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION
);

CREATE TABLE `cinema`.`reviews` (
                                    `id` BIGINT NOT NULL AUTO_INCREMENT,
                                    `comment` VARCHAR(45) NULL,
                                    `rating` INT NULL,
                                    `user_id` BIGINT NOT NULL,
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


DROP TABLE IF EXISTS `cinema`.`directors`;
DROP TABLE IF EXISTS `cinema`.`movies_actors`;
DROP TABLE IF EXISTS `cinema`.`reviews`;
DROP TABLE IF EXISTS `cinema`.`movies`;
DROP TABLE IF EXISTS `cinema`.`actors`;
DROP TABLE IF EXISTS `cinema`.`admins`;
DROP TABLE IF EXISTS `cinema`.`users`;
DROP TABLE IF EXISTS `cinema`.`persons`;