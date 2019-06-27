package MemoryGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.RuntimeException;

/**
 * Η κλάση υλοποιεί την αποθήκευση και την διεπαφή με το ταμπλό του παιχνιδιού.
 *
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public class Table {
    private final int width;
    private final int height;
    private final int copies;
    private int remainingCards;

    private Card[][] board;

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

        if (copies < 1 || width * height % copies != 0) {
            throw new RuntimeException("Invalid width/height");
        }

        this.width = width;
        this.height = height;
        this.copies = copies;
        this.remainingCards = width * height;

        this.board = new Card[height][width];

        this.GenerateBoard();
    }

    /**
     * @return Ο αριθμός των καρτών που απομένουν πάνω στο ταμπλό.
     */
    int GetRemainingCards() {
        return remainingCards;
    }

    private void DecrementRemainingCards() {
        remainingCards -= copies;
    }

    /**
     * @return Όλες οι κάρτες του ταμπλό σε ένα δισδιάστατο array. Η πρώτη διάσταση περιέχει τις σειρές
     * και η δεύτερη την στήλη.
     */
    Card[][] GetAllCards() {
        return board;
    }

    /**
     *  Αναποδογυρίζει όλες τις κάρτες πάνω στο ταμπλό από την κάτω τους πλευρά.
     */
    void HideAllCards() {
        for (Card[] cards : board) {
            for (Card card : cards) {
                if (card != null) {
                    card.display = false;
                }
            }
        }
    }

    /**
     * Αναποδογυρίζει την επιλεγμένη κάρτα από την πάνω πλευρά. Σε περίπτωση που οι συντεταγμένες που δόθηκαν
     * δεν αντιστοιχούν σε κάποια κάρτα ή αυτή η κάρτα είναι ήδη ανοιχτή τότε επιστρέφει ψευδής.
     * @param coordinates Οι συντεταγμένες Χ/Υ της κάρτας ξεκινώντας από πάνω αριστερά
     * @return Η κάρτα που αναποδογύρισε
     */
    Card OpenCard(Point coordinates) {
        if(
            coordinates.y > board.length || coordinates.y < 1 ||
            coordinates.x > board[coordinates.y - 1].length || coordinates.x < 1 ||
            board[coordinates.y - 1][coordinates.x - 1] == null ||
            board[coordinates.y - 1][coordinates.x - 1].display
        ) {
            return null;
        }
        board[coordinates.y - 1][coordinates.x - 1].display = true;
        return board[coordinates.y - 1][coordinates.x - 1];
    }

    /**
     * Αφαιρεί όλες τις ανοιχτές κάρτες από το ταμπλό ανεξαρτήτου το είδος της κάθε ανοιχτής κάρτας
     */
    void RemoveOpenCards() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != null && board[i][j].display) {
                    board[i][j] = null;
                }
            }
        }
        DecrementRemainingCards();
    }

    /**
     * @return Αν όλες οι ανοιχτές κάρτες είναι ίδιες.
     */
    Card SameOpenCards() {
        Card firstOpenCard = null;
        for (Card[] cards : board) {
            for (Card card : cards) {
                if (card != null && card.display) {
                    if (firstOpenCard == null) {
                        firstOpenCard = card;
                    }
                    if (card.id != firstOpenCard.id) {
                        return null;
                    }
                }
            }
        }
        return firstOpenCard;
    }

    /**
     * @return Ένα ArrayList με τις συντεταγμένες όλων των ανοιχτών καρτών πάνω στο ταμπλό
     */
    ArrayList<Point> GetOpenCardsCoordinates() {
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] != null && board[i][j].display) {
                    points.add(new Point(j + 1, i + 1));
                }
            }
        }
        return points;
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
