package ar.edu.unq.persistencia;

public class PartidoSimple extends Partido {

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
