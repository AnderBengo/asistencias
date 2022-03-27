package com.asistencias.app.service;

import com.asistencias.app.model.Auxiliar;
import com.asistencias.app.repository.AuxiliarRepo;
import com.asistencias.app.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuxiliarServiceImpl implements AuxiliarService{

    private final AuxiliarRepo auxiliarRepo;

    @Override
    public Auxiliar guardar(Auxiliar auxiliar) throws ServiceException {
        try {
            return auxiliarRepo.save(auxiliar);
        } catch (DataAccessException e) {
            throw new ServiceException("Error en la base de datos al insertar el auxiliar.");
        }
    }

    @Override
    public Optional<Auxiliar> buscarPorId(Long id) {
        return auxiliarRepo.findById(id);
    }

    @Override
    public List<Auxiliar> buscarTodo() {
        return auxiliarRepo.findAll();
    }

    @Override
    public Optional<Auxiliar> buscarPorUsuarioAndPassword(String usuario, String password) {
        return auxiliarRepo.findByUsuarioAndPassword(usuario, password);
    }
}
