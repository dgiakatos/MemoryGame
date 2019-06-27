import MemoryGame.*;
import UserInterfaces.*;

/**
 * Η κλάση Main είναι η πρώτη που θα εκτελεστεί από τον υπολογιστή.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 */
public class Main {

    /**
     * Η μέθοδος δημιουργεί τα πρώτα αντικείμενα ώστε να υλοποιηθεί το παιχνίδι.
     *
     * @param args Δεν χρησιμοποιείται στο πρόγραμμα, όμως είναι απαραίτητη για την εκτέλεση του προγράμματος από τον
     *            υπολογιστή.
     */
    public static void main(String[] args) {
        UserInterface ui = new Console();
        MemoryGame game = new MemoryGame(ui);
        game.NewGame();
    }
}
