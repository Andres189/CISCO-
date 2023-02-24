package com.example.application.service;

import com.example.application.model.Usuario;

import java.util.List;

public interface IUsuarioService {

    public int save(Usuario usuario);
    public int deleteById(int id);

}
