-- USER TYPE
INSERT INTO user_type (description)
	VALUES ('korisnik');
INSERT INTO user_type (description)
	VALUES ('admin');

-- user 
INSERT INTO user (name, password, email, signup_date, rating, user_type_id)
	VALUES ('antunmod', 'test123', 'antun.mod@gmail.com', '2017-12-03', 1.0, 2);
INSERT INTO user (name, password, email, signup_date, rating, user_type_id)
	VALUES ('regularUser', 'regularPassword', 'regular@gmail.com', '2017-12-09', 0.7, 1);	
INSERT INTO user (name, password, email, signup_date, rating, user_type_id)
	VALUES ('testUser', 'testPassword', 'test@gmail.com', '2018-04-21', 0.9, 1);

-- STORE
INSERT INTO store (name)
	VALUES ('KONZUM');
INSERT INTO store (name)
	VALUES ('SPAR');

-- STORE LOCATION
INSERT INTO store_specific(store_id, address)
	VALUES (1, 'Britanski trg 12');
INSERT INTO store_specific(store_id, address)
	VALUES (1, 'Trg Ante Starčevića 1');
INSERT INTO store_specific(store_id, address)
	VALUES (2, 'Jurišićeva ulica 13');

-- SECTOR
INSERT INTO sector (name)
	VALUES ('Supermarketi');

-- CATEGORY
INSERT INTO category (name)
	VALUES ('Vinoteka');
INSERT INTO category (name)
	VALUES ('Voće i povrće');
INSERT INTO category (name)
	VALUES ('Mliječni proizvodi i jaja');
INSERT INTO category (name)
	VALUES ('Pekarnica');
INSERT INTO category (name)
	VALUES ('Delikatesa');
INSERT INTO category (name)
	VALUES ('Mesnica');
INSERT INTO category (name)
	VALUES ('Ribarnica');
INSERT INTO category (name)
	VALUES ('Pića');
INSERT INTO category (name)
	VALUES ('Priprema jela');
INSERT INTO category (name)
	VALUES ('Tjestenina, riža, njoki, tortilje');
INSERT INTO category (name)
	VALUES ('Umaci i začini');
INSERT INTO category (name)
	VALUES ('Konzervirano, juhe, gotova jela');
INSERT INTO category (name)
	VALUES ('Priprema kolača');
INSERT INTO category (name)
	VALUES ('Pahuljice, namazi, kave, čajevi');
INSERT INTO category (name)
	VALUES ('Zdravi kutak');
INSERT INTO category (name)
	VALUES ('Slatkiši i grickalice');
INSERT INTO category (name)
	VALUES ('Smrznuta hrana');
INSERT INTO category (name)
	VALUES ('Bebe i mame');
INSERT INTO category (name)
	VALUES ('Igračke');
INSERT INTO category (name)
	VALUES ('Čišćenje i pospremanje');
INSERT INTO category (name)
	VALUES ('Njega i higijena');
INSERT INTO category (name)
	VALUES ('Kućanske potrepštine');
INSERT INTO category (name)
	VALUES ('Posuđe');
INSERT INTO category (name)
	VALUES ('Kućni ljubimci');
INSERT INTO category (name)
	VALUES ('Party asortiman');
INSERT INTO category (name)
	VALUES ('Škola, ured, knjige za djecu');

-- SUBCATEGORY
INSERT INTO subcategory (name)
	VALUES ('Crna vina');
INSERT INTO subcategory (name)
	VALUES ('Bijela vina');
INSERT INTO subcategory (name)
	VALUES ('Rose i desertna vina');
INSERT INTO subcategory (name)
	VALUES ('Pjenušci');
INSERT INTO subcategory (name)
	VALUES ('Ostalo');

INSERT INTO subcategory (name)
	VALUES ('Voće');
INSERT INTO subcategory (name)
	VALUES ('Povrće');
INSERT INTO subcategory (name)
	VALUES ('Suho voće i povrće, orašasto');
INSERT INTO subcategory (name)
	VALUES ('Smrznuto voće i povrće');
INSERT INTO subcategory (name)
	VALUES ('Začinsko bilje');

INSERT INTO subcategory (name)
	VALUES ('Mlijeko');
INSERT INTO subcategory (name)
	VALUES ('Sirevi');
INSERT INTO subcategory (name)
	VALUES ('Jaja');
INSERT INTO subcategory (name)
	VALUES ('Jogurt i ostalo');
INSERT INTO subcategory (name)
	VALUES ('Vrhnje');
INSERT INTO subcategory (name)
	VALUES ('Margarin, maslac, mast');
INSERT INTO subcategory (name)
	VALUES ('Namazi');
INSERT INTO subcategory (name)
	VALUES ('Mliječni deserti');

