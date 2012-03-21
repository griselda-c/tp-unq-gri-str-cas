package ar.edu.unq.persistencia;

/**
 * TODO: description
 */
public class PartidoSimple extends Partido {

    public Equipo getGanador() {
        if (this.golesEquipo1 > this.golesEquipo2) {
            return this.Equipo1;
        }

        else {
            return this.Equipo2;
        }

    }

}
