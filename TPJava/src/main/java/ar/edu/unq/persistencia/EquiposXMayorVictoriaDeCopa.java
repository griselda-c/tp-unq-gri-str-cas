package ar.edu.unq.persistencia;

import java.util.List;

public class EquiposXMayorVictoriaDeCopa extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        List<Partido> partidosCopa = Dao.getPartidosCopaDeEquipos();
        Dao.getPartidosSimples();
        return equiposXMayorVictoria(partidosCopa, true);
    }

}
