SET FOREIGN_KEY_CHECKS=0;

DELETE FROM `address`;
DELETE FROM `department`;
DELETE FROM `doctor`;
DELETE FROM `examination`;
DELETE FROM `notification`;
DELETE FROM `patient`;
DELETE FROM `receptionist`;
DELETE FROM `reservation`;
DELETE FROM `role`;
DELETE FROM `timetable`;
DELETE FROM `user`;

INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (1, 'Pavlikova', '1964', 'Ujezd u Chocne', '56501');
INSERT INTO `department` (`department_id`, `description`, `name`) VALUES (1, 'Oddelenie popis', 'ARO');
INSERT INTO `doctor` (`doctor_id`, `department_id`, `user_id`) VALUES (1, 1, 1);
INSERT INTO `examination` (`examination_id`, `description`, `length`, `type`, `department_id`) VALUES (1, 'Vysetrenie popis', 5, 'Vysetrenie nohy', 1);
INSERT INTO `notification` (`notification_id`, `message`, `state`, `timestamp`, `user_id`)  VALUES (1, 'Upozornenie', 'SEEN', '2018-01-01 14:54:32', 2);
INSERT INTO `patient` (`patient_id`, `birth_number`, `insurance`, `address_id`, `user_id`) VALUES (1, '1564664', 'Jozef inc', 1, 2);
INSERT INTO `receptionist` (`receptionist_id`, `department_id`, `user_id`) VALUES (1, 1, 3);
INSERT INTO `reservation` (`reservation_id`, `date`, `description`, `state`, `time_from`, `time_to`, `doctor_id`, `examination_id`, `patient_id`) VALUES (1, '2018-11-25', 'Habba', 'CONFIRMED', '15:45:00', '16:00:00', 1, 1, 1);
INSERT INTO `role` (`role_id`, `name`) VALUES (1, 'RECEPTIONIST');
INSERT INTO `role` (`role_id`, `name`) VALUES (2, 'PATIENT');
INSERT INTO `role` (`role_id`, `name`) VALUES (3, 'DOCTOR');
INSERT INTO `role` (`role_id`, `name`) VALUES (4, 'ADMIN');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES (1, '2018-11-25', '6:00:00', '19:00:00', 1);
INSERT INTO `user` (`user_id`, `active`, `email`, `first_name`, `last_name`, `password`, `phone`, `username`, `role_id`) VALUES (1, 1, 'pepek@namornik', 'Pepek', 'Namornik', 'X03MO1qnZdYdgyfeuILPmQ==', '+421915123456', 'pepek', 3);
INSERT INTO `user` (`user_id`, `active`, `email`, `first_name`, `last_name`, `password`, `phone`, `username`, `role_id`) VALUES (2, 1, 'keke@febe.sk', 'Pepek', 'Namornik', 'X03MO1qnZdYdgyfeuILPmQ==', '+421915123456', 'keke', 2);
INSERT INTO `user` (`user_id`, `active`, `email`, `first_name`, `last_name`, `password`, `phone`, `username`, `role_id`) VALUES (3, 1, 'a@b.cz', 'Pepek', 'Namornik', 'X03MO1qnZdYdgyfeuILPmQ==', '+421915123456', 'helepek', 1);
INSERT INTO `user` (`user_id`, `active`, `email`, `first_name`, `last_name`, `password`, `phone`, `username`, `role_id`) VALUES (4, 1, 'pik@cik.cz', 'Jozef', 'Valhala', 'X03MO1qnZdYdgyfeuILPmQ==', '+421915123456', 'adminpipik', 4);

SET FOREIGN_KEY_CHECKS=1;
