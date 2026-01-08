package model;

import java.io.Serializable;

/** 
 * Klasse für die Dozenten im Spiel.
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
*/
public class Lecturer implements Serializable {

    private String name;
    private boolean hasSigned;

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
}