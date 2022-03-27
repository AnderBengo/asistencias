package com.asistencias.app.service;

import com.asistencias.app.model.Aula;
import com.asistencias.app.model.Estudiante;
import com.asistencias.app.repository.AulaRepo;
import com.asistencias.app.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AulaServiceImpl implements AulaService{

    private final AulaRepo aulaRepo;

    @Override
    public Aula guardar(Aula aula) throws ServiceException{

        try {
            return aulaRepo.save(aula);
        } catch (DataAccessException e) {
            throw new ServiceException("Error en la base de datos al insertar el aula.");
        }

    }

    @Override
    public Optional<Aula> buscarPorId(Long id) {
        return aulaRepo.findById(id);
    }

    @Override
    public List<Aula> buscarTodo() {
        return aulaRepo.findAll();
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorCodigo(String codigoEstudiante) {
        return aulaRepo.buscarEstudiantePorCodigo(codigoEstudiante);
    }
}
