package model;

import java.io.Serializable;

/** 
 * Klasse für die Dozenten im Spiel.
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
*/
public class Lecturer implements Serializable {

    private String name;
    private boolean hasSigned = false;

    public String getName (){
        return this.name;
    }

    public void setName (String name){
        this.name = name;
    }

    public boolean getHasSigned(){
        return this.hasSigned;
    }

    public void setHasSigned(boolean hasSigned){
        this.hasSigned = hasSigned;
    }

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 540082607047283589L;

    /** 
     * Gibt an, ob Lecturer bereit ist zu Unterschreiben (noch ohne bedingung, vielleicht ändern?)
     * return true (er ist !noch! jederzeit bereit)
    */
    public boolean isReadyToSign(){
        return true;
    }
    /** 
     * Lecturer unterschreibt, wenn bedingung true ist
    */
    public void sign(){
        if (isReadyToSign()){
            this.hasSigned = true;
            System.out.println("The Lecturer " +getName()+ " is ready to sign!"); 
        }
        else {
            System.out.println("Lecturer " +getName()+ "is not ready to sign yet...");
        }
    }
}