package ar.edu.unq.persistencia;

import junit.framework.Assert;

/**
 * TODO: description
 */
public class TestJugador extends AbstractPartidoTest {

    private Jugador jugador = new Jugador();

    public void testJugador() {
        this.jugador.addHabilidad(new Habilidad(Posicion.Central, 9));
        this.jugador.addHabilidad(new Habilidad(Posicion.MediaPunta, 10));
        this.jugador.addHabilidad(new Habilidad(Posicion.Central, 8));
        int valor = this.jugador.getValorHabilidad(Posicion.Central);
        Assert.assertEquals(valor, 9);
    }
}
