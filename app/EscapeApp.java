package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Klasse für das Hauptmenü des Spiels.
 * 
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
 */
public class EscapeApp {

    /**
     * Der Name der Datei, in der das Spiel gespeichert wird.
     */
    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private boolean gameRunning = true;

    /**
     * Main-Methode zum Starten der Anwendung.
     * 
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(
                "An einem Morgen im Januar 2026 wirst du im Gebaeude A der HTW Berlin mit einer surrealen Situation konfrontiert:"
                        + "\n" +
                        "Eine menschengrosse Schnecke versperrt dir den Weg, waehrend die Ausgaenge verriegelt sind."
                        + "\n" +
                        "Was zunaechst wie ein Angriff wirkt, entpuppt sich als Gespraechsversuch des Tieres." + "\n" +
                        "Um aus dem Gebaeude zu entkommen, musst du Professorin Majuntke finden, die als Einzige den Fluchtweg kennt."
                        + "\n" +
                        "Der Weg zu ihr fuehrt jedoch ueber eine Schnitzeljagd durch die Hochschule:" + "\n" +
                        "Du musst verschiedene Uebungsleiter der Programmierung in Raeumen aufspueren." + "\n" +
                        "Erst wenn du von allen Leitern eine Unterschrift gesammelt hast," + "\n" +
                        "erhaeltst du die notwendigen Informationen, um die Professorin zu finden und die HTW wieder verlassen zu koennen.");
        System.out.println("========================================" + "\n");

        System.out.println("========================================");
        System.out.println("Welcome to the HTW Escape | Main Menu ");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (true) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
            System.out.println("");
        }
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    private void showMainMenu() {
        System.out.println("(1) Start new game");
        if (isGameRunning()) {
            System.out.println("(2) Resume game");
        }
        if (hasSavedGame()) {
            System.out.println("(3) Load saved game");
        }
        if (isGameRunning()) {
            System.out.println("(4) Save game");
        }
        if (hasSavedGame()) {
            System.out.println("(5) Delete save");
        }
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
        System.out.println("");
    }

    /**
     * Liest die Benutzereingabe ein.
     * 
     * @return die Benutzereingabe als String
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Verarbeitet die Benutzereingabe im Hauptmenü.
     * 
     * @param input die Benutzereingabe als String
     * @param input die Benutzereingabe liest und auswertet
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                System.out.println("");
                this.startGame(); // Starten eines neuen Spiels
                break;
            case "2":
                System.out.println("");
                if (isGameRunning()) {
                    resumeGame(); // Fortsetzen des laufenden Spiels
                } else {
                    System.out.println("There's no Save State to load.");
                    System.out.println("");
                }
                break;
            case "3":
                System.out.println("");
                if (hasSavedGame()) {
                    this.loadGame(); // Laden des gespeicherten Spiels
                } else {
                    System.out.println("No saved game found.");
                    System.out.println("");
                }
                break;
            case "4":
                System.out.println("");
                if (isGameRunning()) {
                    this.saveGame(); // Speichern des aktuellen Spiels
                } else {
                    System.out.println("You can't save a game that doesn't exist.");
                    System.out.println("");
                }
                break;
            case "5":
                System.out.println("");
                if (hasSavedGame()) {
                    this.deleteGame(); // Löschen des gespeicherten Spiels
                } else {
                    System.out.println("You can't delete something you didn't create.");
                    System.out.println("");
                }
                break;
            case "6":
                System.out.println("");
                System.out.println("See you next time, Hero!");
                System.out.println(""); // Beenden der Anwendung
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                System.out.println("");
                break;
        }
    }

    /**
     * Startet ein neues Spiel.
     */
    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
     * Setzt das laufende Spiel fort.
     */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * Löscht das gespeicherte Spiel.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        }
    }

    /**
     * Speichert das aktuelle Spiel.
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
    }

    /**
     * Lädt ein gespeichertes Spiel.
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob ein Spiel läuft.
     * 
     * @return true, wenn ein Spiel läuft, sonst false
     */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Prüft, ob das Spiel beendet ist.
     * 
     * @return true, wenn das Spiel beendet ist, sonst false
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob ein gespeichertes Spiel existiert.
     * 
     * @return true, wenn ein gespeichertes Spiel existiert, sonst false
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
