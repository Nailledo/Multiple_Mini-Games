package jeu.ihm.memory;

import java.awt.*;
import javax.swing.*;
import jeu.Controleur;

public class FrameMemory extends JFrame
{  
    private Controleur ctrl;

    public FrameMemory( Controleur ctrl )
    {
        this.ctrl = ctrl;
        this.setTitle    ( "KAMAROK'S GAME - Memory" );
        this.setLocation ( 0, 0 );

        // Récupérer la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);

        this.add ( new PanelMemory( this.ctrl ) );

        // Instruction pour fermer la fenêtre en swing
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(false);
    }   
}