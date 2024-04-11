package fr.hetic;

// Interface pour la stratégie de calcul
interface CalculStrategy {
    long calculer(long number1, long number2);
}

// Stratégie d'addition
class Addition implements CalculStrategy {
    @Override
    public long calculer(long number1, long number2) {
        return number1 + number2;
    }
}

// Stratégie de soustraction
class Soustraction implements CalculStrategy {
    @Override
    public long calculer(long number1, long number2) {
        return number1 - number2;
    }
}

// Stratégie de multiplication
class Multiplication implements CalculStrategy {
    @Override
    public long calculer(long number1, long number2) {
        return number1 * number2;
    }
}

// Factory pour créer les stratégies en fonction de l'opérateur
class CalculStrategyFactory {
    public static CalculStrategy createStrategy(String operation) {
        switch (operation) {
            case "+":
                return new Addition();
            case "-":
                return new Soustraction();
            case "*":
                return new Multiplication();
            default:
                throw new IllegalArgumentException("L'opération n'est pas supportée");
        }
    }
}

// Classe principale qui utilise le motif Strategy et la Factory
public class Calculateur {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java fr.hetic.Calculatrice <nombre1> <nombre2> <+, -, '*'>");
            return;
        }

        long number1 = Long.parseLong(args[0]);
        long number2 = Long.parseLong(args[1]);
        String operation = args[2];

        CalculStrategy strategy = CalculStrategyFactory.createStrategy(operation);
        long result = calculer(number1, number2, strategy);
        System.out.println("Résultat : " + result);
    }

    public static long calculer(long number1, long number2, CalculStrategy strategy) {
        return strategy.calculer(number1, number2);
    }
}