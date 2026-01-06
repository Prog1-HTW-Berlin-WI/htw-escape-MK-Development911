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
}