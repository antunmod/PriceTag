-- USER TYPE
INSERT INTO user_type (user_type_description)
	VALUES ('user');
INSERT INTO user_type (user_type_description)
	VALUES ('admin');

-- user 
INSERT INTO user (user_name, user_password, user_mail, signup_date, user_type_ID)
	VALUES ('antunmod', 'test123', 'antun.mod@gmail.com', '2017-12-03', 2);
INSERT INTO user (user_name, user_password, user_mail, signup_date, user_type_ID)
	VALUES ('regularUser', 'regularPassword', 'regular@gmail.com', '2017-12-09', 1);	

-- STORE
INSERT INTO store (store_name, store_address)
	VALUES ('KONZUM', 'Britanski trg 12');
INSERT INTO store (store_name, store_address)
	VALUES ('KONZUM', 'Trg Ante Starčevića 1');
INSERT INTO store (store_name, store_address)
	VALUES ('SPAR', 'Jurišićeva ulica 13');

-- SECTOR
INSERT INTO sector (sector_name)
	VALUES ('SUPERMARKETI');

-- CATEGORY
INSERT INTO category (category_name)
	VALUES ('VINOTEKA');
INSERT INTO category (category_name)
	VALUES ('VOĆE I POVRĆE');
INSERT INTO category (category_name)
	VALUES ('MLIJEČNI PROIZVODI I JAJA');
INSERT INTO category (category_name)
	VALUES ('PEKARNICA');
INSERT INTO category (category_name)
	VALUES ('DELIKATESA');
INSERT INTO category (category_name)
	VALUES ('MESNICA');
INSERT INTO category (category_name)
	VALUES ('RIBARNICA');
INSERT INTO category (category_name)
	VALUES ('PIĆA');
INSERT INTO category (category_name)
	VALUES ('PRIPRREMA JELA');
INSERT INTO category (category_name)
	VALUES ('TJESTENINA, RIŽA, NJOKI, TORTILJE');
INSERT INTO category (category_name)
	VALUES ('UMACI I ZAČINI');
INSERT INTO category (category_name)
	VALUES ('KONZERVIRANO, JUHE, GOTOVA JELA');
INSERT INTO category (category_name)
	VALUES ('PRIPREMA KOLAČA');
INSERT INTO category (category_name)
	VALUES ('PAHULJICE, NAMAZI, KAVE, ČAJEVI');
INSERT INTO category (category_name)
	VALUES ('ZDRAVI KUTAK');
INSERT INTO category (category_name)
	VALUES ('SLATKIŠI I GRICKALICE');
INSERT INTO category (category_name)
	VALUES ('SMRZNUTA HRANA');
INSERT INTO category (category_name)
	VALUES ('BEBE I MAME');
INSERT INTO category (category_name)
	VALUES ('IGRAČKE');
INSERT INTO category (category_name)
	VALUES ('ČIŠĆENJE I POSPREMANJE');
INSERT INTO category (category_name)
	VALUES ('NJEGA I HIGIJENA');
INSERT INTO category (category_name)
	VALUES ('KUĆANSKE POTREPŠTINE');
INSERT INTO category (category_name)
	VALUES ('POSUĐE');
INSERT INTO category (category_name)
	VALUES ('KUĆNI LJUBIMCI');
INSERT INTO category (category_name)
	VALUES ('PARTY ASORTIMAN');
INSERT INTO category (category_name)
	VALUES ('ŠKOLA, URED, KNJIGE ZA DJECU');

