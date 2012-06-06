package ar.edu.unq.persistencia;

import java.util.List;

public class Servicio {

    public static void main(String[] args) {

        List<Jugador> l = Dao.getJugadores();
        for (Jugador jd : l) {
            System.out.println(jd.getNombre() + " - " + jd.getApellido());
        }
    }
}
