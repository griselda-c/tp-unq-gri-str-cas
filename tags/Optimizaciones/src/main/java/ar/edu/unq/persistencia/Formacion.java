package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Formacion {

    public Formacion() {
        super();
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipo equipo;

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    private List<Titular> titulares = new LinkedList<Titular>();

    private List<Jugador> suplentes = new LinkedList<Jugador>();

    public List<Titular> getTitulares() {
        return titulares;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void setTitulares(List<Titular> titulares) {
        this.titulares = titulares;
    }

    public void addTitular(Titular unTitular) {
        this.titulares.add(unTitular);
    }

    public void setSuplentes(List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

    public Formacion(Equipo equipo) {
        this.equipo = equipo;
    }

}
