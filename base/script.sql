drop database biblio;
create database bilio;
\c bilio;

create table bibliothecaire (
    id serial primary key,
    matricule varchar(20) not null unique,
    nom varchar(100) not null unique,
    adresse varchar(255) not null,
    telephone varchar(15) not null unique,
    email varchar(100) not null unique,
    mot_de_passe varchar(100) not null,
    date_creation date not null
);

create table type_adherent (
    id serial primary key,
    libelle varchar(50) not null, -- "étudiant", "enseignant", "autre"
    duree_abonnement integer not null, -- en mois
);

create table adherent (
    id serial primary key,
    matricule varchar(20) not null unique,
    nom varchar(50) not null,
    prenom varchar(50) not null,
    date_naissance date not null,
    telephone varchar(10) not null unique,
    mot_de_passe varchar(100) not null,
    date_inscription date not null,
    type_adherent_id integer not null references type_adherent(id)
);

create table genre (
    id serial primary key,
    libelle varchar(50) not null unique
);

create table livre (
    id serial primary key,
    isbn varchar(50),
    titre varchar(100) not null,
    auteur varchar(100) not null,
    genre_id integer not null references genre(id),
    date_publication date,
    editeur varchar(100),
    restriction_age integer not null, -- en années
);

create table exemplaire (
    id serial primary key,
    livre_id integer not null references livre(id),
    reference varchar(20) not null unique,
    disponibilite boolean not null default true, -- true = disponible, false = emprunté
);

create table statut_exemplaire (
    id serial primary key,
    exemplaire_id integer not null references exemplaire(id),
    type_adherent_id integer not null references type_adherent(id),
    libelle varchar(50) not null -- "empruntable", "sur place"
);

create table quota(
    id serial primary key,
    type_adherent_id integer not null references type_adherent(id),
    quota_exemplaire_empruntable integer not null, -- nombre d'exemplaires empruntables (azo hoetina mody)
    quota_emprunt_total integer not null, -- nombre d'exemplaires empruntables
    quota_reservation integer not null -- nombre d'exemplaires réservables
);

create table emprunt (
    id serial primary key,
    adherent_id integer not null references adherent(id),
    exemplaire_id integer not null references exemplaire(id),
    date_emprunt date not null,
    date_retour_prevue date not null,
    date_retour_reelle date, -- null si pas encore retourné
);

create table reservation (
    id serial primary key,
    adherent_id integer not null references adherent(id),
    exemplaire_id integer not null references exemplaire(id),
    date_reservation date not null,
    reponse boolean, -- true = acceptée, false = refusée, null si en attente
    date_reponse date, -- null si en attente
);

create table penalisation (
    id serial primary key,
    adherent_id integer not null references adherent(id),
    date_penalisation date not null,
    duree_penalisation integer not null, -- en jours
    motif varchar(255) not null
);

create table prolongement (
    id serial primary key,
    emprunt_id integer not null references emprunt(id),
    date_prolongement_demandee date not null,
);

create table satut_prolongement (
    id serial primary key,
    prolongement_id integer not null references prolongement(id),
    statut varchar(50) not null, -- "accepté", "refusé", "en attente"
    date_reponse date, -- null si en attente
);

ALTER TABLE reservation
DROP COLUMN reponse,
DROP COLUMN date_reponse;

DROP TABLE IF EXISTS satut_prolongement;

ALTER TABLE prolongement
DROP COLUMN IF EXISTS date_prolongement_demandee;
ALTER TABLE prolongement
ADD COLUMN nbr_jours_dmd integer NOT NULL;

-- Table des types de demande (ex : prolongement, réclamation, etc.)
CREATE TABLE type_demande (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

-- Table des demandes faites par les adhérents
CREATE TABLE demande (
    id SERIAL PRIMARY KEY,
    type_demande_id INTEGER NOT NULL REFERENCES type_demande(id),
    adherent_id INTEGER NOT NULL REFERENCES adherent(id),
    exemplaire_id INTEGER NOT NULL REFERENCES exemplaire(id),
    date_demande DATE NOT NULL,
    date_envoi_demande DATE
);

