package fr.hetic;

public class Calculateur {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java fr.hetic.Calculatrice <nombre1> <nombre2> <+, -, ''>");
            return;
        }

        long number1 = Long.parseLong(args[0]);
        long number2 = Long.parseLong(args[1]);
        String operation = args[2];

        calculer(number1, number2, operation);
    }

    public static void calculer(long number1, long number2, String operation) {
        switch (operation) {
            case "+":
                System.out.println(number1 + number2);
                break;
            case "-":
                System.out.println(number1 - number2);
                break;
            case "":
                System.out.println(number1 * number2);
                break;
            default:
                System.out.println("L'opération n'est pas supportée");
        }
    }
}