package com.example.application;
import com.example.application.model.Practica;
import com.example.application.model.Usuario;
import com.mysql.cj.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.aspectj.weaver.ast.Not;

import java.awt.*;
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
            ResultSet registro = consulta.executeQuery("SELECT * FROM usuario WHERE USUARIO ='"+usuario+"' AND CONTRASEÑA ='"+pass+"'");

            if(registro.next()){
                banderLogin = true;
            }
            con.close();
        }catch (SQLException e){
            System.out.println(e);
        }
        return banderLogin;
    }
    public static Boolean permisos(Connection con,String usuario,String pass) {
        Boolean permiso = false;

        try {
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT PERMISOS FROM usuario WHERE USUARIO ='" + usuario + "' AND CONTRASEÑA ='" + pass + "'");
            while (registro.next()) {
                if (registro.getString(1).equals("ADMIN")) {
                    permiso = true;
                }
            }
            con.close();

        } catch (SQLException e) {
            System.out.println("Usuario estandar");
        }
        return permiso;
    }
    public static List Practicas(Connection con){

        List<Usuario> listUsuario = new ArrayList<Usuario>();

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT * FROM usuario");
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

    public static List PracticasGrid(Connection con,String dia,String horario){

        List<Practica> listPracticas = new ArrayList<Practica>();

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT H.horario,concat(prof.nombre,' ',prof.paterno,' ',prof.materno) AS Profesor,m.Materia,s.salon,P.practica,M.alumnosInscritos,\n" +
                    "P.HrEntrada,P.Asistencia,P.HrSalida\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicauno as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT "+dia+"= 8 AND H.horario ='"+horario+"'\n" +
                    "ORDER BY s.salon ASC;");
            while(registro.next()){
                Practica practicaLunes=new Practica(registro.getString(1), registro.getString(2), registro.getString(3), registro.getString(4), registro.getString(5), registro.getInt(6), registro.getString(7), registro.getInt(8), registro.getString(9));
                listPracticas.add(practicaLunes);
            }
            con.close();
        }catch(Exception ex){
            Notification notiFiltroPractica = Notification.show("Revisar valores del filtro dia y horario");
            notiFiltroPractica.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            System.out.println(ex);
        }

        return listPracticas;
    }

    public static List PracticasGridDos(Connection con,String dia,String horario){

        List<Practica> listPracticas = new ArrayList<Practica>();

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT H.horario,concat(prof.nombre,' ',prof.paterno,' ',prof.materno) AS Profesor,m.Materia,s.salon,P.practica,M.alumnosInscritos,\n" +
                    "P.HrEntrada,P.Asistencia,P.HrSalida\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicados as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT "+dia+"= 8 AND H.horario ='"+horario+"'\n" +
                    "ORDER BY s.salon ASC;");
            while(registro.next()){
                Practica practicaLunes=new Practica(registro.getString(1), registro.getString(2), registro.getString(3), registro.getString(4), registro.getString(5), registro.getInt(6), registro.getString(7), registro.getInt(8), registro.getString(9));
                listPracticas.add(practicaLunes);
            }
            con.close();
        }catch(Exception ex){
            Notification notiFiltroPractica = Notification.show("Revisar valores del filtro dia y horario");
            notiFiltroPractica.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            System.out.println(ex);
        }

        return listPracticas;
    }


    public static List comboSalon(Connection con,String dia,String horario){
        List<String> listaSalon = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT s.salon\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicauno as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT "+dia+"= 8 AND H.horario ='"+horario+"'\n" +
                    "ORDER BY s.salon ASC;");
            while(registro.next()){
                listaSalon.add(registro.getString(1));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return listaSalon;
    }

    public static List comboSalonDos(Connection con,String dia,String horario){
        List<String> listaSalon = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT s.salon\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicados as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT "+dia+"= 8 AND H.horario ='"+horario+"'\n" +
                    "ORDER BY s.salon ASC;");
            while(registro.next()){
                listaSalon.add(registro.getString(1));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return listaSalon;
    }

    public static String profePractica(Connection con,String dia,String horario,String salon){
        String profe="";

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT concat(prof.nombre,' ',prof.paterno,' ',prof.materno)\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicauno as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT "+dia+"= 8 AND H.horario ='"+horario+"' AND S.salon='"+salon+"'\n" +
                    "ORDER BY s.salon ASC;");
            while(registro.next()){
                profe=registro.getString(1);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return profe;
    }

    public static String profePracticaDos(Connection con,String dia,String horario,String salon){
        String profe="";

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT concat(prof.nombre,' ',prof.paterno,' ',prof.materno)\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicados as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT "+dia+"= 8 AND H.horario ='"+horario+"' AND S.salon='"+salon+"'\n" +
                    "ORDER BY s.salon ASC;");
            while(registro.next()){
                profe=registro.getString(1);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return profe;
    }

    public static void insertarUsuario(Connection con,String usuario,String pass,String permisos){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO USUARIO(USUARIO,CONTRASEÑA,PERMISOS) VALUES (?,?,?)");
            ps.setString(1,usuario);
            ps.setString(2,pass);
            ps.setString(3,permisos);
            ps.executeUpdate();
            con.close();

        }catch(SQLException e){
            System.out.println(e);
        }

    }
    public static void eliminarUsuario(Connection con,String usuario){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("DELETE FROM USUARIO WHERE USUARIO=?");
            ps.setString(1,usuario);
            ps.executeUpdate();
            Notification notiUsuarioEliminado = Notification.show("Usuario eliminado");
            notiUsuarioEliminado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            Notification notiUsuarioNoEliminado = Notification.show("Usuario no encontrado");
            notiUsuarioNoEliminado.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);

        }
    }

    public static boolean consultarUsuario(Connection con,String usuario){
        boolean banderaExiste=false;

        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT USUARIO FROM USUARIO WHERE USUARIO ='"+usuario+"'");
            if(registro.next()){
                banderaExiste = true;
                Notification notiExiste = Notification.show("Modifica el usuario");
                notiExiste.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
            Notification notiNoExiste = Notification.show("El usuario no existe");
            notiNoExiste.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

        return banderaExiste;
    }

    public static void actualizarUsuario(Connection con, String usuario, String contraseña, String permisos, String validarUsuario){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE USUARIO SET USUARIO=?,CONTRASEÑA=?,PERMISOS=? WHERE USUARIO=?");
            ps.setString(1,usuario);
            ps.setString(2,contraseña);
            ps.setString(3,permisos);
            ps.setString(4,validarUsuario);
            ps.executeUpdate();
            Notification notiModificacionUsuario = Notification.show("Usuario actualizado");
            notiModificacionUsuario.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){

            System.out.println(e);
            Notification notiNoActualizado = Notification.show("Usuario no actualizado");
            notiNoActualizado.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    public static void insertarPractica(Connection con,String dia,String horario,String salon,String practica,String hrentrada){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practicauno SET practica ='"+practica+"',HrEntrada='"+hrentrada+"' WHERE idMateria = "+
                    "(SELECT P.idMateria\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicauno as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT P."+dia+" = 8 AND H.horario ='"+horario+"' AND S.Salon = '"+salon+"')");
            ps.executeUpdate();
            Notification notiEntrada = Notification.show("Entrada registrada");
            notiEntrada.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            Notification notiNoEntrada = Notification.show("Error entrada");
            notiNoEntrada.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }
    }

    public static void insertarPracticaDos(Connection con,String dia,String horario,String salon,String practica,String hrentrada){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practicados SET practica ='"+practica+"',HrEntrada='"+hrentrada+"' WHERE idMateria = "+
                    "(SELECT P.idMateria\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicados as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT P."+dia+" = 8 AND H.horario ='"+horario+"' AND S.Salon = '"+salon+"')");
            ps.executeUpdate();
            Notification notiEntrada = Notification.show("Entrada registrada");
            notiEntrada.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            Notification notiNoEntrada = Notification.show("Error entrada");
            notiNoEntrada.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }
    }

    public static void salidaPracticas(Connection con,String dia,String horario,String salon,int asistencia,String hrsalida){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practicauno SET Asistencia ="+asistencia+",HrSalida='"+hrsalida+"' WHERE idMateria = "+
                    "(SELECT P.idMateria\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicauno as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT P."+dia+" = 8 AND H.horario ='"+horario+"' AND S.Salon = '"+salon+"')");
            ps.executeUpdate();
            Notification notiSalida = Notification.show("Salida registrada");
            notiSalida.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            Notification notiNoSalida = Notification.show("Error");
            notiNoSalida.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }
    }

    public static void salidaPracticasDos(Connection con,String dia,String horario,String salon,int asistencia,String hrsalida){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practicados SET Asistencia ="+asistencia+",HrSalida='"+hrsalida+"' WHERE idMateria = "+
                    "(SELECT P.idMateria\n" +
                    "FROM horario as H \n" +
                    "INNER JOIN practicados as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE NOT P."+dia+" = 8 AND H.horario ='"+horario+"' AND S.Salon = '"+salon+"')");
            ps.executeUpdate();
            Notification notiSalida = Notification.show("Salida registrada");
            notiSalida.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            Notification notiNoSalida = Notification.show("Error");
            notiNoSalida.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }
    }

    public static void tablaPractica(Connection con,String dia,String Horario,String salon){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO practica"+dia+
                    "(SELECT H.horario,concat(prof.nombre,' ',prof.paterno,' ',prof.materno) AS Profesor,m.Materia,s.salon,P.practica,M.alumnosInscritos,\n"+
                    "P.HrEntrada,P.Asistencia,P.HrSalida\n"+
                    "FROM horario as H \n" +
                    "INNER JOIN practicauno as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE S.salon= ? AND H.horario = ?)");
            ps.setString(1,salon);
            ps.setString(2,Horario);
            ps.execute();
            con.close();
        }catch(SQLException e){

            System.out.println(e);
            Notification notiNoActualizado = Notification.show("Error practica");
            notiNoActualizado.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }

    }

    public static void tablaPracticaDos(Connection con,String dia,String Horario,String salon){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO practica"+dia+
                    "(SELECT H.horario,concat(prof.nombre,' ',prof.paterno,' ',prof.materno) AS Profesor,m.Materia,s.salon,P.practica,M.alumnosInscritos,\n"+
                    "P.HrEntrada,P.Asistencia,P.HrSalida\n"+
                    "FROM horario as H \n" +
                    "INNER JOIN practicados as P ON H.idHorario=P."+dia+"\n" +
                    "INNER JOIN materia as M ON M.idMateria=P.idMateria\n" +
                    "INNER JOIN profesor as prof ON prof.idProfesor = M.idProfesor\n" +
                    "INNER JOIN  salon as S on S.idSalon = M.idSalon\n" +
                    "WHERE S.salon= ? AND H.horario = ?)");
            ps.setString(1,salon);
            ps.setString(2,Horario);
            ps.execute();
            con.close();
        }catch(SQLException e){

            System.out.println(e);
            Notification notiNoActualizado = Notification.show("Error practica");
            notiNoActualizado.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }

    }
    public static void registroSalidas(Connection con,String dia,String Horario,String salon,int asistnecia,String salida){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practica"+dia+" SET Asistencia=?,Salida=? WHERE Horario=? AND Salon=?");
            ps.setInt(1,asistnecia);
            ps.setString(2,salida);
            ps.setString(3,Horario);
            ps.setString(4,salon);
            ps.execute();
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }



}
