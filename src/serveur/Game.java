package serveur;

import joueur.JoueurGUI;
import joueur.JoueurID;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Game implements IContext {
    public ArrayList<JoueurID> lesJoueurs = new ArrayList<>();
    public int nbJoueur = 0;
    public

    public void addJoueur(Socket socket) {
        JoueurID joueurID = new JoueurID(socket, "Joueur "+nbJoueur);
        lesJoueurs.add(joueurID);
        nbJoueur ++;

        if (nbJoueur >= 5) {
            startGame();
        }
    }

    private void startGame() {
        for (JoueurID joueur : lesJoueurs) {
            uneManche(joueur);
        }
    }

    private void uneManche(JoueurID joueur) {
//        On demande un nombre aux joueurs en vie.
//        Quand toute reponse on calcul l'objectif

        try {
            InputStream unInput = joueur.getClientSocket().getInputStream();
            OutputStream unOutput = joueur.getClientSocket().getOutputStream();

            ArrayList<Integer> reponses = new ArrayList<Integer>();
            boolean toutLeMondeARepondu = false;
            BufferedReader is = new BufferedReader(new InputStreamReader(unInput));
            PrintStream os = new PrintStream(unOutput);



            try {
                String valeurExpediee = "";

//            while (reponses.size() == serv.nbConnexions) {
                System.out.println("Entree");
                String inputLine;
                int tour = 0;

                while ((inputLine = is.readLine()) != null) {
                    reponses.add(Integer.parseInt(inputLine));
                    tour += 1;
                    System.out.println(" Reponse Recue du joueur " + joueur.getNom() + " : "  + reponses.get(tour-1));
                    joueur.setNb (reponses.get(tour-1));
                    joueur.setReady (true);


                    if (listJ.get(0).getReady() && listJ.get(1).getReady() ) {

                        int moy = (int) (0.4 * (listJ.get(0).getNb() + listJ.get(1).getNb()));

                        if (Math.abs(moy - listJ.get(0).getNb()) == Math.abs(moy - listJ.get(1).getNb())) {
                            listJ.get(0).setEtat("perdant");
                            listJ.get(1).setEtat("perdant");
                            listJ.get(1).setScore (listJ.get(1).getScore()-1);
                            listJ.get(0).setScore (listJ.get(0).getScore()-1);

                            if (listJ.get(1).getScore () == 8)
                            {
                                System.out.println ("Elodie a perdue");
                            }

                            if (listJ.get(0).getScore () == 8)
                            {
                                System.out.println ("Elodie a perdue");
                            }

                        }


                        else if (listJ.get(0).getNb() == 100 && listJ.get(1).getNb() == 0) {
                            listJ.get(0).setEtat("gagnant");
                            listJ.get(1).setEtat("perdant");
                            listJ.get(1).setScore (listJ.get(1).getScore()-1);

                        }

                        else if (listJ.get(0).getNb() == 0 && listJ.get(1).getNb() == 100) {
                            listJ.get(1).setEtat("gagnant");
                            listJ.get(0).setEtat("perdant");
                            listJ.get(0).setScore (listJ.get(1).getScore()-1);

                        }

                        else if (Math.abs(moy - listJ.get(0).getNb()) < Math.abs(moy - listJ.get(1).getNb())) {
                            listJ.get(0).setEtat("gagnant");
                            listJ.get(1).setEtat("perdant");
                            listJ.get(1).setScore (listJ.get(1).getScore()-1);

                            if (Math.abs(moy - listJ.get(0).getNb()) == 0) {
                                listJ.get(1).setScore (listJ.get(1).getScore()-1);
                            }

                            if (listJ.get(1).getScore () == 8)
                            {
                                System.out.println ("Elodie a perdue");
                            }

                        } else {
                            listJ.get(1).setEtat("gagnant");
                            listJ.get(0).setEtat("perdant");

                            listJ.get(0).setScore (listJ.get(0).getScore()-1);

                            if (Math.abs(moy - listJ.get(1).getNb()) == 0) {
                                listJ.get(0).setScore (listJ.get(0).getScore()-1);
                            }

                            if (listJ.get(0).getScore () == 8)
                            {
                                System.out.println ("Louis a perdu");
                            }
                        }

                        System.out.println("La moyenne est de : " + ". " + listJ.get(0).getNom() + " : " + listJ.get(0).getEtat() + ",  " + listJ.get(1).getNom() + " : "+ listJ.get(1).getEtat() + ",  " + listJ.get(0).getNb());

                        listJ.get(0).setReady(false);
                        listJ.get(1).setReady(false);
                        listJ.get(0).result(moy, listJ.get(1).getNb(),  listJ.get(1).getScore());
                        listJ.get(1).result(moy, listJ.get(0).getNb(),  listJ.get(0).getScore());


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
                System.out.println(" Pb d'exception ");
            }



        } catch (IOException e) {
            System.err.println("[uneManche] Erreur : " + e);
            e.printStackTrace();
        }


        }
    }
}
