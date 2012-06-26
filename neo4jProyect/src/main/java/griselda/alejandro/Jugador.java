package griselda.alejandro;

import griselda.alejandro.BaseDeDatos.Relacion;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

/**
 * TODO: description
 */
public class Jugador implements Persistible {
    public String nombre;

    public Integer id;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.id = null;
    }

    public Jugador() {
        super();
        this.nombre = null;
        this.id = null;
    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.JUGADORES);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("nombre", this.nombre);
            nodeSelf.setProperty("id", this.id);
            Node nodeJugadores = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.JUGADORES);
            nodeJugadores.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.JUGADORES, this.id);
            nodeSelf.setProperty("nombre", this.nombre);

        }
        return nodeSelf;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
