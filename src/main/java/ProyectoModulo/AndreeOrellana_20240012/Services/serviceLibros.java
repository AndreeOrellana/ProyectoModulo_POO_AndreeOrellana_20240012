package ProyectoModulo.AndreeOrellana_20240012.Services;

import ProyectoModulo.AndreeOrellana_20240012.Entities.EntityLibro;
import ProyectoModulo.AndreeOrellana_20240012.Models.DTOLibros;
import ProyectoModulo.AndreeOrellana_20240012.Repositories.repositoryLibro;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class serviceLibros {
    @Autowired
    private repositoryLibro objRepoL;

    public List<DTOLibros> getLibros(){
        List<EntityLibro> libro = objRepoL.findAll();
        return libro.stream().map(this::convertToDTOL).collect(Collectors.toList());
    }

    public DTOLibros postLibro(DTOLibros dtoL){
        if (dtoL == null){
            throw new IllegalArgumentException("No pueden haber campos vacios");
        }
        try{
            EntityLibro libro = objRepoL.save(convertToEA(dtoL));
            return convertToDTOL(libro);
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

    public DTOLibros putLibro(DTOLibros dtoL, String id){
        if (dtoL == null){
            throw new IllegalArgumentException("No pueden haber campos vacios");
        }
        EntityLibro libroExist = objRepoL.findById(id).orElseThrow(() -> new EntityNotFoundException("Libro no encontrado con id: " + id));

        libroExist.setLibroName(dtoL.getId())
    }
}