INSERT INTO subcategory (name)
	VALUES ('Svježi kruh');
INSERT INTO subcategory (name)
	VALUES ('Pakirani kruh');
INSERT INTO subcategory (name)
	VALUES ('Dvopek i toast');
INSERT INTO subcategory (name)
	VALUES ('Tijestai kvasci');
INSERT INTO subcategory (name)
	VALUES ('Mlinci i mrvice');
INSERT INTO subcategory (name)
	VALUES ('Kolači');

INSERT INTO subcategory (name)
	VALUES ('Mesna delikatesa');
INSERT INTO subcategory (name)
	VALUES ('Hrenovke i kobasice');
INSERT INTO subcategory (name)
	VALUES ('Sirevi');
INSERT INTO subcategory (name)
	VALUES ('Specijaliteti');
INSERT INTO subcategory (name)
	VALUES ('Plate');

INSERT INTO subcategory (name)
	VALUES ('Piletina i puretina');
INSERT INTO subcategory (name)
	VALUES ('Svinjetina, junetina, ostalo');

INSERT INTO subcategory (name)
	VALUES ('Bezalkoholna');
INSERT INTO subcategory (name)
	VALUES ('Pivo');
INSERT INTO subcategory (name)
	VALUES ('Vino');
INSERT INTO subcategory (name)
	VALUES ('Jaka alkoholna');
INSERT INTO subcategory (name)

	VALUES ('Ulja');
INSERT INTO subcategory (name)
	VALUES ('Šećer i umjetna sladila');
INSERT INTO subcategory (name)
	VALUES ('Brašno');
INSERT INTO subcategory (name)
	VALUES ('Ocat i dresinzi');
INSERT INTO subcategory (name)
	VALUES ('Kaše i krupice');
INSERT INTO subcategory (name)
	VALUES ('Sol');
INSERT INTO subcategory (name)
	VALUES ('Internacionalna kuhinja');

INSERT INTO subcategory (name)
	VALUES ('Tjestenina');
INSERT INTO subcategory (name)
	VALUES ('Riža');
INSERT INTO subcategory (name)
	VALUES ('Njoki');
INSERT INTO subcategory (name)
	VALUES ('Tortilje');

INSERT INTO subcategory (name)
	VALUES ('Proizvodi od rajčice');
INSERT INTO subcategory (name)
	VALUES ('Kečap, senf, majoneza');
INSERT INTO subcategory (name)
	VALUES ('Umaci');
INSERT INTO subcategory (name)
	VALUES ('Začini i mješavine');

INSERT INTO subcategory (name)
	VALUES ('Riblje konzerve');
INSERT INTO subcategory (name)
	VALUES ('Povrće');
INSERT INTO subcategory (name)
	VALUES ('Proizvodi od rajčice');
INSERT INTO subcategory (name)
	VALUES ('Paštete i mesni proizvodi');
INSERT INTO subcategory (name)
	VALUES ('Juhe');
INSERT INTO subcategory (name)
	VALUES ('Gotova jela');
INSERT INTO subcategory (name)
	VALUES ('Kompoti');
INSERT INTO subcategory (name)
	VALUES ('Ajvari i ostalo');

INSERT INTO subcategory (name)
	VALUES ('Gotove smjese i mješavine');
INSERT INTO subcategory (name)
	VALUES ('Sastojci');
INSERT INTO subcategory (name)
	VALUES ('Deserti');
INSERT INTO subcategory (name)
	VALUES ('Pribor za izradu kolača');

INSERT INTO subcategory (name)
	VALUES ('Kave');
INSERT INTO subcategory (name)
	VALUES ('Namazi');
INSERT INTO subcategory (name)
	VALUES ('Pahuljice');
INSERT INTO subcategory (name)
	VALUES ('Čajevi');
INSERT INTO subcategory (name)
	VALUES ('Čokoladni napitci');

INSERT INTO subcategory (name)
	VALUES ('Namirnice za pripremu');
INSERT INTO subcategory (name)
	VALUES ('Napitci');
INSERT INTO subcategory (name)
	VALUES ('Sokovi');
INSERT INTO subcategory (name)
	VALUES ('Kruh, namazi, delikatesa');
INSERT INTO subcategory (name)
	VALUES ('Muesli, pahuljice, kaše');
INSERT INTO subcategory (name)
	VALUES ('Slatkiši i grickalice');
INSERT INTO subcategory (name)
	VALUES ('Suho voće i orašasto');
INSERT INTO subcategory (name)
	VALUES ('Deserti, vrhnja, voćne kašice');
INSERT INTO subcategory (name)
	VALUES ('Čajevi i kave');
INSERT INTO subcategory (name)
	VALUES ('Vitamini i minerali');
