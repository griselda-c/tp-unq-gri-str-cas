package ar.edu.unq.persistencia;

import java.util.LinkedList;
import java.util.List;

public class EmpateXPares extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        List<Partido> empates = empates();
        List<Equipo> equipos = Dao.getEquipos();
        List<DuplaEquipos> duplas = new LinkedList<DuplaEquipos>();
        Equipo actual;
        while (!equipos.isEmpty()) {
            actual = equipos.get(0);
            equipos.remove(actual);
            for (Equipo eq : equipos) {
                duplas.add(new DuplaEquipos(actual, eq));
            }
        }
        for (Partido p : empates) {
            for (DuplaEquipos d : duplas) {
                if (d.poseeA(p.getEquipo1(), p.getEquipo2())) {
                    d.incrementarEmpates();
                }
            }
        }
        String res = "";
        for (DuplaEquipos ds : duplas) {
            res += "   " + ds.getEq1().getId() + "   -   " + ds.getEq2().getId() + "   -   " + ds.getCantidadEmpates()
                    + "\n";
        }
        return res;
    }

}
