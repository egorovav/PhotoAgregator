DROP DATABASE IF EXISTS PhotoMetaData

CREATE DATABASE PhotoMetaData

DROP TABLE IF EXISTS photographers

CREATE TABLE photographers
(
    id   UUID         NOT NULL PRIMARY KEY DEFAULT uuid_generate_v1mc(),
    name varchar(255) NOT NULL CHECK (length(name) > 0),
    CONSTRAINT uk_photographer_id UNIQUE(id),
    CONSTRAINT uk_photographer_name UNIQUE (name)
);

DROP TABLE IF EXISTS photos_meta_data

CREATE TABLE photos_meta_data
(
    id              UUID            NOT NULL PRIMARY KEY DEFAULT uuid_generate_v1mc(),
    photographer    UUID            NOT NULL,
    title           varchar(255)    NOT NULL CHECK (length(title) > 0),
    latitude        NUMERIC(15, 12) NOT NULL DEFAULT 0,
    longitude       NUMERIC(15, 12) NOT NULL DEFAULT 0,
    create_date     TIMESTAMP       NOT NULL DEFAULT CURRENT_DATE,

    CONSTRAINT uk_ UNIQUE (id),
    CONSTRAINT fk_photo_meta_data_photographer FOREIGN KEY (photographer) REFERENCES photographers (id)
        ON DELETE RESTRICT ON UPDATE RESTRICT
);