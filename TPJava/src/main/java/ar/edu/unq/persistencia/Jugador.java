package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Jugador {
    private List<Habilidad> habilidades = new LinkedList<Habilidad>();

    public int getValorHabilidad(final Posicion posicion) {
        int valor = 0;
        for (Habilidad i : this.habilidades) {
            if (i.getValor(posicion) > valor) {
                valor = i.getValor(posicion);
            }
        }
        return valor;
    }

    public void addHabilidad(Habilidad habilidad) {
        this.habilidades.add(habilidad);
    }

}
