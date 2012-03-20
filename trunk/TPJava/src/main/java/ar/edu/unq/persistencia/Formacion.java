package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Formacion {

    public Equipo equipo;

    private List<Titular> titulares = new LinkedList<Titular>();

    public List<Titular> getTitulares() {
        return titulares;
    }

    private List<Jugador> suplentes;

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    private List<Posicion> posiciones = new LinkedList<Posicion>();;

    public void addTitular(final Titular unTitular) {
        this.titulares.add(unTitular);
    }

    public void addPosicion(final Posicion unPosicion) {
        this.posiciones.add(unPosicion);
    }

    public void setSuplentes(final List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

    public List<Posicion> getPosiciones() {
        return this.posiciones;
    }

}
