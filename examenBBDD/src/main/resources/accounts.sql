DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
	accountid SERIAL PRIMARY KEY,
	iban VARCHAR(50) UNIQUE NOT NULL,
	balance DECIMAL(9,2),
	clientid INTEGER NOT NULL
);

INSERT INTO accounts (accountid, iban, balance, clientid) VALUES
(1, 'ES1600812569102753685295', 92.21, 1),
(2, 'ES2131908836405121554653', 255.8, 2),
(3, 'ES0630044689169516429433', 6544.44, 3),
(4, 'ES1604879786986533656561', 26868.39, 4),
(5, 'ES9120953511274128261298', 788627.46, 5),
(6, 'ES1020951177582623825487', 582.67, 6),
(7, 'ES7420388883247211424149', 553.24, 6),
(8, 'ES6621004373989234176713', 51555.84, 7),
(9, 'ES2720956691119558361144', 608080.91, 8),
(10, 'ES6300755568091884217234', 5485.05, 4);

SELECT setval(pg_get_serial_sequence('accounts', 'accountid'), coalesce(max(accountid)+1, 1), false) FROM accounts;



----



DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
                          accountid SERIAL PRIMARY KEY,
                          iban VARCHAR(50) UNIQUE NOT NULL,
                          balance DECIMAL(9,2),
                          clientid INTEGER NOT NULL
);

CREATE TABLE clients (
                         clientid SERIAL PRIMARY KEY,
                         dni VARCHAR(15) UNIQUE NOT NULL,
                         nombre VARCHAR (50) NOT NULL,
                         apellidos VARCHAR (50) NOT NULL,
                         telefono VARCHAR (50) NOT NULL,
                         usuario VARCHAR (50) NOT NULL,
                         contrasenya VARCHAR (50) NOT NULL,
                         email VARCHAR (50) NOT NULL,
                         nacionalidad VARCHAR (50) NOT NULL,
                         dob DATE NOT NULL,
                         calle VARCHAR (50) NOT NULL,
                         municipio VARCHAR (50) NOT NULL,
                         provincia VARCHAR (50) NOT NULL,
                         cp INTEGER NOT NULL
);

INSERT INTO accounts (accountid, iban, balance, clientid) VALUES
                                                              (1, 'ES1600812569102753685295', 92.21, 1),
                                                              (2, 'ES2131908836405121554653', 255.8, 2),
                                                              (3, 'ES0630044689169516429433', 6544.44, 3),
                                                              (4, 'ES1604879786986533656561', 26868.39, 4),
                                                              (5, 'ES9120953511274128261298', 788627.46, 5),
                                                              (6, 'ES1020951177582623825487', 582.67, 6),
                                                              (7, 'ES7420388883247211424149', 553.24, 6),
                                                              (8, 'ES6621004373989234176713', 51555.84, 7),
                                                              (9, 'ES2720956691119558361144', 608080.91, 8),
                                                              (10, 'ES6300755568091884217234', 5485.05, 4);

INSERT INTO clients (clientid, dni, nombre, apellidos, telefono, usuario, contrasenya, email, nacionalidad, dob, calle, municipio, provincia, cp) VALUES
    (1, '66459472Y','Maria Dolors', 'Agudo Royo', '639659948', 'mariadolors_65','iB4bB1eH', 'ocdrbd0cjo@talk21.com', 'España', '1965-05-09','Sector Catalunya, 60','Tivissa','Tarragona', 10887);

SELECT setval(pg_get_serial_sequence('accounts', 'accountid'), coalesce(max(accountid)+1, 1), false) FROM accounts;