package ar.edu.unq.persistencia;

import junit.framework.Assert;

/**
 * TODO: description
 */
public class TestPartidoDeCopa extends AbstractPartidoTest {

    PartidoSimple partido1 = new PartidoSimple(equipo1, equipo2);

    PartidoSimple partido2 = new PartidoSimple(equipo1, equipo2);

    PartidoDeCopa superPartido = new PartidoDeCopa(partido1, partido2);

    public void testEmpateSimple() {
        partido1.setGolesEquipos(0, 0);
        partido2.setGolesEquipos(3, 3);
        Assert.assertTrue(superPartido.empateSimple());
        partido1.setGolesEquipos(0, 0);
        partido2.setGolesEquipos(1, 3);
        Assert.assertFalse(superPartido.empateSimple());
    }

    public void testEmpateComplejo() {
        partido1.setGolesEquipos(3, 0);
        partido2.setGolesEquipos(0, 3);
        Assert.assertTrue(superPartido.empateComplejo());
        partido1.setGolesEquipos(0, 0);
        partido2.setGolesEquipos(1, 3);
        Assert.assertFalse(superPartido.empateComplejo());
        partido1.setGolesEquipos(2, 1);
        partido2.setGolesEquipos(5, 6);
        Assert.assertTrue(superPartido.empateComplejo());
    }

    public void testIrAPenales() {
        partido1.setGolesEquipos(3, 0);
        partido2.setGolesEquipos(0, 3);
        superPartido.setGolesPenalesAcertadosEquipos(3, 2);
        Assert.assertEquals(superPartido.irAPenales(), equipo1);
        superPartido.setGolesPenalesAcertadosEquipos(1, 2);
        Assert.assertEquals(superPartido.irAPenales(), equipo2);
    }

    public void testGetGanador() {

        // empate a penales
        partido1.setGolesEquipos(3, 0);
        partido2.setGolesEquipos(0, 3);
        superPartido.setGolesPenalesAcertadosEquipos(3, 2);
        Assert.assertEquals(superPartido.getGanador(), equipo1);

        // empate en partidos, diferencia de goles
        partido1.setGolesEquipos(2, 0);
        partido2.setGolesEquipos(0, 3);
        superPartido.setGolesPenalesAcertadosEquipos(3, 2);
        Assert.assertEquals(superPartido.getGanador(), equipo2);

        // dos empates, diferencia de goles
        partido1.setGolesEquipos(2, 2);
        partido2.setGolesEquipos(3, 3);
        superPartido.setGolesPenalesAcertadosEquipos(3, 2);
        Assert.assertEquals(superPartido.getGanador(), equipo1);

        // un ganador, un empate
        partido1.setGolesEquipos(3, 2);
        partido2.setGolesEquipos(3, 3);
        superPartido.setGolesPenalesAcertadosEquipos(3, 2);
        Assert.assertEquals(superPartido.getGanador(), equipo1);

        // un empate, un ganador
        partido1.setGolesEquipos(2, 2);
        partido2.setGolesEquipos(3, 5);
        superPartido.setGolesPenalesAcertadosEquipos(3, 2);
        Assert.assertNull(partido1.getGanador());
        Assert.assertEquals(partido2.getGanador(), equipo2);
        Assert.assertFalse((partido1.getIntGanador() == (partido2.getIntGanador())));
        Assert.assertEquals(superPartido.getGanador(), equipo2);
    }
}
