package model;

import java.util.Scanner;
import java.io.Serializable;
import model.Hero;

/**
 * Abstrakte Klasse für alle Aliens (Friendly, Enemy) im Spiel.
 * 
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
 */

public abstract class Alien implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;

    public String name;
    public int lifePoints = 10;
    public boolean friendly;
    public String greeting;

    /**
     * Konstruktor für ein Alien.
     * 
     * @param name
     * @param lifePoints
     * @param friendly
     */
    public Alien() {
        lifePoints = 10;
    }

    // Getter
    /**
     * Getter für den Namen des Aliens.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter für die Lebenspunkte des Aliens.
     * 
     * @return lifePoints
     */
    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Getter, ob das Alien freundlich ist.
     * 
     * @return friendly
     */
    public boolean getFriendly() {
        return friendly;
    }

    // Setter

    /**
     * Setter für die Lebenspunkte des Aliens.
     * 
     * @param lifePoints
     */
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    /**
     * Setter für den Namen des Aliens.
     * 
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter, ob das Alien freundlich ist.
     * 
     * @param friendly
     */
    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }

    public void takeDamage(int amount) {
        this.lifePoints -= amount;
        System.out
                .println("Your enemy takes " + amount + " damage! It only has " + lifePoints + " HP left, keep going!");
    }

    /**
     * Überprüft, ob das Alien besiegt wurde.
     * 
     * @return true, wenn die Lebenspunkte 0 sind, sonst false
     */
    public boolean isDefeated() {
        if (this.lifePoints <= 0) {
            this.lifePoints = 0;
            return true;
        }
        return false;
    }

}