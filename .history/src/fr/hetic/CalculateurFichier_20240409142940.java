package fr.hetic;

import fr.hetic.Calculateur;
import java.io.File;

public class CalculateurFichier {
    public static void main(String[] args) {

        String dossierOp = args[0];

        File dossier = new File(dossierOp);

        File[] fichiers = dossier.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".op");
            }
        });

        PrintWriter writer = new PrintWriter(new File(dossierOp + "/" + fichier.getName().replace(".op", ".res")));

        for (File fichier : fichiers) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fichier));
                String ligne = reader.readLine();
                while (ligne != null) {
                    String[] elements = ligne.split(" ");
                    long number1 = Long.parseLong(elements[0]);
                    long number2 = Long.parseLong(elements[1]);
                    String operation = elements[2];
                    long result = calculer(number1, number2, operation);

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