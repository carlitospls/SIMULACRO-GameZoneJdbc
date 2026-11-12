# SIMULACRO FINAL · GameZone

Tienda de videojuegos. Cada **juego** pertenece a una **plataforma**.

**Formato examen:** 4 cambios. No se dice qué reto es cada uno: reconócelo tú (`MAPA.md`).
**Tiempo:** 30-40 min por cambio. Si uno se atasca más de 45 min, déjalo compilando y pasa al siguiente.
**Puedes usar:** todo el material de `APOYO-EXAMEN-DWES` (guías, proyectos de consulta, plantillas `dw…`). Sin internet.

**Antes de cada cambio:** clic derecho en el proyecto → Local History → **Put Label** ("antes del cambio N").
**Después de cada cambio:** compila, reinicia, haz TODAS sus comprobaciones y **Commit and Push**.

---

## Cambio 1

La tienda quiere saber de qué año es cada juego. Cada juego guarda su **año de lanzamiento**:
un número entero obligatorio entre 1970 y 2026. Se indica al dar de alta y al modificar un juego.
Si no es válido, el formulario muestra junto al campo
_"El año de lanzamiento debe estar entre 1970 y 2026"_ (si está vacío: _"El año de lanzamiento es obligatorio"_)
y no se pierde lo escrito. El listado de juegos muestra una columna **Año**.

Años de los juegos actuales:

| Juego | Plataforma | Año |
| EA Sports FC 26 | PlayStation 5 | 2025 |
| God of War Ragnarok | PlayStation 5 | 2022 |
| Astro Bot | PlayStation 5 | 2024 |
| EA Sports FC 26 | Nintendo Switch | 2025 |
| Mario Kart World | Nintendo Switch | 2025 |
| Zelda Tears of the Kingdom | Nintendo Switch | 2023 |
| Halo Infinite | Xbox Series | 2021 |
| Forza Horizon 5 | Xbox Series | 2021 |
| Hollow Knight | PC | 2017 |
| Minecraft | PC | 2011 |

Comprobaciones:

- [x ] El listado muestra la columna Año con estos valores.
- [ x] Alta con el año vacío → "El año de lanzamiento es obligatorio", sin perder lo demás.
- [ x] Alta con 1969 y con 2027 → el mensaje del rango.
- [ x] Alta válida (año 2020) → aparece en el listado con su año.
- [x ] Editar _Minecraft_ → el año sale relleno (2011). Cambiarlo a 2012 → el listado muestra 2012.

---

## Cambio 2

Se han detectado juegos dados de alta dos veces. A partir de ahora **no puede haber dos juegos
con el mismo nombre en la misma plataforma** (sin distinguir mayúsculas). Sí puede repetirse el
nombre en plataformas distintas: un mismo juego sale para varias consolas.

Si se intenta, el formulario vuelve a mostrarse con _"Ya existe ese juego en esa plataforma"_ junto
al campo Nombre, y con todo lo escrito (también la plataforma elegida).

Comprobaciones:

- [ ] Alta "astro bot" en PlayStation 5 → mensaje; precio y plataforma siguen rellenos.
- [ ] Alta "Astro Bot" en PC → se crea (otra plataforma).
- [x] Editar _Halo Infinite_ cambiando solo el precio → se guarda.
- [ ] Editar _Halo Infinite_ poniéndole "Forza Horizon 5" → mensaje.
- [ ] Editar _EA Sports FC 26_ de Switch cambiando solo el precio → se guarda (el de PS5 es otra plataforma).

---

## Cambio 3

En el listado de plataformas debe verse, junto a cada una, **cuántos juegos tiene** (columna
**"Juegos"**). Las plataformas sin juegos también aparecen, con 0.

Comprobaciones:

- [ ] PlayStation 5 3 · Nintendo Switch 3 · Xbox Series 2 · PC 2 · **Sega Dreamcast 0**.
- [ ] Crear un juego en PC → PC pasa a 3.
- [ ] El desplegable de plataformas del formulario de juegos sigue funcionando.

---

## Cambio 4

Al pulsar el nombre de una plataforma en su listado se abre una página titulada
**"Catálogo de &lt;plataforma&gt;"** con sus juegos (nombre y precio), **del más caro al más barato**.
Si no tiene juegos: _"No hay juegos para esta plataforma"_. Botón para volver a Plataformas.
Si se escribe a mano una plataforma que no existe → página "No encontrado".

Comprobaciones:

- [ ] Pulsar _Nintendo Switch_ → "Catálogo de Nintendo Switch": Mario Kart World 79.99 €, Zelda… 59.99 €, EA Sports FC 26 49.99 € (en ese orden).
- [ ] Pulsar _Sega Dreamcast_ → el mensaje de que no hay juegos.
- [ ] Volver → Plataformas (y la columna del cambio 3 sigue ahí).
- [ ] `localhost:8080/plataformas/99/juegos` → "No encontrado".

---

## Al terminar

Apunta para cada cambio: qué reto era, cuánto tardaste, dónde te atascaste. Push y te lo reviso.
