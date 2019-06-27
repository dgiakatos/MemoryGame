package MemoryGame;

import java.awt.*;
import java.util.HashSet;
/**
 * Η κλάση υλοποιεί τις κινήσεις του υπολογιστή στο κλασικό παιχνίδι.
 *
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class ClassicCPU extends CPU {

    /**
     * Η μέθοδος ξεκινάει έναν καινούργιο παίκτη που ελέγχει ο υπολογιστής.
     * @param pTRP Η πιθανότητα από το 0 εως το 100 να θυμάται ο υπολογιστής μια κάρτα που άνοιξε
     * @param copies Ο αριθμός των καρτών που πρέπει να κάνει ζευγάρι ο υπολογιστής
     * @param width Το πλάτος του ταμπλό του παιχνιδιού
     * @param height Το μήκος του ταμπλό του παχνιδιού
     */
    public ClassicCPU(int pTRP, int copies, int width, int height) {
        super(pTRP, copies, width, height);
    }

    public Point GetNextMove() {
        if(AllOpenedCardsSame()) {
            Card openedCard = openedCards.get(openedCards.keySet().iterator().next());
            HashSet<Point> knownLocations = rememberedCards.get(openedCard);
            if(knownLocations != null) {
                HashSet<Point> points = new HashSet<>();
                points.addAll(knownLocations);
                points.addAll(openedCards.keySet());
                if(points.size() == copies) {
                    for (Point nextPoint : knownLocations) {
                        if (!openedCards.containsKey(nextPoint)) {
                            return nextPoint;
                        }
                    }
                }
            }
        }
        if(openedCards.size() == 0) {
            for (Card card : rememberedCards.keySet()) {
                HashSet<Point> knownLocations = rememberedCards.get(card);
                if (knownLocations.size() == copies) {
                    return knownLocations.iterator().next();
                }
            }
        }
        return RandomPointFromUnopenedCards();
    }
}
