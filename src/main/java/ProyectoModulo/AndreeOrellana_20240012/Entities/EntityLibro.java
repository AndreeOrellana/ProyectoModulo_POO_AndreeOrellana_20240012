package ProyectoModulo.AndreeOrellana_20240012.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LIBROS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EntityLibro {

    @Id
    @Column(name = "ID");
    private Long id;

    @Column(name = "TITULO");
    private String titulo;

    @Column(name = "ISBN");
    private String isbn;

    @Column(name = "AÑO_PUBLICACION");
    private Long año_publicacion;

    @Column(name = "GENERO");
    private String genero;

    @Column(name = "AUTORES_ID");
    private Long autores_id;


}
