package es.daw.gamezonejdbc.dto;

import es.daw.gamezonejdbc.model.Plataforma;
import es.daw.gamezonejdbc.model.Juego;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto del formulario de juego (viaja entre la vista y el controlador).
 * El <select> solo envía el código de la plataforma, por eso aquí es un Integer.
 */
@Data
@NoArgsConstructor
public class JuegoForm {

    private Integer codigo;            // null = alta, con valor = edición

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @Min(value = 1970, message = "El año de lanzamiento debe estar entre 1970 y 2026")
    @Max(value = 2026, message = "El año de lanzamiento debe estar entre 1970 y 2026")
    @NotNull(message = "El año de lanzamiento es obligatorio")
    private Integer año;

    @NotNull(message = "Selecciona una plataforma")
    private Integer codigoPlataforma;

    /** Juego (modelo) → formulario. Se usa al editar. */
    public static JuegoForm toDto(Juego p) {
        JuegoForm form = new JuegoForm();
        form.setCodigo(p.getCodigo());
        form.setNombre(p.getNombre());
        form.setPrecio(p.getPrecio());
        form.setAño(p.getAño());
        form.setCodigoPlataforma(p.getPlataforma().getCodigo());
        return form;
    }

    /** Formulario → Juego (modelo). De la plataforma solo tenemos el código. */
    public Juego toModelEntity() {
        Plataforma plataforma = new Plataforma(codigoPlataforma, null);
        return new Juego(codigo, nombre, precio, año, plataforma);
    }
}
