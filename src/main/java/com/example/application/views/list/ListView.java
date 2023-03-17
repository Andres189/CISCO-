package com.example.application.views.list;

import com.example.application.SAPDB;
import com.example.application.model.Apartado;
import com.example.application.model.Practica;
import com.example.application.model.Usuario;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.themes.LumoLightTheme;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.HighlightAction;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.aspectj.weaver.ast.Not;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@PageTitle("Login")
@Route(value = "")
public class ListView extends VerticalLayout {
Button btnEntrar = new Button("Entrar");
TextField txtfUsuario = new TextField("Usuario:");
PasswordField pswUsuario = new PasswordField("Contraseña:");
Boolean permisos=false;
Icon iconUsuario = new Icon("vaadin","user");
Icon iconPass = new Icon("vaadin","lock");
H2 headerMenu = new H2("Login");
Button btnPracticas = new Button("Practicas");
Button btnApartado = new Button("Apartado");
Button btnAgregarUsuario  = new Button("Usuarios");
Icon iconAgregarUsuario = new Icon("vaadin","user");
Icon iconPracticas = new Icon("vaadin","specialist");
Icon iconPrestamos = new Icon("vaadin","calendar-user");
ComboBox<String> comboDia = new ComboBox<>("Día:");
ComboBox<String> comboHorario = new ComboBox<>("Horario:");
Button btnBuscarGridPracticas = new Button("Buscar");
Icon iconBuscar = new Icon("lumo","reload");
Icon iconBuscarSalida = new Icon("lumo","reload");
TextField txtfProfesor = new TextField("Profesor:");
ComboBox<String> comboSalon = new ComboBox<>("Salon");
TextField txtfPractica = new TextField("Practica:");
TextField txtfHR_Entrada = new TextField("Hora entrada:");
Button btnRegistrarEntrada = new Button("Entrada");
Icon iconoEntradaPracticasc = new Icon("lumo","checkmark");
TextField txtfAlumnosPracticas = new TextField("Alumnos:");
ComboBox<String> comboSalonSalida = new ComboBox<>("Salon:");
TextField txtfProfesorSalida = new TextField("Profesor");
TextField txtfHR_Salida = new TextField("Hora salida:");
Button btnSalidaPracticas = new Button("Salida");
Icon iconSalidaPracticas = new Icon("vaadin","sign-out");

// Menu Usuarios
TextField txtUsuarioInsertar = new TextField("Usuarios:");
TextField txtContraseñaInsertar = new TextField("Contraseña:");
ComboBox<String> comboPermisosInsertar = new ComboBox<>("Permisos:");
Button btnRegistrarUsuario = new Button("Registrar");
TextField txtUsuarioBuscarModificar = new TextField("Usuario:");
TextField txtModificarUsuario = new TextField("Usuario nuevo:");
Button btnBuscarUsuario = new Button("Buscar");
TextField txtContraseñaModificar = new TextField("Contraseña nueva:");
ComboBox<String> comboPermisosModificar = new ComboBox<>("Modificar permisos:");
Button btnModificarUsuario = new Button("Modificar");
Button btnCanelarModificarUsuario = new Button("Cancelar");
TextField txtEliminarUsuario = new TextField("Usuario:");
Button btnEliminarUsuario = new Button("Eliminar");
Grid<Practica> grid = new Grid<>(Practica.class,false);
Grid<Practica> gridSalida = new Grid<>(Practica.class,false);
H2 headerEntradas = new H2("Entradas");
H2 headerSalida = new H2("Salidas");
//Practicas salidas
ComboBox<String> comboDiaSalida = new ComboBox<>("Dia:");
ComboBox<String> comboHorarioSalida = new ComboBox<>("Horario:");
Button btnSalidaGridBuscar = new Button("Buscar");
String diaEntrada;
String horarioEntrada;
String diaSalida;
String horarioSalida;
//Boton de cerrar sesion
Button btnCerrarSesion = new Button("Salir");
Icon iconoCerrarSesion = new Icon("vaadin","sign-out");
//Menu Apartado
DatePicker.DatePickerI18n fechaFormato = new DatePicker.DatePickerI18n();
DatePicker fechaApartado = new DatePicker("Fecha:");
DatePicker fechaApartadoBuscar = new DatePicker("Fecha:");
ComboBox<String> comboHorarioApartado = new ComboBox<>("Horario:");
Button btnBuscarApartado = new Button("Buscar");
Icon iconoBuscarApartado = new Icon("lumo","reload");
ComboBox<String> comboSalonesApartados = new ComboBox<>("Salon:");
TextField txtfProfesorApartado = new TextField("Profesor:");
TextField txtfMateriaApartado = new TextField("Materia:");
Button btnConfirmarApartado = new Button("Confirmar");
Button btnCancelarApartado = new Button("Cancelar");
Icon iconoConfirmarApartado = new Icon("vaadin","check");
Icon iconoCancelarApartado = new Icon("vaadin","close-circle");
H2 headerSalidaApartado = new H2("Salidas");
H2 headerEntradaApartado = new H2("Entradas");
DatePicker fechaEntradaApartado = new DatePicker("Fecha:");
ComboBox<String> comboHorarioEntradaApartado = new ComboBox<>("Horario:");
DatePicker fechaSalidaApartado = new DatePicker("Fecha:");
ComboBox<String> comboHorarioSalidaApartado = new ComboBox<>("Horario:");
Button btnBuscarEntradaApartado = new Button("Buscar");
Button btnBuscarSalidaApartado = new Button("Buscar");
ComboBox<String> comboSalonesEntradaApartado = new ComboBox<>("Salon:");
TextField txtfPracticaEntradaApartado = new TextField("Pactica:");
TextField txtfProfesorEntradaApartado = new TextField("Profesor:");
TextField txtfHoraEntradaApartado = new TextField("Hora entrada:");
Button btnEntradaApartado = new Button("Entrada");
ComboBox<String> comboSalonesSalidaApartado = new ComboBox<>("Salon:");
TextField txtfAlumnosSalidaApartado = new TextField("Alumnos:");
TextField txtfProfesorSalidaApartado = new TextField("Profesor:");
TextField txtfHoraSalidaApartado = new TextField("Hora salida:");
Button btnSalidaApartado = new Button("Salida");
Button btnEntradasySalidasApartado = new Button("Entradas y Salidas");
Icon iconoBuscarEntradaApartado = new Icon("lumo","reload");
Icon iconoBuscarSalidaApartado = new Icon("lumo","reload");
Icon iconoSalidaApartado = new Icon("vaadin","sign-out");
Icon iconoEntradaApartado = new Icon("lumo","checkmark");
Grid<Apartado>gridEntradaApartado = new Grid<>(Apartado.class,false);
Grid<Apartado>gridSalidaApartado = new Grid<>(Apartado.class,false);
Button btnRegistroApartado = new Button("Registro Apartado");
Icon iconoRegistroApartado = new Icon("vaadin","pencil");
TimePicker timeEntradaPrestamo = new TimePicker();
TimePicker timeSalidaPrestamo = new TimePicker();
TimePicker timeEntrada = new TimePicker();
TimePicker timeSalida = new TimePicker();
TextField txtfDiaApartado = new TextField("Día:");
ComboBox<String> comboDiaApartado = new ComboBox<>("Día:");
H3 headerBusquedaApartado = new H3("Busqueda");
H3 headerRegistroApartado = new H3("Registro");
Grid<Apartado> gridPrestamoRegistro = new Grid<>(Apartado.class,false);
String dateApartado;
String horarioApartado;
//Menu administracion
Button btnAdministracion = new Button("Administración");
Button btnDescargarPracticas = new Button("Descargar practicas");
Button btnDescargarApartados = new Button("Descargar apartados");
Icon iconoDescargarPracticas = new Icon("lumo","arrow-down");
Icon iconoMenuAdmin = new Icon("vaadin","sliders");
H3 headerDescargaPracticas = new H3("Descargar todas las tablas");
H3 headerDescargarindividaul = new H3("Descarga individual");
H3 headerLimpiarTablas = new H3("Limpiar tablas");
Button btnDescargaLunes = new Button("Lunes");
Button btnDescargaMartes = new Button("Martes");
Button btnDescargaMiercoles = new Button("Miercoles");
Button btnDescargaJueves = new Button("Jueves");
Button btnDescargaViernes = new Button("Viernes");
Button btnDescargaSabado = new Button("Sabado");
Button btnLimpiarTablas = new Button("Limpiar tablas");
Icon iconoLimpiarTablas = new Icon("vaadin","eraser");
String fechaApartadoEntrada;
String hrEntradaApartado;
String fechaApartadoSalida;
String hrSalidaApartado;
Button btnLimpiarEntradaPrestamo = new Button("Limpiar");


