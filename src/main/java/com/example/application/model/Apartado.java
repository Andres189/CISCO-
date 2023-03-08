package com.example.application.model;

import java.util.Date;

public class Apartado {

    Date fecha;
    String horario;
    String profesor;
    String materia;
    String salon;
    String practica;
    String HoraEntrada;
    int asistencia;
    String HoraSalida;

    public Apartado(Date fecha,String horario,String profesor,String materia,String salon,String practica,String horaEntrada,int asistencia,String horaSalida){
        this.fecha = fecha;
        this.horario = horario;
        this.profesor = profesor;
        this.materia = materia;
        this.salon = salon;
        this.practica = practica;
        HoraEntrada = horaEntrada;
        this.asistencia = asistencia;
        HoraSalida = horaSalida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public String getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        HoraEntrada = horaEntrada;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public String getHoraSalida() {
        return HoraSalida;
    }

    public void setHoraSalida(String horaSalida) {
        HoraSalida = horaSalida;
    }
}
