package ar.edu.unq.persistencia;

public class Habilidad {

    private Posicion posicion;

    private int valor;

    public String getPosicionString() {
        return posicion.toString();
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
