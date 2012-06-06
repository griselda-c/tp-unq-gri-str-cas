package ar.edu.unq.reportes;

import java.util.List;

import ar.edu.unq.persistencia.Dao;
import ar.edu.unq.persistencia.Partido;

public class EquiposXMayorVictoriaDeCopa extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        long inicio = System.currentTimeMillis();
        List<Partido> partidosCopa = Dao.getPartidosCopaDeEquipos();
        Dao.getPartidosSimples();
        String res = equiposXMayorVictoria(partidosCopa, true);
        System.out.println("Historial eq x > victoria copa = " + (System.currentTimeMillis() - inicio)
                + " milisegundos");

        return res;
    }

}
