package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Jugador {

    private String nombre;

    private String apellido;

    private int camiseta;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public int getCamiseta() {
        return this.camiseta;
    }

    public void setCamiseta(final int camiseta) {
        this.camiseta = camiseta;
    }

    public Jugador(final String nombreT, final String apellidoT, final int camisetaT) {
        super();
        this.nombre = nombreT;
        this.apellido = apellidoT;
        this.camiseta = camisetaT;
    }

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

    public void addHabilidad(final Habilidad habilidad) {
        this.habilidades.add(habilidad);
    }

}
