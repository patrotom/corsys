-- SET FOREIGN_KEY_CHECKS=0;

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

SET FOREIGN_KEY_CHECKS=1;

INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (1, 'Klášterského', '420', 'Krasíkov', '56301');
INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (2, 'Filipova', '749', 'Zbýšov', '66411');
INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (3, 'Švédská', '1515', 'Hostouň', '27353');
INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (4, 'Boskovická', '1106', 'Ostrovec', '39833');
INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (5, 'Zámečnická', '332', 'Poniklá', '51401');
INSERT INTO `address` (`address_id`, `street`, `number`, `city`, `zip_code`) VALUES (6, 'Lazaretní', '871', 'Kvílice', '27375');

INSERT INTO `department` (`department_id`, `name`, `description`) VALUES (1, 'Kožné', 'todo');
INSERT INTO `department` (`department_id`, `name`, `description`) VALUES (2, 'Ortopédia', 'todo');
INSERT INTO `department` (`department_id`, `name`, `description`) VALUES (3, 'Estetická chirurgia', 'todo');

INSERT INTO `role` (`role_id`, `name`) VALUES (1, 'PATIENT');
INSERT INTO `role` (`role_id`, `name`) VALUES (2, 'DOCTOR');
INSERT INTO `role` (`role_id`, `name`) VALUES (3, 'RECEPTIONIST');
INSERT INTO `role` (`role_id`, `name`) VALUES (4, 'ADMIN');

INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (1, 1, 'tereza.stechova', 'X03MO1qnZdYdgyfeuILPmQ==', 'Tereza.stechova@seznam.cz', 'Tereza', 'Štěchová', '+420734929900', 1);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (2, 1, 'valerie.bukackova', 'X03MO1qnZdYdgyfeuILPmQ==', 'Valerie.Bukackova@seznam.cz', 'Valérie', 'Bukáčková', '+420722153768', 1);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (3, 1, 'slavomir.malina', 'X03MO1qnZdYdgyfeuILPmQ==', 'Slavomir.Malina@seznam.cz', 'Slavomír', 'Malina', '+420738770452', 1);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (4, 1, 'erik.husak', 'X03MO1qnZdYdgyfeuILPmQ==', 'Erik.Husak@seznam.cz', 'Erik', 'Husák', '+420723319551', 1);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (5, 1, 'lubomir.juchelka', 'X03MO1qnZdYdgyfeuILPmQ==', 'Lubomir.Juchelka@seznam.cz', 'Lubomír', 'Juchelka', '+420720353671', 1);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (6, 1, 'marek.kunes', 'X03MO1qnZdYdgyfeuILPmQ==', 'Marek.Kunes@seznam.cz', 'Marek', 'Kuneš', '+420734711257', 2);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (7, 1, 'oto.slajs', 'X03MO1qnZdYdgyfeuILPmQ==', 'Oto.slajs@seznam.cz', 'Oto', 'Šlajs', '+420738780848', 2);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (8, 1, 'vaclav.veleba', 'X03MO1qnZdYdgyfeuILPmQ==', 'Vaclav.Veleba@seznam.cz', 'Václav', 'Veleba', '+420739562521', 2);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (9, 1, 'lubomir.vavra', 'X03MO1qnZdYdgyfeuILPmQ==', 'Lubomir.Vavra@seznam.cz', 'Lubomír', 'Vávra', '+420608644368', 2);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (10, 1, 'vendula.vabelkova', 'X03MO1qnZdYdgyfeuILPmQ==', 'Vendula.Kabelkova@seznam.cz', 'Vendula', 'Kabelková', '+420770297628', 2);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (11, 1, 'olga.kubova', 'X03MO1qnZdYdgyfeuILPmQ==', 'Olga.Kubova@seznam.cz', 'Olga', 'Kubová', '+420729718908', 3);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (12, 1, 'felix.miskovsky', 'X03MO1qnZdYdgyfeuILPmQ==', 'Felix.Miskovsky@seznam.cz', 'Felix', 'Miškovský', '+420724610062', 3);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (13, 1, 'vera.loukotova', 'X03MO1qnZdYdgyfeuILPmQ==', 'Vera.Loukotova@seznam.cz', 'Věra', 'Loukotová', '+420733530133', 3);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (14, 1, 'oskar.zatloukal', 'X03MO1qnZdYdgyfeuILPmQ==', 'Oskar.Zatloukal@seznam.cz', 'Oskar', 'Zatloukal', '+420604256422', 3);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (15, 1, 'jaromir.synek', 'X03MO1qnZdYdgyfeuILPmQ==', 'Jaromir.Synek@seznam.cz', 'Jaromír', 'Synek', '+420607947721', 3);
INSERT INTO `user` (`user_id`, `active`, `username`, `password`, `email`, `first_name`, `last_name`, `phone`, `role_id`) VALUES (16, 1, 'kvetoslav.cina', 'X03MO1qnZdYdgyfeuILPmQ==', 'Kvetoslav.Cina@seznam.cz', 'Květoslav', 'Cina', '+420774752593', 4);

