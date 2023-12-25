-- fizioterapevt
INSERT INTO fizioterapevt (username_fizioterapevta,ime_fizioterapevta,priimek_fizioterapevta,ulica,hisna_stevilka,postna_stevilka,kraj) VALUES ('mg7954','Meghan','Gamble','West Eritrea Way','375','09344','Benton Harbor');
INSERT INTO fizioterapevt (username_fizioterapevta,ime_fizioterapevta,priimek_fizioterapevta,ulica,hisna_stevilka,postna_stevilka,kraj) VALUES ('wn2649','Walter','Nolan','North Elmira Ln.','71121','47355','Bridgeport');
INSERT INTO fizioterapevt (username_fizioterapevta,ime_fizioterapevta,priimek_fizioterapevta,ulica,hisna_stevilka,postna_stevilka,kraj) VALUES ('cl0375','Chadwick','Lamb','South Gilette Ct.','90858','18560','Pierre');


-- pacient
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('wy4833','mg7954','Warren','Zamora','1999-2-12');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('is0186','wn2649','Isaiah','Saunders','1982-6-8');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('ar5211','cl0375','Aladdin','Ruiz','1989-5-22');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('vm8830','mg7954','Vernon','Mueller','1974-10-28');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('bm4862','wn2649','Brandon','Mueller','2001-10-16');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('rd8252','mg7954','Randall','Duffy','1971-5-22');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('gc6420','cl0375','Giacomo','Chandler','1994-3-6');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('br8621','mg7954','Beck','Rios','1985-3-8');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('df6329','mg7954','Davis','Ford','1987-11-2');
INSERT INTO pacient (username_pacienta,username_fizioterapevta,ime_pacienta,priimek_pacienta,datum_rojstva) VALUES ('lr9814','wn2649','Lucas','Rush','1989-12-28');

-- termin
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'mg7954','2024-1-15 07:00:00','2024-1-15 08:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('is0186','wn2649','2024-1-20 12:00:00','2024-1-20 13:00:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'wn2649','2024-1-25 11:0:00','2024-1-25 12:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('bm4862','mg7954','2024-1-30 09:00:00','2024-1-30 10:00:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'cl0375','2024-2-4 08:00:00','2024-2-4 09:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('df6329','cl0375','2024-2-9 07:00:00','2024-2-9 08:00:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'mg7954','2024-2-14 15:00:00','2024-2-14 16:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'wn2649','2024-2-19 09:00:00','2024-2-19 10:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('ar5211','mg7954','2024-2-24 13:00:00','2024-2-24 14:00:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'cl0375','2024-2-29 07:00:00','2024-2-29 08:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('wy4833','wn2649','2024-3-5 08:00:00','2024-3-5 09:00:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'mg7954','2024-3-10 09:00:00','2024-3-10 10:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('lr9814','cl0375','2024-3-15 11:00:00','2024-3-15 12:00:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'mg7954','2024-3-20 13:00:00','2024-3-20 14:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'cl0375','2024-3-25 11:00:00','2024-3-25 12:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'cl0375','2024-3-30 12:00:00','2024-3-30 13:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'cl0375','2024-4-4 13:00:00','2024-4-4 14:00:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES ('gc6420','wn2649','2024-4-9 13:30:00','2024-4-9 14:30:00','1');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'mg7954','2024-4-14 14:15:00','2024-4-14 15:15:00','0');
INSERT INTO termin (username_pacienta,username_fizioterapevta,zacetek,konec,je_zaseden) VALUES (NULL,'cl0375','2024-4-19 15:00:00','2024-4-19 16:00:00','0');

-- vaja
INSERT INTO vaja (ime_vaje, opis_vaje, url) VALUES ('Bird dog', 'Get on your hands and knees', 'jKNjWVsnOLgrs1G');
INSERT INTO vaja (ime_vaje, opis_vaje, url) VALUES ('Stretching wrist flexors', 'Hold the palm of one hand with the other hand while keeping your elbow straight on the affected arm. Now pull your hand back gently to feel a stretch in the forearm.', 'ZtkBQ3wfZ5jGE4c');
INSERT INTO vaja (ime_vaje, opis_vaje, url) VALUES ('Extension lunge', 'Stand up with your feet together and take a step forward with the good leg. The affected leg must stay planted on the ground with the heel down. Return to the starting position and repeat.', 'FY8vWg6VqycAVhr');
INSERT INTO vaja (ime_vaje, opis_vaje, url) VALUES ('Standing soleus stretching', 'Stand and place both hands on a wall, with your feet about half a meter from the wall. Place one leg behind the other and slowly bend the knees while keeping the heels on the floor until you feel a stretch in the calf of the back leg. Maintain the stretch and relax.', 'JaTyMe4FpU0ruDd');
INSERT INTO vaja (ime_vaje, opis_vaje, url) VALUES ('Left side head bending', 'In a sitting or standing position, bend your head to the left as far as possible. Think about putting your ear on top of your shoulder, without any elevation of the shoulder. Modify the amount of chin retraction (tucking your chin) in order to centralize the pain.', 'PPrvBAXZW9bMl1y');

-- fizioplan
INSERT INTO fizioplan (username_pacienta, naslov_fizioplana, poskodba, trajanje_od, trajanje_do, opis_fizioplana) VALUES ('is0186','Naslov1','Poskodba1','2024-1-19','2024-4-19','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in');
INSERT INTO fizioplan (username_pacienta, naslov_fizioplana, poskodba, trajanje_od, trajanje_do, opis_fizioplana) VALUES ('bm4862','Naslov2','Poskodba2','2024-2-22','2024-3-22','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in');
INSERT INTO fizioplan (username_pacienta, naslov_fizioplana, poskodba, trajanje_od, trajanje_do, opis_fizioplana) VALUES ('df6329','Naslov3','Poskodba3','2024-1-7','2024-2-7','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in');

-- vnos
INSERT INTO vnos (id_vaje, id_fizioplana, st_setov, st_ponovitev) VALUES (1, 1, 3, 12);
INSERT INTO vnos (id_vaje, id_fizioplana, st_setov, st_ponovitev) VALUES (2, 1, 3, 12);
INSERT INTO vnos (id_vaje, id_fizioplana, st_setov, st_ponovitev) VALUES (3, 1, 3, 12);

-- obvestilo
INSERT INTO obvestilo (id_termina, ts, vsebina) VALUES (2, '2023-12-10 15:10:10', 'Naročeni ste na pregled dne 20. 1. 2024 ob 12:00 pri dr. Walter Nolan.');
INSERT INTO obvestilo (id_termina, ts, vsebina) VALUES (4, '2023-12-7 16:20:10', 'Naročeni ste na pregled dne 30. 1. 2024 ob 9:00 pri dr. Meghan Gamble.');


