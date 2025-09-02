package jeu.ihm.PFC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import jeu.Controleur;

public class PanelPFC extends JPanel implements ActionListener 
{
    private Controleur ctrl;

    private JPanel panelInfoTitre;
    private JPanel panelHaut;
    private JPanel panelChoix;
    private JPanel panelCombat; 
    private JPanel panelFinPartie;
    private JPanel panelBtnRecommencer;
    private JPanel panelBtnMenu; 

    private JLabel lblTitre;
    private JLabel lblTexte;
    private JLabel lblNbTour;
    
    // Labels pour l'affichage du combat
    private JLabel lblJ1Combat;
    private JLabel lblJ2Combat;
    private JLabel lblChoixJ1;
    private JLabel lblChoixJ2;

    private JLabel  lblPtJ1;
    private JLabel  lblPtJ2;

    private JButton btnPierre;
    private JButton btnFeuille;
    private JButton btnCiseau;

    private JButton btnParametre;
    private JButton btnChangerJoueur;
    private JButton btnNouvelleManche;

    private JButton btnMenu;
    private JButton btnRecommencerPartie;


    private int joueur = 1;
    private int nbTour = 1;
    private int ptJ1   = 0;
    private int ptJ2   = 0;
    
    private char choixJ1;
    private char choixJ2;

    private ImageIcon imgPierre;
    private ImageIcon imgFeuille;
    private ImageIcon imgCiseau;

