package MemoryGame;

/**
 * Η κλάση υλοποιεί μία κάρτα.
 *
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public class Card {
    public final int id;
    public boolean display;

    /**
     * Η μέθοδος δημιουργεί τη κάρτα, καταχωρώντας σε δύο μεταβλητές τον αριθμό και την κατάσταση (ανοικτή/κλειστή) της.
     *
     * @param id Ο αριθμός της κάρτας.
     */
    Card(int id) {
        this.id = id;
        display = true;
    }

    /**
     * Η μέθοδος ελέγχει αν δύο κάρτες είναι ίσες.
     *
     * @param obj Αντικείμενο που περιέχει τις κάρτες.
     * @return  true: Αν οι κάρτες είναι ίσες.
     *          false: Αν οι κάρτες δεν είναι ίσες.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Card)) {
            return false;
        } else {
            return this.id == ((Card)obj).id;
        }
    }

    /**
     * Η μέθοδος επιστρέφει το id της κάρτας.
     *
     * @return  Το id της κάρτας.
     */
    @Override
    public int hashCode() {
        return this.id;
    }
}
