START TRANSACTION;

DROP TABLE IF EXISTS tricks CASCADE;

-- Only create one main tricks table, so if I wanted to grab all tricks together, I can.
CREATE TABLE tricks
(
	
   trick_id SERIAL,
   name VARCHAR(64) NOT NULL,
	-- Enforce?
   flip_or_shuv VARCHAR(20),
   stance VARCHAR(64) NOT NULL,
   known VARCHAR(10) NOT NULL
	
);

-- CREATE INSERTS TO POPULATE WITH ANY STARTING DATA
--populate flip_tricks table
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

COMMIT;
