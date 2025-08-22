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

    private Integer ano_publicacion;
    

}
