package model;

/**
 * Kindklasse für den Friendly Alien im Spiel.
 * 
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
 */

public class Friendly extends Alien {

    /**
     * Konstruktor für ein freundliches Alien.
     */
    public Friendly() {
        super();
        name = "Butters";
        friendly = true;
        greeting = "My name is Butters! You seem lost..Do you need any help?";

    }

    /**
     * Interaktion mit dem freundlichen Alien Butters.
     */
    public void butters() {
        System.out.println(greeting);
        System.out.println("This Alien seems friendly...Do you want to ask for Help (1) or Flee? (2)");
        System.out.println("");
    }
}
