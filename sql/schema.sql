CREATE TABLE `conygre`.`user` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `first_name` VARCHAR(50) NOT NULL,
                                  `last_name` VARCHAR(55) NOT NULL,
                                  `email_id` VARCHAR(100) NOT NULL,
                                  `phone` VARCHAR(13) NOT NULL,
                                  PRIMARY KEY (`id`))

CREATE TABLE `conygre`.`portfolio` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `purchase_date` DATE NOT NULL,
                                       `user_id` INT NOT NULL,
                                       `stock_name` VARCHAR(100) NOT NULL,
                                       `amount` INT NOT NULL,
                                       `current_value` FLOAT NOT NULL,
                                       PRIMARY KEY (`id`),
                                       INDEX `portfolio_user_fk_idx` (`user_id` ASC) VISIBLE,
                                       CONSTRAINT `portfolio_user_fk`
                                           FOREIGN KEY (`user_id`)
                                               REFERENCES `conygre`.`user` (`id`)
                                               ON DELETE CASCADE
                                               ON UPDATE NO ACTION);