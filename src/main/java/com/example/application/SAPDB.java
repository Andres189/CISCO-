package com.example.application;
import com.mysql.cj.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

import java.sql.*;

public class SAPDB {

    static Connection con = null;

    public static Connection Conexion(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sapdb","root","");
            //Notification exito = Notification.show("Conexion exitosa");
            //exito.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
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

}
