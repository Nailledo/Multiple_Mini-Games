    package jeu;

    import javax.swing.JFrame;
    import jeu.ihm.PFC.FramePFC;
    import jeu.ihm.memory.FrameMemory;
    import jeu.ihm.menu.FrameMenu;
    import jeu.ihm.parametre.FrameParametre;
    import jeu.metier.PFC.*;
    import jeu.metier.memory.*;

    public class Controleur 
    {
        private FrameMenu    frameMenu;

        private FrameParametre frameParametre;

        private Memory       memory;
        private FrameMemory  frameMemory;

        private FramePFC     framePFC;
        private PFC          pfc;

        public Controleur()
        {
            this.frameMenu = new FrameMenu( this );
            this.frameMenu.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // sert a mettre en plein écran
            
            // Parametre
            this.frameParametre = new FrameParametre( this, null);

            // PFC
            this.pfc       = new PFC( true);
            this.framePFC  = new FramePFC( this );
            this.framePFC.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

            // Memory
            this.frameMemory = new FrameMemory( this );
            this.frameMemory.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        }    

        public FrameMenu getFrameMenu() { return this.frameMenu; }

        /* ------------------------------------ */
        /*			Parametre					*/
        /* ------------------------------------ */
        public void setJeuActuel( JFrame frameJeuActuel ) 
        { 
            this.frameParametre.remove( this.frameParametre );
            this.frameParametre = new FrameParametre(this, frameJeuActuel);
        }
        public FrameParametre getFrameParametre() { return this.frameParametre; }

        /* ------------------------------------ */
        /*			PIERRE FEUILLE CISEAU		*/
        /* ------------------------------------ */
        public FramePFC  getFramePFC   ()                        { return this.framePFC;  }
        public void      setChoixPFC   (char choix, int joueur ) { this.pfc.setChoix( choix, joueur ); }
        public int       combatPFC     ()                        { return this.pfc.combat(); }
        public boolean   isChoixFaitPFC()                        { return this.pfc.isChoixFait(); }


        /* ------------------------------------ */
        /*			Memory					    */
        /* ------------------------------------ */
        public FrameMemory getFrameMemory()   { return this.frameMemory;    }
        
        public int[][]     getTabMemory( String difficulte )    
        { 
            this.memory = new Memory( difficulte );
            return this.memory.construireMemory(); 
        }

        public static void main(String[] args) { new Controleur(); }
    }