    public PanelPFC(Controleur ctrl ) 
    {
        this.ctrl    = ctrl;

        this.setLayout(new BorderLayout(10, 10));

        // Panel principal qui contiendra tout sauf la barre de titre
        JPanel mainContent = new JPanel(new GridLayout(5, 1, 0, 20));
        mainContent.setBackground(Color.WHITE);
        
        // Barre de titre avec fond gris foncé
        this.panelInfoTitre = new JPanel(new BorderLayout());
        this.panelInfoTitre.setBackground(new Color(50, 50, 50));
        this.panelInfoTitre.setPreferredSize(new Dimension(0, 60));
        
        this.panelHaut = new JPanel(new BorderLayout(20, 0));
        this.panelHaut.setOpaque(false);
        
        // Panel pour les points des joueurs
        JPanel scorePanel = new JPanel(new GridLayout(1, 2, 50, 0));
        scorePanel.setOpaque(false);
        
        this.panelChoix = new JPanel(new GridLayout(1, 3, 50, 0));
        this.panelChoix.setOpaque(false);
        
        // Panel pour les boutons de contrôle (nouvelle manche, menu, etc.)
        // Création des panels de centrage avec FlowLayout
        this.panelBtnRecommencer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panelBtnMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Création du panel principal avec GridLayout
        this.panelFinPartie = new JPanel(new GridLayout(2, 1, 0, 10));
        this.panelFinPartie.setOpaque(false);
        
        // Création des panels pour centrer chaque bouton
        JPanel panelBtnRecommencer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelBtnMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBtnRecommencer.setOpaque(false);
        panelBtnMenu.setOpaque(false);
        
        // Création du panel de combat avec layout pour positionner J1 à gauche et J2 à droite
        this.panelCombat = new JPanel(new GridLayout(1, 2, 100, 0));
        this.panelCombat.setOpaque(false);  // Rend le panel transparent
        
        // Panels pour chaque joueur (pour organiser verticalement le nom et le choix)
        JPanel panelJ1 = new JPanel(new GridLayout(2, 1));
        JPanel panelJ2 = new JPanel(new GridLayout(2, 1));
        panelJ1.setOpaque(false);
        panelJ2.setOpaque(false);
        
        // Création des labels pour l'affichage du combat
        this.lblJ1Combat = new JLabel("Joueur 1", SwingConstants.CENTER);
        this.lblJ2Combat = new JLabel("Joueur 2", SwingConstants.CENTER);
        this.lblChoixJ1 = new JLabel("", SwingConstants.CENTER);
        this.lblChoixJ2 = new JLabel("", SwingConstants.CENTER);
        
        // Configuration des labels
        this.lblJ1Combat.setFont(new Font("Arial", Font.BOLD, 24 ));
        this.lblJ2Combat.setFont(new Font("Arial", Font.BOLD, 24 ));
        this.lblChoixJ1 .setFont(new Font("Arial", Font.PLAIN, 20));
        this.lblChoixJ2 .setFont(new Font("Arial", Font.PLAIN, 20));

        /* ------------------------------------ */
        /* Création des composants */
        /* ------------------------------------ */
        this.lblTitre = new JLabel("Pierre Feuille Ciseau", SwingConstants.CENTER);
        this.lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTitre.setVerticalAlignment(SwingConstants.CENTER);
        this.lblTitre.setFont(new Font("Arial", Font.BOLD, 28));
        this.lblTitre.setForeground(Color.WHITE);

        this.btnParametre = new JButton("⚙");  
        this.btnParametre.setFont(new Font("Arial", Font.BOLD, 20));
        this.btnParametre.setBackground(new Color(80, 80, 100));
        this.btnParametre.setForeground(Color.WHITE);

        // Style commun pour tous les boutons de contrôle
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonBg = new Color(70, 130, 180);
        Color buttonFg = Color.WHITE;
        Dimension buttonSize = new Dimension(200, 40);

        this.btnChangerJoueur = new JButton("Je suis prêt !");
        this.btnChangerJoueur.setFont(buttonFont);
        this.btnChangerJoueur.setBackground(buttonBg);
        this.btnChangerJoueur.setForeground(buttonFg);
        this.btnChangerJoueur.setPreferredSize(buttonSize);
        this.btnChangerJoueur.setFocusPainted(false);
        this.btnChangerJoueur.setVisible(false);

        this.btnNouvelleManche = new JButton("Nouvelle Manche");
        this.btnNouvelleManche.setFont(buttonFont);
        this.btnNouvelleManche.setBackground(buttonBg);
        this.btnNouvelleManche.setForeground(buttonFg);
        this.btnNouvelleManche.setPreferredSize(buttonSize);
        this.btnNouvelleManche.setFocusPainted(false);
        this.btnNouvelleManche.setVisible(false);

        this.btnRecommencerPartie = new JButton("Recommencer la partie");
        this.btnRecommencerPartie.setFont(buttonFont);
        this.btnRecommencerPartie.setBackground(buttonBg);
        this.btnRecommencerPartie.setForeground(buttonFg);
        this.btnRecommencerPartie.setPreferredSize(buttonSize);
        this.btnRecommencerPartie.setFocusPainted(false);
        this.btnRecommencerPartie.setVisible(false);

        this.btnMenu = new JButton("Menu");
        this.btnMenu.setFont(buttonFont);
        this.btnMenu.setBackground(buttonBg);
        this.btnMenu.setForeground(buttonFg);
        this.btnMenu.setPreferredSize(buttonSize);
        this.btnMenu.setFocusPainted(false);
        this.btnMenu.setVisible(false);

        // Création des boutons avec images redimensionnées
        int largeur = 150; // largeur souhaitée en pixels
        int hauteur = 150; // hauteur souhaitée en pixels

        this.imgPierre     = new ImageIcon("jeu/image/cailloux_pfc.png");
        Image imagePierre  = imgPierre.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        this.imgPierre     = new ImageIcon(imagePierre);

        this.imgFeuille    = new ImageIcon("jeu/image/feuille_pfc.png");
        Image imageFeuille = imgFeuille.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        this.imgFeuille    = new ImageIcon(imageFeuille);

        this.imgCiseau     = new ImageIcon("jeu/image/ciseau_pfc.png");
        Image imageCiseau  = imgCiseau.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        this.imgCiseau     = new ImageIcon(imageCiseau);
 
        this.btnPierre  = new JButton(imgPierre);
        this.btnPierre.setContentAreaFilled(false);
        this.btnPierre.setBorderPainted(false);
        this.btnPierre.setBackground(buttonFg);

        this.btnFeuille = new JButton(imgFeuille);
        this.btnFeuille.setContentAreaFilled(false);
        this.btnFeuille.setBorderPainted(false);

        this.btnCiseau  = new JButton(imgCiseau);
        this.btnCiseau.setContentAreaFilled(false);
        this.btnCiseau.setBorderPainted(false);
        
        this.lblTexte = new JLabel("Au tour du Joueur " + this.joueur + ", faites votre choix", SwingConstants.CENTER);
        this.lblTexte.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.lblNbTour = new JLabel( "Tour : " +  this.nbTour++, SwingConstants.CENTER );
        this.lblNbTour.setHorizontalAlignment(SwingConstants.CENTER);

        this.lblPtJ1 = new JLabel("Point du Joueur 1 : " + this.ptJ1, SwingConstants.CENTER);
        this.lblPtJ2 = new JLabel("Point du Joueur 2 : " + this.ptJ2, SwingConstants.CENTER);

        /* ------------------------------------ */
        /* Positionnement des composants */
        /* ------------------------------------ */

        this.panelInfoTitre.add(this.lblTitre    , BorderLayout.CENTER);
        this.panelInfoTitre.add(this.btnParametre, BorderLayout.EAST);

        // Panel pour le texte
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setOpaque(false);
        textPanel.add(this.lblTexte);
        
        // Panel pour le numéro du tour
        JPanel tourPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tourPanel.setOpaque(false);
        tourPanel.add(this.lblNbTour);
        
        // Panel pour les scores
        JPanel pointsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        pointsPanel.setOpaque(false);
        pointsPanel.add(this.lblPtJ1);
        pointsPanel.add(this.lblPtJ2);
        
        this.panelHaut.add(textPanel, BorderLayout.NORTH  );
        this.panelHaut.add(tourPanel, BorderLayout.CENTER );
        this.panelHaut.add(pointsPanel, BorderLayout.SOUTH);

        this.panelChoix.add(this.btnPierre  );
        this.panelChoix.add(this.btnFeuille );
        this.panelChoix.add(this.btnCiseau  );

        // Assemblage du panel de combat
        panelJ1.add(this.lblJ1Combat);
        panelJ1.add(this.lblChoixJ1);
        panelJ2.add(this.lblJ2Combat);
        panelJ2.add(this.lblChoixJ2);
        
        this.panelCombat.add(panelJ1);
        this.panelCombat.add(panelJ2);
        this.panelCombat.setVisible(false);  // Caché par défaut

        // Ajout des boutons dans leur panel de centrage respectif
        panelBtnRecommencer.add(this.btnRecommencerPartie);
        panelBtnMenu.add(this.btnMenu);
        
        // Ajout des panels de centrage au panel principal
        this.panelFinPartie.add(panelBtnRecommencer);
        this.panelFinPartie.add(panelBtnMenu);
        this.panelFinPartie.setVisible(false);

        // Ajout des composants au mainContent
        mainContent.add(this.panelHaut);
        mainContent.add(this.panelChoix);
        mainContent.add(this.panelCombat);
        
        // Panel pour les boutons de contrôle
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlPanel.setOpaque(false);
        controlPanel.add(this.btnChangerJoueur);
        controlPanel.add(this.btnNouvelleManche);
        mainContent .add(controlPanel);
        
        // Ajout du panel de fin de partie
        mainContent.add(this.panelFinPartie);

        // Assemblage final
        this.add(this.panelInfoTitre, BorderLayout.NORTH);
        this.add(mainContent, BorderLayout.CENTER);
        
        // Définir la couleur de fond du panel principal
        this.setBackground(Color.WHITE);

        /* ------------------------------------ */
        /* Activation des composants */
        /* ------------------------------------ */
        initActionListeners();
    }

