package ar.edu.unq.persistencia;

public class PartidoSimple extends Partido {

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Equipo equipo1;

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    protected Equipo equipo2;

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

    int golesEquipo1 = 0;

    int golesEquipo2 = 0;

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

    /*
     * public PartidoSimple(Equipo e1, Equipo e2){ setEquipos(e1,e2); }
     */

    public PartidoSimple(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    @Override
    public Equipo getGanador() {
        /*
         * if (getGolesEquipo1() > getGolesEquipo2()) { return getEquipo1(); } else { return getEquipo2(); }
         */

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

    /*
     * public static void main(String args[]) { PartidoSimple p1 = new PartidoSimple(); p1.setEquipo1(new Equipo("e1"),
     * 5); p1.setEquipo2(new Equipo("e2"), 3);
     * 
     * System.out.println(p1.getGanador().getNombre() + " " + p1.getGolesEquipo1() + " " + p1.getGolesEquipo2());
     * 
     * }
     */

}
