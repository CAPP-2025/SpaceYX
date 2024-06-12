INSERT INTO navette (nom, capacite, status) VALUES ('navette1', 3, 'OK');
INSERT INTO navette (nom, capacite, status) VALUES ('navette2', 5, 'OK');
INSERT INTO navette (nom, capacite, status) VALUES ('navette3', 4, 'OBSOLETE');

INSERT INTO revision (date,navette_id, id_navette) VALUES ('2025-01-01',1,1);
INSERT INTO revision (date,navette_id, id_navette) VALUES ('2025-01-01',2,2);
INSERT INTO revision (date,navette_id, id_navette) VALUES ('2025-01-01',3,3);

INSERT INTO vol (vol_date, navette_id, id_navette, status) VALUES ('2024-01-01 12:00:00', 1, 1, 'PASSED');
INSERT INTO vol (vol_date, navette_id, id_navette, status) VALUES ('2024-01-01 12:00:00', 2, 2, 'PASSED');
INSERT INTO vol (vol_date, navette_id, id_navette, status) VALUES ('2024-01-01 12:00:00', 3, 3, 'PASSED');

INSERT INTO reservation (vol_id, id_vol) VALUES (1, 1);
INSERT INTO reservation (vol_id, id_vol) VALUES (2, 2);
INSERT INTO reservation (vol_id, id_vol) VALUES (3, 3);


