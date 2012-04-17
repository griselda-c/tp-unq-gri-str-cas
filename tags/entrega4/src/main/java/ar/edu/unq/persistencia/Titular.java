package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
public class Titular {

    private int id;

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Titular(final Jugador jugador, final Posicion posicion) {
        this.jugador = jugador;
        this.posicion = posicion;
    }

    private Jugador jugador;

    public Jugador getJugador() {
        return jugador;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    private Posicion posicion;

}
