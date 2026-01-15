package app;

import java.util.Scanner;
import model.HTWRoom;
import model.Hero;

/**
* Klasse für die notwendigen Elemente und Aufrufe des Spiels.
* @author Sophie Amrollah Majdabadi
* @author Marvin Kühne
*/

public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private final int MAX_ROUNDS = 24;
    private int rounds = 0;
    
    /**
     * Konstruktor der EscapeGame-Klasse.
     */
    public EscapeGame() {
        this.hero = new Hero();
    }
    
    public void chooseName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Before you can start your journey, please choose a name for your Hero: ");
        String heroName = scanner.nextLine();
        hero.setName(heroName);
        System.out.println("Your name has been set to " +heroName+ ". Your journey begins now, Try to find Miss Majuntke and Escape HTW!");
    }

    /** 
     * Startet das Spiel.
    */
    public void run() {
        //System.out.println("The game has started. Or not?");
        chooseName();
    }
    /** 
     * Setzt den Spielstatus.
    */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
     /** 
     * Gibt den erstellten Helden zurück.
     * @return der Held des Spiels
    */
    public Hero getHero() {
        return hero;
    }
    /** 
     * Prüft, ob ein Spiel läuft.
     * @return true, wenn das Spiel läuft, sonst false
    */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /** 
     * Hauptspielschleife.
     * Enthält momentan nur die Runden- und Regenerationslogik.
    */
    public void gameloop() {
        while (isGameRunning() && !isGameFinished()) {
            for (rounds = 0; rounds < MAX_ROUNDS; rounds++) {
                System.out.println("Round " + (rounds + 1) + " begins.");
            }
                boolean longRest = true;
                
                hero.regenerate(longRest); 
                    if (longRest) {
                        rounds+=1;
                        System.out.println("Long rest taken, you skipped one round! HP restored by 10"); 
                    } else {
                        System.out.println("Short rest taken, no rounds skipped! HP restored by 3");
                    }
        }

}
    /** 
     * Prüft, ob ein GameOver eingetreten ist.
     * @return true, wenn das Spiel durch maximale Runden oder HP = 0 vorbei ist, sonst false
    */
    public boolean isGameOver() {
        if( !hero.isOperational() ) {
            System.out.println("Game Over! Your hero has fallen.");
            return true;
        }
        if (rounds >= MAX_ROUNDS) {
            System.out.println("Game Over! You have run out of time.");
            System.exit(0);
            return true;
        }
        return false;
        

         /** 
     * Prüft, ob das Spiel beendet ist.
    */   
    }
    public boolean isGameFinished() {
        return gameFinished;
    }
    /** 
     * Setzt das Spiel in den beendet-Status.
    */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