INSERT INTO subcategory (name)
	VALUES ('Bez glutena');
INSERT INTO subcategory (name)
	VALUES ('Eko');

INSERT INTO subcategory (name)
	VALUES ('Keksi');
INSERT INTO subcategory (name)
	VALUES ('Grickalice');
INSERT INTO subcategory (name)
	VALUES ('Čokolade');
INSERT INTO subcategory (name)
	VALUES ('Bombonjere');
INSERT INTO subcategory (name)
	VALUES ('Snackovi');
INSERT INTO subcategory (name)
	VALUES ('Bomboni, lizalice, žvakaće gume');

INSERT INTO subcategory (name)
	VALUES ('Voće i povrće');
INSERT INTO subcategory (name)
	VALUES ('Riba i plodovi mora');
INSERT INTO subcategory (name)
	VALUES ('Tijesto i pripravci od tijesta');
INSERT INTO subcategory (name)
	VALUES ('Jela od mesa i povrća');
INSERT INTO subcategory (name)
	VALUES ('Pizze');
INSERT INTO subcategory (name)
	VALUES ('Sladoledi i deserti');

INSERT INTO subcategory (name)
	VALUES ('Dječja hrana');
INSERT INTO subcategory (name)
	VALUES ('Pelene i vlažne maramice');
INSERT INTO subcategory (name)
	VALUES ('Dječja njega i higijena');
INSERT INTO subcategory (name)
	VALUES ('Deterdženti i omekšivaći');
INSERT INTO subcategory (name)
	VALUES ('Bočice, dude, pribor');
INSERT INTO subcategory (name)
	VALUES ('Prve igračke');
INSERT INTO subcategory (name)
	VALUES ('Za mame');

INSERT INTO subcategory (name)
	VALUES ('Prve igračke');
INSERT INTO subcategory (name)
	VALUES ('Igračke za djevojčice');
INSERT INTO subcategory (name)
	VALUES ('Igračke za dječake');
INSERT INTO subcategory (name)
	VALUES ('Društvene igre, kreativni setovi, ostalo');
INSERT INTO subcategory (name)
	VALUES ('Slikovnice');

INSERT INTO subcategory (name)
	VALUES ('Pranje rublje');
INSERT INTO subcategory (name)
	VALUES ('Pranje posuđa');
INSERT INTO subcategory (name)
	VALUES ('Sredstva za čišćenje');
INSERT INTO subcategory (name)
	VALUES ('Vrećice i filteri za usisavače');
INSERT INTO subcategory (name)
	VALUES ('Pribor za čišćenje');
INSERT INTO subcategory (name)
	VALUES ('Njega obuće');

INSERT INTO subcategory (name)
	VALUES ('Higijenski proizvodi');
INSERT INTO subcategory (name)
	VALUES ('Njega tijela');
INSERT INTO subcategory (name)
	VALUES ('Njega zubi');
INSERT INTO subcategory (name)
	VALUES ('Njega kose');
INSERT INTO subcategory (name)
	VALUES ('Njega lica');
INSERT INTO subcategory (name)
	VALUES ('Brijanje i depilacija');
INSERT INTO subcategory (name)
	VALUES ('Poklon setovi');
INSERT INTO subcategory (name)
	VALUES ('Parfemi');

INSERT INTO subcategory (name)
	VALUES ('Papirnati proizvodi');
INSERT INTO subcategory (name)
	VALUES ('Kućanski tekstil');
INSERT INTO subcategory (name)
	VALUES ('Osvježivaći prostora');
INSERT INTO subcategory (name)
	VALUES ('Mali kućanski aparati');
INSERT INTO subcategory (name)
	VALUES ('Vrećice');
INSERT INTO subcategory (name)
	VALUES ('Masni papir, folije, alu posude');
INSERT INTO subcategory (name)
	VALUES ('Insekticidi i repelenti');
INSERT INTO subcategory (name)
	VALUES ('Lampioni i svijeće');
INSERT INTO subcategory (name)
	VALUES ('Baterije i žarulje');
INSERT INTO subcategory (name)
	VALUES ('Vrećice i filteri za usisavače');
INSERT INTO subcategory (name)
	VALUES ('Ostale potrepštine');

INSERT INTO subcategory (name)
	VALUES ('Tave, lonci, posude za pečenje');
INSERT INTO subcategory (name)
	VALUES ('Plastične i staklene posude');
INSERT INTO subcategory (name)
	VALUES ('Kuhinjska pomagala');
INSERT INTO subcategory (name)
	VALUES ('Čaše, vrčevi, servisi, ostalo');

INSERT INTO subcategory (name)
	VALUES ('Psi');
INSERT INTO subcategory (name)
	VALUES ('Mačke');
