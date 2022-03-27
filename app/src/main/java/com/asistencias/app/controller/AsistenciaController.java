package com.asistencias.app.controller;

import com.asistencias.app.controller.dto.response.CustomResponse;
import com.asistencias.app.model.Asistencia;
import com.asistencias.app.model.Estudiante;
import com.asistencias.app.service.AsistenciaService;
import com.asistencias.app.service.AulaService;
import com.asistencias.app.service.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asistencias.app.controller.dto.response.CodeEnum.SUCCESS;
import static com.asistencias.app.controller.dto.response.CodeEnum.WARNING;
import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    private AsistenciaService asistenciaService;
    private AulaService aulaService;

    public AsistenciaController(AsistenciaService asistenciaService, AulaService aulaService) {
        this.asistenciaService = asistenciaService;
        this.aulaService = aulaService;
    }

    @GetMapping
    public List<Asistencia> listar(){
        return asistenciaService.buscarTodo();
    }

    @GetMapping("/buscar-por-codigo/{codigoEstudiante}")
    public ResponseEntity<?> buscarAsistenciaPorCodigoEstudiante(@PathVariable String codigoEstudiante){
        return ResponseEntity
                .status(OK)
                .body(CustomResponse.builder()
                        .code(SUCCESS).message("Transacción realizada")
                        .data(asistenciaService.buscarPorEstudianteCodigo(codigoEstudiante))
                        .build());
    }

    @PostMapping("/registrar-asistencia")
    public ResponseEntity<?> registrarAsistencia(@RequestBody Asistencia asistencia) throws ServiceException {

        Optional<Estudiante> estudiante = aulaService.buscarEstudiantePorCodigo(asistencia.getEstudiante().getCodigoEstudiante());

        if(!estudiante.isPresent()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("No existe estudiante con el código: "+ asistencia.getEstudiante().getCodigoEstudiante()).build());
        }

        Optional<Asistencia> asistenciaEncontrada = asistenciaService.buscarPorEstudianteCodigo(asistencia.getEstudiante().getCodigoEstudiante());

        asistencia.setEstudiante(estudiante.get());

        if(!asistenciaEncontrada.isPresent()){
            return ResponseEntity
                    .status(CREATED)
                    .body(CustomResponse.builder()
                            .code(SUCCESS).message("Asistencia registrada")
                            .data(asistenciaService.guardar(asistencia)).build());
        }

        return ResponseEntity
                .status(BAD_REQUEST)
                .body(CustomResponse.builder()
                        .code(WARNING).message("No se puede registrar la asistencia del estudiante 2 veces.")
                        .data(asistenciaEncontrada)
                        .build());
    }

    @PutMapping("/registrar-salida")
    public ResponseEntity<?> registrarSalida(@RequestBody Asistencia asistenciaFront) {

        Optional<Estudiante> estudiante = aulaService.buscarEstudiantePorCodigo(asistenciaFront.getEstudiante().getCodigoEstudiante());

        if(!estudiante.isPresent()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("No existe estudiante con el código: "+ asistenciaFront.getEstudiante().getCodigoEstudiante()).build());
        }

        Optional<Asistencia> asistenciaEncontrada = asistenciaService
                .buscarPorEstudianteCodigo(asistenciaFront.getEstudiante().getCodigoEstudiante());

        if(!asistenciaEncontrada.isPresent()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(CustomResponse.builder()
                            .code(WARNING).message("Para registrar la salida, primero debe registrar la asistencia. Código de estudiante: "+ asistenciaFront.getEstudiante().getCodigoEstudiante()+".").build());
        }
        Asistencia asistencia = asistenciaEncontrada.get();

        if(!asistenciaFront.getIngresoConfirmado() || !asistenciaFront.getSalidaConfirmado()) {
            return ResponseEntity.status(BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .code(WARNING)
                            .message("Para registrar la salida, el  ingreso debe ser confirmado.")
                            .build());
        }
        asistencia.setSalidaConfirmado(asistenciaFront.getSalidaConfirmado());

        if(asistencia.getIngresoConfirmado() && asistencia.getSalidaConfirmado() && asistencia.getFechaHoraSalida() != null){
            return ResponseEntity.status(BAD_REQUEST)
                    .body(CustomResponse.builder()
                            .code(WARNING)
                            .message("No se puede registrar la salida del estudiante 2 veces.")
                            .data(asistencia)
                            .build());
        }

        return ResponseEntity
                .status(CREATED)
                .body(CustomResponse.builder()
                        .code(SUCCESS).message("Salida registrada")
                        .data(asistenciaService.registrarSalida(asistencia)).build());

    }

    @GetMapping("/reporte")
    public ResponseEntity<?> reporte(){
        Map<String, Object> response = new HashMap<>();
        response.put("ingresoConfirmado", asistenciaService.obtenerReporteAuxiliar(true));
        response.put("salidaConfirmado", asistenciaService.obtenerReporteAuxiliar(false));


        return new ResponseEntity<>(response, OK);
    }
}
