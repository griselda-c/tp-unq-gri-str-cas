package ar.edu.unq.persistencia;

public class PartidoDeCopa extends Partido {

    PartidoSimple partidoSimple1;

    PartidoSimple partidoSimple2;

    int golesPenalesEquipo1 = 0;

    int golesPenalesEquipo2 = 0;

    public void setGolesPenalesAcertadosEquipos(int golesPenalesEquipo1, int golesPenalesEquipo2) {
        this.golesPenalesEquipo1 = golesPenalesEquipo1;
        this.golesPenalesEquipo2 = golesPenalesEquipo2;
    }

    public PartidoDeCopa(PartidoSimple e1, PartidoSimple e2) {
        partidoSimple1 = e1;
        partidoSimple2 = e2;
    }

    public int golesEquipo1() {
        return (partidoSimple1.getGolesEquipo1() + partidoSimple2.getGolesEquipo1());
    }

    public int golesEquipo2() {
        return (partidoSimple1.getGolesEquipo2() + partidoSimple2.getGolesEquipo2());
    }

    public boolean empateComplejo() {
        return ((partidoSimple1.getIntGanador() != partidoSimple2.getIntGanador()) & (golesEquipo1() == golesEquipo2()));
    }

    public boolean empateSimple() {
        return (partidoSimple1.getIntGanador() == 0 & partidoSimple2.getIntGanador() == 0);
    }

    public boolean empate() {
        return empateSimple() | empateComplejo();
    }

    public Equipo irAPenales() {
        // new PartidoSimple(e1,e2).getGanador();
        /*
         * while(golesPenalesEquipo1 == golesPenalesEquipo2) { this.golesPenalesEquipo1 = (int) (Math.random() * 10);
         * this.golesPenalesEquipo2 = (int) (Math.random() * 10); }
         */
        if (golesPenalesEquipo1 > golesPenalesEquipo2)
            return getEquipo1();
        else
            return getEquipo2();
    }

    public Equipo getEquipo1() {
        return partidoSimple1.getEquipo1();
    }

    public Equipo getEquipo2() {
        return partidoSimple1.getEquipo2();
    }

    @Override
    public Equipo getGanador() {
        if (empate()) {
            return irAPenales();
        } else if (partidoSimple1.getIntGanador() == (partidoSimple2.getIntGanador())) {
            return partidoSimple1.getGanador();
        } else if (partidoSimple1.getIntGanador() == 0) {
            return partidoSimple2.getGanador();
        } else if (partidoSimple2.getIntGanador() == 0) {
            return partidoSimple1.getGanador();
        } else if (golesEquipo1() < golesEquipo2()) {
            return getEquipo2();
        }
        return getEquipo1();// contar goles
    }
}
