package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
abstract class Partido {

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    abstract Equipo getGanador();

}
