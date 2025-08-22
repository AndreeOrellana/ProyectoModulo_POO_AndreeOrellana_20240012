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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiLibros")
public class librosController {
    @Autowired
    private serviceLibros objServiceL;

    /*SOLICITUD GET*/

    @GetMapping("/getLibro")
    public List<DTOLibros> getLibros(){
        return objServiceL getLibros();
    }

    /*SOLICITUD POST*/

    @PostMapping("/postLibro")
    public ResponseEntity<?> postArea(@RequestBody DTOLibros dto){
        try {
            DTOLibros answer = objServiceL.postArea(dto);
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

    @PutMapping("/putLibro/{id}")
    public ResponseEntity<?> putLibro (@Validated @RequestBody DTOLibros dto @PathVariable String id BindingResult dataResult){
        if (dataResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            dataResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        try{
            DTOLibros answer = objServiceL.putLibro(dto, id);
            if (answer == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Error al actualizar los datos",
                        "errorType", "VALIDATION_ERROR",
                        "message", "Datos inválidos, vuelva a intentarlo"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Libro modificado correctamente, Success",
                    "data", answer
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error critico no controlado",
                    "message", "Error al actualizar el libro",
                    "detail", e.getMessage()
            ));
        }
    }

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