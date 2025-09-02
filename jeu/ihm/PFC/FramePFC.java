package jeu.ihm.PFC;

import java.awt.*;
import javax.swing.*;
import jeu.Controleur;

public class FramePFC extends JFrame
{  
    private Controleur ctrl;

    private PanelPFC panelPFC;

    public FramePFC( Controleur ctrl )
    {
        this.ctrl = ctrl;
        this.setTitle    ( "KAMAROK'S GAME - Pierre Feuille Ciseau" );
        this.setLocation ( 0, 0 );

        // Récupérer la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);

        
        this.panelPFC = new PanelPFC( this.ctrl ); 
        this.add( this.panelPFC );

        // Instruction pour fermer la fenêtre en swing
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(false);
    }  

    public void reinitialiserJeu()
    {
        // Demander au contrôleur de recréer un nouveau panel
        PanelPFC nouveauPanel = new PanelPFC( this.ctrl );
        
        // Récupérer le parent (la JFrame) et remplacer l'ancien panel par le nouveau
        Container parent = this.getParent();
        parent.remove(this);
        parent.add(nouveauPanel);
        
        // Rafraîchir l'affichage
        parent.revalidate();
        parent.repaint();
    }
}