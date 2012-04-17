package ar.edu.unq.persistencia;

import junit.framework.Assert;

public class TestEquipo extends AbstractPartidoTest {

    @Override
    public void setUp() {
        this.equipo1.setTecnico(this.tecnico1);
        this.jugador1.addHabilidad(new Habilidad(Posicion.Arquero, 8));
        this.jugador1.addHabilidad(new Habilidad(Posicion.Enganche, 5));
        this.jugador2.addHabilidad(new Habilidad(Posicion.Lateral, 4));
        this.jugador2.addHabilidad(new Habilidad(Posicion.MediaPunta, 2));
        this.jugador3.addHabilidad(new Habilidad(Posicion.Enganche, 5));
        this.jugador3.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 9));
        this.jugador4.addHabilidad(new Habilidad(Posicion.Arquero, 10));
        this.jugador4.addHabilidad(new Habilidad(Posicion.Arquero, 7));
        this.jugador5.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 3));
        this.jugador5.addHabilidad(new Habilidad(Posicion.MediaPunta, 2));
        this.jugador6.addHabilidad(new Habilidad(Posicion.Lateral, 5));
        this.jugador6.addHabilidad(new Habilidad(Posicion.Central, 8));
        this.jugador6.addHabilidad(new Habilidad(Posicion.Enganche, 9));
        this.jugador7.addHabilidad(new Habilidad(Posicion.MediaPunta, 3));
        this.jugador8.addHabilidad(new Habilidad(Posicion.MediaPunta, 6));
        this.jugador8.addHabilidad(new Habilidad(Posicion.Arquero, 7));
        this.jugador9.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 6));
        this.jugador9.addHabilidad(new Habilidad(Posicion.Enganche, 8));
        this.jugador10.addHabilidad(new Habilidad(Posicion.Central, 0));
        this.jugador10.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 0));

        this.equipo1.agregarJugador(this.jugador1);
        this.equipo1.agregarJugador(this.jugador2);
        this.equipo1.agregarJugador(this.jugador3);
        this.equipo1.agregarJugador(this.jugador4);
        this.equipo1.agregarJugador(this.jugador5);
        this.equipo1.agregarJugador(this.jugador6);
        this.equipo1.agregarJugador(this.jugador7);
        this.equipo1.agregarJugador(this.jugador8);
        this.equipo1.agregarJugador(this.jugador9);
        this.equipo1.agregarJugador(this.jugador10);
        this.equipo1.addPosicion(Posicion.Arquero);
        this.equipo1.addPosicion(Posicion.Delantero);
        this.equipo1.addPosicion(Posicion.Lateral);
        this.equipo1.addPosicion(Posicion.MediaPunta);
        this.equipo1.addPosicion(Posicion.VolanteLateral);
        this.equipo1.addPosicion(Posicion.VolanteDefensivo);

    }

    public void testFormarEquipo() {

        Formacion formacionTem = this.equipo1.armarFormacion();
        System.out.println(formacionTem.getTitulares().size());
        System.out.println(formacionTem.getSuplentes().size());
        Assert.assertEquals(formacionTem.getTitulares().size(), 6);
        Assert.assertEquals(formacionTem.getSuplentes().size(), 4);
    }
}
