package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
public abstract class AbstractPartidoHibernate extends AbstractHibernateTest {

    protected Equipo equipo1 = new Equipo();

    protected Equipo equipo2 = new Equipo();

    protected Tecnico tecnico1 = new Tecnico("Don tecnico", "Perez");

    protected Tecnico tecnico2 = new Tecnico("Don tecnico1", "Perez1");

    protected Jugador jugador1 = new Jugador("Juan", "Perez", 7);

    protected Jugador jugador2 = new Jugador("Juan1", "Perez1", 2);

    protected Jugador jugador3 = new Jugador("Juan2", "Perez2", 9);

    protected Jugador jugador4 = new Jugador("Juan3", "Perez3", 7);

    protected Jugador jugador5 = new Jugador("Juan4", "Perez4", 5);

    protected Jugador jugador6 = new Jugador("Juan5", "Perez5", 4);

    protected Jugador jugador7 = new Jugador("Juan6", "Perez6", 6);

    protected Jugador jugador8 = new Jugador("Juan7", "Perez7", 1);

    protected Jugador jugador9 = new Jugador("Juan8", "Perez8", 2);

    protected Jugador jugador10 = new Jugador("Juan9", "Perez9", 7);

    protected Jugador jugador11 = new Jugador("Juan10", "Perez10", 7);

    protected Jugador jugador12 = new Jugador("Juan11", "Perez11", 2);

    protected Jugador jugador13 = new Jugador("Juan12", "Perez12", 9);

    protected Jugador jugador14 = new Jugador("Juan13", "Perez13", 7);

    protected Jugador jugador15 = new Jugador("Juan14", "Perez14", 5);

    protected Jugador jugador16 = new Jugador("Juan15", "Perez15", 4);

    protected Jugador jugador17 = new Jugador("Juan16", "Perez16", 6);

    protected Jugador jugador18 = new Jugador("Juan17", "Perez17", 1);

    protected Jugador jugador19 = new Jugador("Juan18", "Perez18", 2);

    protected Jugador jugador20 = new Jugador("Juan19", "Perez19", 7);

    protected Jugador jugador21 = new Jugador("Juan20", "Perez20", 2);

    protected Jugador jugador22 = new Jugador("Juan21", "Perez21", 7);

    @Override
    public void setUp() {
        this.equipo1.setTecnico(this.tecnico1);
        this.equipo2.setTecnico(this.tecnico2);

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
        this.jugador11.addHabilidad(new Habilidad(Posicion.Arquero, 8));
        this.jugador11.addHabilidad(new Habilidad(Posicion.Enganche, 5));
        this.jugador12.addHabilidad(new Habilidad(Posicion.Lateral, 4));
        this.jugador12.addHabilidad(new Habilidad(Posicion.MediaPunta, 2));
        this.jugador13.addHabilidad(new Habilidad(Posicion.Enganche, 5));
        this.jugador13.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 9));
        this.jugador14.addHabilidad(new Habilidad(Posicion.Arquero, 10));
        this.jugador14.addHabilidad(new Habilidad(Posicion.Arquero, 7));
        this.jugador15.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 3));
        this.jugador15.addHabilidad(new Habilidad(Posicion.MediaPunta, 2));
        this.jugador16.addHabilidad(new Habilidad(Posicion.Lateral, 5));
        this.jugador16.addHabilidad(new Habilidad(Posicion.Central, 8));
        this.jugador16.addHabilidad(new Habilidad(Posicion.Enganche, 9));
        this.jugador17.addHabilidad(new Habilidad(Posicion.MediaPunta, 3));
        this.jugador18.addHabilidad(new Habilidad(Posicion.MediaPunta, 6));
        this.jugador18.addHabilidad(new Habilidad(Posicion.Arquero, 7));
        this.jugador19.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 6));
        this.jugador19.addHabilidad(new Habilidad(Posicion.Enganche, 8));
        this.jugador20.addHabilidad(new Habilidad(Posicion.Central, 0));
        this.jugador20.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 0));
        this.jugador21.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 6));
        this.jugador21.addHabilidad(new Habilidad(Posicion.Enganche, 8));
        this.jugador22.addHabilidad(new Habilidad(Posicion.Central, 0));
        this.jugador22.addHabilidad(new Habilidad(Posicion.VolanteDefensivo, 0));

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
        this.equipo1.agregarJugador(this.jugador11);

        this.equipo1.addPosicion(Posicion.Arquero);
        this.equipo1.addPosicion(Posicion.Delantero);
        this.equipo1.addPosicion(Posicion.Lateral);
        this.equipo1.addPosicion(Posicion.MediaPunta);
        this.equipo1.addPosicion(Posicion.VolanteLateral);
        this.equipo1.addPosicion(Posicion.Central);
        this.equipo1.addPosicion(Posicion.Delantero);
        this.equipo1.addPosicion(Posicion.Lateral);
        this.equipo1.addPosicion(Posicion.MediaPunta);
        this.equipo1.addPosicion(Posicion.VolanteLateral);
        this.equipo1.addPosicion(Posicion.VolanteDefensivo);

        this.equipo2.agregarJugador(this.jugador12);
        this.equipo2.agregarJugador(this.jugador13);
        this.equipo2.agregarJugador(this.jugador14);
        this.equipo2.agregarJugador(this.jugador15);
        this.equipo2.agregarJugador(this.jugador16);
        this.equipo2.agregarJugador(this.jugador17);
        this.equipo2.agregarJugador(this.jugador18);
        this.equipo2.agregarJugador(this.jugador19);
        this.equipo2.agregarJugador(this.jugador20);
        this.equipo2.agregarJugador(this.jugador21);
        this.equipo2.agregarJugador(this.jugador22);

        this.equipo2.addPosicion(Posicion.Arquero);
        this.equipo2.addPosicion(Posicion.Delantero);
        this.equipo2.addPosicion(Posicion.Lateral);
        this.equipo2.addPosicion(Posicion.Enganche);
        this.equipo2.addPosicion(Posicion.Lateral);
        this.equipo2.addPosicion(Posicion.MediaPunta);
        this.equipo2.addPosicion(Posicion.Delantero);
        this.equipo2.addPosicion(Posicion.Lateral);
        this.equipo2.addPosicion(Posicion.MediaPunta);
        this.equipo2.addPosicion(Posicion.VolanteLateral);
        this.equipo2.addPosicion(Posicion.VolanteDefensivo);

    }

}
