package griselda.alejandro;

import griselda.alejandro.nopersistibles.Posicion;
import griselda.alejandro.persistibles.Equipo;
import griselda.alejandro.persistibles.Habilidad;
import griselda.alejandro.persistibles.Jugador;
import griselda.alejandro.persistibles.PartidoDeCopa;
import griselda.alejandro.persistibles.PartidoSimple;
import griselda.alejandro.persistibles.Tecnico;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Generador {

    public static Integer N_EQUIPOS = 10;

    public static Integer NPartidosSimples = 10000;

    public static Integer NPartidosDeCopa = 5000;

    public static Integer N_JUGADORES = 20;

    public static Integer HAB_X_JUGADOR = 4;

    public static int N_POSICIONES = 11;

    public static Equipo[] equipos;

    public static Random generador = new Random();

    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        BaseDeDatos.abrirBase();
        //BaseDeDatos.persistir(new Jugador());
        //crearYPersistirEquipos();
        //crearYPersistirPartidosDeCopa();
        //crearYPersistirPartidosSimples();
        System.out.println("Se guardo todo en " + (System.currentTimeMillis() - inicio) + " milisegundos");
    }

    
    public static void crearYPersistirPartidosDeCopa() {
        // List<PartidoSimple> partidosSimples = new LinkedList<PartidoSimple>();
        List<PartidoDeCopa> partidosDeCopa = new LinkedList<PartidoDeCopa>();
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
            partidosDeCopa.add(partidoC);
        }
        BaseDeDatos.persistirColeccion(partidosDeCopa);
    }

    public static void crearYPersistirPartidosSimples() {
        List<PartidoSimple> partidosSimples = new LinkedList<PartidoSimple>();
        for (int i = 0; i < NPartidosSimples; i++) {
            PartidoSimple p1;

            int a = generador.nextInt(N_EQUIPOS);
            int b = getNotEqual(a, N_EQUIPOS);
            p1 = new PartidoSimple(equipos[a], equipos[b]);
            p1.setGolesEquipos(generador.nextInt(7), generador.nextInt(7));

            // BaseDeDatos.persistir(p1);
            partidosSimples.add(p1);
        }
        BaseDeDatos.persistirColeccion(partidosSimples);
    }

    public static void crearYPersistirEquipos() {
        equipos = new Equipo[N_EQUIPOS];
        for (int i = 0; i < N_EQUIPOS; i++) {
            equipos[i] = new Equipo("Equipo-" + generador.nextInt(1000));
            for (int j = 0; j < N_JUGADORES; j++) {
                Jugador nuevoJugador = new Jugador("jugador" + generador.nextInt(1000), "Apellido" + generador.nextInt(1000), (i + 1));
                for (int k = 0; k < HAB_X_JUGADOR; k++) {
                    nuevoJugador.addHabilidad(new Habilidad(Posicion.values()[generador.nextInt(8)], generador.nextInt(10)));
                }
                equipos[i].agregarJugador(nuevoJugador);
            }
            equipos[i].addPosicion(Posicion.Arquero);
            for (int k = 1; k < N_POSICIONES; k++) {
                equipos[i].addPosicion(Posicion.values()[generador.nextInt(7) + 1]);
            }
            equipos[i].setTecnico(new Tecnico("NomTecnico " + i, "ApelTecnico" + i));
            equipos[i].armarFormacion();
            BaseDeDatos.persistir(equipos[i]);
        }
    }

    public static int getNotEqual(int entero, int max) {
        int res = generador.nextInt(max);
        while (entero == res) {
            res = generador.nextInt(max);
        }
        return res;
    }
}
