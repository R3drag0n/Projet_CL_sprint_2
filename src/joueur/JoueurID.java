package joueur;

import serveur.Game;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class JoueurID {

    private Socket clientSocket;
    private int score ;
    private int nb ;
    private String nom;
    private String etat;
    private boolean ready;
    private boolean alive = true;
    private Game myGame;

    public JoueurID(Socket socket, String noms, Game game){
        System.out.println("[Construct JoueurID] :" +game);
        clientSocket = socket;
        score = 9;
        nb = 0;
        nom = noms;
        etat = "neutre";
        ready = false;
        myGame = game;
    }

    public void setNb(int nbe){
        nb = nbe;
    }

    public int getNb(){
       return nb;
    }

    public void result (int moy, int nb_adv, int score_adv)
    {
        try {
            OutputStream unOutput = clientSocket.getOutputStream();
            PrintStream os = new PrintStream(unOutput);
            os.println("Vous êtes " + etat + " ! " +  String.valueOf(score) + " Votre adversaire avait joué " + String.valueOf(nb_adv) + "  la moyenne était " + String.valueOf(moy) + " L'adversaire a un score de " + String.valueOf(score_adv)  );
        }

        catch (IOException e) {
            System.err.println("Quelque chose ne va pas : " + e );
            e.printStackTrace();
        }

    }

    public String getNom (){
        return nom;
    }


    public void setReady(boolean rea){
        ready = rea;
    }

    public boolean getReady(){
        return ready;
    }

    public void setScore(int sco){
        score = sco;
    }

    public int getScore(){
        return score;
    }

    public void setEtat(String eta){
        etat = eta;
    }

    public String getEtat(){
        return etat;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public boolean isAlive() {
        return alive;
    }

    public void uneManche(JoueurID joueur) {
//        On demande un nombre aux joueurs en vie.
//        Quand toute reponse on calcul l'objectif

        try {
            InputStream unInput = joueur.getClientSocket().getInputStream();
            OutputStream unOutput = joueur.getClientSocket().getOutputStream();

            ArrayList<Integer> reponses = new ArrayList<Integer>();
            BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
            PrintStream os = new PrintStream(unOutput);



            try {
                String valeurExpediee = "";

//            while (reponses.size() == serv.nbConnexions) {
                String inputLine;
                int tour = 0;

                while ((inputLine = is.readLine()) != null) {
                    reponses.add(Integer.parseInt(inputLine));
                    tour += 1;
                    System.out.println(" Reponse Recue du joueur " + joueur.getNom() + " : "  + reponses.get(tour-1));
                    joueur.setNb (reponses.get(tour-1));
                    joueur.setReady (true);

                    System.out.println("check 1");
                    if (allReady()) {
                        System.out.println("check 2");
                        double moy = objective();
                        System.out.println("check 3");
                        selectWinner(moy);
                        System.out.println("check 4");

//                        if (Math.abs(moy - listJ.get(0).getNb()) == Math.abs(moy - listJ.get(1).getNb())) {
//                            listJ.get(0).setEtat("perdant");
//                            listJ.get(1).setEtat("perdant");
//                            listJ.get(1).setScore (listJ.get(1).getScore()-1);
//                            listJ.get(0).setScore (listJ.get(0).getScore()-1);
//
//                            if (listJ.get(1).getScore () == 8)
//                            {
//                                System.out.println ("Elodie a perdue");
//                            }
//
//                            if (listJ.get(0).getScore () == 8)
//                            {
//                                System.out.println ("Elodie a perdue");
//                            }
//
//                        }
//
//                        else if (listJ.get(0).getNb() == 100 && listJ.get(1).getNb() == 0) {
//                            listJ.get(0).setEtat("gagnant");
//                            listJ.get(1).setEtat("perdant");
//                            listJ.get(1).setScore (listJ.get(1).getScore()-1);
//
//                        }
//
//                        else if (listJ.get(0).getNb() == 0 && listJ.get(1).getNb() == 100) {
//                            listJ.get(1).setEtat("gagnant");
//                            listJ.get(0).setEtat("perdant");
//                            listJ.get(0).setScore (listJ.get(1).getScore()-1);
//
//                        }
//
//                        else if (Math.abs(moy - listJ.get(0).getNb()) < Math.abs(moy - listJ.get(1).getNb())) {
//                            listJ.get(0).setEtat("gagnant");
//                            listJ.get(1).setEtat("perdant");
//                            listJ.get(1).setScore (listJ.get(1).getScore()-1);
//
//                            if (Math.abs(moy - listJ.get(0).getNb()) == 0) {
//                                listJ.get(1).setScore (listJ.get(1).getScore()-1);
//                            }
//
//                            if (listJ.get(1).getScore () == 8)
//                            {
//                                System.out.println ("Elodie a perdue");
//                            }
//
//                        } else {
//                            listJ.get(1).setEtat("gagnant");
//                            listJ.get(0).setEtat("perdant");
//
//                            listJ.get(0).setScore (listJ.get(0).getScore()-1);
//
//                            if (Math.abs(moy - listJ.get(1).getNb()) == 0) {
//                                listJ.get(0).setScore (listJ.get(0).getScore()-1);
//                            }
//
//                            if (listJ.get(0).getScore () == 8)
//                            {
//                                System.out.println ("Louis a perdu");
//                            }
//                        }
//
//                        System.out.println("La moyenne est de : " + ". " + listJ.get(0).getNom() + " : " + listJ.get(0).getEtat() + ",  " + listJ.get(1).getNom() + " : "+ listJ.get(1).getEtat() + ",  " + listJ.get(0).getNb());
//
//                        listJ.get(0).setReady(false);
//                        listJ.get(1).setReady(false);
//                        listJ.get(0).result(moy, listJ.get(1).getNb(),  listJ.get(1).getScore());
//                        listJ.get(1).result(moy, listJ.get(0).getNb(),  listJ.get(0).getScore());


                    }
                }


//                String chaines[] = reponse.split(" ");

//                if (chaines[0].contentEquals("PING")) {
//                    valeurExpediee = "PONG";
//                    System.out.println(" Reponse serveur "	+ valeurExpediee);
//                }
                os.println("Le serveur a pris en compte votre reponse : " + reponses);
                //}
//            }
            } catch ( Exception e) {
                System.out.println(" Pb d'exception : "+e);
            }



        } catch (IOException e) {
            System.err.println("[uneManche] Erreur : " + e);
            e.printStackTrace();
        }


    }

    private boolean allReady() {
        System.out.println("[allReady] check 1 : "+myGame.lesJoueurs);
        for (JoueurID j : myGame.lesJoueurs) {
            System.out.println("[allReady] check 2 : " + j);
            if (!j.getReady()) {
                return false;
            }
        }
        return true;
    }

    private double objective() {
        int obj = 0;
        int nbr = 0;
        for (JoueurID j : myGame.lesJoueurs) {
            if (j.isAlive()) {
                nbr ++;
                obj += j.getNb();
            }
        }
        return (obj / nbr) * 0.8;
    }

    private void selectWinner(double moy) {
        ArrayList<Double> distances = new ArrayList<>();
        for (JoueurID j : myGame.lesJoueurs) {
            if (j.isAlive()) {
                distances.add(Math.abs(j.getNb() - moy));
            } else {
                distances.add(300.0);
            }
        }
        double min = Collections.min(distances);
        for (int i=0 ; i<distances.size() ; i++) {
            JoueurID j = myGame.lesJoueurs.get(i);
            if (distances.get(i) != min && j.isAlive()) {
                j.setEtat("Perdant");
                j.setScore( j.getScore() - 1 );
                System.out.println(j.nom + " -> Perdant");
            }
            j.setReady(false);
        }
    }
}
