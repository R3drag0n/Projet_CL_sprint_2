package joueur;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JoueurGUI extends JFrame implements PropertyChangeListener {

    private int reponse;
    private int score;
    private int scoreAdv ;
    private int nbAdv ;
    private Joueur joueur;

    private JTextField jTextFieldScore;
    private JTextField jTextFieldScoreAdv;
    private JTextField jTextFieldNbAdv;

    private JTextField jTextFieldReponse;

    // Constructors
    public JoueurGUI(Joueur unJoueur) {
        super(unJoueur.getName());
        joueur = unJoueur;

        // On vient ensuite "écouter" le joueur (c'est la classe JoueurGUI qui va
        // recevoir les notifications)
        joueur.getPcSupport().addPropertyChangeListener(this);
       // automate.getPortefeuille().getPropertyChangeSupport().addPropertyChangeListener(this);

        score = joueur.getScore();
        scoreAdv = joueur.getScoreAdv();
        nbAdv = joueur.getNbAdv();
    }

    private JLabel createJLabelScore() {
        JLabel jLabelSommenpoche = new JLabel("Votre Score");
        jLabelSommenpoche.setPreferredSize(new Dimension(150, 40));
        jLabelSommenpoche.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelSommenpoche;
    }

    private JLabel createJLabelScoreAdv() {
        JLabel jLabelSommeAdv = new JLabel("Score de votre adversaire");
        jLabelSommeAdv.setPreferredSize(new Dimension(150, 40));
        jLabelSommeAdv.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelSommeAdv;
    }

    private JLabel createJLabelNbAdv() {
        JLabel jLabelNbAdv = new JLabel("Nombre votre adversaire");
        jLabelNbAdv.setPreferredSize(new Dimension(150, 40));
        jLabelNbAdv.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelNbAdv;
    }

    private JLabel createJLabelReponse() {
        JLabel jLabelSommetraitee = new JLabel("Choix d'un entier entre 0 et 100");
        jLabelSommetraitee.setPreferredSize(new Dimension(150, 40));
        jLabelSommetraitee.setHorizontalTextPosition(SwingConstants.CENTER);
        return jLabelSommetraitee;
    }


    public JTextField createJTextFieldScore() {
        if (jTextFieldScore == null) {
            jTextFieldScore = new JTextField();
            jTextFieldScore.setText(Integer.toString(this.getScore()));
            jTextFieldScore.setSize(new Dimension(120, 37));
            jTextFieldScore.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldScore.setEditable(false);
        }
        return jTextFieldScore;
    }

    public JTextField createJTextFieldNbAdv() {
        if (jTextFieldNbAdv == null) {
            jTextFieldNbAdv = new JTextField();
            jTextFieldNbAdv.setText(Integer.toString(this.getNbAdv()));
            jTextFieldNbAdv.setSize(new Dimension(120, 37));
            jTextFieldNbAdv.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldNbAdv.setEditable(false);
        }
        return jTextFieldNbAdv;
    }

    public JTextField createJTextFieldScoreAdv() {
        if (jTextFieldScoreAdv == null) {
            jTextFieldScoreAdv = new JTextField();
            jTextFieldScoreAdv.setText(Integer.toString(this.getScoreAdv()));
            jTextFieldScoreAdv.setSize(new Dimension(120, 37));
            jTextFieldScoreAdv.setHorizontalAlignment(SwingConstants.LEFT);
            jTextFieldScoreAdv.setEditable(false);
        }
        return jTextFieldScoreAdv;
    }

    private JTextField createJTextFieldReponse() {
        if (jTextFieldReponse == null) {
            jTextFieldReponse = new JTextField();
            jTextFieldReponse.setSize(new Dimension(140, 37));
            jTextFieldReponse.setBackground(new Color(181, 217, 38));
            jTextFieldReponse.setForeground(new Color(0, 0, 128));
            jTextFieldReponse.setFont(new Font("Courier New", 1, 18));
            jTextFieldReponse.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
        }
        return jTextFieldReponse;
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

    /** Initialisation de la GUI pour l'interface client */
    public void initGUI() {
        System.out.println(joueur);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            getContentPane().setForeground(new Color(255, 0, 128));
            this.setLocation(25, 350);
            this.setVisible(true);
            this.setFont(new Font("Antique Olive", 0, 10));
            getContentPane().setBackground(new Color(255, 128, 64));
            getContentPane().setLayout(new BorderLayout(3, 2));

            // Panel pour les boutons
            JPanel jPanelsud = new JPanel();
            getContentPane().add(jPanelsud, BorderLayout.SOUTH);
            jPanelsud.setPreferredSize(new Dimension(392, 36));
            jPanelsud.setBackground(new Color(255, 128, 128));
            // Valider
            JButton valider = new JButton("Valider");
            jPanelsud.add(valider);
            valider.setSize(200, 30);
            valider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    boolean connexionOk = true;
//                    TODO : le boolean est juste pour le test, il faut le modifier
                    if (connexionOk == false) {
                        setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(getContentPane(),
                                "Pas de serveur ou nombre d'actions maximal atteint!\n Fermez le client.",
                                "Erreur transaction", JOptionPane.ERROR_MESSAGE);
                    } else {
//                        System.out.println("Action");
//                        setSommenpoche(Integer.parseInt(jTextFieldSommenpoche.getText()));
                        try {
                            reponse = Integer.parseInt(jTextFieldReponse.getText());
                            if (reponse>100 | reponse<0) {
                                // On fait planter le try
                                Integer.parseInt("10.2");
                            }
                            System.out.println("Vous avez choisi : " + reponse);
                            joueur.envoiReponse(jTextFieldReponse.getText());
                        } catch (Exception e) {
                            System.out.println("Veuillez choisir une réponse valide");
                        }
//                        System.out.println("Vous avez choisit : " + Integer.parseInt(jTextFieldReponse.getText()));
//                        if (getTypeoperation().equals("retrait")) {
//                            System.out.println(" somme traitee en retrait " + jTextFieldSommeatraiter.getText());
//                            automate.demandeRetrait(Integer.parseInt(jTextFieldSommeatraiter.getText()));
//                        }
//                        if (getTypeoperation().equals("depot")) {
//                            System.out.println(" somme traitee en depot " + jTextFieldSommeatraiter.getText());
//                            automate.demandeDepot(Integer.parseInt(jTextFieldSommeatraiter.getText()));
//                        }
//                        automate.deconnexionBanque();
//                        System.out.println("Deconnection banque. Etat du compte : " + automate);
                    }
                }
            });

            JButton quitter = new JButton("Quitter");
            jPanelsud.add(quitter);
            quitter.setSize(200, 30);
            quitter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.exit(0);
                }
            });

            // Panel pour Score (ouest)
            JPanel jPanelouest = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelouest, BorderLayout.WEST);
            jPanelouest.setOpaque(true);
            jPanelouest.setFont(new Font("Tahoma", 1, 13));
            jPanelouest.setSize(150, 118);
            jPanelouest.add(createJLabelScore());
            jPanelouest.add(createJTextFieldScore());
            jPanelouest.setBorder(new LineBorder(Color.WHITE, 4, false));

            // Panel pour Score adversaire (est)
            JPanel jPanelest = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelest, BorderLayout.EAST);
            jPanelest.setOpaque(true);
            jPanelest.setFont(new Font("Tahoma", 1, 13));
            jPanelest.setSize(150, 118);

            jPanelest.add(createJLabelScoreAdv());
            jPanelest.add(createJTextFieldScoreAdv());
            jPanelest.setBorder(new LineBorder(Color.WHITE, 4, false));

            // Panel pour Reponse (centre)
            JPanel jPanelcentre = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelcentre, BorderLayout.CENTER);
            jPanelcentre.setOpaque(true);
            jPanelcentre.setForeground(new Color(255, 128, 64));
            jPanelcentre.setBackground(new Color(255, 128, 0));
            jPanelcentre.setBorder(new LineBorder(new Color(255, 128, 0), 4, false));
            jPanelcentre.setPreferredSize(new Dimension(200, 118));

            jPanelcentre.add(createJLabelReponse());
            jPanelcentre.add(createJTextFieldReponse());

            // Panel pour Reponse de l'adversaire (haut)
            JPanel jPanelnor = new JPanel(new GridLayout(2, 1, 4, 10));
            getContentPane().add(jPanelnor, BorderLayout.NORTH);
            jPanelnor.setOpaque(true);
            jPanelnor.setForeground(new Color(255, 128, 64));
            jPanelnor.setBackground(new Color(255, 128, 0));
            jPanelnor.setBorder(new LineBorder(new Color(255, 128, 0), 4, false));
            jPanelnor.setPreferredSize(new Dimension(200, 118));

            jPanelnor.add(createJLabelNbAdv());
            jPanelnor.add(createJTextFieldNbAdv());


            pack();
            this.setAlwaysOnTop(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        jTextFieldScore.setText(Integer.toString(joueur.getScore()));
        jTextFieldScoreAdv.setText(Integer.toString(joueur.getScoreAdv()));
        jTextFieldNbAdv.setText(Integer.toString(joueur.getNbAdv()));
    }



//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        jTextFieldSommenpoche.setText(Integer.toString(automate.getArgentEnPoche()));
//    }
//
}
