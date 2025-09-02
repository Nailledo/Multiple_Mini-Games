package jeu.ihm.menu;

import java.awt.*;
import javax.swing.*;
import jeu.Controleur;

public class FrameMenu extends JFrame
{  
    private Controleur ctrl;

    public FrameMenu( Controleur ctrl )
    {
        this.ctrl = ctrl;
        this.setTitle    ( "KAMAROK'S GAME" );
        this.setLocation ( 0, 0 );

        // Récupérer la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);

        this.add ( new PanelMenu( this.ctrl ) );

        // Instruction pour fermer la fenêtre en swing
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }   
}