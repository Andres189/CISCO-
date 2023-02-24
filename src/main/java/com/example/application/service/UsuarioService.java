package com.example.application.service;

import com.example.application.model.Usuario;
import com.example.application.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuariosRepository iUsuariosRepository;
    @Override
    public int save(Usuario usuario) {
        int row;
        try{

            row = iUsuariosRepository.save(usuario);

        }catch(Exception ex){
            throw ex;
        }
        return row;
    }

    @Override
    public int deleteById(int id) {

        int row;
        try{
            row = iUsuariosRepository.deleteById(id);
        }catch(Exception ex){
            throw ex;
        }
        return row;
    }
}
