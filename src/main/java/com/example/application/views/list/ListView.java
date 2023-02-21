package com.example.application.views.list;

import com.example.application.SAPDB;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.sql.*;
import java.time.DayOfWeek;

@PageTitle("Login")
@Route(value = "")



public class ListView extends VerticalLayout {
    String username = "DESKTOP-SVV986M\\Cristina";
    String pass = "1234";
    String url = "";
Button btnEntrar = new Button("Entrar");
TextField txtfUsuario = new TextField("Usuario:");
PasswordField pswUsuario = new PasswordField("Contraseña:");
Icon iconUsuario = new Icon("vaadin","user");
Icon iconPass = new Icon("vaadin","lock");
H2 headerMenu = new H2("Login");
Button btnPracticas = new Button("Practicas");
Button btnApartado = new Button("Apartado");
Icon iconPracticas = new Icon("vaadin","specialist");
Icon iconApartado = new Icon("vaadin","calendar");

ComboBox<DayOfWeek> comboDia = new ComboBox<>("Dia");


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


    }
    public void menuLogin(){
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
        //Boton entrar
        btnEntrar.addClickShortcut(Key.ENTER);
        btnEntrar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
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


    public void menuBotonoes(){
        //Creacion de layout para los botones practicas y apartado
        HorizontalLayout layoutBotones = new HorizontalLayout(btnPracticas,btnApartado);
        //Botones
        btnPracticas.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_LARGE);
        btnApartado.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_LARGE);
        btnPracticas.setIcon(iconPracticas);
        btnApartado.setIcon(iconApartado);
        //agregar componentes
        add(layoutBotones);
        //Orden del contenido
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
        setMargin(true);

    }

}
