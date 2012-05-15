package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
public abstract class Partido {

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract Equipo getGanador();

    public abstract int getGolesEquipo1();

    public abstract int getGolesEquipo2();

    public abstract Equipo getEquipo1();

    public abstract Equipo getEquipo2();

}
