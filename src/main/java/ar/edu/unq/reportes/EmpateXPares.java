package ar.edu.unq.reportes;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unq.persistencia.Dao;
import ar.edu.unq.persistencia.Equipo;
import ar.edu.unq.persistencia.Partido;

public class EmpateXPares extends GeneradorHistoriales {

    @Override
    public String call() throws Exception {
        long inicio = System.currentTimeMillis();
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
        System.out.println("Historial empate x pares = " + (System.currentTimeMillis() - inicio) + " milisegundos");
        return res;
    }

}
