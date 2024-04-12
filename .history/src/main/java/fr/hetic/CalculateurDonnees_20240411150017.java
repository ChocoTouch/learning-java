package fr.hetic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CalculateurDonnees {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String utilisateur = "etudiant";
        String motDePasse = "MT4@hetic2324";

        try {
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion établie avec succès !");
            // Vous pouvez maintenant effectuer des opérations sur la base de données à travers la connexion
            // N'oubliez pas de fermer la connexion lorsque vous avez terminé :`


            // connexion.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
}