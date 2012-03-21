package ar.edu.unq.persistencia;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * TODO: description
 */
public class TestEquipo extends TestCase {

    private Equipo equipo = new Equipo();

    private Tecnico tecnico = new Tecnico();

    private Jugador jugador1 = new Jugador();

    private Jugador jugador2 = new Jugador();

    private Jugador jugador3 = new Jugador();

    private Jugador jugador4 = new Jugador();

    private Jugador jugador5 = new Jugador();

    private Jugador jugador6 = new Jugador();

    private Jugador jugador7 = new Jugador();

    private Jugador jugador8 = new Jugador();

    private Jugador jugador9 = new Jugador();

    private Jugador jugador10 = new Jugador();

    @Override
    public void setUp() {
        this.equipo.setTecnico(this.tecnico);
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

        this.equipo.agregarJugador(this.jugador1);
        this.equipo.agregarJugador(this.jugador2);
        this.equipo.agregarJugador(this.jugador3);
        this.equipo.agregarJugador(this.jugador4);
        this.equipo.agregarJugador(this.jugador5);
        this.equipo.agregarJugador(this.jugador6);
        this.equipo.agregarJugador(this.jugador7);
        this.equipo.agregarJugador(this.jugador8);
        this.equipo.agregarJugador(this.jugador9);
        this.equipo.agregarJugador(this.jugador10);
        this.equipo.addPosicion(Posicion.Arquero);
        this.equipo.addPosicion(Posicion.Delantero);
        this.equipo.addPosicion(Posicion.Lateral);
        this.equipo.addPosicion(Posicion.MediaPunta);
        this.equipo.addPosicion(Posicion.VolanteLateral);
        this.equipo.addPosicion(Posicion.VolanteDefensivo);

    }

    public void testFormarEquipo() {

        Formacion formacionTem = this.equipo.armarFormacion();
        System.out.println(formacionTem.getTitulares().size());
        System.out.println(formacionTem.getSuplentes().size());
        Assert.assertTrue(formacionTem.getTitulares().size() == 6);
    }
}
