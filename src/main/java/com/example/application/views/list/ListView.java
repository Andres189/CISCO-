package com.example.application.views.list;

import com.example.application.SAPDB;
import com.example.application.model.Practica;
import com.example.application.model.Usuario;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.themes.LumoLightTheme;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@PageTitle("Login")
@Route(value = "")
public class ListView extends VerticalLayout {
Button btnEntrar = new Button("Entrar");
TextField txtfUsuario = new TextField("Usuario:");
PasswordField pswUsuario = new PasswordField("Contraseña:");
Icon iconUsuario = new Icon("vaadin","user");
Icon iconPass = new Icon("vaadin","lock");
H2 headerMenu = new H2("Login");
Button btnPracticas = new Button("Practicas");
Button btnApartado = new Button("Apartado");
Button btnAgregarUsuario  = new Button("Usuarios");
Icon iconAgregarUsuario = new Icon("vaadin","user");
Icon iconPracticas = new Icon("vaadin","specialist");
Icon iconPrestamos = new Icon("vaadin","calendar-user");
H2 headerPraticasEntradas = new H2("ENTRADAS");
H2 headerPraticasSalidas = new H2("SALIDAS");
ComboBox<String> comboDia = new ComboBox<>("Día:");
ComboBox<String> comboHorario = new ComboBox<>("Horario:");
Button btnBuscarGridPracticas = new Button("Buscar");
Icon iconBuscar = new Icon("lumo","reload");
TextField txtfProfesor = new TextField("Profesor:");
ComboBox<String> comboSalon = new ComboBox<>("Salon");
TextField txtfPractica = new TextField("Practica:");
TextField txtfHR_Entrada = new TextField("Hora entrada:");
Button btnRegistrarEntrada = new Button("Entrada");
Icon iconoEntradaPracticasc = new Icon("lumo","checkmark");
TextField txtfAlumnosPracticas = new TextField("Alumnos:");
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




