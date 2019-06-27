package MemoryGame;

/**
 * Η κλάση υλοποιεί μία φόρμα, που περιλαμβάνει το όνομα, τις νίκες και τα βήματα που έκανε ένας παίκτης στο παιχνίδι.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class FileForm {

    private String name;
    private String wins;
    private String steps;

    /**
     * Η μέθοδος αποθηκεύει το όνομα.
     *
     * @param name Το όνομα.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Η μέθοδος αποθηκεύει τις νίκες.
     *
     * @param wins Τις νίκες.
     */
    void setWins(String wins) {
        this.wins = wins;
    }

    /**
     * Η μέθοδος αποθηκεύει τα βήματα.
     *
     * @param steps Τα βήματα.
     */
    public void setSteps(String steps) {
        this.steps = steps;
    }

    /**
     * Η μέθοδος επιστρέφει το όνομα.
     *
     * @return Το όνομα.
     */
    public String getName() {
        return name;
    }

    /**
     * Η μέθοδος επιστρέφει τις νίκες.
     *
     * @return Τις νίκες
     */
    String getWins() {
        return wins;
    }

    /**
     * Η μέθοδος επιστρέφει τα βήματα.
     *
     * @return Τα βήματα
     */
    public String getSteps() {
        return steps;
    }

    /**
     * Η μέθοδος αυξάνει τις νίκες κατά ένα.
     */
    void increaseWins() { wins = String.valueOf(Integer.valueOf(wins) + 1); }
}
