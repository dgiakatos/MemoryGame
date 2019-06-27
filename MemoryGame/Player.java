package MemoryGame;

/**
 * Η κλάση δημιουργεί ένα παίχτη.
 *
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 */
public class Player {
    private int moves = 0;

    /**
     * Η μέθοδος επιστρέφει τις κινήσεις του παίχτη.
     *
     * @return Τον αριθμό των κινήσεων.
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Η μέθοδος αυξάνει τις κινήσεις του παίχτη.
     *
     * @param a Ο αριθμός των κινήσεων.
     */
    public void increaseMovesBy(int a) {
        moves += a;
    }
}
