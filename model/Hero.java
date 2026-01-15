package model;

import java.io.Serializable;
import app.EscapeGame;

/** 
 * Klasse für den Helden im Spiel.
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
*/
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;

    private String name;
    private int hp;
    private int maxHP;
    private int xp;
    private String[] signedExerciseLeaders;
    private EscapeGame game;
    private int healAmountLong = 10;
    private int healAmountShort = 3;

    /**
     * Gibt namen des Heroes zurueck
     * @return der Name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Setzt den Namen des Heroes fest
     * @param name der neue Name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gibt HP zurueck
     * @return Anzahl an HP
     */
    public int getHp(){
        return this.hp;
    }

    /**
     * Setzt HP fest
     * @param hp neue HP Wert
     */
    public void setHp(int hp){
        this.hp = hp;
    }

    /**
     * Gibt den maximalen HP Wert zurueck
     * @return Anzahl maximaler HP
     */
    public int getMaxHP(){
        return this.maxHP;
    }
     /**
      * Setzt die maximalen HP fest
      * @param maxHP neue maximale HP Wert
      */
    public void setMaxHP(int maxHP){
        this.maxHP = maxHP;
    }

    /**
     * Gibt die Anzahl an XP zurueck
     * @return Anzahl der XP
     */
    public int getXp(){
        return this.xp;
    }

    /**
     * Setzt die XP fest
     * @param xp neue Wert fuer XP
     */
    public void setXp(int xp){
        this.xp = xp;
    }

    /** 
     * Nimmt Schaden am Helden.
     * @param damage der zugefügte Schaden
    */
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(name + " takes " + damage + " damage. Current HP: " + hp + "/" + maxHP);
    }
    /**       
    * Regeneriert die HP des Helden.
    * @param longRest true für lange Pause (volle Heilung), false für kurze Pause (teilweise Heilung)
    */
    public void regenerate(boolean longRest) {
        if (longRest) {
            this.hp += healAmountLong;
            System.out.println(name + " takes a long rest and fully regenerates to " + hp + "/" + maxHP + " HP.");
            } else {
            this.hp += healAmountShort;
            if (this.hp > this.maxHP) this.hp = this.maxHP;
            System.out.println(name + " takes a short rest and regenerates to " + hp + "/" + maxHP + " HP.");
        }
        if (this.hp > this.maxHP) {
            this.hp = this.maxHP;
        }
    }
    /**
     * Versucht, dem Kampf zu entkommen.
     * @return true, wenn die Flucht erfolgreich war, sonst false
     */
    public boolean flee() {

        boolean success = Math.random() < 0.42;

        if (success) {
            System.out.println(name + " successfully flees from the encounter!");
        } else {
            System.out.println(name + " failed to flee!");
        }
        return success;
    }
    /** 
     * Führt einen Angriff durch. Außerdem: Berechnung von kritischen Treffern und Fehlschlägen.
     * @return der verursachte Schaden
    */
    public int attack() {
    double chance = Math.random();
    
    if(chance < 0.13) {
        System.out.println(name + " misses the attack!");
        return 0;
    }

    int damage = (int) (this.xp * 2.3 + 1);

    if (chance >= 0.13 && chance < 0.25) {
        damage *= 2;
        System.out.println(name + " lands a critical hit! DOUBLE DAMAGE");
    }
    System.out.println(name + " attacks and deals " + damage + " damage.");
    return damage;
}
    /** 
     * Unterschreibt bei einem Übungsleiter.
     * @param lecturer der Übungsleiter, der unterschreibt
     * @return TBD
    */
    public void signedExerciseLeaders(Lecturer lecturer) {
        String lecName = lecturer.getName();

        for (String signature : signedExerciseLeaders) {
            if (signature != null && signature.equals(lecName)) {
                System.out.println(name + " has already signed with " + lecName + ".");
                return;
            }
        }

        for (int i = 0; i < signedExerciseLeaders.length; i++) {
            if (signedExerciseLeaders[i] == null) {
                signedExerciseLeaders[i] = lecName;
                System.out.println(name + " successfully signed with " + lecName + "!");
                return;
            }
        }
    }
    /** 
     * Fügt dem Helden Erfahrungspunkte hinzu.
     * @param xp die hinzuzufügenden Erfahrungspunkte
     * @return die neuen Erfahrungspunkte
    */
    public int addExperiencePoints(int xp) {
        this.xp += xp;
        return this.xp;
    }
    /** 
     * Prüft, ob der Held noch betriebsbereit ist (HP > 0).
     * @return true, wenn der Held betriebsbereit ist, sonst false
    */
    public boolean isOperational() {
        return this.hp > 0;
    }
}
