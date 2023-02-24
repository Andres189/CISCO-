package com.example.application.views.list;

import com.example.application.SAPDB;
import com.example.application.model.Usuario;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.themes.LumoLightTheme;
import com.vaadin.flow.component.combobox.ComboBox;
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
ComboBox<String> comboDia = new ComboBox<>("Día:");
ComboBox<String> comboHorario = new ComboBox<>("Horario:");
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
        //Creacion de layout para los botones practicas y apartado
        HorizontalLayout layoutBotones = new HorizontalLayout(btnPracticas,btnApartado,btnAgregarUsuario);
        //Botones estilo
        btnAgregarUsuario.setIcon(iconAgregarUsuario);
        btnApartado.setIcon(iconPrestamos);
        btnPracticas.setIcon(iconPracticas);
        btnAgregarUsuario.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnApartado.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        btnPracticas.addThemeVariants(ButtonVariant.LUMO_LARGE,ButtonVariant.LUMO_PRIMARY);
        //grid
        //agregar componentes
        add(
                layoutBotones

        );
        //Orden de botones de Practicas y Apartado
        setJustifyContentMode(JustifyContentMode.START);
        setMargin(true);

    }

    private void menuPracticas(){
        removeAll();
        menuBotonoes();
        //layout vertical para las practicas
        HorizontalLayout layout1 = new HorizontalLayout(comboDia,comboHorario);
        HorizontalLayout layout2 = new HorizontalLayout(comboSalon,txtfProfesor,txtfPractica,txtfHR_Entrada,btnRegistrarEntrada);
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
        //Aliniar componentes
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
        Grid<Usuario> grid = new Grid<>(Usuario.class,false);
        grid.addColumn(Usuario::getIdUsuario).setHeader("ID");
        grid.addColumn(Usuario::getUsuario).setHeader("Usuario");
        grid.addColumn(Usuario::getContraseña).setHeader("Contraseña");
        grid.addColumn(Usuario::getPermisos).setHeader("Permisos");
        grid.setItems(SAPDB.Practicas(SAPDB.Conexion()));
        add(
                layout1,
                layout2,
                layout3,
                grid
        );

    }

    private void menuUsuarios(){
        //creacion de objetos
    }
}
