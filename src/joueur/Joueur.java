package joueur;

import java.beans.PropertyChangeSupport;

//TODO : Je comprend pas ce que ça fait...

public class Joueur implements IJoueur {

    private int port = 6666;
    private int score = 9;
    private int scoreAdv = 9;
    private int nbAdv = 100;
    private ClientTCP monClientTCP;
    private String name;
    private PropertyChangeSupport pcSupport;

    public Joueur(String name) {
        monClientTCP = new ClientTCP("localhost", port);
        monClientTCP.connecterAuServeur();
        pcSupport = new PropertyChangeSupport(this);
        this.name = name;
    }

    public void envoiReponse(String reponse) {
        System.out.println("envoieReponse : ok");
        String msgServeur = monClientTCP.transmettreChaine(reponse);

        int dernierScore = getScore();
        score = Integer.parseInt(String.valueOf(msgServeur.charAt(20))) ;

        int dernierScoreAdv = getScore();
        scoreAdv = Integer.parseInt(String.valueOf(msgServeur.charAt(msgServeur.length() - 1))) ;

        int dernierNbAdv = getNbAdv();

        if (String.valueOf(msgServeur.charAt(52)).equals(" ")){
            if (String.valueOf(msgServeur.charAt(51)).equals(" ")){
                nbAdv = Integer.parseInt(String.valueOf(msgServeur.charAt(50))) ;
            }
            else {
                nbAdv = 10* Integer.parseInt(String.valueOf(msgServeur.charAt(50)))  + Integer.parseInt(String.valueOf(msgServeur.charAt(51))) ;
            }
        }
        else {
            nbAdv = 100;
        }


        pcSupport.firePropertyChange("somme", dernierScore, getScore());
        pcSupport.firePropertyChange("sommeAdv", dernierScoreAdv, getScoreAdv());
        pcSupport.firePropertyChange("sommeAdv", dernierNbAdv, getNbAdv());
    }

    public String getName() {
        return name;
    }

    public PropertyChangeSupport getPcSupport() {
        return pcSupport;
    }

    public int getScore() {
        return score;
    }

    public int getScoreAdv() {
        return scoreAdv;
    }

    public int getNbAdv() {
        return nbAdv;
    }

    //    public void setArgentEnPoche(int uneSomme) {
//        portefeuille.setArgentPoche(uneSomme);
//    }

//    public Portefeuille getPortefeuille() {
//        return portefeuille;
//    }

//    public int getArgentEnPoche() {
//        return portefeuille.getSomme();
//    }

//    @Override
//    public void depot(int unDepot) {
//        System.out.println("Dépôt de " + unDepot);
//        portefeuille.retirerSomme(unDepot);
//        System.out.println("Somme en poche finale " + portefeuille.getSomme());
//    }

//    @Override
//    public void retrait(int unRetrait) {
//        System.out.println("Retrait de " + unRetrait);
//        portefeuille.ajouterSomme(unRetrait);
//        System.out.println("Somme en poche finale " + portefeuille.getSomme());
//    }

//    public int demandeRetrait(int laSomme) {
//        int valeurRetrait = 0;
//        String valeurTransmise = monClientTCP.transmettreChaine("retrait " + laSomme);
//        valeurRetrait = Integer.parseInt(valeurTransmise);
//        retrait(valeurRetrait);
//        return valeurRetrait;
//    }

//    public int demandeDepot(int laSomme) {
//        int valeurDepot = 0;
//        String valeurTransmise = monClientTCP.transmettreChaine("depot " + laSomme);
//        valeurDepot = Integer.parseInt(valeurTransmise);
//        depot(valeurDepot);
//        return valeurDepot;
//    }

//    @Override
//    public boolean connexionBanque() {
//        return monClientTCP.connecterAuServeur();
//    }

//    @Override
//    public void deconnexionBanque() {
//        monClientTCP.deconnecterDuServeur();
//    }

    @Override
    public String toString() {
        return "Score : " + score;
    }

}
