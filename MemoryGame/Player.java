package MemoryGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλάση υλοποιεί ένα παίχτη. Χρησιμοποιείται για να κρατείται τα σκορ.
 * Επίσης χρησιμεύει και στον επεξεργαστή να γνωρίζει τι συμβαίνει στο παιχνίδι
 * για να μπορεί να διαλέξει κάρτα
 *
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public class Player {
    private int moves = 0;

    protected int score = 0; // Amount of cards the user has matched successfully
    protected int copies;
    private int openedCards = 0;
    protected final String name;


    /**
     * @param name Το όνομα του παίκτη
     * @param copies Ο αριθμός των ίδιων καρτών που πρέπει να βρει ο παίκτης
     */
    public Player(String name, int copies) {
        this.name = name;
        this.copies = copies;
    }

    /**
     * Ενημερώνει τον παίκτη/υπολογιστή για την κάρτα που άνοιξε ο ίδιος ή κάποιος άλλος παίκτης στο ταμπλό
     * έτσι ωστέ να την θυμάται.
     * @param point Οι συντεταγμένες της κάρτας στο ταμπλό
     * @param card Ο τύπος της κάρτας
     */
    public void AlertAboutOpenCard(Point point, Card card) {

    }

    /**
     * Ενημερώνει τον παίκτη/υπολογιστή ότι ένα είδος κάρτας έχει βρεθεί από κάποιον παίκτη και έχει φύγει από το ταμπλό.
     * @param card Ο τύπος της κάρτας
     * @param cardsCoords Οι θέσεις της κάρτας στο ταμπλό
     */
    public void AlertAboutFoundCards(Card card, ArrayList<Point> cardsCoords) {

    }

    /**
     * Ενημερώνει τον παίκτη/υπολογιστή για το είδος της κάρτας που μόλις άνοιξε.
     * @param point Οι συντεταγμένες της κάρτας στο ταμπλό
     * @param card Ο τύπος της κάρτας
     */
    public void AlertAboutTheIdentityOfOpenedCard(Point point, Card card) {

    }

    /**
     * Η μέθοδος επιστρέφει τις κινήσεις του παίχτη.
     *
     * @return Τον αριθμό των κινήσεων.
     */
    int GetMoves() {
        return moves;
    }

    /**
     * Η μέθοδος αυξάνει τις κινήσεις του παίχτη.
     */
    void IncrementMoves() {
        moves++;
    }

    /**
     * Η μέθοδος επιστρέφει το όνομα του παίχτη.
     *
     * @return Το όνομα.
     */
    public String GetName() {
        return name;
    }

    /**
     * Η μέθοδος επιστρέφει το πλήθος των καρτών που έχουν ανοίξει.
     *
     * @return Το πλήθος των καρτών που έχουν ανοίξει.
     */
    int GetOpenedCards() {
        return openedCards;
    }

    /**
     * Η μέθοδος αυξάνει το πλήθος των καρτών που έχουν ανοίξει.
     */
    void IncrementOpenCards() {
        openedCards++;
    }

    /**
     * Η μέθοδος μηδενίζει το πλήθος των καρτών που έχουν ανοίξει.
     */
    void ResetOpenedCards() {
        openedCards = 0;
    }

    /**
     * Η μέθοδος επιστρέφει τη βαθμολογία του παίχτη.
     *
     * @return Τη βαθμολογία.
     */
    int GetScore() {
        return score;
    }

    /**
     * Η μέθοδος αυξάνει τη βαθμολογία του παίχτη.
     */
    void IncrementScore() { score++; }
}
