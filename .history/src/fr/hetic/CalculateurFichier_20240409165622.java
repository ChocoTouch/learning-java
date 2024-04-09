package fr.hetic;

import java.io.*;

public class CalculateurFichier {
    public static void main(String[] args) throws FileNotFoundException {
        String dossierOp = args[0];

        File dossier = new File(dossierOp);
        File[] fichiers = dossier.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".op");
            }
        });

        if (fichiers == null) {
            System.out.println("Le dossier est vide");
            return;
        }

        for (File fichier : fichiers) {
            PrintWriter writer = new PrintWriter(dossierOp + "/" + fichier.getName().replace(".op", ".res"));

            try {
                BufferedReader reader = new BufferedReader(new FileReader(fichier));
                String ligne = reader.readLine();
                while (ligne != null) {
                    String[] elements = ligne.split(" ");
                    writer.println(calculer(elements[0], elements[1], elements[2]));

                    ligne = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }

    public static String calculer(String number1, String number2, String operation) {
        if (!isLong(number1) || !isLong(number2)) {
            return "ERREUR";
        }

        long number1Long = Long.parseLong(number1);
        long number2Long = Long.parseLong(number2);

        return switch (operation) {
            case "+" -> String.valueOf(number1Long + number2Long);
            case "-" -> String.valueOf(number1Long - number2Long);
            case "*" -> String.valueOf(number1Long * number2Long);
            default -> "ERREUR";
        };
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}