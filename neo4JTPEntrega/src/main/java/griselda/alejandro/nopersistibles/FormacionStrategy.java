package griselda.alejandro.nopersistibles;

import griselda.alejandro.persistibles.Equipo;
import griselda.alejandro.persistibles.Formacion;
import griselda.alejandro.persistibles.Jugador;
import griselda.alejandro.persistibles.Titular;

import java.util.LinkedList;
import java.util.List;

public class FormacionStrategy {

    public Formacion armarFormacion(Equipo unEquipo) {
        List<Jugador> suplentes = new LinkedList<Jugador>();
        // guarda en suplentes todos los jugadores
        suplentes.addAll(unEquipo.getJugadores());

        List<Posicion> posicionesPendientes = new LinkedList<Posicion>();

        Formacion formacion = new Formacion(unEquipo);
        formacion.setSuplentes(suplentes);

        for (Posicion i : unEquipo.getPosiciones()) {
            int maximo = 0;
            Jugador masApto = null;
            for (Jugador j : suplentes) {
                if (j.getValorHabilidad(i) > maximo) {
                    masApto = j;
                    maximo = j.getValorHabilidad(i);
                }
            }

            if (maximo > 0) {

                formacion.addTitular(new Titular(masApto, i));
                suplentes.remove(masApto);
            } else {
                posicionesPendientes.add(i);
            }

        }

        for (Posicion p : posicionesPendientes) {
            Jugador jugadorActual = suplentes.get(0);
            suplentes.remove(jugadorActual);
            formacion.addTitular(new Titular(jugadorActual, p));
        }
        return formacion;
    }
}
