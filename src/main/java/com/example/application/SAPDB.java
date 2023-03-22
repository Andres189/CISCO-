package com.example.application;
import com.example.application.model.Apartado;
import com.example.application.model.Practica;
import com.example.application.model.Usuario;
import com.mysql.cj.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.aspectj.weaver.ast.Not;

import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    public static void descargarLunes(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicalunes into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Lunes.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga lunes");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }

    }
    public static void descargarMartes(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicamartes into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Martes.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga martes");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }

    }

    public static void descargarMiercoles(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicamiercoles into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Miercoles.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga miercoles");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }

    }

    public static void descargarJueves(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicajueves into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Jueves.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga jueves");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }

    }
    public static void descargarViernes(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicaviernes into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Viernes.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga viernes");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }

    }

    public static void descargarSabado(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicasabado into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Sabado.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga sabado");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }

    }

    public static void descargaPrestamo(Connection con){
        try{
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM practicaprestamo into OUTFILE 'C:/Users/ajg_0/OneDrive/Documentos/Practica_Prestamo.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\\n';");
            ps.execute();
            con.close();
        }catch(SQLException e){
            Notification notiDescargaLunesError = Notification.show("Error en la descarga sabado");
            notiDescargaLunesError.addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println(e);
        }
    }

    public static void limpiarTablas(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practicauno SET practica = NULL, HrEntrada = NULL, HrSalida = NULL, Asistencia = NULL");
            ps.execute();
            ps = con.prepareStatement("UPDATE practicados SET practica = NULL, HrEntrada = NULL, HrSalida = NULL, Asistencia = NULL");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicalunes");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicamartes");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicamiercoles");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicajueves");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicaviernes");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicasabado");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicaprestamo");
            ps.execute();
            con.close();
            Notification notiLimpiar = Notification.show("Tablas limpias");
            notiLimpiar.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }catch(SQLException ex){

            System.out.println(ex);
            Notification notiNoLimpias = Notification.show("Error al limpiar tablas");
            notiNoLimpias.addThemeVariants(NotificationVariant.LUMO_ERROR);

        }

    }

    public static List horarioUno(Connection con,String dia){

        List<String> listaHorario = new ArrayList<>();

        try{

            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT DISTINCT H.horario \n" +
                    "FROM practicauno AS P\n" +
                    "INNER JOIN Horario AS H ON H.idHorario = P."+dia+"\n"+
                    "WHERE NOT "+dia+"= 8\n" +
                    "ORDER BY H.idHorario ASC");

            while(registro.next()){
                listaHorario.add(registro.getString(1));
            }
            con.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        return listaHorario;

    }

    public static List horarioDos(Connection con,String dia){

        List<String> listaHorario = new ArrayList<>();

        try{

            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT DISTINCT H.horario \n" +
                    "FROM practicados AS P\n" +
                    "INNER JOIN Horario AS H ON H.idHorario = P."+dia+"\n"+
                    "WHERE NOT "+dia+"= 8\n" +
                    "ORDER BY H.idHorario ASC");

            while(registro.next()){
                listaHorario.add(registro.getString(1));
            }
            con.close();

        }catch(SQLException ex){
            System.out.println(ex);
        }

        return listaHorario;
    }

    public static void registrarPrestamo(Connection con,String fecha,String hrEntrada,String hrSalida,String materia,String profesor,String salon){
        try{
            PreparedStatement ps;
            ps=con.prepareStatement("INSERT INTO registroprestamos(Fecha,HrEntrada,HrSalida,Materia,Profesor,Salon) VALUES (?,?,?,?,?,?)");
            ps.setString(1,fecha);
            ps.setString(2,hrEntrada);
            ps.setString(3,hrSalida);
            ps.setString(4,materia);
            ps.setString(5,profesor);
            ps.setString(6,salon);
            ps.execute();
            Notification notiApartado = Notification.show("Apartado confirmado");
            notiApartado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            System.out.println(e);
            Notification notiNoApartado = Notification.show("Error");
            notiNoApartado.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    public static List gridPrestamosEntrada(Connection con,String fecha,String horaEntrada){
        List<Apartado> listaPrestamos = new ArrayList<>();
        try{

            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT Fecha,CONCAT(HrEntrada,'-',HrSalida) AS 'Horario',Profesor,Materia,Salon,Practica,Entrada,Asistencia,Salida \n" +
                    "FROM `registroprestamos`\n" +
                    "WHERE Fecha='"+fecha+"' AND HrEntrada='"+horaEntrada+"';");

            while(registro.next()){
                Apartado gridApartado = new Apartado(registro.getString(1),registro.getString(2),registro.getString(3),registro.getString(4),registro.getString(5),registro.getString(6),registro.getString(7),registro.getInt(8),registro.getString(9));
                listaPrestamos.add(gridApartado);
            }
            con.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        return listaPrestamos;
    }

    public static List gridPrestamoSalida(Connection con,String fecha,String horaSalida){
        List<Apartado> listaPrestamos = new ArrayList<>();
        try{

            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT Fecha,CONCAT(HrEntrada,'-',HrSalida) AS 'Horario',Profesor,Materia,Salon,Practica,Entrada,Asistencia,Salida \n" +
                    "FROM `registroprestamos`\n" +
                    "WHERE Fecha='"+fecha+"' AND HrSalida='"+horaSalida+"';");

            while(registro.next()){
                Apartado gridApartadoSalida = new Apartado(registro.getString(1),registro.getString(2),registro.getString(3),registro.getString(4),registro.getString(5),registro.getString(6),registro.getString(7),registro.getInt(8),registro.getString(9));
                listaPrestamos.add(gridApartadoSalida);
            }
            con.close();

        }catch(SQLException e){
            System.out.println(e);
        }
        return listaPrestamos;
    }

    public static String getDiaSemana(Date date){

        int diaNumero = date.getDay();
        String dia=null;

        switch (diaNumero){
            case 1:{
                dia="Lunes";
                break;
            }
            case 2:{
                dia="Martes";
                break;
            }
            case 3:{
                dia="Miercoles";
                break;
            }
            case 4:{
                dia="Jueves";
                break;
            }
            case 5:{
                dia="Viernes";
                break;
            }
            case 6:{
                dia="Sabado";
                break;
            }

        }

        return dia;

    }

    public static List comboSalonApartadoEntrada(Connection con,String fecha,String hrEntrada){
        List<String> listaSalon = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT Salon FROM registroPrestamos WHERE Fecha='"+fecha+"' AND HrEntrada='"+hrEntrada+"';");
            while(registro.next()){
                listaSalon.add(registro.getString(1));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return  listaSalon;
    }

    public static List comboSalonApartadoSalida(Connection con,String fecha,String hrSalida){
        List<String> listaSalon = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT Salon FROM registroPrestamos WHERE Fecha='"+fecha+"' AND HrSalida='"+hrSalida+"';");
            while(registro.next()){
                listaSalon.add(registro.getString(1));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return  listaSalon;
    }

    public static String profesorEntradaApartados(Connection con,String fecha,String hrEntrada,String salon){
        String profesor = null;
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT Profesor FROM registroPrestamos WHERE Fecha='"+fecha+"' AND HrEntrada='"+hrEntrada+"' AND Salon='"+salon+"';");
            while(registro.next()){
                profesor=registro.getString(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return  profesor;
    }

    public static String profesorSalidaApartados(Connection con,String fecha,String hrSalida,String salon){
        String profesor = null;
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT Profesor FROM registroPrestamos WHERE Fecha='"+fecha+"' AND HrSalida='"+hrSalida+"' AND Salon='"+salon+"';");
            while(registro.next()){
                profesor=registro.getString(1);
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return  profesor;
    }

    public static List salonesPrestamos(Connection con){
        List<String> listaSalones = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT SALON FROM SALON ORDER BY idSalon ASC;");
            while(registro.next()){
                listaSalones.add(registro.getString(1));
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return  listaSalones;
    }

    public static void entradaPrestamos(Connection con,String fecha,String hrEntrada,String salon,String practica,String hrEntradaClase){

        try{
            PreparedStatement ps;
            ps=con.prepareStatement("UPDATE registroprestamos SET practica = ?, Entrada = ? WHERE HrEntrada=? AND Fecha=? AND Salon=?;");
            ps.setString(1,practica);
            ps.setString(2,hrEntradaClase);
            ps.setString(3,hrEntrada);
            ps.setString(4,fecha);
            ps.setString(5,salon);
            ps.execute();
            Notification notiApartado = Notification.show("Entrada registrada");
            notiApartado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            System.out.println(e);
            Notification notiNoApartado = Notification.show("Error");
            notiNoApartado.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }

    public static void salidaPrestamos(Connection con,int asistencia,String salida,String hrSalida,String fecha,String salon){

        try{
            PreparedStatement ps;
            ps=con.prepareStatement("UPDATE registroprestamos SET Asistencia = ?, Salida = ? WHERE HrSalida=? AND Fecha=? AND Salon=?;");
            ps.setInt(1,asistencia);
            ps.setString(2,salida);
            ps.setString(3,hrSalida);
            ps.setString(4,fecha);
            ps.setString(5,salon);
            ps.execute();
            Notification notiApartado = Notification.show("Salida registrada");
            notiApartado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            con.close();
        }catch(SQLException e){
            System.out.println(e);
            Notification notiNoApartado = Notification.show("Error");
            notiNoApartado.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }

    public static List<Apartado> gridRegistrarApartadoUno(Connection con,String dia,String horario,LocalDate fecha){
        List<Apartado> listApartado = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT S.Salon,H.Horario,concat(P.nombre,' ',P.paterno,' ',P.materno)\n" +
                    "FROM practicauno AS PRAC\n" +
                    "INNER JOIN materia as M ON M.idMateria=PRAC.idMateria\n" +
                    "INNER JOIN profesor as P ON P.idProfesor=M.idProfesor\n" +
                    "INNER JOIN salon AS S ON S.idSalon=M.idSalon\n" +
                    "INNER JOIN horario as H ON h.idHorario=PRAC."+dia+"\n" +
                    "WHERE NOT PRAC."+dia+"= 8 AND H.horario='"+horario+"';");
            while(registro.next()){
                Apartado gridApartadoRegistro= new Apartado(registro.getString(1),registro.getString(2),registro.getString(3));
                listApartado.add(gridApartadoRegistro);
            }
            registro = consulta.executeQuery("SELECT Salon, CONCAT(HrEntrada,'-',HrSalida),Profesor,Fecha\n" +
                    "FROM registroprestamos WHERE Fecha = '"+fecha+"'");
            while(registro.next()){
                Apartado gridApartado = new Apartado(registro.getString(1), registro.getString(2), registro.getString(3), registro.getString(4));
                listApartado.add(gridApartado);
            }
            con.close();
        }catch(SQLException e){

        }
        return listApartado;
    }

    public static List<Apartado> gridRegistrarApartadoDos(Connection con, String dia, String horario, LocalDate fecha){
        List<Apartado> listApartado = new ArrayList<>();
        try{
            Statement consulta = con.createStatement();
            ResultSet registro = consulta.executeQuery("SELECT S.Salon,H.Horario,concat(P.nombre,' ',P.paterno,' ',P.materno)\n" +
                    "FROM practicados AS PRAC\n" +
                    "INNER JOIN materia as M ON M.idMateria=PRAC.idMateria\n" +
                    "INNER JOIN profesor as P ON P.idProfesor=M.idProfesor\n" +
                    "INNER JOIN salon AS S ON S.idSalon=M.idSalon\n" +
                    "INNER JOIN horario as H ON h.idHorario=PRAC."+dia+"\n" +
                    "WHERE NOT PRAC."+dia+"= 8 AND H.horario='"+horario+"';");
            while(registro.next()){
                Apartado gridApartadoRegistro= new Apartado(registro.getString(1),registro.getString(2),registro.getString(3));
                listApartado.add(gridApartadoRegistro);
            }
            registro = consulta.executeQuery("SELECT Salon, CONCAT(HrEntrada,'-',HrSalida),Profesor,Fecha\n" +
                    "FROM registroprestamos WHERE Fecha = '"+fecha+"'");
            while(registro.next()){
                Apartado gridApartado = new Apartado(registro.getString(1), registro.getString(2), registro.getString(3), registro.getString(4));
                listApartado.add(gridApartado);
            }
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return listApartado;
    }

    public static void practicaPrestamoEntrada(Connection con,LocalDate date,String hrentrada,String salon){

        try {
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO  practicaprestamo(Fecha,HrEntrada,HrSalida,Materia,Profesor,Salon,Practica,Entrada)\n" +
                    "SELECT Fecha,HrEntrada,HrSalida,Materia,Profesor,Salon,Practica,Entrada FROM registroprestamos\n" +
                    "WHERE Fecha='"+date+"' AND HrEntrada=? AND Salon =?");
            ps.setString(1,hrentrada);
            ps.setString(2,salon);
            ps.execute();
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }

    }

    public static void practicaPrestamoSalida(Connection con,LocalDate date,String hrsalida,String salon,int asistencia,String salida){

        try {
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE practicaprestamo\n" +
                    "SET Asistencia=?,Salida=?\n" +
                    "WHERE Fecha='"+date+"' AND HrSalida=? AND Salon =?");
            ps.setInt(1,asistencia);
            ps.setString(2,salida);
            ps.setString(3,hrsalida);
            ps.setString(4,salon);
            ps.execute();
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }

    }

    public static void limpiarBD(Connection con){

        try{
            PreparedStatement ps;
            ps = con.prepareStatement("DELETE FROM practicauno");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM practicados");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM registroprestamos");
            ps.execute();
            ps = con.prepareStatement("DELETE FROM materia");
            ps.execute();
            con.close();
            Notification notiBD = Notification.show("BD limpia");
            notiBD.addThemeVariants(NotificationVariant.LUMO_SUCCESS,NotificationVariant.LUMO_PRIMARY);
        }catch(SQLException e){
            System.out.println(e);
            Notification notiError = Notification.show("Error");
            notiError.addThemeVariants(NotificationVariant.LUMO_ERROR,NotificationVariant.LUMO_PRIMARY);
        }

    }

}
