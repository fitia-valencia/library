INSERT INTO bibliothecaire (
    matricule, nom, adresse, telephone, email, mot_de_passe, date_creation
)
VALUES (
    'BIB001',
    'Rabe',
    'Lot II A 123 Ambohimanarina',
    '0321234567',
    'rabe.biblio@exemple.com',
    'biblio123', -- à hasher en prod
    CURRENT_DATE
);
INSERT INTO type_adherent (libelle, duree_abonnement)
VALUES 
    ('Eleve', 12),         -- 1 an
    ('Professeur', 24);    -- 2 ans
INSERT INTO genre (id, libelle) VALUES
(1, 'Roman'),
(2, 'Science-fiction'),
(3, 'Histoire'),
(4, 'Biographie'),
(5, 'Fantasy');
INSERT INTO livre (isbn, titre, auteur, genre_id, date_publication, editeur, restriction_age) VALUES
('9781234567890', 'Le Voyageur du Temps', 'H. G. Wells', 2, '1895-01-01', 'Penguin Books', 12),
('9781234567891', 'Les Miserables', 'Victor Hugo', 1, '1862-04-03', 'Gallimard', 10),
('9781234567892', 'Harry Potter a l ecole des sorciers', 'J.K. Rowling', 5, '1997-06-26', 'Bloomsbury', 8),
('9781234567893', 'Une breve histoire du temps', 'Stephen Hawking', 3, '1988-04-01', 'Flammarion', 14),
('9781234567894', 'Steve Jobs', 'Walter Isaacson', 4, '2011-10-24', 'Simon & Schuster', 16);
-- Exemplaires pour "Le Voyageur du Temps" (id = 1)
INSERT INTO exemplaire (livre_id, reference, disponibilite) VALUES
(6, 'EX001-A', true),
(6, 'EX001-B', true);

-- Exemplaires pour "Les Misérables" (id = 2)
INSERT INTO exemplaire (livre_id, reference, disponibilite) VALUES
(7, 'EX002-A', true),
(7, 'EX002-B', true),
(7, 'EX002-C', true);

-- Exemplaires pour "Harry Potter..." (id = 3)
INSERT INTO exemplaire (livre_id, reference, disponibilite) VALUES
(8, 'EX003-A', true),
(8, 'EX003-B', true);

-- Exemplaires pour "Une brève histoire du temps" (id = 4)
INSERT INTO exemplaire (livre_id, reference, disponibilite) VALUES
(9, 'EX004-A', true),
(9, 'EX004-B', true);

-- Exemplaires pour "Steve Jobs" (id = 5)
INSERT INTO exemplaire (livre_id, reference, disponibilite) VALUES
(10, 'EX005-A', true),
(10, 'EX005-B', true);

--quota pour les types d'adhérents
INSERT INTO quota (type_adherent_id, quota_exemplaire_empruntable, quota_emprunt_total, quota_reservation)
VALUES
(3, 2, 3, 2), -- Élève
(4, 4, 5, 4); -- Professeur

--update des références des exemplaires
UPDATE exemplaire
SET reference = 'EX' || id || '-' || livre_id;

INSERT INTO type_demande (libelle)
VALUES 
    ('emprunt'),
    ('reservation'),
    ('prolongement');
