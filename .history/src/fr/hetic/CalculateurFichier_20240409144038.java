package fr.hetic;

import fr.hetic.Calculateur;
import java.io.File;
import java.io.FilenameFilter;

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

        System.out.println(fichiers.length);

    }
}