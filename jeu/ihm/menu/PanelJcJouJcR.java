package jeu.ihm.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import jeu.Controleur;

public class PanelJcJouJcR extends JPanel
{
    private Controleur ctrl;
    private String     modeDeJeu;

    private JButton  btnJcJ;
    private JButton  btnJcR;
    private JButton  btnParametre;
    private JPanel panelInfoTitre;
    private JLabel lblTitre;

    public PanelJcJouJcR( Controleur ctrl, String modeDeJeu )
    {
        this.ctrl = ctrl;
        this.modeDeJeu = modeDeJeu;

        /* ------------------------------------ */
        /*			Création des composants		*/
        /* ------------------------------------ */

        this.btnJcJ = new JButton("Joueur contre Joueur");
        this.btnJcR = new JButton("Joueur contre Robot");

        this.setLayout(new BorderLayout(10, 10));

        // Panel principal qui contiendra tout sauf la barre de titre
        JPanel mainContent = new JPanel(new GridLayout(1, 2, 0, 20));
        mainContent.setBackground(Color.WHITE);
        
        // Barre de titre avec fond gris foncé
        this.panelInfoTitre = new JPanel(new BorderLayout());
        this.panelInfoTitre.setBackground(new Color(50, 50, 50));
        this.panelInfoTitre.setPreferredSize(new Dimension(0, 60));

        this.lblTitre = new JLabel("Pierre Feuille Ciseau", SwingConstants.CENTER);
        this.lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTitre.setVerticalAlignment(SwingConstants.CENTER);
        this.lblTitre.setFont(new Font("Arial", Font.BOLD, 28));
        this.lblTitre.setForeground(Color.WHITE);

        this.btnParametre = new JButton();
        this.btnParametre.setBackground(new Color(80, 80, 100));
        this.btnParametre.setForeground(Color.WHITE);


        /* ------------------------------------ */
        /*		Positionnement des composants   */
        /* ------------------------------------ */
        this.panelInfoTitre.add(this.lblTitre    , BorderLayout.CENTER);
        this.panelInfoTitre.add(this.btnParametre, BorderLayout.EAST);

        mainContent.add ( this.btnJcJ );
        mainContent.add ( this.btnJcR );

        this.add(this.panelInfoTitre, BorderLayout.NORTH);
        this.add(mainContent, BorderLayout.CENTER);
    }
}
