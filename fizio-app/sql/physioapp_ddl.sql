DROP TABLE IF EXISTS VNOS;

DROP TABLE IF EXISTS VAJA;

DROP TABLE IF EXISTS FIZIOPLAN;

DROP TABLE IF EXISTS OBVESTILO;

DROP TABLE IF EXISTS TERMIN;

DROP TABLE IF EXISTS PACIENT;

DROP TABLE IF EXISTS FIZIOTERAPEVT;

/*==============================================================*/
/* Table: FIZIOPLAN                                             */
/*==============================================================*/
CREATE TABLE FIZIOPLAN
(
   ID_FIZIOPLANA        INT NOT NULL AUTO_INCREMENT,
   USERNAME_PACIENTA    VARCHAR(100) NOT NULL,
   NASLOV_FIZIOPLANA    VARCHAR(150),
   POSKODBA             VARCHAR(100),
   TRAJANJE_OD          DATE,
   TRAJANJE_DO          DATE,
   OPIS_FIZIOPLANA      VARCHAR(5000),
   PRIMARY KEY (ID_FIZIOPLANA)
);

/*==============================================================*/
/* Table: FIZIOTERAPEVT                                         */
/*==============================================================*/
CREATE TABLE FIZIOTERAPEVT
(
   USERNAME_FIZIOTERAPEVTA VARCHAR(100) NOT NULL,
   IME_FIZIOTERAPEVTA   VARCHAR(100),
   PRIIMEK_FIZIOTERAPEVTA VARCHAR(100),
   ULICA                VARCHAR(50),
   HISNA_STEVILKA       VARCHAR(10),
   POSTNA_STEVILKA      INT,
   KRAJ                 VARCHAR(50),
   PRIMARY KEY (USERNAME_FIZIOTERAPEVTA)
);

/*==============================================================*/
/* Table: OBVESTILO                                             */
/*==============================================================*/
CREATE TABLE OBVESTILO
(
   ID_OBVESTILA         INT NOT NULL AUTO_INCREMENT,
   ID_TERMINA           INT NOT NULL,
   NASLOV_OBVESTILA     VARCHAR(150) NOT NULL,
   TS                   TIMESTAMP,
   VSEBINA              VARCHAR(150),
   PRIMARY KEY (ID_OBVESTILA)
);

/*==============================================================*/
/* Table: PACIENT                                               */
/*==============================================================*/
CREATE TABLE PACIENT
(
   USERNAME_PACIENTA    VARCHAR(100) NOT NULL,
   USERNAME_FIZIOTERAPEVTA VARCHAR(100) NOT NULL,
   IME_PACIENTA         VARCHAR(100),
   PRIIMEK_PACIENTA     VARCHAR(100),
   DATUM_ROJSTVA        DATE,
   PRIMARY KEY (USERNAME_PACIENTA)
);

/*==============================================================*/
/* Table: TERMIN                                               */
/*==============================================================*/
CREATE TABLE TERMIN
(
   ID_TERMINA           INT NOT NULL AUTO_INCREMENT,
   USERNAME_PACIENTA    VARCHAR(100),
   USERNAME_FIZIOTERAPEVTA VARCHAR(100) NOT NULL,
   ZACETEK              DATETIME,
   KONEC                DATETIME,
   JE_ZASEDEN           BOOL DEFAULT FALSE,
   PRIMARY KEY (ID_TERMINA)
);

/*==============================================================*/
/* Table: VAJA                                                  */
/*==============================================================*/
CREATE TABLE VAJA
(
   ID_VAJE              INT NOT NULL AUTO_INCREMENT,
   IME_VAJE             VARCHAR(100),
   OPIS_VAJE            VARCHAR(5000),
   URL                  VARCHAR(500),
   PRIMARY KEY (ID_VAJE)
);

/*==============================================================*/
/* Table: VNOS                                                  */
/*==============================================================*/
CREATE TABLE VNOS
(
   ID_VNOSA             INT NOT NULL AUTO_INCREMENT,
   ID_VAJE              INT NOT NULL,
   ID_FIZIOPLANA        INT NOT NULL,
   ST_SETOV             INT,
   ST_PONOVITEV         INT,
   KOMENTAR             VARCHAR(5000),
   PRIMARY KEY (ID_VNOSA)
);

ALTER TABLE FIZIOPLAN ADD CONSTRAINT FK_FIZIOPLAN_USERNAME_PACIENTA FOREIGN KEY (USERNAME_PACIENTA)
      REFERENCES PACIENT (USERNAME_PACIENTA) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE OBVESTILO ADD CONSTRAINT FK_OBVESTILO_ID_TERMINA FOREIGN KEY (ID_TERMINA)
      REFERENCES TERMIN (ID_TERMINA) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE PACIENT ADD CONSTRAINT FK_PACIENT_USERNAME_FIZIOTERAPEVTA FOREIGN KEY (USERNAME_FIZIOTERAPEVTA)
      REFERENCES FIZIOTERAPEVT (USERNAME_FIZIOTERAPEVTA) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE TERMIN ADD CONSTRAINT FK_TERMIN_USERNAME_PACIENTA FOREIGN KEY (USERNAME_PACIENTA)
      REFERENCES PACIENT (USERNAME_PACIENTA) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE TERMIN ADD CONSTRAINT FK_TERMIN_USERNAME_FIZIOTERAPEVTA FOREIGN KEY (USERNAME_FIZIOTERAPEVTA)
      REFERENCES FIZIOTERAPEVT (USERNAME_FIZIOTERAPEVTA) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE VNOS ADD CONSTRAINT FK_VNOS_ID_VAJE FOREIGN KEY (ID_VAJE)
      REFERENCES VAJA (ID_VAJE) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE VNOS ADD CONSTRAINT FK_VNOS_ID_FIZIOPLANA FOREIGN KEY (ID_FIZIOPLANA)
      REFERENCES FIZIOPLAN (ID_FIZIOPLANA) ON DELETE CASCADE ON UPDATE CASCADE;
