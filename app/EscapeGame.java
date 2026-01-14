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
    */
    public Hero getHero() {
        return hero;
    }
    /** 
     * Prüft, ob ein Spiel läuft.
    */
    public boolean isGameRunning() {
        return gameRunning;
    }
    /** 
     * Prüft, ob das Spiel beendet ist.
    */
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
