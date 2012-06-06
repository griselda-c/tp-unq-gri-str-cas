package ar.edu.unq.reportes;

import java.util.List;

import ar.edu.unq.persistencia.Dao;
import ar.edu.unq.persistencia.Partido;

/**
 * TODO: description
 */
public class EquiposXMenorVictoriaTotal extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        long inicio = System.currentTimeMillis();
        List<Partido> partidos = Dao.getPartidosCopaDeEquipos();
        partidos.addAll(Dao.getPartidosSimples());
        String res = equiposXMayorVictoria(partidos, true);
        System.out.println("Eq x > victorias = " + (System.currentTimeMillis() - inicio) + " milisegundos");
        return res;
    }

}
