package griselda.alejandro;

import griselda.alejandro.persistibles.Equipo;
import griselda.alejandro.persistibles.Jugador;
import griselda.alejandro.persistibles.PartidoSimple;

import java.util.List;

public class Dao {
	
		
	
	public static List<Jugador> getJugadores() {
		return BaseDeDatos.getColeccionInstanciasDe(BaseDeDatos.getNodoClaseROOT(Jugador.class), Relacion.CLASE$INSTANCIA, Jugador.class);
    }

    public static List<Equipo> getEquipos() {
    	return BaseDeDatos.getColeccionInstanciasDe(BaseDeDatos.getNodoClaseROOT(Equipo.class), Relacion.CLASE$INSTANCIA, Equipo.class);
    }
    
    public static List<PartidoSimple> getPartidosSimples() {
    	return BaseDeDatos.getColeccionInstanciasDe(BaseDeDatos.getNodoClaseROOT(PartidoSimple.class), Relacion.CLASE$INSTANCIA, PartidoSimple.class);
    }
	
    /*public static Jugador getJugadorPorNombre(String nombre) {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Jugador.class);
        q.add(Restrictions.eq("nombre", nombre));
        List<Jugador> l = q.list();
        return l.get(0);
    }

   


    public static List<Partido> getPartidosCopaDeEquipos() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(PartidoDeCopa.class);
        q.setCacheable(true);
        List<Partido> l = q.list();
        return l;
    }


    public static List<PartidoSimple> getPartidosSimplesDeEquipos(Equipo eq1, Equipo eq2) {

        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(PartidoSimple.class);
        q.setCacheable(true);
        q.add(Restrictions
                .disjunction()
                .add(Restrictions.conjunction().add(Restrictions.eq("equipo1", eq1))
                        .add(Restrictions.eq("equipo2", eq2)))
                .add(Restrictions.conjunction().add(Restrictions.eq("equipo1", eq2))
                        .add(Restrictions.eq("equipo2", eq1))));

        Criteria criteriaEquipo1 = q.createCriteria("equipo1");
        criteriaEquipo1.setFetchMode("tecnico", FetchMode.SELECT);
        criteriaEquipo1.setFetchMode("formacion", FetchMode.SELECT);

        Criteria criteriaEquipo2 = q.createCriteria("equipo2");
        criteriaEquipo2.setFetchMode("tecnico", FetchMode.SELECT);
        criteriaEquipo2.setFetchMode("formacion", FetchMode.SELECT);

        return q.list();
    }

    public static Equipo getEquipoPorId(int nombre) {
        Session session = SessionManager.getSession();
        return (Equipo) session.get(Equipo.class, nombre);
    }

    public static List<Formacion> getFormaciones() {
        Session session = SessionManager.getSession();
        Criteria q = session.createCriteria(Formacion.class);
        List<Formacion> l = q.list();
        return l;
    }*/

}
