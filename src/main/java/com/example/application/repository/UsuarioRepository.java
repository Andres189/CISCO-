package com.example.application.repository;

import com.example.application.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository implements IUsuariosRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Usuario usuario) {
        String SQL = "INSERT INTO USUARIOS VALUES(?,?,?,?)";
        return jdbcTemplate.update(SQL,new Object[]{usuario.getIdUsuario(),usuario.getUsuario(),usuario.getContraseña(),usuario.getPermisos()});
    }
    @Override
    public int deleteById(int id) {
        String SQL = "DELETE FROM USUARIOS WHERE ID_USUARIO=?";
        return jdbcTemplate.update(SQL,id);
    }
}
