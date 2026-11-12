package es.daw.gamezonejdbc.dto;

import es.daw.gamezonejdbc.model.Plataforma;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlataformaDto {
    private Integer codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;

    /** Plataforma (modelo) → formulario (vista). Se usa al editar. */
    public static PlataformaDto toDto(Plataforma p) {
        PlataformaDto plataformaDto = new PlataformaDto();
        plataformaDto.setCodigo(p.getCodigo());
        plataformaDto.setNombre(p.getNombre());
        return plataformaDto;
    }

    /** Formulario (vista) → Plataforma (modelo).*/
    public static Plataforma toModelEntity(PlataformaDto f) {

        return new Plataforma(f.getCodigo(), f.getNombre());
    }



}
