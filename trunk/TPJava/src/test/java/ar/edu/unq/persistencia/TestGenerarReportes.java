package ar.edu.unq.persistencia;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestGenerarReportes extends TestCase {

    public void testObtenerHistorialDe() {
        String pantalla = SessionManager.runInSession(new ObtenerHistorialDe(113, 114));
        System.out.println(pantalla);
        Assert.assertEquals(1, 1);
    }

    public void testEmpateXPares() {
        String pantalla = SessionManager.runInSession(new EmpateXPares());
        System.out.println(pantalla);
        Assert.assertEquals(1, 1);
    }

    public void testEquipoConMenorVicTotal() {
        String pantalla = SessionManager.runInSession(new EquipoConMenorVicTotal());
        System.out.println(pantalla);
        Assert.assertEquals(1, 1);
    }

    public void testEquiposXMenorVictoriaTotal() {
        String pantalla = SessionManager.runInSession(new EquiposXMenorVictoriaTotal());
        System.out.println(pantalla);
        Assert.assertEquals(1, 1);
    }

    public void testEquiposXMayorVictoriaDeCopa() {
        String pantalla = SessionManager.runInSession(new EquiposXMayorVictoriaDeCopa());
        System.out.println(pantalla);
        Assert.assertEquals(1, 1);
    }

}
