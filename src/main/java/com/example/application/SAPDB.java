package com.example.application;
import com.example.application.model.Usuario;
import com.mysql.cj.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SAPDB {

    static Connection con = null;

    public static Connection Conexion(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sapdb","root","");
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }catch(SQLException e){
            System.out.println(e);
        }
        return con;
    }
    public static Boolean Login(String usuario,String pass,Connection con){
        Boolean banderLogin=false;
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT * FROM USUARIOS WHERE USUARIO ='"+usuario+"' AND CONTRASEÑA ='"+pass+"'");

            if(registro.next()){
                banderLogin = true;
            }
            con.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return banderLogin;
    }
    public static List Practicas(Connection con){

        List<Usuario> listUsuario = new ArrayList<Usuario>();

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT * FROM USUARIOS");
            while(registro.next()){
                Usuario u=new Usuario(registro.getInt(1),registro.getString(2),registro.getString(3),registro.getString(4));
                listUsuario.add(u);
            }
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }

        return listUsuario;
    }

}
