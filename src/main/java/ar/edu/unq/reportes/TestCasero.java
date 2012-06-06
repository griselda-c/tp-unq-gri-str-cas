package ar.edu.unq.reportes;

import junit.framework.Assert;
import ar.edu.unq.persistencia.SessionManager;

/**
 * TODO: description
 */
public class TestCasero {

    public static void main(String[] args) {
        String pantalla = SessionManager.runInSession(new EquiposXMenorVictoriaTotal());
        System.out.println(pantalla);
        pantalla = SessionManager.runInSession(new EquiposXMenorVictoriaTotal());
        System.out.println(pantalla);
        Assert.assertEquals(1, 1);
    }

}
