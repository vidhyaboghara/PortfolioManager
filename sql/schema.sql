CREATE TABLE `hackathon`.`user` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `first_name` VARCHAR(50) NOT NULL,
                                  `last_name` VARCHAR(55) NOT NULL,
                                  `email_id` VARCHAR(100) NOT NULL,
                                  `phone` VARCHAR(13) NOT NULL,
                                  PRIMARY KEY (`id`))

CREATE TABLE `hackathon`.`stock` (
                                    `stock_id` INT NOT NULL AUTO_INCREMENT,
                                    `stock_name` VARCHAR(100) NOT NULL,
                                    `close_price` FLOAT NOT NULL,
                                    `date` DATE NOT NULL,
                                    PRIMARY KEY (`stock_id`));

CREATE TABLE `hackathon`.`transaction` (
                                          `transaction_id` INT NOT NULL AUTO_INCREMENT,
                                          `transaction_date` DATE NOT NULL,
                                          `transaction_type` VARCHAR(10) NOT NULL,
                                          `amount` INT NOT NULL,
                                          `user_id` INT NOT NULL,
                                          `stock_id` INT NOT NULL,
                                          PRIMARY KEY (`transaction_id`),
                                          INDEX `transaction_user_fk_idx` (`user_id` ASC) VISIBLE,
                                          INDEX `transaction_stock_fk_idx` (`stock_id` ASC) VISIBLE,
                                          CONSTRAINT `transaction_user_fk`
                                              FOREIGN KEY (`user_id`)
                                                  REFERENCES `hackathon`.`user` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION,
                                          CONSTRAINT `transaction_stock_fk`
                                              FOREIGN KEY (`stock_id`)
                                                  REFERENCES `hackathon`.`stock` (`stock_id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION);