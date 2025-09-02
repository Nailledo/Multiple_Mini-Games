package jeu.ihm.parametre;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import jeu.Controleur;
import jeu.ihm.PFC.FramePFC;
import jeu.ihm.PFC.PanelPFC;
import jeu.ihm.memory.FrameMemory;
import jeu.ihm.memory.PanelMemory;
public class PanelParametre extends JPanel implements ActionListener
{
    private Controleur ctrl;
    private JFrame     frameJeuActuel;

    private JLabel    lblTitre;

    private JButton btnReprendre;
    private JButton btnRecommencer;
    private JButton btnMenu;
    private JButton btnQuitter;

    private JPanel panelBouton;

    public PanelParametre( Controleur ctrl, JFrame frameJeuActuel )
    {
        this.ctrl           = ctrl;
        this.frameJeuActuel = frameJeuActuel;
        this.setLayout( new BorderLayout() );

        /* ------------------------------------ */
        /*			Création des composants		*/
        /* ------------------------------------ */

        this.lblTitre = new JLabel("PARAMETRE", SwingConstants.CENTER);

        this.panelBouton = new JPanel( new GridLayout(4, 1, 40, 40 ));

        this.btnReprendre   = new JButton("Reprendre");
        this.btnRecommencer = new JButton("Recommencer");
        this.btnMenu        = new JButton("Menu");
        this.btnQuitter     = new JButton("Quitter le jeu");

        /* ------------------------------------ */
        /*	Positionnement des composants	*/
        /* ------------------------------------ */

        this.panelBouton.add ( this.btnReprendre   );
        this.panelBouton.add ( this.btnRecommencer );
        this.panelBouton.add ( this.btnMenu        );
        this.panelBouton.add ( this.btnQuitter     );

        this.add ( lblTitre        , BorderLayout.NORTH  );
        this.add ( this.panelBouton, BorderLayout.CENTER );


        /* ------------------------------------ */
        /*			activation des composants*/
        /* ------------------------------------ */

        this.btnReprendre  .addActionListener(this);
        this.btnRecommencer.addActionListener(this);
        this.btnMenu       .addActionListener(this);
        this.btnQuitter    .addActionListener(this);
        
    }

    public void actionPerformed(ActionEvent e )
    {
        if ( e.getSource() == this.btnReprendre )
        {
            this.ctrl.getFrameParametre().setVisible(false);
        }
        if ( e.getSource() == this.btnRecommencer )
        {  
            // Fermer la fenêtre des paramètres
            this.ctrl.getFrameParametre().setVisible(false);
            
            // Identifier le type de jeu et le redémarrer
            if ( this.frameJeuActuel instanceof FramePFC )
            {
                // Redémarrer le jeu PFC en utilisant la même logique que dans PanelPFC
                PanelPFC nouveauPanel = new PanelPFC(this.ctrl);
                
                // Récupérer le conteneur de la frame et remplacer le panel
                Container parent = this.frameJeuActuel.getContentPane();
                parent.removeAll(); // Supprimer tous les composants
                parent.add(nouveauPanel);
                
                // Rafraîchir l'affichage
                parent.revalidate();
                parent.repaint();
            }
            else if ( this.frameJeuActuel instanceof FrameMemory )
            {
                // Redémarrer le jeu Memory
                PanelMemory nouveauPanel = new PanelMemory(this.ctrl );
                
                // Récupérer le parent (la JFrame) et remplacer l'ancien panel par le nouveau
                Container parent = this.getParent();
                parent.remove(this);
                parent.add(nouveauPanel);
                
                // Rafraîchir l'affichage
                parent.revalidate();
                parent.repaint();
            }
        }
        if ( e.getSource() == this.btnMenu )
        {
            // Fermer la fenêtre des paramètres
            this.ctrl.getFrameParametre().setVisible(false);
            
            // Fermer le jeu actuel et retourner au menu
            this.frameJeuActuel.setVisible(false);
            this.ctrl.getFrameMenu().setVisible(true);
        }
        if ( e.getSource() == this.btnQuitter )
        {
            System.exit(0);
        }
    }
}
