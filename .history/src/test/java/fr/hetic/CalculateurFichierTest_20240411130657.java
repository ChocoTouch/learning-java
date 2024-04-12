package fr.hetic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculateurFichierTest {

    @Test
    public void testCalculFichier() {
        String result = CalculateurFichier.calculer("5", "2", "+");
        assertEquals("7", result);
    }

    @Test
    public void testCalculFichierError() {
        String result = CalculateurFichier.calculer("12", "4", "b");
        assertEquals("ERREUR", result);
    }

    @Test
    public void testCalculFichierError2() {
        String result = CalculateurFichier.calculer("33", "z", "+");
        assertEquals("ERREUR", result);
    }

}
