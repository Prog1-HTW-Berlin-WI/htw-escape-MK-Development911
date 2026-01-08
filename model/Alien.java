package model;

import java.io.Serializable;

/**
* Abstrakte Klasse für alle Aliens (Friendly, Enemy) im Spiel.
* @author Sophie Amrollah Majdabadi
* @author Marvin Kühne
*/

public abstract class Alien implements Serializable{

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 1729389822767173584L;

    private String name; 
    private int lifePoints;
    private boolean friendly; 

    /**
     * Konstruktor für ein Alien.
     * @param name
     * @param lifePoints
     * @param friendly
     */
    public Alien(String name, int lifePoints, boolean friendly) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
    }
        // Getter


        /**
         * Getter für den Namen des Aliens.
         * @return name
         */
        public String getName() { return name; }
        

        /**
         * Getter für die Lebenspunkte des Aliens.
         * @return lifePoints
         */
        public int getLifePoints() { return lifePoints; }
        
        /**
         * Getter, ob das Alien freundlich ist.
         * @return friendly
         */
        public boolean isFriendly() { return friendly; }

        // Setter

        /** 
         * Setter für die Lebenspunkte des Aliens.
         * @param lifePoints
         */
        public void setLifePoints(int lifePoints) { this.lifePoints = lifePoints; }

        /**
        * Setter für den Namen des Aliens.
        * @param name
        */

        public void setName(String name) { this.name = name; }

        /**
        * Setter, ob das Alien freundlich ist.
        * @param friendly
        */
        public void setFriendly(boolean friendly) { this.friendly = friendly; }
}