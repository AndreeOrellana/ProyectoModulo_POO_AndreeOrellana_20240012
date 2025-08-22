package ProyectoModulo.AndreeOrellana_20240012.Models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class DTOLibros {

    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String isbn;

    private Long año_publicacion;

    private String genero;

    private Long autores_id;

}
