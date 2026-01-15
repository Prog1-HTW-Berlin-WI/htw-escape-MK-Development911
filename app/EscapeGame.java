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
        chooseName();
        gameloop();
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
            System.out.println("Round " + (rounds + 1) + " begins.");
                showGameMenu();
                gameMenu();
            
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


    private void showGameMenu() {
        System.out.println("Game Menu:");
        System.out.println("[E] Explore HTW");
        System.out.println("[H] Hero Status");
        System.out.println("[S] Show signatures");
        System.out.println("[R] Rest");
        System.out.println("[L] Leave Game");
        System.out.println("Choose an action: ");
    }

    private void gameMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        switch (choice) {
            case "E":
                System.out.println("Exploring HTW...");
                System.out.println("There are two rooms in front of you: Room A and Room B. Which one do you want to enter? (A/B)");
                String roomChoice = scanner.nextLine().toUpperCase();
                rounds++;
                if (roomChoice.equals("A")) {
                    System.out.println("You have entered Room A.");
                } else if (roomChoice.equals("B")) {
                    System.out.println("You have entered Room B.");
                } else {
                    System.out.println("Invalid room choice.");
                }
                break;
            case "H":
                System.out.println("Hero Status:");
                System.out.println("Name: " + hero.getName());
                System.out.println("HP: " + hero.getHp() + "/" + hero.getMaxHP());
                System.out.println("XP: " + hero.getXp()); 
                // Placeholder for showing signatures logic
                break;
            case "S":
                System.out.println("Showing signatures...");
                // Placeholder for showing signatures logic

                break;
            case "R":
                System.out.println("Do you want to take a long rest (1) or a short rest (2)?");
                String restChoice = scanner.nextLine();
                if (restChoice.equals("1")) {
                    hero.regenerate(true);
                } else if (restChoice.equals("2")) {
                    hero.regenerate(false);
                } else {
                    System.out.println("Invalid choice. No rest taken.");
                }
                break;
            case "L":
                System.out.println("You left the game, Hero! See you next time!");
                setGameRunning(false);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
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
            System.out.println("Mrs. Majuntke boards her ship and leaves!");
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
