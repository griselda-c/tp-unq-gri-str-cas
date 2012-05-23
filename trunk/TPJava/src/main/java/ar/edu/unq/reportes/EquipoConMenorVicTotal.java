package ar.edu.unq.reportes;

import ar.edu.unq.persistencia.Dao;

public class EquipoConMenorVicTotal extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        long inicio = System.currentTimeMillis();
        String res = equiposXMayorVictoria(Dao.getPartidosSimples(), false);
        System.out.println("Historial eq x < victoria = " + (System.currentTimeMillis() - inicio) + " milisegundos");
        return res;
    }
}
