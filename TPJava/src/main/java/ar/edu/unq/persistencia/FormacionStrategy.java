package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class FormacionStrategy {

    public Formacion armarFormacion(Equipo unEquipo) {
        List<Jugador> suplentes = new LinkedList<Jugador>();
        suplentes.addAll(unEquipo.getJugadores());
        List<Posicion> posicionesPendientes = new LinkedList<Posicion>();

        Formacion formacion = new Formacion();
        formacion.setSuplentes(suplentes);

        for (Posicion i : formacion.getPosiciones()) {
            int maximo = 0;
            Jugador masApto = null;
            for (Jugador j : suplentes) {
                if (j.getValorHabilidad(i) > maximo) {
                    masApto = j;
                    maximo = j.getValorHabilidad(i);
                }
            }

            if (maximo > 0) {
                suplentes.remove(masApto);
                formacion.addTitular(new Titular(masApto, i));
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
