package ar.edu.unq.persistencia;

public class Tecnico {

    public Tecnico() {
        super();
    }

    public Tecnico(String nombre, String apellido) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String nombre;

    private String apellido;

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

    private FormacionStrategy formacion;

    public Formacion armarFormacion(Equipo unEquipo) {
        formacion = new FormacionStrategy();
        return this.formacion.armarFormacion(unEquipo);
    }

}
