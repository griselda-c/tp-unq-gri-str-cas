package ar.edu.unq.persistencia;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * TODO: description
 */
public class Generador {

    public static Integer N_EQUIPOS = 10;

    public static Integer NPartidosSimples = 10000;

    public static Integer NPartidosDeCopa = 5000;

    public static Integer N_JUGADORES = 20;

    public static Integer HAB_X_JUGADOR = 4;

    public static int N_POSICIONES = 11;

    public static Equipo[] equipos;

    public static Random generador = new Random();

    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public static Session session;

    public static void main(String[] args) {
        crearYPersistirEquipos();
        crearYPersistirPartidosDeCopa();
        crearYPersistirPartidosSimples();
    }

    public static void crearYPersistirPartidosDeCopa() {
        session = sf.openSession();
        for (int i = 0; i < NPartidosDeCopa; i++) {

            PartidoSimple p1;
            PartidoSimple p2;

            int a = generador.nextInt(N_EQUIPOS);
            int b = getNotEqual(a, N_EQUIPOS);
            p1 = new PartidoSimple(equipos[a], equipos[b]);
            p1.setGolesEquipos(generador.nextInt(7), generador.nextInt(7));

            p2 = new PartidoSimple(equipos[a], equipos[b]);
            p2.setGolesEquipos(generador.nextInt(7), generador.nextInt(7));

            PartidoDeCopa partidoC = new PartidoDeCopa(p1, p2);
            partidoC.setGolesPenalesAcertadosEquipos(generador.nextInt(7), generador.nextInt(7));
            session.saveOrUpdate(partidoC);
            session.flush();
        }
        session.close();
    }

    public static void crearYPersistirPartidosSimples() {
        session = sf.openSession();
        for (int i = 0; i < NPartidosSimples; i++) {
            PartidoSimple p1;

            int a = generador.nextInt(N_EQUIPOS);
            int b = getNotEqual(a, N_EQUIPOS);
            p1 = new PartidoSimple(equipos[a], equipos[b]);
            p1.setGolesEquipos(generador.nextInt(7), generador.nextInt(7));

            session.saveOrUpdate(p1);
            session.flush();
        }
        session.close();
    }

    public static void crearYPersistirEquipos() {
        session = sf.openSession();
        equipos = new Equipo[N_EQUIPOS];
        for (int i = 0; i < N_EQUIPOS; i++) {
            equipos[i] = new Equipo();
            for (int j = 0; j < N_JUGADORES; j++) {
                Jugador nuevoJugador = new Jugador("jugador" + generador.nextInt(1000), "Apellido"
                        + generador.nextInt(1000), (i + 1));
                for (int k = 0; k < HAB_X_JUGADOR; k++) {
                    nuevoJugador.addHabilidad(new Habilidad(Posicion.values()[generador.nextInt(8)], generador
                            .nextInt(10)));
                }
                equipos[i].agregarJugador(nuevoJugador);
            }
            equipos[i].addPosicion(Posicion.Arquero);
            for (int k = 1; k < N_POSICIONES; k++) {
                equipos[i].addPosicion(Posicion.values()[generador.nextInt(7) + 1]);
            }
            equipos[i].setTecnico(new Tecnico("NomTecnico " + i, "ApelTecnico" + i));
            equipos[i].armarFormacion();
            session.saveOrUpdate(equipos[i]);
            session.flush();
        }
        session.close();
    }

    public static int getNotEqual(int entero, int max) {
        int res = generador.nextInt(max);
        while (entero == res) {
            res = generador.nextInt(max);
        }
        return res;
    }
}
