package ar.edu.unq.persistencia;

public class Tecnico {

    private FormacionStrategy formacion = new FormacionStrategy();

    public Formacion armarFormacion(Equipo unEquipo) {
        return this.formacion.armarFormacion(unEquipo);
    }

}
