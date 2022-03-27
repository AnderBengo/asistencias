package com.asistencias.app.controller;

import com.asistencias.app.controller.dto.response.CustomResponse;
import com.asistencias.app.model.Aula;
import com.asistencias.app.model.Auxiliar;
import com.asistencias.app.service.AuxiliarService;
import com.asistencias.app.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import java.util.Optional;

import static com.asistencias.app.controller.dto.response.CodeEnum.SUCCESS;
import static com.asistencias.app.controller.dto.response.CodeEnum.WARNING;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auxiliares")
public class AuxiliarController {

    private AuxiliarService auxiliarService;

    public AuxiliarController(AuxiliarService auxiliarService) {
        this.auxiliarService = auxiliarService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAuxiliar(@RequestBody Auxiliar auxiliar) throws ServiceException {
        return new ResponseEntity<>(CustomResponse.builder()
                .code(SUCCESS).message("Auxiliar registrado")
                .data(auxiliarService.guardar(auxiliar)).build(), CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> registrarAuxiliar(@PathVariable Long id) {
        return new ResponseEntity<>(CustomResponse.builder()
                .code(SUCCESS).message("Auxiliar registrado")
                .data(auxiliarService.buscarPorId(id).get()).build(), OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> ingresarAlSistema(@PathParam("usuario") String usuario, @PathParam("password") String password) {
        Optional<Auxiliar> auxiliar = auxiliarService.buscarPorUsuarioAndPassword(usuario, password);
        if(!auxiliar.isPresent()) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .message("Usuario y/o contraseña incorrectos.")
                            .code(WARNING).build());

        }

        return ResponseEntity
                .status(OK)
                .body(CustomResponse.builder()
                        .message("Login exitoso")
                        .code(SUCCESS).data(auxiliar.get()).build());

    }

}
