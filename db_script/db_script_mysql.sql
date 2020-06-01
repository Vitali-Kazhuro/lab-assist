CREATE DATABASE IF NOT  EXISTS lab_assist;

USE lab_assist;

CREATE TABLE `sample` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `cipher` VARCHAR(255) NOT NULL,
                          `series` VARCHAR(255),
                          `sampling_report` VARCHAR(255) NOT NULL,
                          `quantity` VARCHAR(255) NOT NULL,
                          `object_of_study_id` INT NOT NULL,
                          `test_report_id` INT NOT NULL,
                          PRIMARY KEY (`id`)
);

CREATE TABLE `applicant` (
                             `id` INT NOT NULL AUTO_INCREMENT,
                             `organization` VARCHAR(255) NOT NULL,
                             `address` VARCHAR(255) NOT NULL,
                             `mailing_address` varchar(255) NOT NULL,
                             `iban` varchar(255) NOT NULL,
                             `bank` varchar(255) NOT NULL,
                             `bank_address` varchar(255) NOT NULL,
                             `bic` varchar(255) NOT NULL,
                             `unp` varchar(255) NOT NULL,
                             `okpo` varchar(255) NOT NULL,
                             `telephones` varchar(255) NOT NULL,
                             `email` varchar(255) NOT NULL,
                             `contract_number` varchar(255) NOT NULL,
                             `contract_date` date NOT NULL,
                             `head_position` varchar(255) NOT NULL,
                             `head_name` varchar(255) NOT NULL,
                             PRIMARY KEY (`id`)
);

CREATE TABLE `employee` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(255) NOT NULL,
                            `position` VARCHAR(255) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE `test_report` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `protocol_number` INT NOT NULL,
                               `date` DATE NOT NULL,
                               `start_date` DATE NOT NULL,
                               `end_date` DATE NOT NULL,
                               `applicant_id` INT NOT NULL,
                               PRIMARY KEY (`id`)
);

CREATE TABLE `sampling_authority` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `applicant_id` INT NOT NULL,
                                      `title` VARCHAR(255) NOT NULL,
                                      PRIMARY KEY (`id`)
);

CREATE TABLE `object_of_study` (
                                   `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                                   `title` VARCHAR(255) NOT NULL,
                                   `producer` VARCHAR(255),
                                   `sampling_authority_id` INT NOT NULL,
                                   `regulatory_document_id` INT NOT NULL,
                                   PRIMARY KEY (`id`)
);

CREATE TABLE `regulatory_document` (
                                       `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                                       `title` VARCHAR(255) NOT NULL,
                                       PRIMARY KEY (`id`)
);

CREATE TABLE `element` (
                           `id` INT NOT NULL AUTO_INCREMENT UNIQUE,
                           `title` VARCHAR(255) NOT NULL,
                           `symbol` VARCHAR(255),
                           PRIMARY KEY (`id`)
);

CREATE TABLE `norm` (
                        `id` INT NOT NULL AUTO_INCREMENT,
                        `element_id` INT NOT NULL,
                        `regulatory_document_id` INT NOT NULL,
                        `value` VARCHAR(255) NOT NULL,
                        `units` VARCHAR(255) NOT NULL,
                        PRIMARY KEY (`id`)
);

CREATE TABLE `test_report_doer` (
                                    `test_report_id` INT NOT NULL,
                                    `employee_id` INT NOT NULL
);

CREATE TABLE `test_method` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `title` VARCHAR(255) NOT NULL,
                               PRIMARY KEY (`id`)
);

CREATE TABLE `sample_norm` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `sample_id` INT NOT NULL,
                               `norm_id` INT NOT NULL,
                               `result1` DOUBLE,
                               `result2` DOUBLE,
                               `detection_limit` VARCHAR(255),
                               PRIMARY KEY (`id`)
);

