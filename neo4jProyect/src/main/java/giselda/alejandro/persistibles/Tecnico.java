package giselda.alejandro.persistibles;

import griselda.alejandro.BaseDeDatos;
import griselda.alejandro.BaseDeDatos.Relacion;
import griselda.alejandro.Persistible;
import griselda.alejandro.nopersistibles.FormacionStrategy;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public class Tecnico implements Persistible {

    public String nombre;

    public String apellido;

    public Integer id;

    public FormacionStrategy formacion;

    public Tecnico() {
        super();
        this.id = null;
        this.nombre = null;
        this.apellido = null;
    }

    public Tecnico(String nombre, String apellido) {
        super();
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public Persistible createObjectOfNode(Node nodo) {
        return null;
        // TODO

    }

    @Override
    public Node persistirEn(AbstractGraphDatabase instancia) {
        Node nodeSelf;
        if (this.id == null) {
            this.id = BaseDeDatos.getNewIdEntidad(instancia, BaseDeDatos.TECNICOS);
            nodeSelf = instancia.createNode();
            nodeSelf.setProperty("id", this.id);
            Node nodeEntidades = BaseDeDatos.getNodoEntidad(instancia, BaseDeDatos.TECNICOS);
            nodeEntidades.createRelationshipTo(nodeSelf, Relacion.ENTIDAD);
        } else {
            nodeSelf = BaseDeDatos.getInstanciaFromEntidad(instancia, BaseDeDatos.TECNICOS, this.id);
        }

        // self properties
        nodeSelf.setProperty("nombre", this.nombre);
        nodeSelf.setProperty("apellido", this.apellido);

        return nodeSelf;
    }

    // access

    @Override
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public Formacion armarFormacion(Equipo unEquipo) {
        formacion = new FormacionStrategy();
        return this.formacion.armarFormacion(unEquipo);
    }

}
