INSERT INTO `user` (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','ADMIN');
INSERT INTO `user` (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','KORISNIK');
INSERT INTO `user` (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','KORISNIK');
              
INSERT INTO mesto (id, grad, drzava) VALUES (1, 'Novi Sad', 'SRB');
INSERT INTO mesto (id, grad, drzava) VALUES (2, 'Budimpešta', 'HUN');

INSERT INTO festival (id, naziv, datum_pocetka, datum_zavrsetka, cena, dostupne_karte, mesto_id) VALUES (1, 'EXIT', '2021/07/02', '2021/07/07', 5000, 125, 1);
INSERT INTO festival (id, naziv, datum_pocetka, datum_zavrsetka, cena, dostupne_karte, mesto_id) VALUES (2, 'Tamburica Fest', '2021/06/25', '2021/06/28', 1800, 30, 1);
INSERT INTO festival (id, naziv, datum_pocetka, datum_zavrsetka, cena, dostupne_karte, mesto_id) VALUES (3, 'Sziget', '2021/08/15', '2021/08/20', 8500, 5500, 2);

INSERT INTO rezervacija (id, kupljene_karte, ukupna_cena, festival_id) VALUES (1, 20, 56000, 2);
INSERT INTO rezervacija (id, kupljene_karte, ukupna_cena, festival_id) VALUES (2, 100, 500000, 1);
INSERT INTO rezervacija (id, kupljene_karte, ukupna_cena, festival_id) VALUES (3, 1500, 12750000, 3);