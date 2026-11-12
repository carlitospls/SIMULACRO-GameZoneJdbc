package es.daw.gamezonejdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fila de la tabla JUEGO.
 * En la tabla hay una columna codigo_plataforma (FK); en Java guardamos el objeto Plataforma completo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Juego {

    private Integer codigo;
    private String nombre;
    private double precio;
    private int año;
    private Plataforma plataforma;
}
