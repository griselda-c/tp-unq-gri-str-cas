package griselda.alejandro;

import griselda.alejandro.reportes.EmpateXPares;

import java.util.Iterator;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class MainReportes {
	public static void main(String[] args) {	
		
		String resutado = BaseDeDatos.runInSession(new EmpateXPares());
		//String resutado = BaseDeDatos.runInSession(new EquipoConMenorVicTotal());
		System.out.println(resutado);		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void buscarEquiposDe(Node node) {
				
		/*BaseDeDatos.abrirBase();
		Transaction tx = BaseDeDatos.instanciaBD.beginTx();
		
		try{
			Node nodoPartidoSimple = BaseDeDatos.getNodoClaseROOT(PartidoSimple.class);
			
			Iterable<Relationship> relaciones = nodoPartidoSimple.getRelationships(Relacion.CLASE$INSTANCIA);
	        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
	            Relationship relacion = iterator.next();    
	            Node[] nodos = relacion.getNodes();
	            for (int i = 0; i < nodos.length; i++) {
	                if (!nodos[i].equals(nodoPartidoSimple)){
	                	// nodos[i] es un partidoSimple
	                	//System.out.println(nodos[i].getProperty("golesEquipo1"));
	                	buscarEquiposDe(nodos[i]);
	                }
	            }
	        }	
	        tx.success();
		}
        catch (Exception e) {
			e.printStackTrace();
		}      
		finally{
			tx.finish();
		}
		BaseDeDatos.cerrarBase();*/
		
		Iterable<Relationship> relaciones = node.getRelationships(Relacion.PSIMPLE$EQUIPO1);
		if (!relaciones.iterator().hasNext()) {
            System.out.println("no se encontraron relaciones");
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();    
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(node)){
                	// nodos[i] es un equipo
                	System.out.println(nodos[i].getProperty("nombre"));                	
                }
            }
        }		
	}
}
