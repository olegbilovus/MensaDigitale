DROP database IF EXISTS MensaDigitale;
CREATE database MensaDigitale;
USE MensaDigitale;

CREATE TABLE consumatore (
    email VARCHAR(150) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    statoServizi BOOLEAN NOT NULL,
    codiceFiscale VARCHAR(16) NOT NULL,
    dataDiNascita DATE NOT NULL,
    indirizzo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    cellulare VARCHAR(20),
    comuneNascita VARCHAR(50) NOT NULL,
    provinciaNascita VARCHAR(50) NOT NULL,
    cittadinanza VARCHAR(50) NOT NULL,
    rifugiato BOOLEAN NOT NULL,
    residenzaNucleoFamiliare BOOLEAN NOT NULL,
    saldo INT NOT NULL DEFAULT 0,
    fasciaPagamento INT NOT NULL DEFAULT 0
);

CREATE TABLE addetto (
    email VARCHAR(150) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    lvlPermessi INT NOT NULL
);

CREATE TABLE administrator (
    email VARCHAR(150) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(20) NOT NULL
);

CREATE TABLE piatto (
    nome VARCHAR(80) PRIMARY KEY,
    ingredienti VARCHAR(200) NOT NULL,
    calorie INT,
    proteine INT,
    grassi INT,
    sodio INT,
    carboidrati INT
);

CREATE TABLE valutazione (
    email VARCHAR(150),
    piatto VARCHAR(80),
    recensione INT NOT NULL,
    dataValutazione DATE NOT NULL,
    PRIMARY KEY (email , piatto),
    FOREIGN KEY (email)
        REFERENCES consumatore (email)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (piatto)
        REFERENCES piatto (nome)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE fasciaOraria (
    id INT PRIMARY KEY,
    fascia VARCHAR(20)
);

CREATE TABLE prenotazione (
    id VARCHAR(150),
    email VARCHAR(150),
    dataPrenotazione DATE NOT NULL,
    sala INT NOT NULL,
    fasciaOraria INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (email)
        REFERENCES consumatore (email)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fasciaOraria)
        REFERENCES fasciaOraria (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE richiesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL,
    esito INT DEFAULT 0,
    valutatore VARCHAR(150),
    FOREIGN KEY (email)
        REFERENCES consumatore (email)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (valutatore)
        REFERENCES addetto (email)
        ON DELETE CASCADE ON UPDATE CASCADE
);