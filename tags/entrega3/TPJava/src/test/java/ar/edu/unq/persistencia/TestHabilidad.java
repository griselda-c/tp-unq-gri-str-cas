package ar.edu.unq.persistencia;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TODO: description
 */
public class TestHabilidad extends TestCase {

    /*
     * private Posicion pos = Posicion.Arquero; private int valor;
     */

    public void testGetValor() {
        Habilidad hab = new Habilidad(Posicion.Arquero, 4);
        Assert.assertEquals(4, hab.getValor(Posicion.Arquero));

    }

    public void testGetValorCero() {
        Habilidad hab = new Habilidad(Posicion.Arquero, 4);
        Assert.assertEquals(0, hab.getValor(Posicion.MediaPunta));

    }

}
