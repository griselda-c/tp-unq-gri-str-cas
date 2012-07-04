package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.DAO;
import griselda.alejandro.Persistible;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Formacion implements Persistible {

    public Integer id;

    public Equipo equipo;

    public List<Titular> titulares;

    public List<Jugador> suplentes;

    public Formacion() {
        this.id = null;
        this.equipo = null;
        this.titulares = new LinkedList<Titular>();
        this.suplentes = new LinkedList<Jugador>();
    }

    public Formacion(Equipo equipo) {
        this.id = null;
        this.equipo = equipo;
        this.titulares = new LinkedList<Titular>();
        this.suplentes = new LinkedList<Jugador>();
    }

    @Override
    public Persistible completeObjectWithNode(Node nodo) {
        Integer idNodo = (Integer) nodo.getProperty("id");
        if (BaseDeDatos.existe(Equipo.class, idNodo)) {
            return BaseDeDatos.detectar(Equipo.class, idNodo);
        } else {
            this.id = idNodo;
            this.suplentes = DAO.getJugadoresFrom(nodo, Relacion.FORMACIONSUPLENTE);
            this.titulares = DAO.getTitularesFrom(nodo, Relacion.FORMACIONTITULAR);
            BaseDeDatos.guardar(this);
        }
        return this;
    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.FORMACIONES);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.FORMACIONES);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.FORMACIONES, this.id);
        }

        // many-to-one
        // El equipo siempre crea la formacion
        // BaseDeDatos.crearRelacionEntre(nodeSelf, equipo.persistirEn(instancia), Relacion.FORMACIONEQUIPO);

        // one-to-many
        for (Titular titular : this.titulares) {
            BaseDeDatos.crearRelacionEntre(nodeSelf, titular.persistirEn(instancia), Relacion.FORMACIONTITULAR);
        }

        for (Jugador suplente : this.suplentes) {
            BaseDeDatos.crearRelacionEntre(nodeSelf, suplente.persistirEn(instancia), Relacion.FORMACIONSUPLENTE);
        }

        return nodeSelf;
    }

    // access
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Titular> getTitulares() {
        return titulares;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void setTitulares(List<Titular> titulares) {
        this.titulares = titulares;
    }

    public void addTitular(Titular unTitular) {
        this.titulares.add(unTitular);
    }

    public void setSuplentes(List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

}
