package com.asistencias.app.repository;

import com.asistencias.app.model.Aula;
import com.asistencias.app.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AulaRepo extends JpaRepository<Aula, Long> {

    @Query("FROM Estudiante e WHERE e.codigoEstudiante = ?1")
    Optional<Estudiante> buscarEstudiantePorCodigo(String codigoEstudiante);
}
