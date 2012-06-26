package griselda.alejandro;

import griselda.alejandro.BaseDeDatos.Relacion;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

/**
 * TODO: description
 */
public class Equipo implements Persistible {

    String nombre;

    Integer id;

    List<Jugador> jugadores;

    public Equipo() {
        super();
        this.id = null;
        this.nombre = null;
        this.jugadores = new LinkedList<Jugador>();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.id = null;
        this.jugadores = new LinkedList<Jugador>();
    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.EQUIPOS);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("nombre", this.nombre);
            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.EQUIPOS);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.EQUIPOS, this.id);
            nodeSelf.setProperty("nombre", this.nombre);
        }
        for (Jugador jugador : this.jugadores) {
            Node jugadorNode = jugador.persistirEn(instancia);
            if (!BaseDeDatos.existeRelacion(nodeSelf, jugadorNode, Relacion.EQUIPOJUGADOR)) {
                nodeSelf.createRelationshipTo(jugadorNode, Relacion.EQUIPOJUGADOR);
            }
        }
        return nodeSelf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

}
