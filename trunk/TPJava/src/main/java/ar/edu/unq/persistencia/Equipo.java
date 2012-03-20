package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Equipo {

    private Tecnico tecnico;

    private List<Posicion> posiciones = new LinkedList<Posicion>();

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    private List<Jugador> jugadores = new LinkedList<Jugador>();

    private Formacion formacion = new Formacion();

    public Formacion armarFormacion() {
        return this.tecnico.armarFormacion(this);
    }

    public void addPosicion(Posicion unPosicion) {
        this.posiciones.add(unPosicion);
    }

    public void agregarJugador(Jugador j) {
        this.jugadores.add(j);
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public Formacion getFormacion() {
        return this.formacion;
    }

    public List<Posicion> getPosiciones() {
        return this.posiciones;
    }

}