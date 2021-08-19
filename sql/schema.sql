CREATE SCHEMA `hackathon` ;

CREATE TABLE `hackathon`.`user` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `first_name` VARCHAR(50) NOT NULL,
                                  `last_name` VARCHAR(55) NOT NULL,
                                  `email_id` VARCHAR(100) NOT NULL,
                                  `phone` VARCHAR(13) NOT NULL,
                                  PRIMARY KEY (`id`));

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

ALTER TABLE `hackathon`.`stock`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`stock_id`, `date`);
;

ALTER TABLE `hackathon`.`transaction`
DROP FOREIGN KEY `transaction_stock_fk`;
ALTER TABLE `hackathon`.`transaction`
DROP INDEX `transaction_stock_fk_idx` ,
ADD INDEX `transaction_stock_fk_idx` (`stock_id` ASC, `transaction_date` ASC) VISIBLE;
;
ALTER TABLE `hackathon`.`transaction`
    ADD CONSTRAINT `transaction_stock_fk`
        FOREIGN KEY (`stock_id` , `transaction_date`)
            REFERENCES `hackathon`.`stock` (`stock_id` , `date`);


INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('1', 'WFC', '40.669998', '2021-08-09');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('2', 'BAC', '399.880005', '2021-08-09');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('3', 'GS', '157.330002', '2021-08-09');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('4', 'JPM', '100.739998', '2021-08-09');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('5', 'MS', '48.650002', '2021-08-09');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('6', 'WFC', '41.430000', '2021-08-10');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('7', 'BAC', '407.970001', '2021-08-10');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('8', 'GS', '159.259995', '2021-08-10');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('9', 'JPM', '102.040001', '2021-08-10');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('10', 'MS', '49.630001', '2021-08-10');

INSERT INTO `hackathon`.`user` (`id`, `first_name`, `last_name`, `email_id`, `phone`) VALUES ('1', 'Vidya', 'Boghara', 'vboghara@dal.ca', '9027893803');
INSERT INTO `hackathon`.`user` (`id`, `first_name`, `last_name`, `email_id`, `phone`) VALUES ('2', 'Ceci', 'Cai', 'cecicai@gmail.com', '9028945675');
INSERT INTO `hackathon`.`user` (`id`, `first_name`, `last_name`, `email_id`, `phone`) VALUES ('3', 'Vera', 'Fan', 'verafan@gmail.com', '7896582536');

UPDATE `hackathon`.`stock` SET `stock_id` = '1' WHERE (`stock_id` = '6');
UPDATE `hackathon`.`stock` SET `stock_id` = '2' WHERE (`stock_id` = '7');
UPDATE `hackathon`.`stock` SET `stock_id` = '3' WHERE (`stock_id` = '8');
UPDATE `hackathon`.`stock` SET `stock_id` = '4' WHERE (`stock_id` = '9');
UPDATE `hackathon`.`stock` SET `stock_id` = '5' WHERE (`stock_id` = '10');

INSERT INTO `hackathon`.`transaction` (`transaction_id`, `transaction_date`, `transaction_type`, `amount`, `user_id`, `stock_id`) VALUES ('1', '2021-08-09', 'buy', '10', '1', '3');
INSERT INTO `hackathon`.`transaction` (`transaction_id`, `transaction_date`, `transaction_type`, `amount`, `user_id`, `stock_id`) VALUES ('2', '2021-08-09', 'buy', '15', '2', '5');
INSERT INTO `hackathon`.`transaction` (`transaction_id`, `transaction_date`, `transaction_type`, `amount`, `user_id`, `stock_id`) VALUES ('3', '2021-08-09', 'buy', '9', '3', '2');
INSERT INTO `hackathon`.`transaction` (`transaction_id`, `transaction_date`, `transaction_type`, `amount`, `user_id`, `stock_id`) VALUES ('4', '2021-08-10', 'sell', '6', '1', '3');
INSERT INTO `hackathon`.`transaction` (`transaction_id`, `transaction_date`, `transaction_type`, `amount`, `user_id`, `stock_id`) VALUES ('5', '2021-08-10', 'sell', '11', '2', '5');
INSERT INTO `hackathon`.`transaction` (`transaction_id`, `transaction_date`, `transaction_type`, `amount`, `user_id`, `stock_id`) VALUES ('6', '2021-08-10', 'sell', '4', '3', '2');

INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('1', 'WFC', '47.30', '2021-08-19');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('2', 'BAC', '40.22', '2021-08-19');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('3', 'GS', '393.18', '2021-08-19');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('4', 'JPM', '154.22', '2021-08-19');
INSERT INTO `hackathon`.`stock` (`stock_id`, `stock_name`, `close_price`, `date`) VALUES ('5', 'MS', '99.17', '2021-08-19');
