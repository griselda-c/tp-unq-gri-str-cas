package griselda.alejandro.nopersistibles;

import griselda.alejandro.Persistible;
import griselda.alejandro.persistibles.Equipo;

/**
 * TODO: description
 */
public abstract class Partido implements Persistible {

   public abstract Equipo getGanador();

    public abstract Integer getGolesEquipo1();

    public abstract Integer getGolesEquipo2();

    public abstract Equipo getEquipo1();

    public abstract Equipo getEquipo2();

}
