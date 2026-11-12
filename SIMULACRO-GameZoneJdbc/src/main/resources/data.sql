-- Plataformas (Sega Dreamcast no tiene juegos)
INSERT INTO plataforma (nombre) VALUES ('PlayStation 5');   -- 1
INSERT INTO plataforma (nombre) VALUES ('Nintendo Switch'); -- 2
INSERT INTO plataforma (nombre) VALUES ('Xbox Series');     -- 3
INSERT INTO plataforma (nombre) VALUES ('PC');              -- 4
INSERT INTO plataforma (nombre) VALUES ('Sega Dreamcast');  -- 5

-- Juegos
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('EA Sports FC 26', 69.99, 1, 2025);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('God of War Ragnarok', 59.99, 1, 2022);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Astro Bot', 49.99, 1, 2024);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('EA Sports FC 26', 49.99, 2, 2025);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Mario Kart World', 79.99, 2, 2025);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Zelda Tears of the Kingdom', 59.99, 2, 2023);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Halo Infinite', 29.99, 3, 2021);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Forza Horizon 5', 39.99, 3, 2021);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Hollow Knight', 14.99, 4, 2017);
INSERT INTO juego (nombre, precio, codigo_plataforma, año) VALUES ('Minecraft', 26.95, 4, 2011);
