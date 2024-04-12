package fr.hetic;

public class Ligne {
    private final int fichierId;
    private final int position;
    private final String param1;
    private final String param2;
    private final String operateur;

    public Ligne(int fichierId, int position, String param1, String param2, String operateur) {
        this.fichierId = fichierId;
        this.position = position;
        this.param1 = param1;
        this.param2 = param2;
        this.operateur = operateur;
    }

    public int getFichierId() {
        return fichierId;
    }

    public int getPosition() {
        return position;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String getOperateur() {
        return operateur;
    }
    
    // Méthode pour afficher les informations sur la ligne de manière lisible
    @Override
    public String toString() {
        return param1 + " " + param2 + " " + operateur;
    }
}