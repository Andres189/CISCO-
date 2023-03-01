package com.example.application.model;
import lombok.Data;

@Data
public class Practica {

    String horario;
    String profesor;
    String materia;
    String salon;
    String practica;
    int alumnosInscritos;
    String HrEntrada;
    int asistencia;
    String HrSalida;

    public Practica(String horario, String profesor,String materia, String salon, String practica, int alumnosInscritos, String hrEntrada, int asistencia, String hrSalida) {
        this.horario = horario;
        this.profesor = profesor;
        this.materia = materia;
        this.salon = salon;
        this.practica = practica;
        this.alumnosInscritos = alumnosInscritos;
        HrEntrada = hrEntrada;
        this.asistencia = asistencia;
        HrSalida = hrSalida;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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

    public int getAlumnosInscritos() {
        return alumnosInscritos;
    }

    public void setAlumnosInscritos(int alumnosInscritos) {
        this.alumnosInscritos = alumnosInscritos;
    }

    public String getHrEntrada() {
        return HrEntrada;
    }

    public void setHrEntrada(String hrEntrada) {
        HrEntrada = hrEntrada;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public String getHrSalida() {
        return HrSalida;
    }

    public void setHrSalida(String hrSalida) {
        HrSalida = hrSalida;
    }
}
