package fr.hetic;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CalculateurDonnees {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
        String utilisateur = "etudiant";
        String motDePasse = "MT4@hetic2324";

        try {
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion établie avec succès !");
            
            // Récupération de la liste des tables de la bdd
            /* 
            // Création de l'instruction SQL pour récupérer les noms de table
            String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'";
            Statement statement = connexion.createStatement();
            
            // Exécution de la requête SQL
            ResultSet resultSet = statement.executeQuery(sql);
            
            // Parcours des résultats
            System.out.println("Tables dans la base de données :");
            while(resultSet.next()) {
                // Récupération du nom de la table
                String tableName = resultSet.getString("table_name");
                
                // Affichage du nom de la table
                System.out.println(tableName);
            }
            */
            
             
            // Récupération du nom des colonnes d'une table
            /*
            // Création de l'instruction SQL pour décrire la structure de la table
            String sql = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'ligne'";
            Statement statement = connexion.createStatement();
            
            // Exécution de la requête SQL
            ResultSet resultSet = statement.executeQuery(sql);
            
            // Parcours des résultats
            System.out.println("Structure de la table 'ligne' :");
            while(resultSet.next()) {
                // Récupération des informations sur les colonnes
                String columnName = resultSet.getString("column_name");
                String dataType = resultSet.getString("data_type");
                
                // Affichage des informations sur les colonnes
                System.out.println("Nom de colonne : " + columnName + ", Type de données : " + dataType);
            }
            */
            
            // Récupération des fichiers de type "OP"
            List<Fichier> fichiers = new ArrayList<>();
            String sqlFichiers = "SELECT id, nom, type FROM fichier WHERE type = 'OP'";
            Statement statementFichiers = connexion.createStatement();
            ResultSet resultSetFichiers = statementFichiers.executeQuery(sqlFichiers);
            while (resultSetFichiers.next()) {
                int idFichier = resultSetFichiers.getInt("id");
                String nomFichier = resultSetFichiers.getString("nom");
                String typeFichier = resultSetFichiers.getString("type");
                Fichier fichier = new Fichier(idFichier, nomFichier, typeFichier);
                fichiers.add(fichier);
            }
            resultSetFichiers.close();
            statementFichiers.close();

            // Traitement des fichiers
            for (Fichier fichier : fichiers) {
                try (FileWriter writer = new FileWriter("operations/"+ fichier.getNom() + ".res")) {
                    System.out.println("Traitement du fichier '" + fichier.getNom() + "'dans le fichier operations situe a la racine :");
                    String sqlLignes = "SELECT param1, param2, operateur, fichier_id, position FROM ligne WHERE fichier_id = " + fichier.getId() + " ORDER BY position";
                    Statement statementLignes = connexion.createStatement();
                    ResultSet resultSetLignes = statementLignes.executeQuery(sqlLignes);
                    while (resultSetLignes.next()) {
                        String param1 = String.valueOf(resultSetLignes.getInt("param1"));
                        String param2 = String.valueOf(resultSetLignes.getInt("param2"));
                        String operateur = resultSetLignes.getString("operateur");
                        Ligne ligne = new Ligne(fichier.getId(), resultSetLignes.getInt("position"), param1, param2, operateur);
                        String resultat;
                        try {
                            resultat = CalculateurFichier.calculer(param1, param2, operateur);
                        } catch (IllegalArgumentException e) {
                            resultat = "ERREUR";
                        }
                        writer.write(resultat + "\n");
                    }
                    resultSetLignes.close();
                    statementLignes.close();
                } catch (IOException e) {
                    System.out.println("Erreur lors de l'écriture du fichier " + fichier.getNom() + ".res : " + e.getMessage());
                }
            }


            // Fermeture des ressources
            connexion.close();
            System.out.println("Connexion fermée avec succès !");
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
    
    public static long calculer(long number1, long number2, String operation) {
        CalculStrategy strategy = CalculStrategyFactory.createStrategy(operation);
        return strategy.calculer(number1, number2);
    }
}