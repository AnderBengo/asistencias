package com.asistencias.app.service;

import com.asistencias.app.model.Asistencia;
import com.asistencias.app.repository.AsistenciaRepo;
import com.asistencias.app.repository.AulaRepo;
import com.asistencias.app.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AsistenciaServiceImpl implements AsistenciaService{

    private final AsistenciaRepo asistenciaRepo;
    private final AulaRepo aulaRepo;

    @Override
    public Asistencia guardar(Asistencia asistencia) throws ServiceException {

        if(!asistencia.getIngresoConfirmado() || asistencia.getSalidaConfirmado()){
            throw new ServiceException("Parámetros inválidos");
        }

        try {
            LocalDateTime fechaActual = LocalDateTime.now();
            LocalDate fechaQuery = LocalDate.now();
            asistencia.setFechaHoraIngreso(fechaActual);
            asistencia.setFechaQuery(fechaQuery);
            return asistenciaRepo.save(asistencia);
        } catch (DataAccessException e) {
            throw new ServiceException("Error en la base de datos al insertar la asistencia.");
        }
    }

    @Override
    public Optional<Asistencia> buscarPorId(Long id) {
        return asistenciaRepo.findById(id);
    }

    @Override
    public List<Asistencia> buscarTodo() {
        return asistenciaRepo.findAll();
    }

    @Override
    public Optional<Asistencia> buscarPorEstudianteCodigo(String codigoEstudiante) {
        LocalDate fechaActual = LocalDate.now();
        return asistenciaRepo.findByEstudiante_CodigoEstudianteAndFechaQuery(codigoEstudiante, fechaActual);
    }

    @Override
    public Asistencia registrarSalida(Asistencia asistencia) {
        LocalDateTime fechaActual = LocalDateTime.now();
        asistencia.setFechaHoraSalida(fechaActual);
        return asistenciaRepo.save(asistencia);
    }

    @Override
    public List<Asistencia> obtenerReporteAuxiliar(boolean ingreso) {
        LocalDate fechaActual = LocalDate.now();
        return asistenciaRepo.findByFechaQueryAndIngresoConfirmadoAndEstudiante_Aula_Id(fechaActual, ingreso,1L);
    }


}
