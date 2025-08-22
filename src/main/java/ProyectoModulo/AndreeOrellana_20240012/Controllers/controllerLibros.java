package ProyectoModulo.AndreeOrellana_20240012.Controllers;

import ProyectoModulo.AndreeOrellana_20240012.Models.DTOLibros;
import ProyectoModulo.AndreeOrellana_20240012.Services.serviceLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/apiLibros")
public class librosController {
    @Autowired
    private serviceLibros objServiceL;

    /*SOLICITUD GET*/

    @GetMapping("/getLibro")
    public List<DTOLibros> getLibros(){
        return objServiceL.getLibros();
    }

    /*SOLICITUD POST*/

    @PostMapping("/postLibro")
    public ResponseEntity<?> postArea(@RequestBody DTOLibros dto){
        try {
            DTOLibros answer = objServiceL.postLibro(dto);
            if (answer == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Error al guardar los datos",
                        "errorType", "VALIDATION_ERROR",
                        "message", "Datos invalidos, vuelva a intentarlo"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Libro registrado correctamente",
                    "data", answer
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error critico no controlado",
                    "message", "Error al registrar el libro",
                    "detail", e.getMessage()
            ));
        }
    }

    /*SOLICITUD PUT*/

    

    /*SOLICITUD DELETE*/

    @DeleteMapping("/deleteLibro/{id}")
    public ResponseEntity <?> deleteLibro (@PathVariable String idLibro){
        try{
            if (!objServiceL.removeArea(idLibro)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header(
                        "Error, ID no encontrado", "ID del libro no encontrado").body(Map.of(
                        "status", "No encontrado, error",
                        "message", "El ID del libro no ha sido encontrado",
                        "timeStamp", Instant.now().toString()
                ));
            }
            return ResponseEntity.ok(Map.of(
                    "status", "proceso completado correctamente",
                    "message", "Libro eliminado correctamente, Success"
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error critico no controlado",
                    "message", "Error al eliminar el area",
                    "detail", e.getMessage()
            ));
        }

    }
}