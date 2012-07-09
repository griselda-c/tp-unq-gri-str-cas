package griselda.alejandro.reportes;

import java.util.LinkedList;
import java.util.List;

import griselda.alejandro.Dao;
import griselda.alejandro.nopersistibles.Partido;


public class EquipoConMenorVicTotal extends GeneradorHistoriales {

    public String call() throws Exception {
        long inicio = System.currentTimeMillis();
        List<Partido> partidosSimples = new LinkedList<Partido>();
        partidosSimples.addAll(Dao.getPartidosSimples());       
        
        String res = equiposXMayorVictoria(partidosSimples, false);
        long fin = System.currentTimeMillis() - inicio;
		System.out.println("Historial eq x < victoria = " + fin + " milisegundos");
        return res;
    }
}