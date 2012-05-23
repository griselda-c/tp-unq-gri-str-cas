package ar.edu.unq.reportes;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.persistencia.Dao;
import ar.edu.unq.persistencia.Equipo;
import ar.edu.unq.persistencia.Partido;

/**
 * TODO: description
 */
public class ObtenerHistorialDe extends GeneradorHistoriales {

    private int equipo1;

    private int equipo2;

    public ObtenerHistorialDe(int equipo1, int equipo2) {
        super();
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    @Override
    public String call() throws Exception {
        long inicio = System.currentTimeMillis();
        Equipo equipoA = Dao.getEquipoPorId(equipo1);
        Equipo equipoB = Dao.getEquipoPorId(equipo2);

        List<Partido> partidos = Dao.getPartidosSimplesDeEquipos(equipoA, equipoB);
        List<Partido> pGanadosXEquipoA = new LinkedList<Partido>();
        List<Partido> pGanadosXEquipoB = new LinkedList<Partido>();
        List<Partido> empatados = new LinkedList<Partido>();

        for (Partido prtd : partidos) {
            if (equipoA.equals(prtd.getGanador())) {
                pGanadosXEquipoA.add(prtd);
            } else if (equipoB.equals(prtd.getGanador())) {
                pGanadosXEquipoB.add(prtd);
            } else {
                empatados.add(prtd);
            }

        }

        String res = "";
        res += equipoA.getId() + " - " + equipoB.getId() + "\n";

        for (Partido prtd : pGanadosXEquipoA) {
            res += "   " + prtd.getGolesEquipo1() + "    -    " + prtd.getGolesEquipo2() + "       " + equipoA.getId()
                    + "\n";
        }

        for (Partido prtd : pGanadosXEquipoB) {
            res += "   " + prtd.getGolesEquipo1() + "    -    " + prtd.getGolesEquipo2() + "       " + equipoB.getId()
                    + "\n";
        }

        for (Partido prtd : empatados) {
            res += "   " + prtd.getGolesEquipo1() + "    -    " + prtd.getGolesEquipo2() + "       " + "Empate" + "\n";
        }
        System.out.println("Obtener historial de = " + (System.currentTimeMillis() - inicio) + " milisegundos");

        return res;
    }

}
