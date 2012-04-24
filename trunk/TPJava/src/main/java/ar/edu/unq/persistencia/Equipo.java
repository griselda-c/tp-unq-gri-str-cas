package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Equipo {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setFormacion(Formacion formacion) {
        this.formacion = formacion;
    }

    public Equipo() {
        super();
    }

    private Tecnico tecnico = new Tecnico();

    private List<Posicion> posiciones = new LinkedList<Posicion>();

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    private List<Jugador> jugadores = new LinkedList<Jugador>();

    private Formacion formacion;

    public Formacion armarFormacion() {
        this.setFormacion(tecnico.armarFormacion(this));
        return this.getFormacion();
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