INSERT INTO `doctor` (`doctor_id`, `user_id`, `department_id`) VALUES (1, 1, 3);
INSERT INTO `doctor` (`doctor_id`, `user_id`, `department_id`) VALUES (2, 2, 2);
INSERT INTO `doctor` (`doctor_id`, `user_id`, `department_id`) VALUES (3, 3, 3);
INSERT INTO `doctor` (`doctor_id`, `user_id`, `department_id`) VALUES (4, 4, 1);
INSERT INTO `doctor` (`doctor_id`, `user_id`, `department_id`) VALUES (5, 5, 1);

INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (1, 'Prehliadka', 2, 'todo', 1);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (2, 'Odber krvi', 2, 'todo', 1);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (3, 'Ošetrenie popálenín', 4, 'todo', 1);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (4, 'Prehliadka', 1, 'todo', 2);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (5, 'Odber krvi', 5, 'todo', 2);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (6, 'Vyšetrenie kolena', 3, 'todo', 2);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (7, 'Prehliadka', 5, 'todo', 3);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (8, 'Zväčšenie pier', 3, 'todo', 3);
INSERT INTO `examination` (`examination_id`, `type`, `length`, `description`, `department_id`) VALUES (9, 'Odstránenie materského znamienka', 5, 'todo', 3);

INSERT INTO `patient` (`patient_id`, `birth_number`, `insurance`, `address_id`, `user_id`) VALUES (1, '955612/2510', 'Union', 1, 6);
INSERT INTO `patient` (`patient_id`, `birth_number`, `insurance`, `address_id`, `user_id`) VALUES (2, '955211/1668', 'Dôvera', 2, 7);
INSERT INTO `patient` (`patient_id`, `birth_number`, `insurance`, `address_id`, `user_id`) VALUES (3, '900414/6096', 'VŠZP', 3, 8);
INSERT INTO `patient` (`patient_id`, `birth_number`, `insurance`, `address_id`, `user_id`) VALUES (4, '940524/2176', 'VŠZP', 4, 9);
INSERT INTO `patient` (`patient_id`, `birth_number`, `insurance`, `address_id`, `user_id`) VALUES (5, '970427/2600', 'VŠZP', 5, 10);

INSERT INTO `receptionist` (`receptionist_id`, `user_id`, `department_id`) VALUES (1, 11, 2);
INSERT INTO `receptionist` (`receptionist_id`, `user_id`, `department_id`) VALUES (2, 12, 3);
INSERT INTO `receptionist` (`receptionist_id`, `user_id`, `department_id`) VALUES (3, 13, 1);
INSERT INTO `receptionist` (`receptionist_id`, `user_id`, `department_id`) VALUES (4, 14, 2);
INSERT INTO `receptionist` (`receptionist_id`, `user_id`, `department_id`) VALUES (5, 15, 2);

INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('1', '2018-11-26', '06:00', '18:00', '1');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('2', '2018-11-26', '05:00', '19:00', '2');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('3', '2018-11-26', '05:00', '18:00', '3');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('4', '2018-11-26', '06:00', '18:00', '4');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('5', '2018-11-26', '06:00', '18:00', '5');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('6', '2018-11-27', '05:00', '18:00', '1');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('7', '2018-11-27', '06:00', '18:00', '2');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('8', '2018-11-27', '06:00', '18:00', '3');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('9', '2018-11-27', '06:00', '19:00', '4');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('10', '2018-11-27', '05:00', '18:00', '5');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('11', '2018-11-28', '06:00', '18:00', '1');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('12', '2018-11-28', '06:00', '19:00', '2');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('13', '2018-11-28', '06:00', '19:00', '3');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('14', '2018-11-28', '06:00', '18:00', '4');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('15', '2018-11-28', '06:00', '19:00', '5');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('16', '2018-11-29', '05:00', '18:00', '1');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('17', '2018-11-29', '05:00', '19:00', '2');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('18', '2018-11-29', '05:00', '18:00', '3');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('19', '2018-11-29', '05:00', '18:00', '4');
INSERT INTO `timetable` (`timetable_id`, `date`, `time_from`, `time_to`, `doctor_id`) VALUES ('20', '2018-11-29', '06:00', '18:00', '5');

INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (1, '2018-11-26', '06:00:00', '06:30:00', 'CONFIRMED', 'Lorem', 3, 1, 7);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (2, '2018-11-26', '06:30:00', '07:15', 'CANCELED', 'Lorem', 2, 1, 8);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (3, '2018-11-26', '11:45', '13:00', 'UNCONFIRMED', 'Lorem', 3, 1, 9);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (4, '2018-11-26', '15:00', '15:45', 'CONFIRMED', 'Lorem', 4, 3, 8);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (5, '2018-11-27', '15:00', '16:15', 'UNCONFIRMED', 'Lorem', 5, 2, 5);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (6, '2018-11-27', '12:00', '12:45', 'CONFIRMED', 'Lorem', 4, 2, 6);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (7, '2018-11-28', '8:00', '8:30', 'UNCONFIRMED', 'Lorem', 5, 4, 1);
INSERT INTO `reservation` (`reservation_id`, `date`, `time_from`, `time_to`, `state`, `description`, `patient_id`, `doctor_id`, `examination_id`) VALUES (8, '2018-11-29', '9:15', '10:15', 'UNCONFIRMED', 'Lorem', 1, 5, 3);

-- SET FOREIGN_KEY_CHECKS=1;
