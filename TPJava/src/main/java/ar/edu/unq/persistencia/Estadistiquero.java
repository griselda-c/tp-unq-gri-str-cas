package ar.edu.unq.persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TODO: description
 */
public class Estadistiquero {

    public static String obtenerHistorialDe(int equipo1, int equipo2) {
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

        return res;

    }

    public static String equiposXMayorVictoriaDeCopa() {
        List<Partido> partidosCopa = Dao.getPartidosCopaDeEquipos();
        Dao.getPartidosSimples();
        return equiposXMayorVictoria(partidosCopa, true);
    }

    public static String equiposXMenorVictoriaTotal() {
        List<Partido> partidos = Dao.getPartidosCopaDeEquipos();
        partidos.addAll(Dao.getPartidosSimples());

        return equiposXMayorVictoria(partidos, true);
    }

    public static String equiposXMayorVictoria(List<Partido> partidos, boolean menorAMayor) {
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

    private static Equipo buscarConMasVictorias(Map<Equipo, Integer> equiposVictorias, List<Equipo> equipos) {
        Equipo equipoMasVictoria = equipos.get(0);
        for (Equipo eqv : equipos) {
            if (equiposVictorias.get(equipoMasVictoria) < equiposVictorias.get(eqv)) {
                equipoMasVictoria = eqv;
            }
        }
        return equipoMasVictoria;
    }

    private static Equipo buscarConMenosVictorias(Map<Equipo, Integer> equiposVictorias, List<Equipo> equipos) {
        Equipo equipoMenorVictoria = equipos.get(0);
        for (Equipo eqv : equipos) {
            if (equiposVictorias.get(equipoMenorVictoria) > equiposVictorias.get(eqv)) {
                equipoMenorVictoria = eqv;
            }
        }
        return equipoMenorVictoria;
    }

    public static class DuplaEquipos {
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

    public static List<Partido> empates() {
        List<Partido> simple = Dao.getPartidosSimples();
        List<Partido> empates = new LinkedList<Partido>();
        for (Partido simp : simple) {
            if (simp.getGanador() == null) {
                empates.add(simp);
            }
        }
        return empates;
    }

    public static String empateXPares() {
        List<Partido> empates = empates();
        List<Equipo> equipos = Dao.getEquipos();
        List<Estadistiquero.DuplaEquipos> duplas = new LinkedList<Estadistiquero.DuplaEquipos>();
        Equipo actual;
        while (!equipos.isEmpty()) {
            actual = equipos.get(0);
            equipos.remove(actual);
            for (Equipo eq : equipos) {
                duplas.add(new Estadistiquero.DuplaEquipos(actual, eq));
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

    public static void main(String[] args) {
        System.out.println(empateXPares());
    }

    public static String EquipoConMenorVicTotal() {

        return equiposXMayorVictoria(Dao.getPartidosSimples(), false);

    }
}
