package griselda.alejandro;

import java.util.List;

public class Persistidor {

    public static void main(String[] args) {

        Jugador j = new Jugador("Jose");
        Jugador h = new Jugador("Juanjo");
        BaseDeDatos.persistir(j);
        BaseDeDatos.persistir(j);
        BaseDeDatos.persistir(h);
        Equipo e = new Equipo("San Lorenzo");
        e.jugadores.add(j);
        BaseDeDatos.persistir(e);
        BaseDeDatos.persistir(e);
        BaseDeDatos.persistir(e);
        List<Jugador> jugadores = BaseDeDatos.getJugadores();
        System.out.println(jugadores.size());
        for (Jugador jugador : jugadores) {
            System.out.println("Nombre: " + jugador.getNombre() + " - id: " + jugador.getId());
        }

        List<Equipo> equipos = BaseDeDatos.getEquipos();
        System.out.println(equipos.size());
        for (Equipo equipo : equipos) {
            System.out.println("Nombre: " + equipo.getNombre() + " - id: " + equipo.getId() + " - jugadores: "
                    + equipo.getJugadores().size());
        }
    }
}
