package ProyectoModulo.AndreeOrellana_20240012.Services;

import ProyectoModulo.AndreeOrellana_20240012.Entities.EntityLibro;
import ProyectoModulo.AndreeOrellana_20240012.Models.DTOLibros;
import ProyectoModulo.AndreeOrellana_20240012.Repositories.repositoryLibro;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

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
            EntityLibro libro = objRepoL.save(convertToEL(dtoL));
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

        libroExist.setId(dtoL.getId());
        libroExist.setTitulo(dtoL.getTitulo());

        EntityLibro libro = objRepoL.save(libroExist);
        return convertToDTOL(libro);
    }

    public boolean removeArea(String id){
        try{
            if (id == null || id.trim().isEmpty()){
                throw new IllegalArgumentException("El id del Libro no puede ser nulo o vacio");
            }
            objRepoL.deleteById(id);
            return true;
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("No se encontro el libro");
        }
    }

    private DTOLibros convertToDTOL(EntityLibro libro){
        DTOLibros dtoL = new DTOLibros();
        dtoL.setId(libro.getId());
        dtoL.setTitulo(libro.getTitulo());
        dtoL.setIsbn(libro.getIsbn());
        dtoL.setAño_publicacion(libro.getAño_publicacion());
        dtoL.setGenero(libro.getGenero());
        dtoL.setAutores_id(libro.getAutores_id());
        return dtoL;
    }

    private EntityLibro convertToEL(DTOLibros dtoL){
        EntityLibro libro = new EntityLibro();
        dtoL.setId(libro.getId());
        dtoL.setTitulo(libro.getTitulo());
        dtoL.setIsbn(libro.getIsbn());
        dtoL.setAño_publicacion(libro.getAño_publicacion());
        dtoL.setGenero(libro.getGenero());
        dtoL.setAutores_id(libro.getAutores_id());
        return libro;
    }

    public DTOLibros postArea(DTOLibros dto) {
        return null;
    }
}
