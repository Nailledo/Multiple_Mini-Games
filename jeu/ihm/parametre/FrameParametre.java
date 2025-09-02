package jeu.ihm.parametre;

import java.awt.*;
import javax.swing.*;
import jeu.Controleur;

public class FrameParametre extends JFrame 
{
    private Controleur ctrl;

    private JFrame     frameJeuActuel;

    public FrameParametre( Controleur ctrl, JFrame frameJeuActuel )
    {
        this.ctrl = ctrl;
        this.setTitle    ( "KAMAROK'S GAME" );

        // Récupérer la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int)(screenSize.getWidth () / 3);
        int hauteur = (int)(screenSize.getHeight() / 2);
        
        // Définir la taille de la fenêtre
        this.setSize(largeur, hauteur);
        
        // Centrer la fenêtre à l'écran
        this.setLocationRelativeTo(null);      

        this.frameJeuActuel = frameJeuActuel; 
        this.add ( new PanelParametre( this.ctrl, this.frameJeuActuel ) );

        // Instruction pour fermer la fenêtre en swing
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );  

        this.setVisible(false);
    }
    public void setJeuActuel( JFrame frameJeuActuel ) { this.frameJeuActuel = frameJeuActuel; this.revalidate(); this.repaint();}
}