-- SUBCATEGORY
INSERT INTO subcategory (subcategory_name)
	VALUES ('CRNA VINA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BIJELA VINA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ROSE I DESERTNA VINA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PJENUŠCI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('OSTALO');

INSERT INTO subcategory (subcategory_name)
	VALUES ('VOĆE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('POVRĆE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SUHO VOĆE I POVRĆE, ORAŠASTO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SMRZNUTO VOĆE I POVRĆE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ZAČINSKO BILJE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('MLIJEKO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SIREVI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('JAJA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('JOGURT I OSTALO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('VRHNJE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MARGARIN, MASLAC, MAST');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NAMAZI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MLIJEČNI DESERTI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('SVJEŽI KRUH');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PAKIRANI KRUH');
INSERT INTO subcategory (subcategory_name)
	VALUES ('DVOPEK I TOAST');
INSERT INTO subcategory (subcategory_name)
	VALUES ('TIJESTA I KVASCI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MLINCI I MRVICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KOLAČI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('MESNA DELIKATESA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('HRENOVKE I KOBASICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SIREVI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SPECIJALITETI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PLATE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('PILETINA I PURETINA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SVINJETINA, JUNETINA, OSTALO');

INSERT INTO subcategory (subcategory_name)
	VALUES ('BEZALKOHOLNA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PIVO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('VINO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('JAKA ALKOHOLNA');
INSERT INTO subcategory (subcategory_name)

	VALUES ('ULJA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ŠEĆER I UMJETNA SLADILA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BRAŠNO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('OCAT I DRESINZI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KAŠE I KRUPICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SOL');
INSERT INTO subcategory (subcategory_name)
	VALUES ('INTERNACIONALNA KUHINJA');

INSERT INTO subcategory (subcategory_name)
	VALUES ('TJESTENINA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('RIŽA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NJOKI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('TORTILJE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('PROIZVODI OD RAJČICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KEČAP, SENF, MAJONEZA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('UMACI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ZAČINI I MJEŠAVINE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('RIBLJE KONZERVE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('POVRĆE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PROIZVODI OD RAJČICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PAŠTETE I MESNI PROIZVODI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('JUHE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('GOTOVA JELA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KOMPOTI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('AJVARI I OSTALO');

INSERT INTO subcategory (subcategory_name)
	VALUES ('GOTOVE SMJESE I MJEŠAVINE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SASTOJCI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('DESERTI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PRIBOR ZA IZRADU KOLAČA');

INSERT INTO subcategory (subcategory_name)
	VALUES ('KAVE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NAMAZI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PAHULJICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ČAJEVI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ČOKOLADNI NAPITCI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('NAMIRNICE ZA PRIPREMU');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NAPITCI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SOKOVI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KRUH, NAMAZI, DELIKATESA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MUESLI, PAHULJICE, KAŠE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SLATKIŠI I GRICKALICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SUHO VOĆE I ORAŠASTO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('DESERTI, VRHNJA, VOĆNE KAŠICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ČAJEVI I KAVE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('VITAMINI I MINERALI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BEZ GLUTENA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('EKO');

INSERT INTO subcategory (subcategory_name)
	VALUES ('KEKSI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('GRICKALICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ČOKOLADE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BOMBONJERE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SNACKOVI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BOMBONI, LIZALICE, ŽVAKAĆE GUME');

INSERT INTO subcategory (subcategory_name)
	VALUES ('VOĆE I POVRĆE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('RIBA I PLODOVI MORA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('TIJESTO I PRIPRAVCI OD TIJESTA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('JELA OD MESA I POVRĆA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PIZZE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SLADOLEDI I DESERTI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('DJEČJA HRANA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PELENE I VLAŽNE MARAMICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('DJEČJA NJEGA I HIGIJENA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('DETERDŽENTI I OMEKŠIVAČI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BOČICE, DUDE, PRIBOR');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PRVE IGRAČKE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ZA MAME');

INSERT INTO subcategory (subcategory_name)
	VALUES ('PRVE IGRAČKE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('IGRAČKE ZA DJEVOJČICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('IGRAČKE ZA DJEČAKE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('DRUŠTVENE IGRE, KREATIVNI SETOVI, OSTALO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SLIKOVNICE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('PRANJE RUBLJA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PRANJE POSUĐA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SREDSTVA ZA ČIŠĆENJE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('VREĆICE I FILTERI ZA USISAVAČE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PRIBOR ZA ČIŠĆENJE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NJEGA OBUĆE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('HIGIJENSKI PROIZVODI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NJEGA TIJELA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NJEGA ZUBI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NJEGA KOSE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('NJEGA LICA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BRIJANJE I DEPILACIJA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('POKLON SETOVI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PARFEMI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('PAPIRNATI PROIZVODI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KUĆANSKI TEKSTIL');
INSERT INTO subcategory (subcategory_name)
	VALUES ('OSVJEŽIVAČI PROSTORA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MALI KUĆANSKI APARATI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('VREĆICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MASNI PAPIR, FOLIJE, ALU POSUDE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('INSPEKTICIDI I REPELENTI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('LAMPIONI I SVIJEĆE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('BATERIJE I ŽARULJE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('VREĆICE I FILTERI ZA USISAVAČE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('OSTALE POTREPŠTINE');

INSERT INTO subcategory (subcategory_name)
	VALUES ('TAVE, LONCI, POSUDE ZA PEČENJE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('PLASTIČNE I STAKLENE POSUDE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KUHINJSKA POMAGALA');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ČAŠE, VRČEVI, SERVISI, OSTALO');

INSERT INTO subcategory (subcategory_name)
	VALUES ('PSI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('MAČKE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('OSTALI LJUBIMCI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('ČAŠE, TANJURI, PRIBOR ZA JELO');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SALVETE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('SVIJEĆE, BALONI, UKRASI');

INSERT INTO subcategory (subcategory_name)
	VALUES ('BILJEŽNICE');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ŠKOLSKE TORBE I RUKSACI');
INSERT INTO subcategory (subcategory_name)
	VALUES ('ŠKOLSKI PRIBOR');
INSERT INTO subcategory (subcategory_name)
	VALUES ('URED');
INSERT INTO subcategory (subcategory_name)
	VALUES ('KNJIGE ZA DJECU');

-- sector_category
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 1);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 2);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 3);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 4);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 5);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 6);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 7);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 8);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 9);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 10);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 11);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 12);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 13);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 14);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 15);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 16);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 17);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 18);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 19);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 20);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 21);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 22);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 23);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 24);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 25);
INSERT INTO sector_category (sector_ID, category_ID)
	VALUES (1, 26);

-- category_subcategory
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(1, 1);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(1, 2);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(1, 3);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(1, 4);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(1, 5);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(2, 6);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(2, 7);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(2, 8);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(2, 9);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(2, 10);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 11);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 12);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 13);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 14);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 15);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 16);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 17);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(3, 18);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(4, 19);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(4, 20);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(4, 21);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(4, 22);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(4, 23);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(4, 24);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(5, 25);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(5, 26);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(5, 27);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(5, 28);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(5, 29);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(6, 30);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(6, 31);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(8, 32);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(8, 33);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(8, 34);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(8, 35);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 36);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 37);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 38);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 39);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 40);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 41);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(9, 42);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(10, 43);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(10, 44);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(10, 45);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(10, 46);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(11, 47);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(11, 48);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(11, 49);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(11, 50);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 51);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 52);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 53);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 54);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 55);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 56);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 57);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(12, 58);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(13, 59);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(13, 60);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(13, 61);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(13, 62);


INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(14, 63);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(14, 64);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(14, 65);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(14, 66);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(14, 67);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 68);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 69);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 70);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 71);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 72);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 73);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 74);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 75);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 76);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 77);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 78);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(15, 79);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(16, 80);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(16, 81);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(16, 82);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(16, 83);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(16, 84);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(16, 85);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(17, 86);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(17, 87);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(17, 88);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(17, 89);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(17, 90);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(17, 91);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 92);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 93);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 94);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 95);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 96);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 97);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(18, 98);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(19, 99);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(19, 100);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(19, 101);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(19, 102);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(19, 103);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(20, 104);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(20, 105);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(20, 106);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(20, 107);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(20, 108);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(20, 109);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 110);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 111);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 112);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 113);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 114);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 115);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 116);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(21, 117);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 118);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 119);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 120);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 121);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 122);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 123);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 124);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 125);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 126);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 127);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(22, 128);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(23, 129);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(23, 130);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(23, 131);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(23, 132);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(24, 133);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(24, 134);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(24, 135);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(25, 136);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(25, 137);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(25, 138);

INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(26, 139);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(26, 140);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(26, 141);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(26, 142);
INSERT INTO category_subcategory (category_ID, subcategory_ID)
	VALUES(26, 143);

-- PRODUCT SIZE
INSERT INTO product_size (size_type)
	VALUES ('kg');
INSERT INTO product_size (size_type)
	VALUES ('l');
INSERT INTO product_size (size_type)
	VALUES ('kom.');

-- PRODUCT
INSERT INTO PRODUCT(product_name, producer)
	VALUES ('Suncokretovo ulje', 'Zvijezda');
INSERT INTO PRODUCT(product_name, producer)
	VALUES ('Šećer kristal bijeli', 'K plus');

-- SUBCATEGORY PRODUCT
INSERT INTO subcategory_product(subcategory_ID, product_ID)
	VALUES (36, 1);
INSERT INTO subcategory_product(subcategory_ID, product_ID)
	VALUES (37, 2);