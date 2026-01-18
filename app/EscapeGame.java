package app;

import java.util.Scanner;
import model.HTWRoom;
import model.Hero;
import model.Lecturer;
import model.Alien;
import model.Friendly;
import model.Hostile;

/**
* Klasse für die notwendigen Elemente und Aufrufe des Spiels.
* @author Sophie Amrollah Majdabadi
* @author Marvin Kühne
*/

public class EscapeGame {
    private final Hero hero;
    private HTWRoom[] rooms = new HTWRoom[24];
    private boolean gameRunning = true;
    private boolean gameFinished = false;
    private final int MAX_ROUNDS = 24;
    private int rounds = 0;
    private Hostile enemy;
    //TODO koennen vllt bei den einen gespeichert werden & objekt loeschen wenn defeated
    private Friendly alien;
    
    /**
     * Konstruktor der EscapeGame-Klasse.
     */
    public EscapeGame() {
        this.hero = new Hero();
        this.rooms = new HTWRoom[24];
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = new HTWRoom();
            rooms[i].setIdentifier("Room A 0" + (i + 19));
            if (i % 3 == 0) {
                rooms[i].setDescription(", seems to be a PC-Room, maybe I could try and contact anyone?");
            }
            else if (i % 3 == 1) {
                rooms[i].setDescription(", it's a simple classroom, the message \"NO WAY OUT\" is written on the board..");
            }
            else {
                rooms[i].setDescription(", could be used to write an Exam, quite spacious.");
            }
        }
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
        while (isGameRunning() && !isGameFinished() && !isGameOver() && !endGame()) {
            System.out.println("Round " + (rounds + 1) + " begins.");
                showGameMenu();
                gameMenu();
            if (isGameOver() || isGameFinished() || endGame()) {
                System.exit(0);
            }  
        } 
    }

    private void showGameMenu() {
        System.out.println("Game Menu:");
        System.out.println("[E] Explore HTW");
        System.out.println("[H] Hero Status");
        System.out.println("[S] Show signatures");
        System.out.println("[R] Rest");
        System.out.println("[L] Leave Game");
        System.out.println("Choose an option: ");
    }

    private void gameMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        switch (choice) {
            case "E":
                System.out.println("Exploring HTW...");
                System.out.println("There are two rooms in front of you: Room A and Room B. Which one do you want to enter? (A/B)");
                String roomChoice = scanner.nextLine().toUpperCase();
                if (roomChoice.equals("A") || roomChoice.equals("B")) {
                    enterRandomRoom();
                    rounds++;
                } else {
                    System.out.println("Invalid choice, try going either way [A] or [B]");
                }
                break;
            case "H":
                System.out.println("Hero Status:");
                System.out.println("Name: " + hero.getName());
                System.out.println("HP: " + hero.getHp() + "/" + hero.getMaxHP());
                System.out.println("XP: " + hero.getXp()); 
                hero.showSignatures();
                break;
            case "S":
                hero.showSignatures();
                break;
            case "R":
                System.out.println("Do you want to take a long rest (1) or a short rest (2)?");
                String restChoice = scanner.nextLine();
                if (restChoice.equals("1")) {
                    hero.regenerate(true);
                    rounds++;
                } else if (restChoice.equals("2")) {
                    hero.regenerate(false);
                } else {
                    System.out.println("Invalid choice. No rest taken, try choosing either [1] or [2].");
                }
                break;
            case "L":
                System.out.println("You left the game, Hero! See you next time!");
                setGameRunning(false);
                break;
            default:
                System.out.println("Invalid choice. Please try using either option [E], [H], [S], [R] or [L].");
                gameMenu();
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


    private void enterRandomRoom() {
        int randomIndex = (int) (Math.random() * rooms.length);
        HTWRoom selectedRoom = rooms[randomIndex];
        System.out.println("You have entered " + selectedRoom.getIdentifier() + selectedRoom.getDescription());
        
        double eventChance = Math.random();

        double alienChance = 0.52;

        double lecturerChance = 0.28;

        double nothing = 0.20;

        if (eventChance < alienChance) {
            System.out.println("An Alien appears!");
            boolean friendly = Math.random() < 0.50;
            if (friendly){
                alien = new Friendly();
                alien.butters();
                friendlyInputHandler();
            }
            else {
                enemy = new Hostile();
                enemy.manRay();
                hostileInputHandler();
            }
            
        } else if (eventChance < alienChance + lecturerChance) {
            String[] lecturerNames = {"Mrs. Safitri", "Mr. Poeser", "Mrs. Vaseva", "Mrs. Gaertner", "Mrs. Gnaoui"};
            int lecturerIndex = (int) (Math.random() * lecturerNames.length);
            String lecturerName = lecturerNames [lecturerIndex];

            Lecturer lecturer = new Lecturer();
            lecturer.setName(lecturerName);
            selectedRoom.setLecturer(lecturer);

            if (hero.hasSigned(lecturerName)) {
            System.out.println("Lecturer " + lecturerName + " has already signed and went away. Nothing more to do here.");
            return;
            }

            System.out.println("You encounter Lecturer " + lecturer.getName() + "!");
            System.out.println("Do you want to try to get a signature from " + lecturer.getName() + "? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            String signChoice = scanner.nextLine().toUpperCase();
            if (signChoice.equals("Y")) {
                lecturer.sign();
            }
                if (lecturer.getHasSigned()) {
                    hero.signedExerciseLeaders(lecturer);
                } else {
                System.out.println("You chose not to interact with " + lecturer.getName() + ".");
            }
        }
        else {
            System.out.println("There's nobody here.");
        }
    }

    public void friendlyInputHandler(){
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    System.out.println("I see, you were trapped here...I saw Prof. Majuntke arrive in a spaceship herself, " +"\n"+ "maybe she isn't all she seems to be..");
                    break;
                case "2":
                    if (hero.flee() == false) {
                        System.out.println(", try and Flee again! [2]");
                        friendlyInputHandler();
            }   break;
                default:
                    System.out.println("Wrong input, please try either option [1] or [2].");
                    friendlyInputHandler();
                    break;
                }
        }

        public void hostileInputHandler(){
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("You have entered a Battle! Try to defeat the Alien!");
                    combatMenu();
                    break;
                case "2":
                    if (hero.flee() == false) {
                        System.out.println(" You now have to fight the Alien!");
                        combatMenu();
            }   break;
                default:
                    System.out.println("Wrong input, please try either option [1] or [2].");
                    hostileInputHandler();  
                    break;
                }
        }

        public void combatMenu(){
            System.out.println("Press [1] to attack!");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    while (!enemy.isDefeated()) {
                        enemy.takeDamage(hero.attack());
                        hero.takeDamage(enemy.attack());
                }   if (enemy.isDefeated()) {
                        System.out.println(hero.getName()+ " has gained " +hero.addExperiencePoints(5)+ "XP! Good job!");
                        enemy = null;
                }   break;
                default:
                    System.out.println("Wrong input, please try to use \"attack\".");
                    break;
            }
        } 

        public boolean endGame() {
            if(hero.getAllSignaturesCollected()) {
        System.out.println("Congratulations! You have collected all signatures and escaped HTW!");
        setGameFinished(true);
        }
        return isGameFinished();
    }
}
