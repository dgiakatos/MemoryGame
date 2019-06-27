package MemoryGame;

import UserInterfaces.*;

/**
 * Η κλάση υλοποιεί το ίδιο το παιχνίδι με έναν παίκτη.
 * Λειτουργεί ως μια διασύνδεση μεταξύ του δοσμένου UI και του ταμπλό
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 */
public class MemoryGame {
    private final UserInterface UI;
    private Table table;
    private Player player;
    private int copies;

    /**
     * @param UI Ο τρόπος διεπαφής με τον χρήστη που θα χρησιμοποιηθεί από την κλάση
     */
    public MemoryGame(UserInterface UI) {
        this.UI = UI;
    }

    /**
     * Η μέθοδος προετοιμάζει την κλάση και ξεκινάει ένα νέο παιχνίδι.
     * Αρχικά αφότου το UI είναι έτοιμο, ζητείται η δυσκολία από τον χρήστη.
     * Στην συνέχεια εμφανίζονται όλες οι κάρτες για 5 δευτερόλεπτα και έπειτα κλείνουν
     * για να ξεκινήσει το παιχνίδι.
     */
    public void NewGame() {
        UI.Initialize();
        int difficulty = UI.AskForDifficulty();
        player = new Player();
        if(difficulty == 1) {
            copies = 2;
            table = new Table(4, 6, copies);
        } else if(difficulty == 2) {
            copies = 2;
            table = new Table(6, 8, copies);
        } else if(difficulty == 3) {
            copies = 3;
            table = new Table(6, 6, copies);
        }

        UI.RenderTable(table.GetAllCards());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) { }
        table.HideAllCards();

        do {
            NextMove();
            player.increaseMovesBy(copies);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) { }
        } while (table.GetRemainingCards() > 0);

        FinishGame();
    }

    /**
     * Η μέθοδος αρχικά ζητάει από το χρήστη να εισάγει τις συντεταγμένες της κάρτας που θέλει να ανοίξει.
     * Στη συνέχεια ελέγχει:
     * <ul> Αν οι κάρτες που άνοιξε είναι ίδιες, τότε τις διαγράφει από το ταμπλό. <p>
     *      Αν οι κάρτες που άνοιξε είναι διαφορετικές, τότε τις κλείνει. <p>
     * </ul>
     */
    private void NextMove() {
        UI.RenderTable(table.GetAllCards());
        for(int i = 0; i < copies; i++) {
            boolean validCard = table.OpenCard(UI.AskToSelectACard());
            if(!validCard) {
                UI.RenderTable(table.GetAllCards());
                UI.ShowError("Invalid card coordinates");
                i--;
                continue;
            }
            UI.RenderTable(table.GetAllCards());
        }
        if(table.SameOpenCards()) {
            table.RemoveOpenCards();
        } else {
            table.HideAllCards();
        }
    }

    /**
     * Η μέθοδος εμφανίζει στο χρήστη με πόσα βήματα τελείωσε το παιχνίδι.
     */
    private void FinishGame() {
        UI.ShowMessage("You finished with " + player.getMoves() + " moves!!");
    }
}
