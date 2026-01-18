package model;

import java.io.Serializable;

/** 
 * Klasse für die Räume der HTW im Spiel.
 * @author Sophie Amrollah Majdabadi
 * @author Marvin Kühne
*/
public class HTWRoom implements Serializable {

    private String identifier;
    private String description;
    private Lecturer lecturer;

    /**
     * Gibt die Bezeichnung eines Raums zurueck
     * @return die Bezeichnung
     */
    public String getIdentifier(){
        return this.identifier;
    }

    /**
     * Setzt Bezeichnung eines Raums fest
     * @param identifier die neue Bezeichnung des Raums
     */
    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }

    /**
     * Gibt die Beschreibung eines Raums zurueck
     * @return die Beschreibung
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Setzt die Beschreibung eines Raums fest
     * @param description die neue Beschreibung
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Gibt den Lecturer zurueck, der sich im Raum befindet
     * @return der Lecturer
     */
    public Lecturer getLecturer(){
        return this.lecturer;
    }

    /**
     * Setzt fest, welcher Lecturer sich im Raum befindet
     * @param lecturer der festgelegte Lecturer
     */
    public void setLecturer(Lecturer lecturer){
        this.lecturer = lecturer;
    }

    

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 9065680017147292999L;
}
