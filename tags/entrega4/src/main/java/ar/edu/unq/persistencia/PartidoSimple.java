package ar.edu.unq.persistencia;

public class PartidoSimple extends Partido {

    protected Equipo equipo1;

    protected Equipo equipo2;

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    int golesEquipo1 = 0;

    int golesEquipo2 = 0;

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo1(Equipo e1, int g1) {
        this.equipo1 = e1;
        this.golesEquipo1 = g1;
    }

    public void setEquipo2(Equipo e2, int g2) {
        this.equipo2 = e2;
        this.golesEquipo2 = g2;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipos(int golesEquipo1, int golesEquipo2) {
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public PartidoSimple(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    @Override
    public Equipo getGanador() {
        if (getGolesEquipo1() == getGolesEquipo2()) {
            return null;
        } else if (getGolesEquipo1() > getGolesEquipo2()) {
            return getEquipo1();
        } else {
            return getEquipo2();
        }
    }

    public int getIntGanador() {
        if (getGolesEquipo1() == getGolesEquipo2()) {
            return 0;
        } else if (getGolesEquipo1() > getGolesEquipo2()) {
            return 1;
        } else {
            return 2;
        }
    }

}
