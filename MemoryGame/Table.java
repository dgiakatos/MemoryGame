package MemoryGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.RuntimeException;

/**
 * Η κλάση υλοποιεί την αποθήκευση και την διεπαφή με το ταμπλό του παιχνιδιού.
 *
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 */
public class Table {
    private final int width;
    private final int height;
    private final int copies;
    private int remainingCards;

    private Card[][] board;

    private ArrayList<Card> openCards;

    /**
     * Η μέθοδος αρχικοποιεί τις μεταβλητές και ελέγχει ότι είναι εφικτή η δημιουργία ενός ταμπλό με το δοσμένο
     * width/height/copies. Στην συνέχεια γεμίζει με τυχαίο τρόπο το ταμπλό με κάρτες.
     *
     * @param width Το πλάτος του ταμπλό
     * @param height Το μήκος του ταμπλό
     * @param copies Τα αντίγραφα της κάθε κάρτας πάνω στο ταμπλό
     *
     */
    public Table(int width, int height, int copies) {

        if(width * height % copies != 0) {
            throw new RuntimeException("Invalid width/height");
        }

        this.width = width;
        this.height = height;
        this.copies = copies;
        this.remainingCards = width * height;

        this.board = new Card[width][height];
        this.openCards = new ArrayList<>(copies);

        this.GenerateBoard();
    }

    /**
     * @return Ο αριθμός των καρτών που απομένουν πάνω στο ταμπλό.
     */
    public int GetRemainingCards() {
        return remainingCards;
    }

    private void DecrementRemainingCards() {
        remainingCards -= copies;
    }

    /**
     * @return Όλες οι κάρτες του ταμπλό σε ένα δισδιάστατο array. Η πρώτη διάσταση περιέχει τις σειρές
     * και η δεύτερη την στήλη.
     */
    public Card[][] GetAllCards() {
        return board;
    }

    /**
     *  Αναποδογυρίζει όλες τις κάρτες πάνω στο ταμπλό από την κάτω τους πλευρά.
     */
    public void HideAllCards() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != null) {
                    board[i][j].display = false;
                }
            }
        }
        openCards.clear();
    }

    /**
     * Αναποδογυρίζει την επιλεγμένη κάρτα από την πάνω πλευρά. Σε περίπτωση που οι συντεταγμένες που δόθηκαν
     * δεν αντιστοιχούν σε κάποια κάρτα ή αυτή η κάρτα είναι ήδη ανοιχτή τότε επιστρέφει ψευδής.
     * @param coordinates Οι συντεταγμένες Χ/Υ της κάρτας ξεκινώντας από πάνω αριστερά
     * @return Αν η κάρτα άνοιξε
     */
    public boolean OpenCard(Point coordinates) {
        if(
            coordinates.y > board.length || coordinates.y < 1 ||
            coordinates.x > board[coordinates.y - 1].length || coordinates.x < 1 ||
            board[coordinates.y - 1][coordinates.x - 1] == null ||
            board[coordinates.y - 1][coordinates.x - 1].display
        ) {
            return false;
        }
        board[coordinates.y - 1][coordinates.x - 1].display = true;
        openCards.add(board[coordinates.y - 1][coordinates.x - 1]);
        return true;
    }

    /**
     * Αφαιρεί όλες τις ανοιχτές κάρτες από το ταμπλό ανεξαρτήτου το είδος της κάθε ανοιχτής κάρτας
     */
    public void RemoveOpenCards() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != null && board[i][j].display) {
                    board[i][j] = null;
                }
            }
        }
        openCards.clear();
        DecrementRemainingCards();
    }

    /**
     * @return Αν όλες οι ανοιχτές κάρτες είναι ίδιες.
     */
    public boolean SameOpenCards() {
        int id = openCards.get(0).id;
        boolean areSame = true;
        for(Card card : openCards) {
            if(card.id != id) {
                areSame = false;
                break;
            }
        }
        return areSame;
    }

    /**
     * Η μέθοδος υλοποιεί το ταμπλό του παιχνιδιού δημιουργώντας ένα πίνακα με τυχαίους αριθμούς.
     *
     * @link https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
     */
    private void GenerateBoard() {
        ArrayList<Integer> cardIDs = new ArrayList<>();
        for(int i = 0; i < width * height; i++) {
            cardIDs.add(i / copies);
        }
        Collections.shuffle(cardIDs);

        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = new Card(cardIDs.remove(cardIDs.size() - 1));
            }
        }
    }
}
