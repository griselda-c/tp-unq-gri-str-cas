package ar.edu.unq.persistencia;

public class Habilidad {

    private Posicion posicion;

    private int valor;

    public Habilidad(Posicion posicion, int valor) {
        this.posicion = posicion;
        this.valor = valor;
    }

    public int getValor(Posicion posicion) {
        if (this.posicion.equals(posicion)) {
            return this.valor;
        } else {
            return 0;
        }
    }
}