    public ListView() {
        addClassNames("Login");
        setSpacing(false);
        setSizeFull();
        fechaFormato.setDateFormat("dd-MM-yyyy");
        fechaApartado.setI18n(fechaFormato);
        fechaEntradaApartado.setI18n(fechaFormato);
        fechaApartadoBuscar.setI18n(fechaFormato);
        fechaSalidaApartado.setI18n(fechaFormato);

        //mostrar login
        menuLogin();
        //Evento boton ingresar
        btnEntrar.addClickListener(Click -> {

            if (SAPDB.Login(txtfUsuario.getValue(), pswUsuario.getValue(), SAPDB.Conexion())) {

                Notification dentro = Notification.show("Inicio de sesion exitoso");
                dentro.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                permisos = SAPDB.permisos(SAPDB.Conexion(), txtfUsuario.getValue(), pswUsuario.getValue());
                removeAll();
                menuBotonoes();

            } else {

                Notification error = Notification.show("Contraseña o usuario incorrectos");
                error.addThemeVariants(NotificationVariant.LUMO_ERROR);
                pswUsuario.clear();
                pswUsuario.focus();
            }
        });

        btnPracticas.addClickListener(Click -> {
            menuPracticas();
        });

        txtfPractica.addInputListener(inputEvent -> {
            LocalDateTime entrada = LocalDateTime.now();
            int hours = entrada.getHour();
            int minutes = entrada.getMinute();
            if (minutes < 10) {
                txtfHR_Entrada.setValue(String.valueOf(hours) + ":0" + String.valueOf(minutes));
            } else {
                txtfHR_Entrada.setValue(String.valueOf(hours) + ":" + String.valueOf(minutes));
            }

        });

        txtfAlumnosPracticas.addInputListener(inputEvent -> {
            LocalDateTime salida = LocalDateTime.now();
            int hours_s = salida.getHour();
            int minutes_s = salida.getMinute();
            if (minutes_s < 10) {
                txtfHR_Salida.setValue(String.valueOf(hours_s) + ":0" + String.valueOf(minutes_s));
            } else {
                txtfHR_Salida.setValue(String.valueOf(hours_s) + ":" + String.valueOf(minutes_s));
            }

        });

        btnAgregarUsuario.addClickListener(Click -> {
            if (permisos) {
                menuUsuarios();
            } else {
                Notification notiNoPermiso = Notification.show("Permisos insuficientes");
                notiNoPermiso.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        btnRegistrarUsuario.addClickListener(Click -> {
            if (txtUsuarioInsertar.isEmpty() || txtContraseñaInsertar.isEmpty() || comboPermisosInsertar.isEmpty()) {
                Notification errorInsertarUsuario = Notification.show("No se permiten campos vacios");
                errorInsertarUsuario.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } else {
                SAPDB.insertarUsuario(SAPDB.Conexion(), txtUsuarioInsertar.getValue(), txtContraseñaInsertar.getValue(), comboPermisosInsertar.getValue());
                Notification notiUsuarioInsertado = Notification.show("Usuario registrado");
                txtUsuarioInsertar.clear();
                txtContraseñaInsertar.clear();
                comboPermisosInsertar.clear();
                notiUsuarioInsertado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        btnEliminarUsuario.addClickListener(Click -> {
            SAPDB.eliminarUsuario(SAPDB.Conexion(), txtEliminarUsuario.getValue());
            txtEliminarUsuario.clear();
        });

        btnBuscarUsuario.addClickListener(Click -> {
            if (SAPDB.consultarUsuario(SAPDB.Conexion(), txtUsuarioBuscarModificar.getValue())) {

                txtModificarUsuario.setReadOnly(false);
                txtContraseñaModificar.setReadOnly(false);
                comboPermisosModificar.setReadOnly(false);
                txtUsuarioBuscarModificar.setReadOnly(true);
                txtModificarUsuario.setValue(txtUsuarioBuscarModificar.getValue());

            }
        });

        btnModificarUsuario.addClickListener(Click -> {
            if (txtContraseñaModificar.isEmpty() || txtModificarUsuario.isEmpty() || comboPermisosModificar.isEmpty()) {
                Notification notiCamposVaciosModificarUsuario = Notification.show("No puede haber campos vacios");
            } else {
                SAPDB.actualizarUsuario(SAPDB.Conexion(), txtModificarUsuario.getValue(), txtContraseñaModificar.getValue(), comboPermisosModificar.getValue(), txtUsuarioBuscarModificar.getValue());
                txtModificarUsuario.setReadOnly(true);
                txtContraseñaModificar.setReadOnly(true);
                comboPermisosModificar.setReadOnly(true);
                txtUsuarioBuscarModificar.setReadOnly(false);
                txtModificarUsuario.clear();
                txtContraseñaModificar.clear();
                comboPermisosModificar.clear();
                txtUsuarioBuscarModificar.clear();
            }
        });
        btnCanelarModificarUsuario.addClickListener(Click -> {
            txtModificarUsuario.setReadOnly(true);
            txtContraseñaModificar.setReadOnly(true);
            comboPermisosModificar.setReadOnly(true);
            txtUsuarioBuscarModificar.setReadOnly(false);
            txtModificarUsuario.clear();
            txtContraseñaModificar.clear();
            comboPermisosModificar.clear();
            txtUsuarioBuscarModificar.clear();
        });

        btnBuscarGridPracticas.addClickListener(Click -> {
            diaEntrada = comboDia.getValue();
            horarioEntrada = comboHorario.getValue();
            if (diaEntrada.equals("Lunes") || diaEntrada.equals("Martes") || diaEntrada.equals("Miercoles")) {
                grid.setItems(SAPDB.PracticasGrid(SAPDB.Conexion(), diaEntrada, horarioEntrada));
                comboSalon.setItems(SAPDB.comboSalon(SAPDB.Conexion(), diaEntrada, horarioEntrada));
            } else {
                grid.setItems(SAPDB.PracticasGridDos(SAPDB.Conexion(), diaEntrada, horarioEntrada));
                comboSalon.setItems(SAPDB.comboSalonDos(SAPDB.Conexion(), diaEntrada, horarioEntrada));
            }
            grid.setHeight("270px");
            comboSalon.focus();
        });

        btnSalidaGridBuscar.addClickListener(Click -> {
            diaSalida = comboDiaSalida.getValue();
            horarioSalida = comboHorarioSalida.getValue();
            if (diaSalida.equals("Lunes") || diaSalida.equals("Martes") || diaSalida.equals("Miercoles")) {
                gridSalida.setItems(SAPDB.PracticasGrid(SAPDB.Conexion(), diaSalida, horarioSalida));
                comboSalonSalida.setItems(SAPDB.comboSalon(SAPDB.Conexion(), diaSalida, horarioSalida));
            } else {
                gridSalida.setItems(SAPDB.PracticasGridDos(SAPDB.Conexion(), diaSalida, horarioSalida));
                comboSalonSalida.setItems(SAPDB.comboSalonDos(SAPDB.Conexion(), diaSalida, horarioSalida));
            }
            gridSalida.setHeight("270px");
            comboSalonSalida.focus();
        });

        comboSalonSalida.addValueChangeListener(inputEvent -> {
            txtfProfesorSalida.clear();
            txtfHR_Salida.clear();
            if (diaSalida.equals("Lunes") || diaSalida.equals("Martes") || diaSalida.equals("Miercoles")) {
                txtfProfesorSalida.setValue(SAPDB.profePractica(SAPDB.Conexion(), comboDiaSalida.getValue(), comboHorarioSalida.getValue(), comboSalonSalida.getValue()));
            } else {
                txtfProfesorSalida.setValue(SAPDB.profePracticaDos(SAPDB.Conexion(), comboDiaSalida.getValue(), comboHorarioSalida.getValue(), comboSalonSalida.getValue()));
            }

        });

        comboSalon.addValueChangeListener(inputEvent -> {
            txtfProfesor.clear();
            txtfPractica.clear();
            txtfHR_Entrada.clear();
            if (diaEntrada.equals("Lunes") || diaEntrada.equals("Martes") || diaEntrada.equals("Miercoles")) {
                txtfProfesor.setValue(SAPDB.profePractica(SAPDB.Conexion(), comboDia.getValue(), comboHorario.getValue(), comboSalon.getValue()));
            } else {
                txtfProfesor.setValue(SAPDB.profePracticaDos(SAPDB.Conexion(), comboDia.getValue(), comboHorario.getValue(), comboSalon.getValue()));
            }

        });

        btnRegistrarEntrada.addClickListener(Click -> {
            if (diaEntrada.equals("Lunes") || diaEntrada.equals("Martes") || diaEntrada.equals("Miercoles")) {
                SAPDB.insertarPractica(SAPDB.Conexion(), diaEntrada, horarioEntrada, comboSalon.getValue(), txtfPractica.getValue(), txtfHR_Entrada.getValue());
                grid.setItems(SAPDB.PracticasGrid(SAPDB.Conexion(), comboDia.getValue(), comboHorario.getValue()));
                SAPDB.tablaPractica(SAPDB.Conexion(), diaEntrada, horarioEntrada, comboSalon.getValue());
            } else {
                SAPDB.insertarPracticaDos(SAPDB.Conexion(), diaEntrada, horarioEntrada, comboSalon.getValue(), txtfPractica.getValue(), txtfHR_Entrada.getValue());
                grid.setItems(SAPDB.PracticasGridDos(SAPDB.Conexion(), comboDia.getValue(), comboHorario.getValue()));
                SAPDB.tablaPracticaDos(SAPDB.Conexion(), diaEntrada, horarioEntrada, comboSalon.getValue());
            }
            txtfPractica.clear();
            txtfProfesor.clear();
            comboSalon.clear();
            txtfHR_Entrada.clear();
            comboSalon.focus();

        });

        btnSalidaPracticas.addClickListener(Click -> {


            if (diaSalida.equals("Lunes") || diaSalida.equals("Martes") || diaSalida.equals("Miercoles")) {
                SAPDB.salidaPracticas(SAPDB.Conexion(), diaSalida, horarioSalida, comboSalonSalida.getValue(), Integer.parseInt(txtfAlumnosPracticas.getValue()), txtfHR_Salida.getValue());
                gridSalida.setItems(SAPDB.PracticasGrid(SAPDB.Conexion(), diaSalida, horarioSalida));

            } else {
                SAPDB.salidaPracticasDos(SAPDB.Conexion(), diaSalida, horarioSalida, comboSalonSalida.getValue(), Integer.parseInt(txtfAlumnosPracticas.getValue()), txtfHR_Salida.getValue());
                gridSalida.setItems(SAPDB.PracticasGridDos(SAPDB.Conexion(), diaSalida, horarioSalida));
            }
            SAPDB.registroSalidas(SAPDB.Conexion(), diaSalida, horarioSalida, comboSalonSalida.getValue(), Integer.parseInt(txtfAlumnosPracticas.getValue()), txtfHR_Salida.getValue());
            txtfAlumnosPracticas.clear();
            txtfProfesorSalida.clear();
            txtfHR_Salida.clear();
            comboSalonSalida.clear();
            comboSalonSalida.focus();

        });

        btnCerrarSesion.addClickListener(Click -> {

            VaadinSession.getCurrent().close();

        });

        btnApartado.addClickListener(Click -> {

            menuEntradasySalidasApartado();
        });

        btnAdministracion.addClickListener(Click -> {
            if (permisos) {
                menuAdmin();
            } else {
                Notification notiNoPermisos = Notification.show("Permisos insuficientes");
                notiNoPermisos.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }


        });

        btnDescargarPracticas.addClickListener(Click -> {
            try {
                SAPDB.descargarLunes(SAPDB.Conexion());
                SAPDB.descargarMartes(SAPDB.Conexion());
                SAPDB.descargarMiercoles(SAPDB.Conexion());
                SAPDB.descargarJueves(SAPDB.Conexion());
                SAPDB.descargarViernes(SAPDB.Conexion());
                SAPDB.descargarSabado(SAPDB.Conexion());
                Notification notiDescargaLunes = Notification.show("Tablas descargadas");
                notiDescargaLunes.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargaLunes.addClickListener(Click -> {
            try {
                SAPDB.descargarLunes(SAPDB.Conexion());
                Notification notiDescargaLunes = Notification.show("Tabla descargada");
                notiDescargaLunes.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargaMartes.addClickListener(Click -> {
            try {
                SAPDB.descargarMartes(SAPDB.Conexion());
                Notification notiDescargaMartes = Notification.show("Tabla descargada");
                notiDescargaMartes.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargaMiercoles.addClickListener(Click -> {
            try {
                SAPDB.descargarMiercoles(SAPDB.Conexion());
                Notification notiDescargaMiercoles = Notification.show("Tabla descargada");
                notiDescargaMiercoles.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargaJueves.addClickListener(Click -> {
            try {
                SAPDB.descargarJueves(SAPDB.Conexion());
                Notification notiDescargaJueves = Notification.show("Tabla descargada");
                notiDescargaJueves.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargaViernes.addClickListener(Click -> {
            try {
                Notification notiDescargaViernes = Notification.show("Tabla descargada");
                notiDescargaViernes.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                SAPDB.descargarViernes(SAPDB.Conexion());
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargaSabado.addClickListener(Click -> {
            try {
                SAPDB.descargarSabado(SAPDB.Conexion());
                Notification notiDescargaSabado = Notification.show("Tabla descargada");
                notiDescargaSabado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        btnDescargarApartados.addClickListener(Click -> {
            try{
                SAPDB.descargaPrestamo(SAPDB.Conexion());
                Notification notiPrestamo = Notification.show("Prestamo descargado");
                notiPrestamo.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }catch(Exception e){
                System.out.println(e);
            }
        });

        btnLimpiarTablas.addClickListener(Click -> {
            SAPDB.limpiarTablas(SAPDB.Conexion());
        });

        comboDia.addValueChangeListener(inputEvent -> {
            comboHorario.clear();
            if (comboDia.getValue().equals("Lunes") || comboDia.getValue().equals("Martes") || comboDia.getValue().equals("Miercoles")) {
                comboHorario.setItems(SAPDB.horarioUno(SAPDB.Conexion(), comboDia.getValue()));
            } else {
                comboHorario.setItems(SAPDB.horarioDos(SAPDB.Conexion(), comboDia.getValue()));
            }

        });

        comboDiaSalida.addValueChangeListener(inputEvent -> {
            comboHorarioSalida.clear();
            if (comboDiaSalida.getValue().equals("Lunes") || comboDiaSalida.getValue().equals("Martes") || comboDiaSalida.getValue().equals("Miercoles")) {
                comboHorarioSalida.setItems(SAPDB.horarioUno(SAPDB.Conexion(), comboDiaSalida.getValue()));
            } else {
                comboHorarioSalida.setItems(SAPDB.horarioDos(SAPDB.Conexion(), comboDiaSalida.getValue()));
            }
        });


        btnEntradasySalidasApartado.addClickListener(Click -> {

            menuEntradasySalidasApartado();
        });

        btnRegistroApartado.addClickListener(Click -> {

            menuApartados();
        });

        fechaApartado.addValueChangeListener(inputEvent -> {
            dateApartado = fechaApartado.getValue().toString();

        });

        comboHorarioApartado.addValueChangeListener(inputEvent -> {
            horarioApartado = comboHorarioApartado.getValue();
        });

        txtfDiaApartado.addValueChangeListener(inputEvent -> {
            comboHorarioApartado.clear();
            if (txtfDiaApartado.getValue().equals("Lunes") || txtfDiaApartado.getValue().equals("Martes") || txtfDiaApartado.getValue().equals("Miercoles")) {
                comboHorarioApartado.setItems(SAPDB.horarioUno(SAPDB.Conexion(), txtfDiaApartado.getValue()));
            } else {
                comboHorarioApartado.setItems(SAPDB.horarioDos(SAPDB.Conexion(), txtfDiaApartado.getValue()));
            }
        });
        timeEntradaPrestamo.addValueChangeListener(inputEvent -> {
            timeSalidaPrestamo.setMin(timeEntradaPrestamo.getValue());
        });

        btnConfirmarApartado.addClickListener(Click -> {

            String fecha = fechaApartado.getValue().toString();

            if(fechaApartado.isEmpty()||timeEntradaPrestamo.isEmpty()||timeSalidaPrestamo.isEmpty()||txtfMateriaApartado.isEmpty()||txtfProfesorApartado.isEmpty()||comboSalonesApartados.isEmpty()){
                Notification notiCamposVaciosPrestamo = Notification.show("No se permiten campos vacios");
                notiCamposVaciosPrestamo.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }else {
                SAPDB.registrarPrestamo(SAPDB.Conexion(),fechaApartado.getValue().toString(), timeEntradaPrestamo.getValue().toString(),timeSalidaPrestamo.getValue().toString(), txtfMateriaApartado.getValue().toUpperCase(), txtfProfesorApartado.getValue().toUpperCase(), comboSalonesApartados.getValue());
                comboSalonesApartados.clear();
                txtfProfesorApartado.clear();
                txtfMateriaApartado.clear();
                fechaApartado.clear();
                timeEntrada.clear();
                timeSalida.clear();
            }
        });

        btnBuscarEntradaApartado.addClickListener(Click -> {
            gridEntradaApartado.setItems(SAPDB.gridPrestamosEntrada(SAPDB.Conexion(),fechaEntradaApartado.getValue().toString(),timeEntrada.getValue().toString()));
            comboSalonesEntradaApartado.setItems(SAPDB.comboSalonApartadoEntrada(SAPDB.Conexion(),fechaEntradaApartado.getValue().toString(),timeEntrada.getValue().toString()));
            fechaApartadoEntrada = fechaEntradaApartado.getValue().toString();
            hrEntradaApartado = timeEntrada.getValue().toString();
        });

        btnBuscarSalidaApartado.addClickListener(Click ->{
            gridSalidaApartado.setItems(SAPDB.gridPrestamoSalida(SAPDB.Conexion(),fechaSalidaApartado.getValue().toString(),timeSalida.getValue().toString()));
            comboSalonesSalidaApartado.setItems(SAPDB.comboSalonApartadoSalida(SAPDB.Conexion(),fechaSalidaApartado.getValue().toString(),timeSalida.getValue().toString()));
            fechaApartadoSalida = fechaSalidaApartado.getValue().toString();
            hrSalidaApartado = timeSalida.getValue().toString();
        });

        fechaApartadoBuscar.addValueChangeListener(inputEvent ->{
            txtfDiaApartado.setValue(SAPDB.getDiaSemana(Date.valueOf(fechaApartadoBuscar.getValue())));
        });

        comboSalonesEntradaApartado.addValueChangeListener(inputEvent -> {
            txtfProfesorEntradaApartado.setValue(SAPDB.profesorEntradaApartados(SAPDB.Conexion(),fechaApartadoEntrada,hrEntradaApartado,comboSalonesEntradaApartado.getValue()));
        });

        comboSalonesSalidaApartado.addValueChangeListener(inputEvent -> {
           txtfProfesorSalidaApartado.setValue(SAPDB.profesorSalidaApartados(SAPDB.Conexion(),fechaApartadoSalida,hrSalidaApartado,comboSalonesSalidaApartado.getValue()));
        });

        txtfPracticaEntradaApartado.addInputListener(inputEvent ->{
            LocalDateTime entradaPrestamo = LocalDateTime.now();
            int hoursEntradaPrestamo = entradaPrestamo.getHour();
            int minutesEntradaPrestamo = entradaPrestamo.getMinute();
            if (minutesEntradaPrestamo < 10) {
                txtfHoraEntradaApartado.setValue((hoursEntradaPrestamo) + ":0" +(minutesEntradaPrestamo));
            } else {
                txtfHoraEntradaApartado.setValue((hoursEntradaPrestamo) + ":" +(minutesEntradaPrestamo));
            }
        });

        txtfAlumnosSalidaApartado.addInputListener(inputEvent -> {
            LocalDateTime salidaPrestamo = LocalDateTime.now();
            int hoursSalidaPrestamo = salidaPrestamo.getHour();
            int minutesSalidaPrestamo = salidaPrestamo.getMinute();
            if (minutesSalidaPrestamo < 10) {
                txtfHoraSalidaApartado.setValue((hoursSalidaPrestamo) + ":0" +(minutesSalidaPrestamo));
            } else {
                txtfHoraSalidaApartado.setValue((hoursSalidaPrestamo) + ":" +(minutesSalidaPrestamo));
            }
        });

        btnLimpiarEntradaPrestamo.addClickListener(Click ->{

            txtfPracticaEntradaApartado.setValue("");
            txtfProfesorEntradaApartado.setValue("");
            txtfHoraEntradaApartado.setValue("");
            txtfAlumnosSalidaApartado.clear();
            txtfProfesorSalidaApartado.clear();
            txtfHoraSalidaApartado.clear();
            comboSalonesSalidaApartado.setValue("");
            comboSalonesEntradaApartado.setValue("");

        });

        btnEntradaApartado.addClickListener(Click -> {

            SAPDB.entradaPrestamos(SAPDB.Conexion(),fechaApartadoEntrada,hrEntradaApartado,comboSalonesEntradaApartado.getValue(),txtfPracticaEntradaApartado.getValue(),txtfHoraEntradaApartado.getValue());
            SAPDB.practicaPrestamoEntrada(SAPDB.Conexion(),fechaEntradaApartado.getValue(),timeEntrada.getValue().toString(),comboSalonesEntradaApartado.getValue());
            gridEntradaApartado.setItems(SAPDB.gridPrestamosEntrada(SAPDB.Conexion(),fechaApartadoEntrada,hrEntradaApartado));
            txtfPracticaEntradaApartado.clear();
            txtfProfesorEntradaApartado.clear();
            txtfHoraEntradaApartado.clear();
            comboSalonesEntradaApartado.focus();
            comboSalonesEntradaApartado.clear();

        });

        btnSalidaApartado.addClickListener(Click ->{
            SAPDB.salidaPrestamos(SAPDB.Conexion(),Integer.parseInt(txtfAlumnosSalidaApartado.getValue()),txtfHoraSalidaApartado.getValue(),hrSalidaApartado,fechaApartadoSalida,comboSalonesSalidaApartado.getValue());
            gridSalidaApartado.setItems(SAPDB.gridPrestamoSalida(SAPDB.Conexion(),fechaApartadoSalida,hrSalidaApartado));
            SAPDB.practicaPrestamoSalida(SAPDB.Conexion(),fechaSalidaApartado.getValue(),timeSalida.getValue().toString(),comboSalonesSalidaApartado.getValue(),Integer.parseInt(txtfAlumnosSalidaApartado.getValue()),txtfHoraSalidaApartado.getValue());
            txtfAlumnosSalidaApartado.clear();
            txtfProfesorSalidaApartado.clear();
            txtfHoraSalidaApartado.clear();
            comboSalonesSalidaApartado.focus();
            comboSalonesSalidaApartado.clear();


        });

        btnBuscarApartado.addClickListener(Click -> {
           if(txtfDiaApartado.getValue().equals("Lunes")||txtfDiaApartado.getValue().equals("Martes")||txtfDiaApartado.getValue().equals("Miercoles")){
               gridPrestamoRegistro.setItems(SAPDB.gridRegistrarApartadoUno(SAPDB.Conexion(),txtfDiaApartado.getValue(),comboHorarioApartado.getValue(),fechaApartadoBuscar.getValue()));
           }else{
               gridPrestamoRegistro.setItems(SAPDB.gridRegistrarApartadoDos(SAPDB.Conexion(), txtfDiaApartado.getValue(), comboHorarioApartado.getValue(),fechaApartadoBuscar.getValue()));
           }
           });


    }

    private void menuLogin(){
        //Imagen login
        Image img = new Image("images/login.png", "login");
        img.setWidth("150px");
        //Acomodar titulo login
        headerMenu.addClassNames(Margin.Top.XLARGE,Margin.Bottom.MEDIUM);
        //Layout login
        VerticalLayout layoutMenu = new VerticalLayout(txtfUsuario,pswUsuario,btnEntrar);
        //Iconos textfield y pswfield
        txtfUsuario.setPrefixComponent(iconUsuario);
        pswUsuario.setPrefixComponent(iconPass);
        //shortcut entrar
        btnEntrar.addClickShortcut(Key.ENTER);
        //agregar elementos
        add(
                headerMenu,
                img,
                layoutMenu
        );
        //Ordenar componentes
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        layoutMenu.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }


    private void menuBotonoes(){
        //imagen unitec
        Image img = new Image("images/logo_unitec.png", "logoUnitec");
        img.setWidth("200px");
        //Creacion de layout para los botones practicas y apartado
        HorizontalLayout layoutBotones = new HorizontalLayout(btnPracticas,btnApartado,btnRegistroApartado,btnAgregarUsuario,btnAdministracion,btnCerrarSesion);
        //
        setSpacing(true);
        //Botones estilo
        btnAgregarUsuario.setIcon(iconAgregarUsuario);
        btnApartado.setIcon(iconPrestamos);
        btnPracticas.setIcon(iconPracticas);
        btnCerrarSesion.setIcon(iconoCerrarSesion);
        btnAdministracion.setIcon(iconoMenuAdmin);
        btnRegistroApartado.setIcon(iconoRegistroApartado);
        btnAgregarUsuario.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnApartado.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnPracticas.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnCerrarSesion.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);
        btnAdministracion.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnRegistroApartado.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        //agregar componentes
        add(
                img,
                layoutBotones

        );
        //Orden de botones de Practicas y Apartado
        setJustifyContentMode(JustifyContentMode.START);
        setMargin(true);

    }

    private void menuPracticas(){
        setSpacing(false);
        removeAll();
        menuBotonoes();
        grid.removeAllColumns();
        gridSalida.removeAllColumns();
        //layout vertical para las practicas
        HorizontalLayout layout1 = new HorizontalLayout(comboDia,comboHorario,btnBuscarGridPracticas);
        HorizontalLayout layout2 = new HorizontalLayout(comboSalon,txtfPractica,txtfProfesor,txtfHR_Entrada,btnRegistrarEntrada);
        HorizontalLayout layout3 = new HorizontalLayout(comboDiaSalida,comboHorarioSalida,btnSalidaGridBuscar);
        HorizontalLayout layout4 = new HorizontalLayout(comboSalonSalida,txtfAlumnosPracticas,txtfProfesorSalida,txtfHR_Salida,btnSalidaPracticas);
        //Cambiando tamaño de los componentes
        comboDia.setWidth("130px");
        comboHorario.setWidth("170px");
        comboSalon.setWidth("150px");
        txtfProfesor.setWidth("350px");
        txtfPractica.setWidth("200px");
        txtfHR_Entrada.setWidth("110px");
        txtfHR_Salida.setWidth("100px");
        txtfAlumnosPracticas.setWidth("90px");
        txtfProfesorSalida.setWidth("350px");
        comboSalonSalida.setWidth("150px");
        comboDiaSalida.setWidth("130px");
        grid.setHeight("270px");
        gridSalida.setHeight("270px");
        //Permisis de escritura
        txtfHR_Entrada.isReadOnly();
        //estilo botones
        btnRegistrarEntrada.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);
        btnRegistrarEntrada.setIcon(iconoEntradaPracticasc);
        btnSalidaPracticas.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);
        btnSalidaPracticas.setIcon(iconSalidaPracticas);
        btnBuscarGridPracticas.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        btnBuscarGridPracticas.setIcon(iconBuscar);
        btnSalidaGridBuscar.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        btnSalidaGridBuscar.setIcon(iconBuscarSalida);
        //Aliniar componentes
        layout1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layout2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layout3.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layout4.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layout1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        //quitar permiso de escritura
        txtfHR_Entrada.setReadOnly(true);
        txtfHR_Salida.setReadOnly(true);
        txtfProfesor.setReadOnly(true);
        txtfProfesorSalida.setReadOnly(true);
        //Opciones de combobox
        comboDia.setItems("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
        comboDiaSalida.setItems("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");

        grid.addColumn(Practica::getHorario).setHeader("Horario").setAutoWidth(true);
        grid.addColumn(Practica::getProfesor).setHeader("Profesor").setAutoWidth(true);
        grid.addColumn(Practica::getMateria).setHeader("Materia").setAutoWidth(true);
        grid.addColumn(Practica::getSalon).setHeader("Salon").setAutoWidth(true);
        grid.addColumn(Practica::getPractica).setHeader("Practica").setAutoWidth(true);
        grid.addColumn(Practica::getAlumnosInscritos).setHeader("Inscritos").setAutoWidth(true);
        grid.addColumn(Practica::getHrEntrada).setHeader("Entrada").setAutoWidth(true);
        grid.addColumn(Practica::getAsistencia).setHeader("Asistencia").setAutoWidth(true);
        grid.addColumn(Practica::getHrSalida).setHeader("Salida").setAutoWidth(true);
        gridSalida.addColumn(Practica::getHorario).setHeader("Horario").setAutoWidth(true);
        gridSalida.addColumn(Practica::getProfesor).setHeader("Profesor").setAutoWidth(true);
        gridSalida.addColumn(Practica::getMateria).setHeader("Materia").setAutoWidth(true);
        gridSalida.addColumn(Practica::getSalon).setHeader("Salon").setAutoWidth(true);
        gridSalida.addColumn(Practica::getPractica).setHeader("Practica").setAutoWidth(true);
        gridSalida.addColumn(Practica::getAlumnosInscritos).setHeader("Inscritos").setAutoWidth(true);
        gridSalida.addColumn(Practica::getHrEntrada).setHeader("Entrada").setAutoWidth(true);
        gridSalida.addColumn(Practica::getAsistencia).setHeader("Asistencia").setAutoWidth(true);
        gridSalida.addColumn(Practica::getHrSalida).setHeader("Salida").setAutoWidth(true);
        //Scroll
        Div divPracticas = new Div(headerEntradas,layout1,layout2,grid,headerSalida,layout3,layout4,gridSalida);
        divPracticas.setSizeFull();
        Scroller scroll = new Scroller(divPracticas);
        scroll.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroll.setSizeFull();
        add(
                scroll
        );
    }

    private void menuUsuarios(){
        removeAll();
        menuBotonoes();
        grid.removeAllColumns();
        //Layout horizontales de Menu Usuarios
        HorizontalLayout layoutInsertar = new HorizontalLayout(txtUsuarioInsertar,txtContraseñaInsertar,comboPermisosInsertar,btnRegistrarUsuario);
        HorizontalLayout layoutModificar = new HorizontalLayout(txtUsuarioBuscarModificar,btnBuscarUsuario,txtModificarUsuario,txtContraseñaModificar,comboPermisosModificar,btnModificarUsuario,btnCanelarModificarUsuario);
        HorizontalLayout layoutEliminar = new HorizontalLayout(txtEliminarUsuario,btnEliminarUsuario);
        //Permisos de escritura
        txtModificarUsuario.setReadOnly(true);
        txtContraseñaModificar.setReadOnly(true);
        comboPermisosModificar.setReadOnly(true);
        //estilo botones
        btnRegistrarUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        btnBuscarUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        btnModificarUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnEliminarUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);
        btnCanelarModificarUsuario.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);
        //Opciones combo
        comboPermisosInsertar.setItems("ESTANDAR","ADMIN");
        comboPermisosModificar.setItems("ESTANDAR","ADMIN");
        //Alinear layout
        layoutInsertar.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layoutModificar.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layoutEliminar.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        //Agregar componentes
        add(
                layoutInsertar,
                layoutModificar,
                layoutEliminar
        );
    }
    private void menuApartados(){
        removeAll();
        menuBotonoes();
        gridPrestamoRegistro.removeAllColumns();
        //Layout horizontales del menu Apartado
        HorizontalLayout layoutApartado0 = new HorizontalLayout(headerBusquedaApartado);
        HorizontalLayout layoutApartado1 = new HorizontalLayout(fechaApartadoBuscar,txtfDiaApartado,comboHorarioApartado,btnBuscarApartado);
        HorizontalLayout layoutApartado2 = new HorizontalLayout(headerRegistroApartado);
        HorizontalLayout layoutApartado3 = new HorizontalLayout(fechaApartado,timeEntradaPrestamo,timeSalidaPrestamo);
        HorizontalLayout layoutApartado4 = new HorizontalLayout(comboSalonesApartados,txtfProfesorApartado,txtfMateriaApartado,btnConfirmarApartado);
        //Alinear layouts
        layoutApartado1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layoutApartado4.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        timeEntradaPrestamo.setLabel("Entrada");
        timeSalidaPrestamo.setLabel("Salida");
        timeEntradaPrestamo.setStep(Duration.ofMinutes(30));
        timeSalidaPrestamo.setStep(Duration.ofMinutes(30));
        timeEntradaPrestamo.setMin(LocalTime.of(7,0));
        timeEntradaPrestamo.setMax(LocalTime.of(21,30));
        timeSalidaPrestamo.setMin(LocalTime.of(7,30));
        timeSalidaPrestamo.setMax(LocalTime.of(22,0));
        //Estilo de botones
        btnConfirmarApartado.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);
        btnConfirmarApartado.setIcon(iconoConfirmarApartado);
        btnCancelarApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);
        btnCancelarApartado.setIcon(iconoCancelarApartado);
        btnBuscarApartado.setIcon(iconoBuscarApartado);
        btnBuscarApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        //Tamañano de los componentes
        txtfProfesorApartado.setWidth("350px");
        comboSalonesApartados.setWidth("150px");
        fechaApartado.setWidth("150px");
        comboHorarioApartado.setWidth("170px");
        //Opciones de combo
        comboSalonesApartados.setItems(SAPDB.salonesPrestamos(SAPDB.Conexion()));
        //comboDiaApartado.setItems("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
        //comboHorarioApartado.setItems("7:00-8:59","9:00-10:59","11:00-12:59","13:00-14:59","16:00-17:59","18:00-19:59","20:00-21:59");
        txtfDiaApartado.setWidth("130px");
        txtfDiaApartado.setReadOnly(true);
        //
        gridPrestamoRegistro.addColumn(Apartado::getSalon).setAutoWidth(true).setHeader("Salon");
        gridPrestamoRegistro.addColumn(Apartado::getHorario).setAutoWidth(true).setHeader("Horario");
        gridPrestamoRegistro.addColumn(Apartado::getProfesor).setAutoWidth(true).setHeader("Profesor");
        gridPrestamoRegistro.addColumn(Apartado::getFecha).setAutoWidth(true).setHeader("Fecha");
        //Scroll
        Div divPrestamoRegistro = new Div(layoutApartado0,layoutApartado1,layoutApartado2,layoutApartado3,layoutApartado4,gridPrestamoRegistro);
        divPrestamoRegistro.setSizeFull();
        Scroller scroll = new Scroller(divPrestamoRegistro);
        scroll.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroll.setSizeFull();
        //Agregar componentes
        add(
                scroll
        );
    }

    private void menuEntradasySalidasApartado(){
        removeAll();
        menuBotonoes();
        //menuApartados();
        gridEntradaApartado.removeAllColumns();
        gridSalidaApartado.removeAllColumns();
        //Layouts horizontales de menu entradas y salidas de apartado
        HorizontalLayout layoutEntradaApartado1 = new HorizontalLayout(fechaEntradaApartado,timeEntrada,btnBuscarEntradaApartado);
        HorizontalLayout layoutEntradaApartado2 = new HorizontalLayout(comboSalonesEntradaApartado,txtfPracticaEntradaApartado,txtfProfesorEntradaApartado,txtfHoraEntradaApartado,btnEntradaApartado,btnLimpiarEntradaPrestamo);
        HorizontalLayout layoutSalidaApartado1 = new HorizontalLayout(fechaSalidaApartado,timeSalida,btnBuscarSalidaApartado);
        HorizontalLayout layoutSalidaApartado2 = new HorizontalLayout(comboSalonesSalidaApartado,txtfAlumnosSalidaApartado,txtfProfesorSalidaApartado,txtfHoraSalidaApartado,btnSalidaApartado);
        //time
        timeEntrada.setWidth("100px");
        timeSalida.setWidth("100px");
        txtfAlumnosSalidaApartado.setWidth("100px");
        timeEntrada.setLabel("Entrada");
        timeSalida.setLabel("Salida");
        timeEntrada.setStep(Duration.ofMinutes(30));
        timeSalida.setStep(Duration.ofMinutes(30));
        timeEntrada.setMin(LocalTime.of(7,0));
        timeEntrada.setMax(LocalTime.of(21,30));
        timeSalida.setMin(LocalTime.of(7,30));
        timeSalida.setMax(LocalTime.of(22,0));
        btnLimpiarEntradaPrestamo.addClickShortcut(Key.ESCAPE);
        //Alinear layouts
        layoutEntradaApartado1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layoutEntradaApartado2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layoutSalidaApartado1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layoutSalidaApartado2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        //Permisos de escritura
        txtfProfesorEntradaApartado.setReadOnly(true);
        txtfHoraEntradaApartado.setReadOnly(true);
        txtfProfesorSalidaApartado.setReadOnly(true);
        txtfHoraSalidaApartado.setReadOnly(true);
        //Opciones combo
        comboHorarioEntradaApartado.setItems("7:00-8:59","9:00-10:59","11:00-12:59","13:00-14:59","16:00-17:59","18:00-19:59","20:00-21:59");
        comboHorarioEntradaApartado.setValue("7:00-8:59");
        comboHorarioSalidaApartado.setItems("7:00-8:59","9:00-10:59","11:00-12:59","13:00-14:59","16:00-17:59","18:00-19:59","20:00-21:59");
        comboHorarioSalidaApartado.setValue("7:00-8:59");
        //Tamaño de los componentes
        fechaEntradaApartado.setWidth("130px");
        fechaSalidaApartado.setWidth("130px");
        gridEntradaApartado.setHeight("270px");
        gridSalidaApartado.setHeight("270px");
        txtfHoraEntradaApartado.setWidth("110px");
        txtfHoraSalidaApartado.setWidth("110px");
        txtfProfesorEntradaApartado.setWidth("350px");
        txtfProfesorSalidaApartado.setWidth("350px");
        comboSalonesEntradaApartado.setWidth("150px");
        comboSalonesSalidaApartado.setWidth("150px");
        //Estilo
        btnBuscarEntradaApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        btnBuscarEntradaApartado.setIcon(iconoBuscarEntradaApartado);
        btnBuscarSalidaApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        btnBuscarSalidaApartado.setIcon(iconoBuscarSalidaApartado);
        btnEntradaApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);
        btnEntradaApartado.setIcon(iconoEntradaApartado);
        btnSalidaApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);
        btnSalidaApartado.setIcon(iconoSalidaApartado);
        btnLimpiarEntradaPrestamo.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        //Grid entrada apartado
        gridEntradaApartado.addColumn(Apartado::getFecha).setHeader("Fecha").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getHorario).setHeader("Horario").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getProfesor).setHeader("Profesor").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getMateria).setHeader("Materia").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getSalon).setHeader("Salon").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getPractica).setHeader("Practica").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getHoraEntrada).setHeader("Entrada").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getAsistencia).setHeader("Asistencia").setAutoWidth(true);
        gridEntradaApartado.addColumn(Apartado::getHoraSalida).setHeader("Salida").setAutoWidth(true);
        //Grid Salida apartado
        gridSalidaApartado.addColumn(Apartado::getFecha).setHeader("Fecha").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getHorario).setHeader("Horario").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getProfesor).setHeader("Profesor").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getMateria).setHeader("Materia").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getSalon).setHeader("Salon").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getPractica).setHeader("Practica").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getHoraEntrada).setHeader("Entrada").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getAsistencia).setHeader("Asistencia").setAutoWidth(true);
        gridSalidaApartado.addColumn(Apartado::getHoraSalida).setHeader("Salida").setAutoWidth(true);
        //Scroll
        Div divApartado = new Div(headerEntradaApartado,layoutEntradaApartado1,layoutEntradaApartado2,gridEntradaApartado,headerSalidaApartado,layoutSalidaApartado1,layoutSalidaApartado2,gridSalidaApartado);
        divApartado.setSizeFull();
        Scroller scrollApartado = new Scroller(divApartado);
        scrollApartado.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scrollApartado.setSizeFull();

        //Agregar componentes
        add(
                scrollApartado
        );
    }
    private void menuAdmin(){
        removeAll();
        menuBotonoes();
        //Layouts
        HorizontalLayout H1 = new HorizontalLayout(headerDescargaPracticas);
        HorizontalLayout H2 = new HorizontalLayout(btnDescargarPracticas,btnDescargarApartados);
        HorizontalLayout H3 = new HorizontalLayout(headerDescargarindividaul);
        HorizontalLayout H4 = new HorizontalLayout(btnDescargaLunes,btnDescargaMartes,btnDescargaMiercoles,btnDescargaJueves,btnDescargaViernes,btnDescargaSabado);
        HorizontalLayout H5 = new HorizontalLayout(headerLimpiarTablas);
        //Botones
        btnDescargarPracticas.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargaLunes.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargaMartes.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargaMiercoles.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargaJueves.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargaViernes.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargaSabado.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnLimpiarTablas.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnDescargarApartados.addThemeVariants(ButtonVariant.LUMO_CONTRAST,ButtonVariant.LUMO_PRIMARY);
        btnLimpiarTablas.setIcon(iconoLimpiarTablas);
        add(H1,H2,H3,H4,H5,btnLimpiarTablas);
    }
}
