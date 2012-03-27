package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
abstract class Partido {

    protected Equipo equipo1;

    protected int golesEquipo1;

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    protected Equipo equipo2;

    protected int golesEquipo2;

    public void setEquipo1(Equipo e1, int g1) {
        this.equipo1 = e1;
        this.golesEquipo1 = g1;
    }

    public void setEquipo2(Equipo e2, int g2) {
        this.equipo2 = e2;
        this.golesEquipo2 = g2;
    }

    abstract Equipo getGanador();

}
