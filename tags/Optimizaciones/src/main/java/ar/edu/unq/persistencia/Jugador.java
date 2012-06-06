package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class Jugador {

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNroCamiseta() {
        return this.nroCamiseta;
    }

    public void setNroCamiseta(int nroCamiseta) {
        this.nroCamiseta = nroCamiseta;
    }

    private String nombre;

    private String apellido;

    private int nroCamiseta;

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

    public Jugador(final String nombreT, final String apellidoT, final int camisetaT) {
        super();
        this.nombre = nombreT;
        this.apellido = apellidoT;
        this.nroCamiseta = camisetaT;
    }

    public Jugador() {
        super();
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

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public void addHabilidad(final Habilidad habilidad) {
        this.habilidades.add(habilidad);
    }

}