    private void initActionListeners() 
    {
        SwingUtilities.invokeLater(() -> 
        {
            this.btnPierre           .addActionListener(this);
            this.btnFeuille          .addActionListener(this);
            this.btnCiseau           .addActionListener(this);
            this.btnChangerJoueur    .addActionListener(this);
            this.btnNouvelleManche   .addActionListener(this);
            this.btnRecommencerPartie.addActionListener(this);
            this.btnMenu             .addActionListener(this);
            this.btnParametre        .addActionListener(this);
        });

    }

    @Override
    public void actionPerformed(ActionEvent evt) 
    {
        if ( evt.getSource() == this.btnParametre )
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            this.ctrl.setJeuActuel(frame);
            this.ctrl.getFrameParametre().setVisible(true);
        }
        
        if ( evt.getSource() == this.btnRecommencerPartie )
        {
            // Demander au contrôleur de recréer un nouveau panel
            PanelPFC nouveauPanel = new PanelPFC(this.ctrl );
            
            // Récupérer le parent (la JFrame) et remplacer l'ancien panel par le nouveau
            Container parent = this.getParent();
            parent.remove(this);
            parent.add(nouveauPanel);
            
            // Rafraîchir l'affichage
            parent.revalidate();
            parent.repaint();
        }
        if ( evt.getSource() == this.btnMenu )
        {
            this.ctrl.getFramePFC() .setVisible(false);
            this.ctrl.getFrameMenu().setVisible(true);
            this.btnRecommencerPartie.doClick();
        }
        if (evt.getSource() == this.btnPierre) 
        {
            this.ctrl.setChoixPFC('P', this.joueur);
            if (this.joueur == 1) this.choixJ1 = 'P';
            else                  this.choixJ2 = 'P';
            this.changerJoueur();
        }
        if (evt.getSource() == this.btnFeuille) 
        {
            this.ctrl.setChoixPFC('F', this.joueur);
            if (this.joueur == 1) this.choixJ1 = 'F';
            else                  this.choixJ2 = 'F';
            this.changerJoueur();
        }
        if (evt.getSource() == this.btnCiseau) 
        {
            this.ctrl.setChoixPFC('C', this.joueur);
            if (this.joueur == 1) this.choixJ1 = 'C';
            else                  this.choixJ2 = 'C';
            this.changerJoueur();
        }
        if (evt.getSource() == this.btnChangerJoueur) 
        {
            this.panelChoix.setVisible(true);
            this.joueur = (this.joueur == 1 ? 2 : 1);
            this.lblTexte.setText("Au tour du Joueur " + this.joueur + ", faites votre choix");
            this.btnChangerJoueur.setVisible(false);
        }
        if ( evt.getSource() == this.btnNouvelleManche )
        {
            // augmenter le nombre de tour
            this.lblNbTour.setText( "Tour : " + this.nbTour++ );

            // reset les choix des joueurs
            this.ctrl.setChoixPFC('n', 1 );
            this.ctrl.setChoixPFC('n', 2 );

            this.btnNouvelleManche.setVisible(false);

            this.panelCombat.setVisible(false);  

            // recommencer la partie jusqu'a 3 pt gagner
            this.btnChangerJoueur.doClick();
        }

