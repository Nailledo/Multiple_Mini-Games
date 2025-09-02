package jeu.metier.memory;

public class Memory 
{
    private int difficulte;

    public Memory(String difficulte)
    {
        this.difficulte = this.choixDifficulte( difficulte );
    }    

    public int[][] construireMemory()
    {
        int[][] tabMemory = new int[this.difficulte][this.difficulte];
        int     nbPaires  = (this.difficulte * this.difficulte) / 2; 
        
        // Créer un tableau avec les valeurs à placer (pour qu'il y ait 2 nombre dans le tableau identiques)
        int[] valeurs = new int[this.difficulte * this.difficulte];
        for(int i = 0; i < nbPaires; i++) 
        {
            valeurs[i*2]     = i;    
            valeurs[i*2 + 1] = i;
        }
        
        // Mélanger le tableau de valeurs
        for(int i = valeurs.length-1; i > 0; i--) 
        {
            int j = (int)(Math.random() * (i + 1));
            // Échanger les valeurs
            int temp = valeurs[i];
            valeurs[i] = valeurs[j];
            valeurs[j] = temp;
        }
        
        // Remplir le tableau
        int index = 0;
        for(int i = 0; i < this.difficulte; i++) 
            for(int j = 0; j < this.difficulte; j++) 
                tabMemory[i][j] = valeurs[index++];
        
        return tabMemory;
    }
    private int choixDifficulte( String difficulte )
    {
        return switch ( difficulte )
        {
            case "Facile"    -> 4;
            case "Moyen"     -> 6;
            case "Difficile" -> 8;
            default          -> 4; // Mode facile par défaut
        };
    }
}
