package com.asistencias.app.controller;

import com.asistencias.app.controller.dto.response.CustomResponse;
import com.asistencias.app.model.Aula;
import com.asistencias.app.service.AulaService;
import com.asistencias.app.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.asistencias.app.controller.dto.response.CodeEnum.SUCCESS;
import static com.asistencias.app.controller.dto.response.CodeEnum.WARNING;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api/aulas")
public class AulaController {

    private AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping("/obtener-aulas")
    public ResponseEntity<?> obtenerAulas() {
        return ResponseEntity
                .status(OK)
                .body(CustomResponse.builder()
                        .code(SUCCESS)
                        .message("Transacción exitosa!")
                        .data(aulaService.buscarTodo()).build());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarAula(@PathVariable Long id) {
        Optional<Aula> aula = aulaService.buscarPorId(id);

        if(!aula.isPresent()) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .message("No se encontró un aula con ese ID: "+id)
                            .code(WARNING).build());

        }


        return ResponseEntity
                .status(OK)
                .body(CustomResponse.builder()
                        .code(SUCCESS)
                        .message("Transacción exitosa!")
                        .data(aulaService.buscarPorId(id)).build());
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAula(@RequestBody Aula aula) throws ServiceException {
        return new ResponseEntity<>(CustomResponse.builder()
                .code(SUCCESS).message("Aula registrada")
                .data(aulaService.guardar(aula)).build(), CREATED);
    }


}
