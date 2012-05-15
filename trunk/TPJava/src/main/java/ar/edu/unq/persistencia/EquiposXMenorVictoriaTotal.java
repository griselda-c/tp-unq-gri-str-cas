package ar.edu.unq.persistencia;

import java.util.List;

/**
 * TODO: description
 */
public class EquiposXMenorVictoriaTotal extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        List<Partido> partidos = Dao.getPartidosCopaDeEquipos();
        partidos.addAll(Dao.getPartidosSimples());
        return equiposXMayorVictoria(partidos, true);
    }

}
