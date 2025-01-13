-- Création de la table
CREATE TABLE f_article (
    ART_CODE VARCHAR(12) NOT NULL,
    ART_CEE_CODE VARCHAR(10),
    ART_CEI_CODE VARCHAR(10),
    ART_CLE_CODE VARCHAR(10),
    ART_CODE_BARRE VARCHAR(12),
    ART_GEO_CODE VARCHAR(12),
    PRIMARY KEY (ART_CODE)
);

-- Ajout de la contrainte de clé étrangère
ALTER TABLE f_article
ADD CONSTRAINT fk_article_geo
FOREIGN KEY (ART_GEO_CODE)
REFERENCES f_geographique (GEO_CODE);
