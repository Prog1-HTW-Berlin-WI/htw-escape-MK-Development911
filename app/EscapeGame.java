package app;

import model.Hero;
import model.HTWRoom;

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
    /** 
     * Prüft, ob ein Spiel läuft.
    */
    public boolean isGameRunning() {
        return gameRunning;
    }
    /** 
     * Setzt den Spielstatus.
    */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
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
    /** 
     * Startet das Spiel.
    */
    public void run() {
        System.out.println("The game has started. Or not?");
    }
    /** 
     * Gibt den erstellten Helden zurück.
    */
    public Hero getHero() {
        return hero;
    }
}
