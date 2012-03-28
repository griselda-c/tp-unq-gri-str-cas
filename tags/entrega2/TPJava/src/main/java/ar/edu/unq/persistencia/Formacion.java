package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Formacion {

    private List<Titular> titulares = new LinkedList<Titular>();

    private List<Jugador> suplentes = new LinkedList<Jugador>();

    public List<Titular> getTitulares() {
        return titulares;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void addTitular(Titular unTitular) {
        this.titulares.add(unTitular);
    }

    public void setSuplentes(List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

}
