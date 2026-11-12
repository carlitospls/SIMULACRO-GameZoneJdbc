DROP TABLE IF EXISTS juego;
DROP TABLE IF EXISTS plataforma;

CREATE TABLE plataforma (
    codigo INT          AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE juego (
    codigo           INT          AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    precio           DOUBLE       NOT NULL,
    codigo_plataforma INT          NOT NULL,
    año                INT          NOT NULL,
    CONSTRAINT fk_juego_plataforma
        FOREIGN KEY (codigo_plataforma) REFERENCES plataforma (codigo)
);
