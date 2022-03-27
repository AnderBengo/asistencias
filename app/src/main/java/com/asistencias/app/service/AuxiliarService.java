package com.asistencias.app.service;

import com.asistencias.app.model.Auxiliar;

import java.util.Optional;

public interface AuxiliarService extends GeneralService<Auxiliar>{

    Optional<Auxiliar> buscarPorUsuarioAndPassword(String usuario, String password);
}
