package griselda.alejandro.nopersistibles;

import giselda.alejandro.persistibles.Equipo;
import griselda.alejandro.Persistible;

/**
 * TODO: description
 */
public abstract class Partido implements Persistible {

    public Integer id;

    @Override
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
