package fr.hetic;

import java.util.function.BiFunction;

// Classe principale qui utilise le motif Strategy et la Factory
public class CalculateurStrategyFactoryFonctionnelle {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java fr.hetic.Calculatrice <nombre1> <nombre2> <+, -, '*'>");
            return;
        }

        long number1 = Long.parseLong(args[0]);
        long number2 = Long.parseLong(args[1]);
        String operation = args[2];

        // interface fonctionnelle
        BiFunction<Long, Long, Long> strategy = createStrategy(operation);
        long result = calculer(number1, number2, strategy);
        System.out.println(result);
    }

    public static long calculer(long number1, long number2, BiFunction<Long, Long, Long> strategy) {
        // Utilisation de la méthode apply de la classe BiFunction
        return strategy.apply(number1, number2);
    }

    public static BiFunction<Long, Long, Long> createStrategy(String operation) {
        switch (operation) {
            case "+":
                return (a, b) -> a + b;
            case "-":
                return (a, b) -> a - b;
            case "*":
                return (a, b) -> a * b;
            default:
                throw new IllegalArgumentException("L'opération n'est pas supportée");
        }
    }
}