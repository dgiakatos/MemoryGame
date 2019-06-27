package UserInterfaces;

import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;
import MemoryGame.Card;

/**
 * Η κλάση υλοποιεί τη διεπαφή μεταξύ του παιχνιδιού και του χρήστη μέσα από τη κονσόλα.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 */
public class Console implements UserInterface {

    /**
     * Η μέθοδος εμφανίζει το αρχικό μήνυμα στην οθόνη.
     */
    public void Initialize() {
        System.out.println("Hello, let's test your memory!!");
    }

    /**
     * Η μέθοδος ζητάει από το χρήστη να επιλέξει ένα από τα 3 επίπεδα δυσκολίας.
     *
     * @return Ακέραιο αριθμό που αντικατοπτρίζει το επίπεδο δυσκολίας.
     */
    public int AskForDifficulty() {
        System.out.print("Difficulty from 1 to 3:\n1) 2 copies of 12 different cards.\n2) 2 copies of 24 different cards.\n3) 3 copies of 12 different cards.\nSelect difficulty: ");
        int difficulty = ReadUnsignedInt();
        do {
            if(difficulty <1 || difficulty >3) {
                ShowError("Invalid value.");
                System.out.print("Please select again: ");
                difficulty = ReadUnsignedInt();
            }
        } while(difficulty <1 || difficulty >3);
        return difficulty;
    }

    /**
     * Η μέθοδος εκτυπώνει στην οθόνη το ταμπλό του παιχνιδιού.<br>
     * Αν η κάρτα είναι κλειστή τότε την εμφανίζει στην οθόνη με το χαρακτήρα *.<br>
     * Αν η κάρτα είναι ανοιχτή τότε την εμφανίζει στην οθόνη με τον αριθμό που αντιστοιχεί στην κάρτα.<br>
     * Αν η κάρτα έχει αφαιρεθεί γιατί ο χρήστης βρήκε δύο ή τρείς ίδιες κάρτες ανάλογα με το επίπεδο της δυσκολία που
     * έχει επιλέξει τότε την εμφανίζει στην οθόνη με το κενό χαρακτήρα.<br>
     * Επίσης εμφανίζει στην οθόνη τον αριθμό τον στηλών και των γραμμών, για να βοηθήσει τον χρήστη να επιλέγει με
     * ευκολία τη κάρτα που θέλει κάθε φορά να ανοίξει.
     *
     * @param cards Πίνακας που περιλαμβάνει όλες τις κάρτες που έχει το ταμπλό. Επίσης περιέχει
     * πληροφορίες για τον αριθμό κάθε κάρτας και αν είναι ανοιχτή ή κλειστή.
     */
    public void RenderTable(Card[][] cards) {
        ClearOutput();
        System.out.print("  ");
        for(int heightCount = 0; heightCount < cards[0].length; heightCount++) {
            System.out.printf("%5d", heightCount + 1);
        }
        System.out.print("\n\n");
        for(int row = 0; row < cards.length; row++) {
            System.out.print((row + 1) + " ");
            for(int column = 0; column < cards[row].length; column++) {
                if(cards[row][column] != null) {
                    if (!cards[row][column].display) {
                        System.out.printf("%5s", "*");
                    } else {
                        System.out.printf("%5d", cards[row][column].id);
                    }
                } else {
                    System.out.printf("%5s", "");
                }
            }
            System.out.println();
        }
    }

    /**
     * Η μέθοδος ζητάει από το χρήστη να επιλέξει τις συντεταγμένες της κάρτας που θέλει να ανοίξει.
     *
     * @return Αντικείμενο της κλάσης Point που περιλαμβάνει τις συντεταγμένες που έχει επιλέξει ο χρήστης.
     */
    public Point AskToSelectACard() {
        Point point = new Point();
        System.out.print("Give the column of the card you want to select: ");
        int pointColumn = ReadUnsignedInt();
        System.out.print("Give the row of the card you want to select: ");
        int pointRow = ReadUnsignedInt();
        point.setLocation(pointColumn, pointRow);
        return point;
    }

    /**
     * Η μέθοδος εμφανίζει στην οθόνη τα σφάλματα που προκύπτουν, ώστε να επιτευχθεί η ομαλή λειτουργία του προγράμματος.
     *
     * @param error Συμβολοσειρά, που περιλαμβάνει το μήνυμα που θα εκτυπωθεί στην οθόνη.
     */
    public void ShowError(String error) {
        System.out.println("Error: " + error);
    }

    /**
     * Η μέθοδος εμφανίζει στην οθόνη τα μηνύματα που προκύπτουν, ώστε να ενημερώνει τον χρήστη.
     *
     * @param message Συμβολοσειρά, που περιλαμβάνει το μήνυμα που θα εκτυπωθεί στην οθόνη.
     */
    public void ShowMessage(String message) {
        ClearOutput();
        System.out.println(message);
    }

    /**
     * Η μέθοδος καθαρίζει την οθόνη.
     * Ελέγχει πρώτα το λειτουργικό του υπολογιστή και έπειτα εκτελεί την κατάλληλη εντολή ώστε να καθαρίσει η οθόνη.
     * Λειτουργικό:
     * <ul> Windows: Εντολή cls <p>
     *      Linux: Εντολή "\033[H\033[2J" <p>
     *      MacOS: Εντολή "\033[H\033[2J" <p>
     *      Unix: Εντολή "\033[H\033[2J" <p>
     * </ul>
     * @link https://stackoverflow.com/questions/2979383/java-clear-the-console
     * @Link https://teamtreehouse.com/community/is-it-possible-to-clear-the-console-during-execution-in-a-java-workspace
     */
    private void ClearOutput() {
        try {
            if(System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
            }
        } catch (IOException | InterruptedException ex) {
            ShowError("Undefined OS");
        }
    }

    /**
     * Η μέθοδος διαβάζει μόνο ένα θετικό ακέραιο αριθμό από το πληκτρολόγιο. Η συγκεκριμένη μέθοδος σχηματίστηκε γιατί η μέθοδος nextInt
     * της κλάσης Scanner διαβάζει οποιοδήποτε χαρακτήρα ή/και αριθμό πληκτρολογήσει ο χρήστης. Αυτό είχε ως αποτέλεσμα να
     * οδηγεί σε σφάλματα καθώς μπορεί να διαβαστεί ένας χαρακτήρας που θα αποθηκευτεί προσωρινά σε μία μεταβλητή ακεραίων.
     *
     * @return Επιστρέφει τον αριθμό που έχει διαβαστεί. Αν διαβαστεί χαρακτήρας επιστρέφει τον αριθμό -1.
     */
    private int ReadUnsignedInt() {
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()) {
            int givenValue = scanner.nextInt();
            if(givenValue >= 0) {
                return givenValue;
            } else {
                return -1;
            }
        } else {
            scanner.next();
            return -1;
        }
    }
}
