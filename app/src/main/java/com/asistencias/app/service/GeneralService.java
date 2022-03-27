package com.asistencias.app.service;

import com.asistencias.app.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T> {

    T guardar(T t) throws ServiceException;
    Optional<T> buscarPorId(Long id);
    List<T> buscarTodo();

}
