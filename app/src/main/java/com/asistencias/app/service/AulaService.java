package com.asistencias.app.service;

import com.asistencias.app.model.Aula;
import com.asistencias.app.model.Estudiante;

import java.util.Optional;

public interface AulaService  extends GeneralService<Aula> {
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigoEstudiante);

}
