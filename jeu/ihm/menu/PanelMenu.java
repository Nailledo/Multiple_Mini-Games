package jeu.ihm.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jeu.Controleur;

public class PanelMenu extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JPanel     panelInfoTitre;
    private JPanel     panelJeu;

    private JLabel     lblTitre;
    private JButton    btnParametre;

    private JButton    btnPFC;
    private JButton    btnMemory;

    public PanelMenu ( Controleur ctrl ) 
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());

        /* ------------------------------------ */
        /* Création des Composants */
        /* ------------------------------------ */
        this.panelInfoTitre = new JPanel(new BorderLayout());
        this.panelJeu       = new JPanel(new GridLayout(3, 3, 50, 50));

        this.lblTitre = new JLabel("KAMAROK'S GAME", SwingConstants.CENTER);
        this.lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTitre.setVerticalAlignment  (SwingConstants.CENTER);
        this.lblTitre.setFont               (new Font("Arial", Font.BOLD, 36));
        this.lblTitre.setForeground         (Color.RED);

        this.btnParametre = new JButton();
        this.btnParametre.setBackground(new Color(80, 80, 100));
        this.btnParametre.setForeground(Color.WHITE);
        
        // BOUTONS DE JEU
        // --------------
        this.btnPFC    = this.creerJButton("Pierre Feuille Ciseau");
        this.btnMemory = this.creerJButton("Memory");


        this.panelJeu.add( this.btnPFC    );
        this.panelJeu.add( this.btnMemory );
        this.panelJeu.add( this.creerJButton("test")); 

        this.panelJeu.add( this.creerJButton("test"));
        this.panelJeu.add( this.creerJButton("test"));
        this.panelJeu.add( this.creerJButton("test"));
 
        this.panelJeu.add( this.creerJButton("test"));
        this.panelJeu.add( this.creerJButton("test"));
        this.panelJeu.add( this.creerJButton("test"));


        /* ------------------------------------ */
        /* Positionnement des composants */
        /* ------------------------------------ */
        this.panelInfoTitre.add(this.lblTitre    , BorderLayout.CENTER);
        this.panelInfoTitre.add(this.btnParametre, BorderLayout.EAST);

        this.add(this.panelInfoTitre, BorderLayout.NORTH);
        this.add(this.panelJeu, BorderLayout.CENTER);

        /* ------------------------------------ */
        /* Activation des composants */
        /* ------------------------------------ */

        this.btnPFC   .addActionListener(this);
        this.btnMemory.addActionListener(this);
    }

    private JButton creerJButton( String nomJeu )
    {
        JButton btn = new JButton( nomJeu );
        btn.setFont(new Font("Arial", Font.PLAIN, 24));
        btn.setBackground(new Color(100, 120, 180)); 
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(60, 63, 65), 2));
        btn.setOpaque(true);

        return btn;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if ( evt.getSource() == this.btnPFC )
        {
            //Mettre invisible la frameMenu
            this.ctrl.getFrameMenu().setVisible(false);
            //Afficher la frame PFC
            this.ctrl.getFramePFC().setVisible(true);
        }

        if ( evt.getSource() == this.btnMemory )
        {
            //Mettre invisible la frameMenu
            this.ctrl.getFrameMenu().setVisible(false);
            //Afficher la frame PFC
            this.ctrl.getFrameMemory().setVisible(true);
        }

        if ( evt.getSource() == this.btnParametre )
        {
            this.ctrl.getFrameParametre().setVisible(true);
        }
    }
}