CREATE TABLE `weather` (
                           `id` INT NOT NULL AUTO_INCREMENT,
                           `date` DATE NOT NULL,
                           `k53_10_temperature` FLOAT NOT NULL,
                           `k53_16_temperature` FLOAT NOT NULL,
                           `k42_10_temperature` FLOAT NOT NULL,
                           `k42_16_temperature` FLOAT NOT NULL,
                           `k53_10_humidity` FLOAT NOT NULL,
                           `k53_16_humidity` FLOAT NOT NULL,
                           `k42_10_humidity` FLOAT NOT NULL,
                           `k42_16_humidity` FLOAT NOT NULL,
                           `k53_10_pressure` INT NOT NULL,
                           `k53_16_pressure` INT NOT NULL,
                           `k42_10_pressure` INT NOT NULL,
                           `k42_16_pressure` INT NOT NULL,
                           PRIMARY KEY (`id`)
);

CREATE TABLE `test_report_test_method` (
                                           `test_report_id` INT NOT NULL,
                                           `test_method_id` INT NOT NULL
);

CREATE TABLE `usr`(
                        id       INT AUTO_INCREMENT PRIMARY KEY,
                        active   BIT          NOT NULL,
                        password VARCHAR(255) NULL,
                        username VARCHAR(255) NULL
);
CREATE TABLE `user_role`(
                        user_id INT          NOT NULL,
                        roles   VARCHAR(255) NULL,
                        CONSTRAINT `user_role_fk0` FOREIGN KEY (`user_id`) REFERENCES `usr` (`id`)
);

ALTER TABLE `test_report_test_method` ADD CONSTRAINT `test_report_test_method_fk0` FOREIGN KEY (`test_report_id`) REFERENCES `test_report`(`id`);

ALTER TABLE `test_report_test_method` ADD CONSTRAINT `test_report_test_method_fk1` FOREIGN KEY (`test_method_id`) REFERENCES `test_method`(`id`);

ALTER TABLE `sample` ADD CONSTRAINT `sample_fk0` FOREIGN KEY (`object_of_study_id`) REFERENCES `object_of_study`(`id`);

ALTER TABLE `sample` ADD CONSTRAINT `sample_fk1` FOREIGN KEY (`test_report_id`) REFERENCES `test_report`(`id`);

ALTER TABLE `sampling_authority` ADD CONSTRAINT `sampling_authority_fk0` FOREIGN KEY (`applicant_id`) REFERENCES `applicant`(`id`);

ALTER TABLE `object_of_study` ADD CONSTRAINT `object_of_study_fk0` FOREIGN KEY (`sampling_authority_id`) REFERENCES `sampling_authority`(`id`);

ALTER TABLE `object_of_study` ADD CONSTRAINT `object_of_study_fk1` FOREIGN KEY (`regulatory_document_id`) REFERENCES `regulatory_document`(`id`);

ALTER TABLE `norm` ADD CONSTRAINT `norm_fk0` FOREIGN KEY (`element_id`) REFERENCES `element`(`id`);

ALTER TABLE `norm` ADD CONSTRAINT `norm_fk1` FOREIGN KEY (`regulatory_document_id`) REFERENCES `regulatory_document`(`id`);

ALTER TABLE `test_report_doer` ADD CONSTRAINT `test_report_doer_fk0` FOREIGN KEY (`test_report_id`) REFERENCES `test_report`(`id`);

ALTER TABLE `test_report_doer` ADD CONSTRAINT `test_report_doer_fk1` FOREIGN KEY (`employee_id`) REFERENCES `employee`(`id`);

ALTER TABLE `sample_norm` ADD CONSTRAINT `sample_norm_fk0` FOREIGN KEY (`sample_id`) REFERENCES `sample`(`id`);

ALTER TABLE `sample_norm` ADD CONSTRAINT `sample_norm_fk1` FOREIGN KEY (`norm_id`) REFERENCES `norm`(`id`);

ALTER TABLE `test_report` ADD CONSTRAINT `test_report_fk1` FOREIGN KEY (`applicant_id`) REFERENCES `applicant`(`id`);

INSERT INTO "test_method" (title) VALUES ('');

INSERT INTO `usr` (id, username, password, active) VALUES
                             (1, 'Administrator', '$2y$08$ni59Jix7BLXm8L0m8u33bu8Nmi2F9BWPpNnLWjyelZcf8w4tJ3FRK', true),
                             (2, 'admin',  '$2y$08$nrIJ9s6C76GH4RhxksTsrOxrALVvCPaE1GfvAeFqh933S4NWjv9KG', true);
INSERT INTO `user_role` (user_id, roles) VALUES
                            (1, 'ADMIN'),
                            (2, 'ADMIN');