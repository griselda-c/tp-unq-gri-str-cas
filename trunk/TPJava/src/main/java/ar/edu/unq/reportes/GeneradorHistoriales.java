package ar.edu.unq.reportes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import ar.edu.unq.persistencia.Dao;
import ar.edu.unq.persistencia.Equipo;
import ar.edu.unq.persistencia.Partido;

public abstract class GeneradorHistoriales implements Callable<String> {
    public String equiposXMayorVictoria(List<Partido> partidos, boolean menorAMayor) {
        Dao.getPartidosSimples();
        List<Equipo> equipos = Dao.getEquipos();

        Map<Equipo, Integer> equiposVictorias = new HashMap<Equipo, Integer>();
        for (Equipo eq : equipos) {
            equiposVictorias.put(eq, 0);
        }

        for (Partido eqc : partidos) {
            if (eqc.getGanador() != null) {
                Integer temp = equiposVictorias.get(eqc.getGanador()) + 1;
                equiposVictorias.put(eqc.getGanador(), temp);
            }
        }

        List<Equipo> equiposOrdenados = new LinkedList<Equipo>();

        Equipo etemp;

        while (!equipos.isEmpty()) {

            if (menorAMayor) {
                etemp = buscarConMenosVictorias(equiposVictorias, equipos);
            } else {
                etemp = buscarConMasVictorias(equiposVictorias, equipos);
            }
            equipos.remove(etemp);
            equiposOrdenados.add(etemp);
        }

        String res = "";

        for (Equipo prtd : equiposOrdenados) {
            res += "   " + prtd.getId() + "    -    " + equiposVictorias.get(prtd) + "\n";
        }

        return res;

    }

    private Equipo buscarConMasVictorias(Map<Equipo, Integer> equiposVictorias, List<Equipo> equipos) {
        Equipo equipoMasVictoria = equipos.get(0);
        for (Equipo eqv : equipos) {
            if (equiposVictorias.get(equipoMasVictoria) < equiposVictorias.get(eqv)) {
                equipoMasVictoria = eqv;
            }
        }
        return equipoMasVictoria;
    }

    private Equipo buscarConMenosVictorias(Map<Equipo, Integer> equiposVictorias, List<Equipo> equipos) {
        Equipo equipoMenorVictoria = equipos.get(0);
        for (Equipo eqv : equipos) {
            if (equiposVictorias.get(equipoMenorVictoria) > equiposVictorias.get(eqv)) {
                equipoMenorVictoria = eqv;
            }
        }
        return equipoMenorVictoria;
    }

    public class DuplaEquipos {
        public Equipo eq1;

        public Equipo eq2;

        public Equipo getEq1() {
            return eq1;
        }

        public Equipo getEq2() {
            return eq2;
        }

        public Integer cantidadEmpates = 0;

        public Integer getCantidadEmpates() {
            return cantidadEmpates;
        }

        public DuplaEquipos(Equipo eq1, Equipo eq2) {
            this.eq1 = eq1;
            this.eq2 = eq2;
        }

        public void incrementarEmpates() {
            this.cantidadEmpates++;
        }

        public boolean poseeA(Equipo eqA1, Equipo eqA2) {
            return (this.eq1.equals(eqA1) & this.eq2.equals(eqA2)) | (this.eq2.equals(eqA1) & this.eq1.equals(eqA2));
        }

    }

    public List<Partido> empates() {
        List<Partido> simple = Dao.getPartidosSimplesEmpty();
        List<Partido> empates = new LinkedList<Partido>();
        for (Partido simp : simple) {
            if (simp.getGanador() == null) {
                empates.add(simp);
            }
        }
        return empates;
    }
}