        int resultat;
        // Combat : retourner le gagnant
        if ( this.ctrl.isChoixFaitPFC() )
        {
            resultat = this.ctrl.combatPFC();

            // Afficher le panel de combat
            this.panelCombat.setVisible(true);
            this.panelChoix .setVisible(false);

            // Mettre à jour les choix des joueurs dans l'affichage du combat
            this.lblChoixJ1.setIcon(this.choixJ1 == 'P' ? this.imgPierre: this.choixJ1 == 'F' ? this.imgFeuille : this.imgCiseau);
            this.lblChoixJ2.setIcon(this.choixJ2 == 'P' ? this.imgPierre: this.choixJ2 == 'F' ? this.imgFeuille : this.imgCiseau);

            switch (resultat)
            {
                case 0 -> 
                {
                    this.lblTexte   .setText("EGALITER ! Personne ne gagne ce tour-ci...");
                    this.lblJ1Combat.setForeground(Color.BLUE);
                    this.lblJ2Combat.setForeground(Color.BLUE);
                }
                case 1 -> 
                {
                    this.lblTexte   .setText("VICTOIRE DU JOUEUR 1 ! ");
                    this.lblJ1Combat.setForeground(Color.GREEN);
                    this.lblJ2Combat.setForeground(Color.RED);
                    this.ptJ1++;
                    this.lblPtJ1.setText("Point du Joueur 1 : " + this.ptJ1);
                }
                case 2 -> 
                {
                    this.lblTexte   .setText("VICTOIRE DU JOUEUR 2 ! ");
                    this.lblJ1Combat.setForeground(Color.RED);
                    this.lblJ2Combat.setForeground(Color.GREEN);
                    this.ptJ2++;
                    this.lblPtJ2.setText("Point du Joueur 2 : " + this.ptJ2 );
                }
            }
            // Enlever le bouton changer joueur car c'est la fin de manche
            this.btnChangerJoueur .setVisible(false);
           
            this.btnNouvelleManche.setVisible(true);

            // Si joueur a 3 pt, alors il gagne
            if ( this.ptJ1 >= 3 || this.ptJ2 >= 3 )
                this.victoire();

        }
    }

    private void changerJoueur()
    {
        // Tour du prochain joueur : écran de changement de joueur
            // boutons visible false
            // changer texte
            // Appuyer sur l'écran quand changement est bon
        this.panelChoix      .setVisible(false);
        this.lblTexte        .setText("Changement de joueur ! Veuillez passez votre téléphone au joueur 2 ! Appuyez sur le bouton lorsque vous êtes prêt.");
        this.btnChangerJoueur.setVisible(true);
    }

    private void victoire()
    {
        if ( this.ptJ1 >= 3 )
        {
            this.lblTexte   .setText( " Le joueur 1 l'emporte !");
            this.lblJ1Combat.setForeground(Color.GREEN);
            this.lblJ2Combat.setForeground(Color.RED);
        }
        else
        {
            this.lblTexte   .setText( " Le joueur 2 l'emporte !");
            this.lblJ1Combat.setForeground(Color.RED);
            this.lblJ2Combat.setForeground(Color.GREEN);
        }

        // Cacher les boutons de jeu
        this.btnNouvelleManche.setVisible(false);
        
        // Afficher les boutons de fin de partie
        this.btnMenu             .setVisible(true);
        this.btnRecommencerPartie.setVisible(true);
        this.panelFinPartie      .setVisible(true);
    }
}