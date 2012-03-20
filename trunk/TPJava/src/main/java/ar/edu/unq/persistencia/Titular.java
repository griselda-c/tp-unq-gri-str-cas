package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
public class Titular {

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