    public ListView() {
        addClassNames("Login");
        setSpacing(false);
        setSizeFull();

        //mostrar login
        menuLogin();
        //Evento boton ingresar
        btnEntrar.addClickListener(Click ->{

            if (SAPDB.Login(txtfUsuario.getValue(),pswUsuario.getValue(),SAPDB.Conexion())){
                Notification dentro = Notification.show("Inicio de sesion exitoso");
                dentro.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                removeAll();
                menuBotonoes();

            }else{
                Notification error = Notification.show("Contraseña o usuario incorrectos");
                error.addThemeVariants(NotificationVariant.LUMO_ERROR);
                pswUsuario.clear();
                pswUsuario.focus();
            }
        });

        btnPracticas.addClickListener(Click ->{
            menuPracticas();
        });

        txtfPractica.addInputListener(inputEvent -> {
            LocalDateTime entrada = LocalDateTime.now();
            int hours  = entrada.getHour();
            int minutes = entrada.getMinute();
            txtfHR_Entrada.setValue(String.valueOf(hours)+":"+String.valueOf(minutes));

        });

        txtfAlumnosPracticas.addInputListener(inputEvent -> {
            LocalDateTime salida = LocalDateTime.now();
            int hours_s  = salida.getHour();
            int minutes_s = salida.getMinute();
            txtfHR_Salida.setValue(String.valueOf(hours_s)+":"+String.valueOf(minutes_s));
        });

        btnAgregarUsuario.addClickListener(Click ->{
            menuUsuarios();
        });

        btnRegistrarUsuario.addClickListener(Click ->{
           if(txtUsuarioInsertar.isEmpty()||txtContraseñaInsertar.isEmpty()||comboPermisosInsertar.isEmpty()){
               Notification errorInsertarUsuario = Notification.show("No se permiten campos vacios");
               errorInsertarUsuario.addThemeVariants(NotificationVariant.LUMO_ERROR);
           }else{
               SAPDB.insertarUsuario(SAPDB.Conexion(),txtUsuarioInsertar.getValue(),txtContraseñaInsertar.getValue(),comboPermisosInsertar.getValue());
               Notification notiUsuarioInsertado = Notification.show("Usuario registrado");
               txtUsuarioInsertar.clear();
               txtContraseñaInsertar.clear();
               comboPermisosInsertar.clear();
               notiUsuarioInsertado.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
           }
        });

        btnEliminarUsuario.addClickListener(Click ->{
           SAPDB.eliminarUsuario(SAPDB.Conexion(),txtEliminarUsuario.getValue());
           txtEliminarUsuario.clear();
        });

        btnBuscarUsuario.addClickListener(Click -> {
           if(SAPDB.consultarUsuario(SAPDB.Conexion(),txtUsuarioBuscarModificar.getValue())){

               txtModificarUsuario.setReadOnly(false);
               txtContraseñaModificar.setReadOnly(false);
               comboPermisosModificar.setReadOnly(false);
               txtUsuarioBuscarModificar.setReadOnly(true);
               txtModificarUsuario.setValue(txtUsuarioBuscarModificar.getValue());

           }
        });

        btnModificarUsuario.addClickListener(Click ->{
            if(txtContraseñaModificar.isEmpty()||txtModificarUsuario.isEmpty()||comboPermisosModificar.isEmpty()){
                Notification notiCamposVaciosModificarUsuario = Notification.show("No puede haber campos vacios");
            }else{
                SAPDB.actualizarUsuario(SAPDB.Conexion(),txtModificarUsuario.getValue(),txtContraseñaModificar.getValue(),comboPermisosModificar.getValue(),txtUsuarioBuscarModificar.getValue());
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
        btnCanelarModificarUsuario.addClickListener(Click ->{
            txtModificarUsuario.setReadOnly(true);
            txtContraseñaModificar.setReadOnly(true);
            comboPermisosModificar.setReadOnly(true);
            txtUsuarioBuscarModificar.setReadOnly(false);
            txtModificarUsuario.clear();
            txtContraseñaModificar.clear();
            comboPermisosModificar.clear();
            txtUsuarioBuscarModificar.clear();
        });

        btnBuscarGridPracticas.addClickListener(Click ->{
            grid.setItems(SAPDB.PracticasGrid(SAPDB.Conexion(),comboDia.getValue(),comboHorario.getValue()));
            comboSalon.setItems(SAPDB.comboSalon(SAPDB.Conexion(),comboDia.getValue(),comboHorario.getValue()));
            grid.setHeight("100%");
        });

        comboSalon.addValueChangeListener(inputEvent -> {
           txtfProfesor.clear();
           txtfPractica.clear();
           txtfHR_Entrada.clear();
           txtfProfesor.setValue(SAPDB.profePractica(SAPDB.Conexion(),comboDia.getValue(),comboHorario.getValue(),comboSalon.getValue()));
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
        HorizontalLayout layoutBotones = new HorizontalLayout(btnPracticas,btnApartado,btnAgregarUsuario);
        //
        setSpacing(true);
        //Botones estilo
        btnAgregarUsuario.setIcon(iconAgregarUsuario);
        btnApartado.setIcon(iconPrestamos);
        btnPracticas.setIcon(iconPracticas);
        btnAgregarUsuario.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnApartado.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnPracticas.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
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
        grid.removeAllColumns();
        //layout vertical para las practicas
        HorizontalLayout layout1 = new HorizontalLayout(comboDia,comboHorario,btnBuscarGridPracticas);
        HorizontalLayout layout2 = new HorizontalLayout(comboSalon,txtfPractica,txtfProfesor,txtfHR_Entrada,btnRegistrarEntrada);
        HorizontalLayout layout3 = new HorizontalLayout(txtfAlumnosPracticas,txtfHR_Salida,btnSalidaPracticas);
        //Cambiando tamaño de los componentes
        comboDia.setWidth("130px");
        comboHorario.setWidth("150px");
        comboSalon.setWidth("150px");
        txtfProfesor.setWidth("350px");
        txtfPractica.setWidth("200px");
        txtfHR_Entrada.setWidth("110px");
        txtfHR_Salida.setWidth("100px");
        txtfAlumnosPracticas.setWidth("90px");
        //Permisis de escritura
        txtfHR_Entrada.isReadOnly();
        //estilo botones
        btnRegistrarEntrada.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);
        btnRegistrarEntrada.setIcon(iconoEntradaPracticasc);
        btnSalidaPracticas.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);
        btnSalidaPracticas.setIcon(iconSalidaPracticas);
        btnBuscarGridPracticas.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_CONTRAST);
        btnBuscarGridPracticas.setIcon(iconBuscar);
        //Aliniar componentes
        layout1.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layout2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        layout3.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        //quitar permiso de escritura
        txtfHR_Entrada.setReadOnly(true);
        txtfHR_Salida.setReadOnly(true);
        txtfProfesor.setReadOnly(true);
        //Opciones de combobox
        comboDia.setItems("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
        comboHorario.setItems("7:00-8:59","9:00-10:59","11:00-12:59","13:00-14:59","16:00-17:59","18:00-19:59","20:00-21:59");
        //grid
        grid.addColumn(Practica::getHorario).setHeader("Horario").setAutoWidth(true);
        grid.addColumn(Practica::getProfesor).setHeader("Profesor").setAutoWidth(true);
        grid.addColumn(Practica::getMateria).setHeader("Materia").setAutoWidth(true);
        grid.addColumn(Practica::getSalon).setHeader("Salon").setAutoWidth(true);
        grid.addColumn(Practica::getPractica).setHeader("Practica").setAutoWidth(true);
        grid.addColumn(Practica::getAlumnosInscritos).setHeader("Inscritos").setAutoWidth(true);
        grid.addColumn(Practica::getHrEntrada).setHeader("Entrada").setAutoWidth(true);
        grid.addColumn(Practica::getAsistencia).setHeader("Asistencia").setAutoWidth(true);
        grid.addColumn(Practica::getHrSalida).setHeader("Salida").setAutoWidth(true);
        grid.setHeight("100%");
        add(
                layout1,
                layout2,
                layout3,
                grid
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
}
