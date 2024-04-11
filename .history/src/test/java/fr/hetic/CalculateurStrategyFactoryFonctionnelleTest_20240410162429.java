import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculateurStrategyFactoryFonctionnelleTest {
    @Test
    public void testAddition() {
        long result = CalculateurStrategyFactoryFonctionnelle.calculer(2, 3, (a, b) -> a + b);
        assertEquals(5, result);
    }

    // Ici d'autres tests pour les autres opérations
}