package com.asistencias.app.repository;

import com.asistencias.app.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepo extends JpaRepository<Asistencia, Long> {

    Optional<Asistencia> findByEstudiante_CodigoEstudianteAndFechaQuery(String codigoEstudiante, LocalDate fechaQuery);

    List<Asistencia> findByFechaQueryAndIngresoConfirmadoAndEstudiante_Aula_Id(LocalDate fechaActual, boolean ingresoConfirmado, Long aula_id);

    List<Asistencia> findByFechaQueryAndSalidaConfirmadoAndEstudiante_Aula_Id(LocalDate fechaActual, boolean salidaConfirmado, Long aula_id);
}
