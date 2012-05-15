package ar.edu.unq.persistencia;

public class EquipoConMenorVicTotal extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        return equiposXMayorVictoria(Dao.getPartidosSimples(), false);
    }
}
