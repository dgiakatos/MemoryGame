package MemoryGame;

/**
 * Η κλάση υλοποιεί μία κάρτα.
 *
 * @author Θεμιστοκλής Χατζηεμμανουήλ
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
}
