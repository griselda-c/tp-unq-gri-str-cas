package ar.edu.unq.persistencia;

import junit.framework.Assert;

/**
 * TODO: description
 */
public class TestPartidoSimple extends AbstractPartidoTest {

    PartidoSimple partido = new PartidoSimple(equipo1, equipo2);

    public void testGetGanador() {
        partido.setGolesEquipos(30, 1000);
        Assert.assertEquals(partido.getGanador(), equipo2);
        partido.setGolesEquipos(3000, 1000);
        Assert.assertEquals(partido.getGanador(), equipo1);
        partido.setGolesEquipos(30, 30);
        Assert.assertNull(partido.getGanador());
    }
}
