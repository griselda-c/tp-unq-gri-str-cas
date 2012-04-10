package ar.edu.unq.persistencia;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

/**
 * TODO: description
 */
public class SaveFormacionTest extends AbstractHibernateTest {
    private Equipo equipo = new Equipo();

    private Tecnico tecnico = new Tecnico();

    private Jugador jugador1 = new Jugador("Juan", "Perez", 7);

    private Jugador jugador2 = new Jugador("Juan1", "Perez1", 2);

    private Jugador jugador3 = new Jugador("Juan2", "Perez2", 9);

    private Jugador jugador4 = new Jugador("Juan3", "Perez3", 7);

    private Jugador jugador5 = new Jugador("Juan4", "Perez4", 5);

    private Jugador jugador6 = new Jugador("Juan5", "Perez5", 4);

    private Jugador jugador7 = new Jugador("Juan6", "Perez6", 6);

    private Jugador jugador8 = new Jugador("Juan7", "Perez7", 1);

    private Jugador jugador9 = new Jugador("Juan8", "Perez8", 2);

    private Jugador jugador10 = new Jugador("Juan9", "Perez9", 7);

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

    public void testSaveFormacion() throws Exception {
        SessionFactory sessionFactory = this.getSessionFactory();
        Session session = sessionFactory.openSession();
        Formacion formacionTem = this.equipo.armarFormacion();

        session.saveOrUpdate(formacionTem);
        session.flush();

        session.close();
    }
}
