package com.asistencias.app.service;

import com.asistencias.app.model.Asistencia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaService extends GeneralService<Asistencia>{

    Optional<Asistencia> buscarPorEstudianteCodigo(String codigoEstudiante);

    Asistencia registrarSalida(Asistencia asistencia);

    List<Asistencia> obtenerReporteAuxiliar(boolean ingreso);
}
