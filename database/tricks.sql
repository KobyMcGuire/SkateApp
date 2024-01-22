START TRANSACTION;


DROP TABLE IF EXISTS tricks, practice_bags, tricks_practice_bags CASCADE;

-- Only create one main tricks table, so if I wanted to grab all tricks together, I can.
CREATE TABLE tricks
(
   trick_id SERIAL,
   name VARCHAR(64) NOT NULL,
   flip_or_shuv VARCHAR(20) NOT NULL,
   stance VARCHAR(64) NOT NULL,
   known VARCHAR(10) NOT NULL,
   CONSTRAINT PK_tricks PRIMARY KEY(trick_id)
);

CREATE TABLE practice_bags
(
   practice_bag_id SERIAL,
   bag_creation_date VARCHAR(64) NOT NULL,
   CONSTRAINT PK_practice_bags PRIMARY KEY(practice_bag_id)
);

CREATE TABLE tricks_practice_bags
(
   practice_bag_id int NOT NULL,
   trick_id int NOT NULL,
   CONSTRAINT PK_tricks_practice_bags PRIMARY KEY(practice_bag_id, trick_id),
	CONSTRAINT FK_tricks_pratice_bags_practice_bags FOREIGN KEY(practice_bag_id) REFERENCES practice_bags(practice_bag_id),
	CONSTRAINT FK_tricks_pratice_bags_tricks FOREIGN KEY(trick_id) REFERENCES tricks(trick_id)
);

-- CREATE INSERTS TO POPULATE WITH ANY STARTING DATA
--populate tricks table
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Kickflip', 'Flip', 'Normal', 'Yes');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Heelflip', 'Flip', 'Normal', 'Yes');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Kickflip', 'Flip', 'Switch', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Heelflip', 'Flip', 'Switch', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Pop Shuv', 'Shuv', 'Normal', 'Yes');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Front Shuv', 'Shuv', 'Normal', 'Yes');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Pop Shuv', 'Shuv', 'Switch', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Front Shuv', 'Shuv', 'Switch', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Varial Kickflip', 'Flip-Shuv', 'Normal', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Varial Heelfip', 'Flip-Shuv', 'Normal', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Varial Kickflip', 'Flip-Shuv','Switch', 'No');
INSERT INTO tricks (name, flip_or_shuv, stance, known) VALUES ('Varial Heelflip', 'Flip-Shuv', 'Switch', 'No');

--populate the practice_bags table
INSERT INTO practice_bags (bag_creation_date) VALUES ('Mon Aug 31 2020'),
INSERT INTO practice_bags (bag_creation_date) VALUES ('Tue Sep 20 2020'),
INSERT INTO practice_bags (bag_creation_date) VALUES ('Fri Dec 01 2021'),

--populate the tricks_practice_bags table
INSERT INTO tricks_practice_bags (practice_bag_id , trick_id) VALUES (1, 1);
INSERT INTO tricks_practice_bags (practice_bag_id , trick_id) VALUES (2, 5);
INSERT INTO tricks_practice_bags (practice_bag_id , trick_id) VALUES (2, 6);
INSERT INTO tricks_practice_bags (practice_bag_id , trick_id) VALUES (3, 1);
INSERT INTO tricks_practice_bags (practice_bag_id , trick_id) VALUES (3, 4);
INSERT INTO tricks_practice_bags (practice_bag_id , trick_id) VALUES (3, 7);

COMMIT;
