package MemoryGame;

import java.awt.*;
import java.util.*;
/**
 * Η κλάση υλοποιεί τις κινήσεις του υπολογιστή σε γενικό επίπεδο.
 * Χρησιμοποιείται ως μια βάση από τα δεδομένα που πρέπει να ξέρει ο υπολογιστής
 * για να κάνει μια σωστή επιλογή ανάλογα με την υλοποιήση (το είδος του παιχνιδιού)
 *
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public abstract class CPU extends Player {
    
    private int probabilityToRememberPercentage;
    protected Random random = new Random();

    HashMap<Point, Card> openedCards = new HashMap<>();
    ArrayList<Point> unknownCards = new ArrayList<>();
    HashMap<Card, HashSet<Point>> rememberedCards =  new HashMap<>();

    /**
     * Η μέθοδος ξεκινάει έναν καινούργιο παίκτη που ελέγχει ο υπολογιστής.
     * @param pTRP Η πιθανότητα από το 0 εως το 100 να θυμάται ο υπολογιστής μια κάρτα που άνοιξε
     * @param copies Ο αριθμός των καρτών που πρέπει να κάνει ζευγάρι ο υπολογιστής
     * @param width Το πλάτος του ταμπλό του παιχνιδιού
     * @param height Το μήκος του ταμπλό του παχνιδιού
     */
    public CPU(int pTRP, int copies, int width, int height) {
        super("CPU", copies);
        this.probabilityToRememberPercentage = pTRP;

        for(int row = 1; row <= height; row++) {
            for(int column = 1; column <= width; column++) {
                unknownCards.add(new Point(column, row));
            }
        }
    }

    @Override
    public void AlertAboutOpenCard(Point point, Card card) {
        if(random.nextInt(100) < probabilityToRememberPercentage) {
            StoreCard(point, card);
        }
    }

    @Override
    public void AlertAboutFoundCards(Card card, ArrayList<Point> cardsCoords) {
        rememberedCards.remove(card);
        for(Point cardCoords : cardsCoords) {
            unknownCards.remove(cardCoords);
        }
    }

    @Override
    public void AlertAboutTheIdentityOfOpenedCard(Point point, Card card) {
        openedCards.put(point, card);
        if(openedCards.size() == copies) {
            openedCards.clear();
        }
    }

    private void StoreCard(Point point, Card card) {
        unknownCards.remove(point);
        HashSet<Point> storedSet = rememberedCards.get(card);
        if(storedSet == null) {
            HashSet<Point> set = new HashSet<>(copies + 1);
            set.add(point);
            rememberedCards.put(card, set);
        } else {
            storedSet.add(point);
        }
    }

    /**
     * Ζητάει από τον υπολογιστή να ανοίξει μια κάρτα στο ταμπλό.
     * @return Τις συντεταγμένες της επόμενης κάρτας που επιθυμεί να ανοίξει ο υπολογιστής.
     */
    public abstract Point GetNextMove();

    boolean AllOpenedCardsSame() {
        if(openedCards.size() == 0) {
            return false;
        }
        Card firstCard = openedCards.get(openedCards.keySet().iterator().next());
        for (Point point : openedCards.keySet()) {
            if (openedCards.get(point).id != firstCard.id) {
                return false;
            }
        }
        return true;
    }

    protected Point RandomPointFromUnopenedCards() {
        Point point = unknownCards.get(random.nextInt(unknownCards.size()));
        while(openedCards.containsKey(point)) {
            point = unknownCards.get(random.nextInt(unknownCards.size()));
        }
        return point;
    }
}
