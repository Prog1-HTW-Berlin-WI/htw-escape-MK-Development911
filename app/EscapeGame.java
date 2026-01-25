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
 * 
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
    private Friendly alien;
    private int regenCounter = 0;
    private int wrongAnswer = 0;

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
            } else if (i % 3 == 1) {
                rooms[i].setDescription(
                        ", it's a simple classroom, the message \"NO WAY OUT\" is written on the board..");
            } else {
                rooms[i].setDescription(", could be used to write an Exam, quite spacious.");
            }
        }
    }

    /**
     * Lässt den Spieler einen Namen für den Helden wählen.
     */
    public void chooseName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Before you can start your journey, please choose a name for your Hero: ");
        System.out.println("");
        String heroName = scanner.nextLine();
        hero.setName(heroName);
        System.out.println("");
        System.out.println("Your name has been set to " + heroName
                + ". Your journey begins now, Try to find Miss Majuntke and Escape HTW!");
    }

    /**
     * Startet das Spiel.
     */
    public void run() {
        if (!hero.hasName()) {
            chooseName();
        }
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
     * 
     * @return der Held des Spiels
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Prüft, ob ein Spiel läuft.
     * 
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
            System.out.println("");
            System.out.println("==================================");
            System.out.println("Round " + (rounds + 1) + " begins.");
            System.out.println("==================================");
            System.out.println("");
            showGameMenu();
            gameMenu();
            if (isGameOver() || isGameFinished() || endGame()) {
                System.exit(0);
            }
        }
    }

    /**
     * Zeigt das Spielmenü an.
     */
    private void showGameMenu() {
        System.out.println("Game Menu:");
        System.out.println("[E] Explore HTW");
        System.out.println("[H] Hero Status");
        System.out.println("[S] Show signatures");
        System.out.println("[R] Rest");
        System.out.println("[L] Leave Game");
        System.out.println("[M] Main Menu");
        System.out.println("Choose an option: ");
    }

    /**
     * Verarbeitet die Eingaben im Spielmenü.
     */
    private void gameMenu() {
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        switch (choice) {
            case "E":
                System.out.println("");
                System.out.println("You're exploring the HTW...");
                System.out.println(
                        "There are two rooms in front of you: Room A and Room B. Which one do you want to enter? (A/B)");
                System.out.println("");
                String roomChoice = scanner.nextLine().toUpperCase();
                if (roomChoice.equals("A") || roomChoice.equals("B")) {
                    enterRandomRoom();
                    rounds++;
                    regenCounter = 0;
                } else {
                    System.out.println("");
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
                } else if (regenCounter != 1 && restChoice.equals("2")) {
                    hero.regenerate(false);
                    regenCounter++;
                } else {
                    if (regenCounter == 1 && restChoice.equals("2")) {
                        System.out.println("You can only short rest once per round!" + "\n" + "No HP recovered.");
                    } else {
                        System.out.println("Invalid choice. No rest taken, try choosing either [1] or [2].");
                    }
                }
                break;
            case "L":
                System.out.println("You left the game, Hero! See you next time!");
                setGameRunning(false);
                break;
            case "M":
                System.out.println("The game has been paused...");
                setGameRunning(false);
                break;
            default:
                System.out.println("Invalid choice. Please try using either option [E], [H], [S], [R] or [L].");
                gameMenu();
        }
    }

    /**
     * Prüft, ob ein GameOver eingetreten ist.
     * 
     * @return true, wenn das Spiel durch maximale Runden oder HP = 0 vorbei ist,
     *         sonst false
     */
    public boolean isGameOver() {
        if (!hero.isOperational()) {
            System.out.println("==================================");
            System.out.println("Game Over! Your hero has fallen.");
            System.out.println("==================================");
            return true;
        }
        if (rounds >= MAX_ROUNDS) {
            System.out.println("==================================");
            System.out.println("Game Over! You have run out of time.");
            System.out.println("Mrs. Majuntke boards her ship and leaves!");
            System.out.println("==================================");
            System.exit(0);
            return true;
        }
        if (wrongAnswer == 2) {
            System.out.println("==================================");
            System.out.println("Game Over! You didn't pass the test...");
            System.out.println("Mrs. Majuntke boards her ship and leaves!");
            System.out.println("==================================");
            System.exit(0);
            return true;
        }
        return false;

        /**
         * Prüft, ob das Spiel beendet ist.
         */
    }

    public boolean isGameFinished() {
        if (gameFinished == true) {
            System.exit(0);
            return true;
        }
        return false;
    }

    /**
     * Setzt das Spiel in den beendet-Status.
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Lässt den Helden einen zufälligen Raum betreten und verarbeitet Ereignisse
     * darin.
     */
    private void enterRandomRoom() {
        int randomIndex = (int) (Math.random() * rooms.length);
        HTWRoom selectedRoom = rooms[randomIndex];
        System.out.println("");
        System.out.println("You have entered " + selectedRoom.getIdentifier() + selectedRoom.getDescription());

        double eventChance = Math.random();

        double alienChance = 0.52;

        double lecturerChance = 0.28;

        if (eventChance < alienChance) {
            System.out.println("");
            System.out.println("An Alien appears!");
            boolean friendly = Math.random() < 0.50;
            if (friendly) {
                alien = new Friendly();
                alien.butters();
                friendlyInputHandler();
            } else {
                enemy = new Hostile();
                enemy.manRay();
                hostileInputHandler();
            }

        } else if (eventChance < alienChance + lecturerChance) {
            String[] lecturerNames = { "Mrs. Safitri", "Mr. Poeser", "Mrs. Vaseva", "Mrs. Gaertner", "Mr. Gnaoui" };
            String[] lecturerDescription = {
                    "She's sitting on a chair with her pink Stanley bottle, she seems to be daydreaming..",
                    "He is drawing a beautiful landscape on the whiteboard, it's quite mesmerizing.",
                    "She's fidgeting with a pen in her hand, she seems to be nervous..",
                    "The window is open, her hair is flying with the wind.",
                    "He looks confidently at you, almost as if he anticipated your arrivial." };
            int lecturerIndex = (int) (Math.random() * lecturerNames.length);
            String lecturerName = lecturerNames[lecturerIndex];
            int descriptionIndex = lecturerIndex;
            String lecturerDescriptions = lecturerDescription[descriptionIndex];
            Lecturer lecturer = new Lecturer();
            lecturer.setName(lecturerName);
            lecturer.setDescription(lecturerDescriptions);
            selectedRoom.setLecturer(lecturer);

            if (hero.hasSigned(lecturerName)) {
                System.out.println("");
                System.out.println("Nothing more to do here.");
                return;
            }

            System.out.println("You encounter " + lecturer.getName() + " " + lecturer.getDescription());
            System.out.println("Do you want to try to get a signature from " + lecturer.getName() + "? (Y/N)");
            System.out.println("");
            Scanner scanner = new Scanner(System.in);
            String signChoice = scanner.nextLine().toUpperCase();
            if (signChoice.equals("Y")) {
                System.out.println("");
                lecturer.sign();
            }
            if (lecturer.getHasSigned()) {
                hero.signedExerciseLeaders(lecturer);
            } else if (signChoice.equals("N")) {
                System.out.println("");
                System.out.println("You chose not to interact with " + lecturer.getName() + ".");
            } else {
                do {
                    System.out.println("");
                    System.out.println("Wrong input, try either \"Y\" or \"N\"");
                    System.out.println("");
                    signChoice = scanner.nextLine().toUpperCase();
                } while (!signChoice.equals("Y") && !signChoice.equals("N"));
                if (signChoice.equals("Y")) {
                    System.out.println("");
                    lecturer.sign();
                }
                if (lecturer.getHasSigned()) {
                    hero.signedExerciseLeaders(lecturer);
                } else if (signChoice.equals("N")) {
                    System.out.println("");
                    System.out.println("You chose not to interact with " + lecturer.getName() + ".");
                }
            }
        } else {
            System.out.println("");
            System.out.println("There's nobody here.");
        }
    }

    /**
     * Verarbeitet die Eingaben für die Begegnungen mit Butters (friendly Alien).
     */
    public void friendlyInputHandler() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("");
                System.out.println("I see, you were trapped here...I saw Prof. Majuntke arrive in a spaceship herself, "
                        + "\n" + "maybe she isn't all she seems to be..");
                System.out.println("");
                break;
            case "2":
                if (hero.flee() == false) {
                    System.out.println(", try and Flee again! [2]");
                    System.out.println("");
                    friendlyInputHandler();
                }
                break;
            default:
                System.out.println("");
                System.out.println("Wrong input, please try either option [1] or [2].");
                System.out.println("");
                friendlyInputHandler();
                break;
        }
    }

    /**
     * Verarbeitet die Eingaben für die Begegnungen mit feindlichen Aliens (ManRay).
     */
    public void hostileInputHandler() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                System.out.println("");
                System.out.println("You have entered a Battle! Try to defeat the Alien!");
                combatMenu();
                break;
            case "2":
                if (hero.flee() == false) {
                    System.out.println("");
                    System.out.println("You now have to fight the Alien!");
                    combatMenu();
                }
                break;
            default:
                System.out.println("");
                System.out.println("Wrong input, please try either option [1] or [2].");
                System.out.println("");
                hostileInputHandler();
                break;
        }
    }

    /**
     * Verarbeitet die Eingaben für das Kampfmenü gegen feindliche Aliens.
     */
    public void combatMenu() {
        System.out.println("");
        System.out.println("Press [1] to fight!");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                while (!enemy.isDefeated()) {
                    enemy.takeDamage(hero.attack());
                    System.out.println("");
                    hero.takeDamage(enemy.attack());
                    System.out.println("");
                }
                if (enemy.isDefeated()) {
                    System.out.println("");
                    System.out.println(hero.getName() + " has gained " + hero.addExperiencePoints(5) + "XP! Good job!");
                    enemy = null;
                }
                break;
            default:
                System.out.println("");
                System.out.println("Wrong input, please try to use \"fight\".");
                System.out.println("");
                combatMenu();
                break;
        }
    }

    /**
     * Beendet das Spiel, wenn alle Unterschriften gesammelt wurden.
     * 
     * @return true, wenn das Spiel beendet ist, sonst false
     */
    public boolean endGame() {
        if (hero.getAllSignaturesCollected()) {
            majuntke();
            endgameMenu();
        }
        return false;
    }

    /**
     * Aufruf für Prof Majuntke im Endgame
     * Methode dient ausschließlich der Übersicht
     */
    public void majuntke() {
        System.out.println("");
        System.out.println("You leave the room and Prof Majuntke stands in the hallway." + "\n" +
                "So you collected all the signatures, huh? If you get the next question right, you're allowed to leave!");
        System.out.println("If your answer is wrong two times, you'll have to stay here forever.");
        System.out.println("");
    }

    /**
     * Methode für das Menü im Endgame, wenn der Gameloop verlassen wurde nachdem
     * alle Überschriften gesammelt wurden.
     */
    public void endgameMenu() {

        double questionChance = Math.random();

        double questionOne = 0.33;
        double questionTwo = 0.66;
        double questionThree = 0.99;

        if (questionChance < questionOne) {
            System.out.println("What is the digit 7?");
            System.out.println("");
            System.out.println("[1] double");
            System.out.println("[2] String");
            System.out.println("[3] int");
            System.out.println("[4] String");
            System.out.println("");
            endgameInputHandler();
        }
        if (questionChance < questionTwo) {
            System.out.println("What is missing?" + "\n" + "public static void main(String[] ? )");
            System.out.println("");
            System.out.println("[1] param");
            System.out.println("[2] int");
            System.out.println("[3] args");
            System.out.println("[4] Array");
            System.out.println("");
            endgameInputHandler();
        }
        if (questionChance < questionThree) {
            System.out.println("What does a method without \"void\" need to work");
            System.out.println("");
            System.out.println("[1] A Coffee");
            System.out.println("[2] 8 hours of sleep");
            System.out.println("[3] return value");
            System.out.println("[4] boolean");
            System.out.println("");
            endgameInputHandler();

        }
    }

    /**
     * Der Input-Handler für das Endgame Menü, hier werden die Eingaben für die
     * Fragen ausgewertet.
     */
    public void endgameInputHandler() {
        Scanner scanner = new Scanner(System.in);
        String endgameChoice = scanner.nextLine();
        switch (endgameChoice) {
            case "1":
                wrongAnswer++;
                while (!isGameOver()) {
                    System.out.println("");
                    System.out.println("Wrong answer, you have one more try!");
                    System.out.println("");
                    endgameMenu();
                }
                break;
            case "2":
                wrongAnswer++;
                while (!isGameOver()) {
                    System.out.println("");
                    System.out.println("Wrong answer, you have one more try!");
                    System.out.println("");
                    endgameMenu();
                }
                break;
            case "3":
                System.out.println("");
                System.out.println("Ding Ding Ding! Your Answer is right! I will grant you a completion certificate!");
                System.out.println("");
                System.out.println("The doors of the HTW opens...You're finally free!");
                System.out.println("");
                setGameFinished(true);
                isGameFinished();
                break;
            case "4":
                wrongAnswer++;
                while (!isGameOver()) {
                    System.out.println("");
                    System.out.println("Wrong answer, you have one more try!");
                    System.out.println("");
                    endgameMenu();
                }
                break;
            default:
                System.out.println("");
                System.out.println("Wrong input, try using [1], [2], [3] or [4]");
                System.out.println("");
                endgameMenu();
                break;
        }
    }

}