INSERT INTO subcategory (name)
	VALUES ('Ostali ljubimci');

INSERT INTO subcategory (name)
	VALUES ('Čaše, tanjuri, pribor za jelo');
INSERT INTO subcategory (name)
	VALUES ('Salvete');
INSERT INTO subcategory (name)
	VALUES ('Svijeće, baloni, ukrasi');

INSERT INTO subcategory (name)
	VALUES ('Bilježnice');
INSERT INTO subcategory (name)
	VALUES ('Školske torbe i ruksaci');
INSERT INTO subcategory (name)
	VALUES ('Školski pribor');
INSERT INTO subcategory (name)
	VALUES ('Ured');
INSERT INTO subcategory (name)
	VALUES ('Knjige za djecu');

-- sector_category
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 1);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 2);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 3);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 4);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 5);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 6);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 7);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 8);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 9);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 10);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 11);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 12);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 13);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 14);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 15);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 16);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 17);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 18);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 19);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 20);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 21);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 22);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 23);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 24);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 25);
INSERT INTO sector_category (sector_id, category_id)
	VALUES (1, 26);

-- category_subcategory
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(1, 1);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(1, 2);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(1, 3);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(1, 4);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(1, 5);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(2, 6);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(2, 7);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(2, 8);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(2, 9);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(2, 10);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 11);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 12);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 13);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 14);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 15);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 16);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 17);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(3, 18);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(4, 19);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(4, 20);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(4, 21);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(4, 22);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(4, 23);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(4, 24);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(5, 25);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(5, 26);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(5, 27);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(5, 28);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(5, 29);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(6, 30);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(6, 31);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(8, 32);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(8, 33);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(8, 34);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(8, 35);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 36);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 37);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 38);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 39);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 40);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 41);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(9, 42);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(10, 43);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(10, 44);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(10, 45);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(10, 46);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(11, 47);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(11, 48);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(11, 49);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(11, 50);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 51);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 52);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 53);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 54);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 55);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 56);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 57);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(12, 58);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(13, 59);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(13, 60);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(13, 61);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(13, 62);


INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(14, 63);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(14, 64);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(14, 65);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(14, 66);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(14, 67);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 68);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 69);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 70);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 71);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 72);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 73);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 74);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 75);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 76);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 77);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 78);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(15, 79);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(16, 80);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(16, 81);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(16, 82);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(16, 83);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(16, 84);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(16, 85);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(17, 86);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(17, 87);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(17, 88);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(17, 89);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(17, 90);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(17, 91);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 92);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 93);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 94);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 95);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 96);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 97);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(18, 98);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(19, 99);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(19, 100);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(19, 101);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(19, 102);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(19, 103);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(20, 104);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(20, 105);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(20, 106);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(20, 107);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(20, 108);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(20, 109);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 110);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 111);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 112);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 113);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 114);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 115);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 116);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(21, 117);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 118);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 119);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 120);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 121);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 122);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 123);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 124);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 125);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 126);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 127);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(22, 128);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(23, 129);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(23, 130);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(23, 131);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(23, 132);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(24, 133);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(24, 134);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(24, 135);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(25, 136);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(25, 137);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(25, 138);

INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(26, 139);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(26, 140);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(26, 141);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(26, 142);
INSERT INTO category_subcategory (category_id, subcategory_id)
	VALUES(26, 143);

-- PRODUCT SIZE
INSERT INTO product_size (type)
	VALUES ('g');
INSERT INTO product_size (type)
	VALUES ('kg');
INSERT INTO product_size (type)
	VALUES ('ml');
INSERT INTO product_size (type)
	VALUES ('l');
INSERT INTO product_size (type)
	VALUES ('kom.');

-- PRODUCER
INSERT INTO producer (name)
	VALUES ('Zvijezda');
INSERT INTO producer (name)
	VALUES ('Premijer');
INSERT INTO producer (name)
	VALUES ('Karlovačko');

-- PRODUCT
INSERT INTO product(name, producer_id)
	VALUES ('Suncokretovo ulje', 1);
INSERT INTO product(name, producer_id)
	VALUES ('Šećer kristal', 2);
INSERT INTO product(name, producer_id)
	VALUES ('Svijetlo pivo', 3);
INSERT INTO product(name, producer_id)
	VALUES ('Ekstra djevičansko maslinovo ulje', 1);

-- SUBCATEGORY PRODUCT
INSERT INTO subcategory_product(subcategory_id, product_id)
	VALUES (36, 1);
INSERT INTO subcategory_product(subcategory_id, product_id)
	VALUES (37, 2);
INSERT INTO subcategory_product(subcategory_id, product_id)
	VALUES (33, 3);
INSERT INTO subcategory_product(subcategory_id, product_id)
	VALUES (36, 4);