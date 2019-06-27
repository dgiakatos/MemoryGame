package MemoryGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Η κλάση υλοποιεί τις κινήσεις του υπολογιστή στο παιχνίδι μονομαχία.
 *
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class DuelCPU extends CPU {


    /**
     * Η μέθοδος ξεκινάει έναν καινούργιο παίκτη που ελέγχει ο υπολογιστής.
     * @param pTRP Η πιθανότητα από το 0 εως το 100 να θυμάται ο υπολογιστής μια κάρτα που άνοιξε
     * @param width Το πλάτος του ταμπλό του παιχνιδιού
     * @param height Το μήκος του ταμπλό του παχνιδιού
     *
     */
    public DuelCPU(int pTRP, int width, int height) {
        super(pTRP, 1, width, height);
    }

    @Override
    public void AlertAboutTheIdentityOfOpenedCard(Point point, Card card) {
        openedCards.clear();
        openedCards.put(point, card);
    }

    @Override
    public Point GetNextMove() {
        Card openedCard = openedCards.get(openedCards.keySet().iterator().next());
        HashSet<Point> knownLocations = rememberedCards.get(openedCard);
        if(knownLocations != null) {
            return knownLocations.iterator().next();
        } else {
            if(unknownCards.size() != 0) {
                return RandomPointFromUnopenedCards();
            } else {
                return RandomPointFromOpenedCards();
            }
        }
    }

    private Point RandomPointFromOpenedCards() {
        List<Card> cards = new ArrayList<>(rememberedCards.keySet());
        Collections.shuffle(cards);
        return rememberedCards.get(cards.get(0)).iterator().next();
    }

    @Override
    protected Point RandomPointFromUnopenedCards() {
        return unknownCards.get(random.nextInt(unknownCards.size()));
    }
}
