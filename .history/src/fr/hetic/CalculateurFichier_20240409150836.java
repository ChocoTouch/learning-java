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
                    long number1 = Long.parseLong(elements[0]);
                    long number2 = Long.parseLong(elements[1]);
                    String operation = elements[2];
                    long result = Calculateur.calculer(number1, number2, operation);

                    writer.println(result);

                    ligne = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }


    }
}