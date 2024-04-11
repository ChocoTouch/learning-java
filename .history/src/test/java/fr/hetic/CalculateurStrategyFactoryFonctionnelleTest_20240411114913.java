package fr.hetic;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculateurStrategyFactoryFonctionnelleTest {
    @Test
    public void testAddition() {
        long result = CalculateurStrategyFactoryFonctionnelle.calculer(10, 3, (a, b) -> a + b);
        assertEquals(13, result);
    }

    @Test
    public void testSoustraction() {
        long result = CalculateurStrategyFactoryFonctionnelle.calculer(12, 7, (a, b) -> a - b);
        assertEquals(5, result);
    }

    @Test
    public void testMultiplication() {
        long result = CalculateurStrategyFactoryFonctionnelle.calculer(3, 3, (a, b) -> a * b);
        assertEquals(9, result);
    }

    // Ici d'autres tests pour les autres opérations
}