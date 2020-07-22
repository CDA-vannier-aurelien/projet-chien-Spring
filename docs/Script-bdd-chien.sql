#------------------------------------------------------------
#        Script MariaDB
#------------------------------------------------------------

 drop database if EXISTS bdd_chien;
 create or replace DATABASE bdd_chien;
 ALTER DATABASE bdd_chien CHARACTER SET utf8 COLLATE utf8_general_ci;
 USE bdd_chien;


#------------------------------------------------------------
# Creation de l utilisateur bdd
#------------------------------------------------------------

CREATE USER IF NOT EXISTS 'chien_user'@'%';
alter user 'chien_user'@'%' identified by 'pwd';
grant all privileges on bdd_chien.* to 'chien_user'@'%';



#------------------------------------------------------------
# Table: Chien
#------------------------------------------------------------

CREATE TABLE Chien(
        id_chien Int  Auto_increment  NOT NULL ,
        nom      Varchar (50) NOT NULL ,
        couleur  Varchar (20) NOT NULL ,
        age      Smallint NOT NULL
	,CONSTRAINT Chien_PK PRIMARY KEY (id_chien)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        id_utilisateur Int  Auto_increment  NOT NULL ,
        login          Varchar (20) NOT NULL ,
        password       Varchar (20) NOT NULL ,
        prenom         Varchar (50) NOT NULL ,
        nom            Varchar (50) NOT NULL ,
        id_chien       Int NOT NULL
	,CONSTRAINT Client_PK PRIMARY KEY (id_utilisateur)

	,CONSTRAINT Client_Chien_FK FOREIGN KEY (id_chien) REFERENCES Chien(id_chien)
)ENGINE=InnoDB;