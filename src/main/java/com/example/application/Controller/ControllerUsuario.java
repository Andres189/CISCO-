package com.example.application.Controller;

import com.example.application.model.ServiceResponse;
import com.example.application.model.Usuario;
import com.example.application.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin("*")
public class ControllerUsuario {
    @Autowired
    private IUsuarioService iUsuarioService;
    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Usuario usuario){
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iUsuarioService.save(usuario);
        if(result ==1){
            serviceResponse.setMessage("Nuevo usuario insertado");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<ServiceResponse> deleteById(@RequestBody int id){
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iUsuarioService.deleteById(id);
        if(result==1){
            serviceResponse.setMessage("Usuario eliminado");
        }
        return new ResponseEntity<>(serviceResponse,HttpStatus.OK);
    }
}
