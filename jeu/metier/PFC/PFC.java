package jeu.metier.PFC;

import java.util.Scanner;

public class PFC 
{
    private static int nbrTour;
    private static int ptJ1;
    private static int ptJ2;

    private Scanner    scanner;

    private char       j1;
    private char       j2;

    public PFC( boolean modeJcJ)
    {
        this.scanner = new Scanner(System.in);

        PFC.nbrTour++;

        // J contre J
        // if ( modeJcJ )
        // {
        //     this.lireEntree( 1 );
        //     this.lireEntree( 2 );
        // }
        // // J contre Robot
        // else
        // {
        //     System.out.println("plus tard");
        // }
        // Décision du vainqueur
        // this.combat();
    }

    public String lireEntree( int joueur ) 
    {
        System.out.println("joueur " + joueur + ", veuillez entrer votre choix (P)ierre  (F)euille  (C)iseau : ");
        
        String choix = scanner.nextLine().toUpperCase();
        while ( !choix.equals("P") && 
                !choix.equals("F") && 
                !choix.equals("C") )
        {
            System.out.println("joueur " + joueur + ", Veuillez entrer votre choix (P)ierre  (F)euille  (C)iseau : ");
            choix = scanner.nextLine();
        }

        if ( joueur == 1 ) this.j1 = choix.charAt(0 );
        else               this.j2 = choix.charAt(0 );

        return choix;     
    }

    public String choixRobot()
    {
        String choix = "";
        int num = (int)(Math.random()*3);

        switch ( num )
        {
            case 0 -> choix = "Pierre";
            case 1 -> choix = "Feuille";
            case 2 -> choix = "Ciseau";
        }
        return choix;
    }

    public int combat()
    {
        if      ( this.j1 == 'P' && this.j2 == 'C' ) { System.out.println( "j1 gagne"); PFC.ptJ1++; return 1; }
        else if ( this.j1 == 'C' && this.j2 == 'P' ) { System.out.println( "j2 gagne"); PFC.ptJ2++; return 2; }
  
        else if ( this.j1 == 'C' && this.j2 == 'F' ) { System.out.println( "j1 gagne"); PFC.ptJ1++; return 1; }
        else if ( this.j1 == 'F' && this.j2 == 'C' ) { System.out.println( "j2 gagne"); PFC.ptJ2++; return 2; }
  
        else if ( this.j1 == 'F' && this.j2 == 'P' ) { System.out.println( "j1 gagne"); PFC.ptJ1++; return 1; }
        else if ( this.j1 == 'P' && this.j2 == 'F' ) { System.out.println( "j2 gagne"); PFC.ptJ2++; return 2; }

        else if ( this.j1 == 'P' && this.j2 == 'P' ) System.out.println( "égalité"); 
        else if ( this.j1 == 'C' && this.j2 == 'C' ) System.out.println( "égalité");
        else if ( this.j1 == 'F' && this.j2 == 'F' ) System.out.println( "égalité");
        return 0;

    }

    public boolean isChoixFait()
    {
        return ( this.j1 == 'P'|| this.j1 == 'F' || this.j1 == 'C' ) && 
               ( this.j2 == 'P'|| this.j2 == 'F' || this.j2 == 'C' ) ;
    }

    public String toString()
    {
        String stat = "-----------------------------------------------------\n";

        // NbrTour
        stat += "Tour " + PFC.nbrTour + "\n";
        // Point J1
        stat += "Joueur 1 : " + PFC.ptJ1 + "\n";
        // Point J2
        stat += "Joueur 2 : " + PFC.ptJ2 + "\n";

        stat += "-----------------------------------------------------\n";
        return stat;
    }

    public void setChoix( char choix, int joueur )
    {
        if ( joueur == 1 ) this.j1 = choix;
        else               this.j2 = choix;
    }
    // public static void main(String[] args) 
    // {
    //     PFC pfc;

    //     while( true )
    //     {
    //         pfc = new PFC( true );
    //     }
            
    // }
}
