package com.example.application.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.MonthDay;

@PageTitle("Interfaz")
@Route(value = "asd")


public class Interfaz extends VerticalLayout{

    Button btnPracticas = new Button("Practicas");
    Button btnApartado = new Button("Apartado");

    public Interfaz(){
        addClassName("Interfaz");
        setSizeFull();
    }

    public void menuBotonoes(){


        HorizontalLayout layoutBotones = new HorizontalLayout(btnPracticas,btnApartado);
        add(layoutBotones);
        layoutBotones.setVerticalComponentAlignment(Alignment.CENTER);

    }



}
