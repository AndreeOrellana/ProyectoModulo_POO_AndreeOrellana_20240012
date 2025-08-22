package ProyectoModulo.AndreeOrellana_20240012.Repositories;

import ProyectoModulo.AndreeOrellana_20240012.Entities.EntityLibro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repositoryLibro extends JpaRepository<EntityLibro, String> {
}
