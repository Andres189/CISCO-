package com.example.application.repository;

import com.example.application.model.Usuario;

import java.util.List;

public interface IUsuariosRepository {

    public int save(Usuario usuario);
    public int deleteById(int id);

}
