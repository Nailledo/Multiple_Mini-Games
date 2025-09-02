package jeu.ihm.memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import jeu.Controleur;

public class PanelMemory extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JPanel panelInfoPartie;
    private JPanel panelChoixDifficulte;
    private JPanel panelNbJoueur;
    private JPanel panelInfoTitre;
    private JPanel panelHaut;
    private JPanel panelScore;

    private JRadioButton  rbFacile;
    private JRadioButton  rbMoyen;
    private JRadioButton  rbDifficile;

    private JRadioButton  rb1J;
    private JRadioButton  rb2J;
    private JRadioButton  rb3J;
    private JRadioButton  rb4J;

    private JButton btnParametre;
    private JLabel  lblTitre;
    private JLabel  lblJoueurActuel; 
    private JLabel[] lblScores; 

    private JButton btnLancerPartie;

    private ButtonGroup btgChoixDifficulte;

    // Variables pour le jeu memory
    private int     premierNumero = -1;
    private JButton premierBouton = null;
    private boolean enCours       = false; 
    
    // Variables pour le système multi-joueurs et points
    private int nbJoueurs = 1;
    private int joueurActuel = 0; // Index du joueur actuel (0, 1, 2, 3)
    private int[] scores; // Tableau des scores pour chaque joueur
    private int pairesRestantes = 0; // Nombre de paires restantes à trouver
    
    public PanelMemory( Controleur ctrl)
    {
        this.ctrl = ctrl;
        this.setLayout( new BorderLayout() );

        /* ------------------------------------ */
        /*			Création des composants		*/
        /* ------------------------------------ */

        this.panelInfoPartie      = new JPanel( new GridLayout(2, 1) );
        this.panelChoixDifficulte = new JPanel( new GridLayout(1, 3) );
        this.panelNbJoueur        = new JPanel( new GridLayout(1, 4) );
        this.panelScore           = new JPanel( new GridLayout(1, 5) );

        // Barre de titre avec fond gris foncé
        this.panelInfoTitre = new JPanel(new BorderLayout());
        this.panelInfoTitre.setBackground(new Color(50, 50, 50));
        this.panelInfoTitre.setPreferredSize(new Dimension(0, 60));
        
        this.panelHaut = new JPanel(new BorderLayout(20, 0));
        this.panelHaut.setOpaque(false);

        this.lblTitre = new JLabel("Memory", SwingConstants.CENTER);
        this.lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTitre.setVerticalAlignment(SwingConstants.CENTER);
        this.lblTitre.setFont(new Font("Arial", Font.BOLD, 28));
        this.lblTitre.setForeground(Color.WHITE);

        this.btnParametre = new JButton();
        this.btnParametre.setBackground(new Color(80, 80, 100));
        this.btnParametre.setForeground(Color.WHITE);

        this.btnLancerPartie = new JButton("Lancer la partie");

        // Labels pour les scores
        this.lblJoueurActuel = new JLabel("Joueur 1", SwingConstants.CENTER);
        this.lblJoueurActuel.setFont(new Font("Arial", Font.BOLD, 16));
        this.lblJoueurActuel.setForeground(Color.BLUE);
        
        this.lblScores = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            this.lblScores[i] = new JLabel("J" + (i+1) + ": 0", SwingConstants.CENTER);
            this.lblScores[i].setFont(new Font("Arial", Font.PLAIN, 14));
        }

        this.btgChoixDifficulte = new ButtonGroup();
        this.rbFacile    = new JRadioButton("Facile, Grille de 4x4", true   );
        this.rbMoyen     = new JRadioButton("Moyen, Grille de 6x6"    );
        this.rbDifficile = new JRadioButton("Difficile, Grille de 8x8");

        ButtonGroup btgNbJoueur = new ButtonGroup();
        this.rb1J = new JRadioButton("1 Joueur", true );
        this.rb2J = new JRadioButton("2 Joueurs");
        this.rb3J = new JRadioButton("3 Joueurs");
        this.rb4J = new JRadioButton("4 Joueurs");


        /* ------------------------------------ */
        /*	Positionnement des composants	    */  
        /* ------------------------------------ */

        this.btgChoixDifficulte.add ( this.rbFacile   );
        this.btgChoixDifficulte.add ( this.rbMoyen    );
        this.btgChoixDifficulte.add ( this.rbDifficile);

        btgNbJoueur.add ( this.rb1J);
        btgNbJoueur.add ( this.rb2J);
        btgNbJoueur.add ( this.rb3J);
        btgNbJoueur.add ( this.rb4J);

        this.panelChoixDifficulte.add ( this.rbFacile    );
        this.panelChoixDifficulte.add ( this.rbMoyen     );
        this.panelChoixDifficulte.add ( this.rbDifficile );

        this.panelNbJoueur.add ( this.rb1J );
        this.panelNbJoueur.add ( this.rb2J );
        this.panelNbJoueur.add ( this.rb3J );
        this.panelNbJoueur.add ( this.rb4J );

        // Ajout des labels de score au panel score
        this.panelScore.add(this.lblJoueurActuel);
        for (int i = 0; i < 4; i++) 
            this.panelScore.add(this.lblScores[i]);

        this.panelInfoPartie.add ( this.panelChoixDifficulte );
        this.panelInfoPartie.add ( this.panelNbJoueur        );

        this.panelInfoTitre.add(this.lblTitre    , BorderLayout.CENTER);
        this.panelInfoTitre.add(this.btnParametre, BorderLayout.EAST);

        this.add ( this.panelInfoTitre      , BorderLayout.NORTH  );
        this.add ( this.panelInfoPartie     , BorderLayout.CENTER );
        this.add ( this.btnLancerPartie     , BorderLayout.SOUTH  );

        /* ------------------------------------ */
        /*		Activation des composants   	*/
        /* ------------------------------------ */

        this.btnLancerPartie.addActionListener( this );
        this.btnParametre   .addActionListener(this);
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        if ( e.getSource() == this.btnParametre )
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            this.ctrl.setJeuActuel(frame);
            this.ctrl.getFrameParametre().setVisible(true);
        }

        if ( e.getSource() == this.btnLancerPartie )
        {
            this.panelInfoPartie.setVisible(false );
            this.btnLancerPartie.setVisible(false );

            // Déterminer le nombre de joueurs
            if      ( this.rb1J.isSelected() ) this.nbJoueurs = 1;
            else if ( this.rb2J.isSelected() ) this.nbJoueurs = 2;
            else if ( this.rb3J.isSelected() ) this.nbJoueurs = 3;
            else                               this.nbJoueurs = 4;

            // Initialiser le système de score
            this.scores       = new int[this.nbJoueurs];
            this.joueurActuel = 0;
            
            // Masquer les labels des joueurs non utilisés
            for (int i = 0; i < 4; i++) 
                this.lblScores[i].setVisible(i < this.nbJoueurs);
        
            String difficulte;
            if      ( this.rbFacile   .isSelected() ) difficulte = "Facile";
            else if ( this.rbMoyen    .isSelected() ) difficulte = "Moyen";
            else                                      difficulte = "Difficile";
            
            int[][] tabMemory = this.ctrl.getTabMemory( difficulte );
            
            // Calculer le nombre de paires
            this.pairesRestantes = (tabMemory.length * tabMemory[0].length) / 2;
            
            this.construireMemory( tabMemory );
            
            // Afficher le panel des scores
            this.add(this.panelScore, BorderLayout.SOUTH);
            this.mettreAJourAffichage();
            
            this.revalidate();
            this.repaint();
        }
        else
        {   
            // Si on est en cours de traitement, on ignore le clic
            if (this.enCours) return;
            
            JButton boutonClique = (JButton) e.getSource();
            
            // Si le bouton est déjà désactivé, on ignore le clic
            if (!boutonClique.isEnabled()) return;
            
            String numeroStr = boutonClique.getActionCommand();
            int    numero    = Integer.parseInt(numeroStr);
            
            // Afficher le numéro sur le bouton
            boutonClique.setText(numeroStr);
            boutonClique.setEnabled(false);

            if (this.premierNumero == -1) 
            {
                // Premier clic : on mémorise
                this.premierNumero = numero;
                this.premierBouton = boutonClique;
            }
            else if (this.premierNumero == numero)
            {
                // Deuxième clic : on compare
                // Les cartes correspondent : elles restent retournées
                System.out.println("Paire trouvée par le joueur " + (this.joueurActuel + 1) + " !");
                
                // Augmenter le score du joueur actuel
                this.scores[this.joueurActuel]++;
                this.pairesRestantes--;
                
                // Le joueur qui trouve une paire continue de jouer
                this.mettreAJourAffichage();
                this.resetSelection();
                
                // Vérifier si le jeu est terminé
                if (this.pairesRestantes == 0) 
                    this.finDePartie();
                
            } 
            else
            {
                // Les cartes ne correspondent pas : on les retourne après un délai
                this.enCours = true;
                
                Timer timer = new Timer(1000, new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {
                        // Remettre les cartes face cachée
                        premierBouton.setText("");
                        premierBouton.setEnabled(true);
                        boutonClique .setText("");
                        boutonClique .setEnabled(true);
                        
                        // Passer au joueur suivant seulement si plusieurs joueurs
                        if (nbJoueurs > 1) 
                        {
                            joueurActuel = (joueurActuel + 1) % nbJoueurs;
                            mettreAJourAffichage();
                        }
                        
                        resetSelection();
                        enCours = false;
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }
    
    private void resetSelection()
    {
        this.premierNumero = -1;
        this.premierBouton = null;
    }
    
    private void mettreAJourAffichage()
    {
        // Mettre à jour le joueur actuel
        this.lblJoueurActuel.setText("Joueur " + (this.joueurActuel + 1));
        
        // Mettre à jour les scores
        for (int i = 0; i < this.nbJoueurs; i++) 
        {
            this.lblScores[i].setText("J" + (i+1) + ": " + this.scores[i]);
            
            // Mettre en évidence le joueur actuel
            if (i == this.joueurActuel) 
            {
                this.lblScores[i].setForeground(Color.BLUE);
                this.lblScores[i].setFont(new Font("Arial", Font.BOLD, 14));
            } 
            else 
            {
                this.lblScores[i].setForeground(Color.BLACK);
                this.lblScores[i].setFont(new Font("Arial", Font.PLAIN, 14));
            }
        }
    }
    
    private void finDePartie()
    {
        // Trouver le(s) gagnant(s)
        int scoreMax = 0;
        for (int i = 0; i < this.nbJoueurs; i++) 
            if (this.scores[i] > scoreMax) 
                scoreMax = this.scores[i];
        
        String message = "Partie terminée !\n\nScores finaux :\n";
        String gagnants = "";
        
        for (int i = 0; i < this.nbJoueurs; i++) 
        {
            message += "Joueur " + (i+1) + " : " + this.scores[i] + " paires\n";
            if (this.scores[i] == scoreMax) 
            {
                if (!gagnants.isEmpty()) gagnants += " et ";
                gagnants += "Joueur " + (i+1);
            }
        }
        
        if (this.nbJoueurs == 1) 
            message += "\nVous avez trouvé " + this.scores[0] + " paires !";
        else 
            message += "\nGagnant(s) : " + gagnants + " avec " + scoreMax + " paires !";
        
        JOptionPane.showMessageDialog(this, message, "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
    }

    private void construireMemory(int[][] tabInt) 
    {
        JPanel panelMemory = new JPanel(new GridLayout(tabInt.length, tabInt.length));

        for (int i = 0; i < tabInt.length; i++) 
        {
            for (int j = 0; j < tabInt[i].length; j++) 
            {
                JButton btnTemp = new JButton(""); // Texte vide au départ
                btnTemp.setActionCommand( "" + tabInt[i][j] ); // Stocke le numéro
                btnTemp.addActionListener(this);
                panelMemory.add(btnTemp);
            }
        }
        this.add(panelMemory, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}