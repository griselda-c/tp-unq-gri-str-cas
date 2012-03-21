package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
abstract class Partido {

    private Equipo Equipo1;

    private int golesEquipo1;

    public void setEquipo1(final Equipo e1, final int g1) {
        this.Equipo1 = e1;
        this.golesEquipo1 = g1;
    }

    public void setEquipo2(final Equipo e2, final int g2) {
        this.Equipo2 = e2;
        this.golesEquipo2 = g2;
    }

    private Equipo Equipo2;

    private int golesEquipo2;

    abstract Equipo getGanador();

}
