package griselda.alejandro;

import griselda.alejandro.persistibles.Equipo;
import griselda.alejandro.persistibles.Formacion;
import griselda.alejandro.persistibles.Habilidad;
import griselda.alejandro.persistibles.Jugador;
import griselda.alejandro.persistibles.PartidoDeCopa;
import griselda.alejandro.persistibles.PartidoSimple;
import griselda.alejandro.persistibles.Tecnico;
import griselda.alejandro.persistibles.Titular;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.AbstractGraphDatabase;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class BaseDeDatos {

	private static final String CONTADORIDPROPERTY = "contadorid";
	private static final String CLASEPROPERTY      = "clase";
	private static final String INICIADOPROPERTY   = "iniciado";
	public static final String IDPROPERTY          = "id";
	private static final String FILEDATA           = "base2";
	
	public static AbstractGraphDatabase instanciaBD;
	
	private static List<Class<?>> getPersistibles(){		
		List<Class<?>> entidadesList = new LinkedList<Class<?>>();
		entidadesList.add(Equipo.class);
		entidadesList.add(Formacion.class);
		entidadesList.add(Habilidad.class);
		entidadesList.add(Jugador.class);
		entidadesList.add(PartidoDeCopa.class);
		entidadesList.add(PartidoSimple.class);
		entidadesList.add(Tecnico.class);
		entidadesList.add(Titular.class);
		return entidadesList;
	}
	
    
    public static void abrirBase() {
        instanciaBD = new EmbeddedGraphDatabase(FILEDATA);
        Transaction tx = instanciaBD.beginTx();
        try {
            if (!instanciaBD.getReferenceNode().hasProperty(INICIADOPROPERTY)) {
            	trace("Se crea una nueva base en " + FILEDATA);
                instanciaBD.getReferenceNode().setProperty(INICIADOPROPERTY, INICIADOPROPERTY);
                crearNodosClase();
            }
            else{
            	trace("Base existente en " + FILEDATA);                
            }
            tx.success();
        } finally {
            tx.finish();
        }
    }

	private static void crearNodosClase() {
		for (Class<?> cl : getPersistibles()) {
			Node equiposNode = instanciaBD.createNode();
		    equiposNode.setProperty(CLASEPROPERTY, cl.toString());
		    equiposNode.setProperty(CONTADORIDPROPERTY, 0);
		    instanciaBD.getReferenceNode().createRelationshipTo(equiposNode, Relacion.ROOT$CLASE);
		    trace("Se crea la clase " + cl.toString());
		}
	}

    
    public static void cerrarBase() {
        instanciaBD.shutdown();
    }

    public static void persistir(Persistible persistible) {
        abrirBase();
        Transaction tx = instanciaBD.beginTx();
        try {
            persistible.persistirEn(instanciaBD);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
    }
    
    public static Node persistirObjeto(Persistible persistible, Class<?> cl){
    	Node nodeObject;
        if (persistible.getId() == null) {
            /**obtiene un nodo con el id setteado y le settea el id a esta instancia*/
            nodeObject = getNuevoNodo(cl, persistible);
            persistible.setPersistir(true);
        } else {
            nodeObject = getNodeInstanciaDeClase(instanciaBD, cl, persistible.getId());
        }
        persistible.persistirSiEsNecesario(nodeObject);
        persistible.setPersistir(false);
        return nodeObject;
    }

    public static void persistirColeccion(List<?> persistibles) {
        abrirBase();
        Transaction tx = instanciaBD.beginTx();
        try {
            for (Object persistible : persistibles) {
                ((Persistible) persistible).persistirEn(instanciaBD);
            }
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.finish();
        }
        cerrarBase();
    }

    
    /**Obtiene el nodo que referencia a todos los*/
    /**objetos de la clase enviada en el parametro*/
    public static Node getNodoClaseROOT(Class<?> cl) {
        Iterable<Relationship> relaciones = instanciaBD.getReferenceNode().getRelationships(Relacion.ROOT$CLASE);
        /**Deberia haber al menos una relacion a un nodo de clase*/
        if (!relaciones.iterator().hasNext()) {
            throw new RuntimeException("No exiten relaciones de clase en el root.");
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();

            for (int i = 0; i < nodos.length; i++) {
                if (nodos[i].hasProperty(CLASEPROPERTY)) {
                    if (nodos[i].getProperty(CLASEPROPERTY).equals(cl.getCanonicalName())) {
                        return nodos[i];
                    }
                }
            }
        }
        /**el nodo se deberia haber creado en la instanciacion de la base**/
        throw new RuntimeException("No exiten nodo root de la clase "+ cl.getCanonicalName());
    }

    public synchronized static Integer getNuevoIdEntidad(Class<?> cl){
        Node nodoClase = getNodoClaseROOT(cl);
        Integer idInstancia = (Integer) nodoClase.getProperty(CONTADORIDPROPERTY);
        nodoClase.setProperty(CONTADORIDPROPERTY, (idInstancia + 1));
        return idInstancia;
    }

    public static Node getNodeInstanciaDeClase(AbstractGraphDatabase instanciaBD, Class<?> cl, Integer idInstancia) {
        Node nodoClase = getNodoClaseROOT(cl);
		Iterable<Relationship> relaciones = nodoClase.getRelationships(Relacion.CLASE$INSTANCIA);
        if (!relaciones.iterator().hasNext()) {
            throw new RuntimeException("No exiten ninguna instancia de la clase " + cl.getCanonicalName());
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (nodos[i].hasProperty(IDPROPERTY)) {
                    if (nodos[i].getProperty(IDPROPERTY).equals(idInstancia)) {
                        return nodos[i];
                    }
                }
            }
        }
        throw new RuntimeException("No exiten nodo de la clase "+ cl.getCanonicalName() +" con el id " + idInstancia);
    }

    public static boolean existeRelacion(Node nodoPadre, Node nodoHijo, Relacion relacionTipo) {
        Iterable<Relationship> relaciones = nodoPadre.getRelationships(relacionTipo);
        if (!relaciones.iterator().hasNext()) {
            return false;
        }
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
            	//TODO esto no soluciona el problema en relaciones hacia la misma instancia
                if (nodos[i].equals(nodoHijo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Map<String, Persistible> identityMap = new HashMap<String, Persistible>();

    public static void guardar(Persistible objeto) {
        identityMap.put(buildKey(objeto), objeto);
    }

    private static String buildKey(Persistible objeto) {
        return objeto.getClass().getCanonicalName() + "#" + objeto.getId();
    }

    private static String buildKey(Class<?> cl, Integer id) {
        return cl.getCanonicalName() + "#" + id;
    }

    @SuppressWarnings("unchecked")
	public static <T> T detectar(Class<T> cl, Integer idNodo) {
        return (T) identityMap.get(buildKey(cl, idNodo));
    }

    public static boolean existe(Class<?> cl, Integer idNodo) {
        return identityMap.containsKey(buildKey(cl, idNodo));
    }
    
    public static void relacionarYPersistir(Node nodoPadre, Persistible persistible, Relacion relacion) {
    	if(persistible != null){    		
	    	Node nodoHijo = persistible.persistirEn(instanciaBD);
	    	if (!existeRelacion(nodoPadre, nodoHijo, relacion)) {
	        	if(nodoPadre.hasRelationship(relacion)){
	        		eliminarRelaciones(nodoPadre, relacion);
	        	}
	            nodoPadre.createRelationshipTo(nodoHijo, relacion);
	        }
    	}
    }

    private static void eliminarRelaciones(Node nodoPadre, Relacion relacionTipo) {
    	Iterable<Relationship> relaciones = nodoPadre.getRelationships(relacionTipo);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();    
            relacion.delete();
        }
	}

	public static void crearRelacionEntre(Node nodoPadre, Node nodoHijo, Relacion relacion) {
        if (!existeRelacion(nodoPadre, nodoHijo, relacion)) {
            nodoPadre.createRelationshipTo(nodoHijo, relacion);
        }
    }
    
	public static void trace(String show) {
		System.out.println(show);
	}


	public static Node getNuevoNodo(Class<?> cl, Persistible persistible) {
        Node nuevoNodo = instanciaBD.createNode();
        Integer nuevoId = BaseDeDatos.getNuevoIdEntidad(cl);
		nuevoNodo.setProperty(IDPROPERTY, nuevoId );  
		persistible.setId(nuevoId);
        Node nodeClase = BaseDeDatos.getNodoClaseROOT(cl);
        nodeClase.createRelationshipTo(nodeClase, Relacion.CLASE$INSTANCIA);
		return nuevoNodo;
	}


	public static void relacionarYPersistirColeccion(Node nodeSelf,	List<?> persistibles, Relacion relacionTipo) {
		for (Object persistible : persistibles) {
            BaseDeDatos.crearRelacionEntre(nodeSelf, ((Persistible) persistible).persistirEn(instanciaBD), Relacion.EQUIPO$JUGADOR);
        }
	}


	//TODO
	/*public static <T extends Enum<T>> List<T> getColeccionEnumerablesDe(Node nodo, String nombreAtributo, Enum<T> cl) {
		List<T> instanciasEnum = new LinkedList<T>();
		String[] nombresEnums = (String[]) nodo.getProperty(nombreAtributo);
		for (String nombreEnum : nombresEnums) {
			//T nuevoEnum = cl.valueOf(nombreEnum);
			instanciasEnum.add((T) T.valueOf(cl,nombreEnum));
		}
		return instanciasEnum;
	}*/

	public static void relacionarYPersistirColeccionEnumerables(Node node, List<?> enumerables, String atributoNombre) {
		String enumerablesArray[] = new String[enumerables.size()];
        Integer arraySlot = 0;
        for (Object enumerable : enumerables) {
        	enumerablesArray[arraySlot] = enumerable.toString();
            arraySlot++;
        }
        node.setProperty(atributoNombre, enumerablesArray);  
	}

	public static <T extends Persistible> T getInstanciaDe(Node nodo, Relacion relacionTipo, Class<T> cl) {
		Node nodoHijo = getNodoPorRelacion(nodo, relacionTipo);
		if(nodoHijo.equals(null)){
			crearORetornarInstancia(cl, nodoHijo);
		}		
		return null;
	}


	@SuppressWarnings("unchecked")
	public static <T extends Persistible> T crearORetornarInstancia(Class<T> cl, Node nodoHijo) {
		Integer idNodo = (Integer) nodoHijo.getProperty(IDPROPERTY);
		if(!existe(cl, idNodo)){
			try {
				return (T) cl.newInstance().completeObjectWithNode(nodoHijo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detectar(cl, idNodo);
	}
	
	public static Node getNodoPorRelacion(Node nodo, Relacion relacionTipo){
		Iterable<Relationship> relaciones = nodo.getRelationships(relacionTipo);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(nodo)){
                    return nodos[i];
                }
            }
        }
        return null;
	}


	public static <T extends Persistible> List<T> getColeccionInstanciasDe(Node nodo, Relacion relacionTipo, Class<T> cl) {
		List<T> persistibles = new LinkedList<T>();
		Iterable<Relationship> relaciones = nodo.getRelationships(relacionTipo);
        for (Iterator<Relationship> iterator = relaciones.iterator(); iterator.hasNext();) {
            Relationship relacion = iterator.next();
            Node[] nodos = relacion.getNodes();
            for (int i = 0; i < nodos.length; i++) {
                if (!nodos[i].equals(nodo)){
                	persistibles.add(crearORetornarInstancia(cl, nodos[i]));
                }
            }
        }		
		return persistibles;
	}

	

}
