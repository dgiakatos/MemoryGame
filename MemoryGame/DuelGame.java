package MemoryGame;

import UserInterfaces.GraphicsComponents.DifficultyForm;
import UserInterfaces.GraphicsComponents.Mode;

import java.awt.*;

/**
 * Η κλάση υλοποιεί το παιχνίδι μονομαχία με δύο παίκτες.
 * Λειτουργεί ως μια διασύνδεση μεταξύ του UI και του ταμπλό
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public class DuelGame extends MemoryGame {

    private Table[] tables = new Table[2];
    private boolean isSecondMove = false;

    /**
     * Προετοιμάζει ένα καινούργιο παιχνίδι μονομαχία με την επιλεγμένη δυσκολία ( Σε περίπτωση που είναι υπολογιστής ).
     * Τα ταμπλό παραμένει ανοιχτό μέχρι την κλήση της StartGame
     *
     * @param difficultyForm Η φόρμα με τα ονόματα και το είδος του κάθε παίκτη
     * @param mode Το είδος του παιχνιδιού (Απλό, Διπλό, Τρίο)
     */
    public DuelGame(DifficultyForm difficultyForm, Mode mode) {
        super(mode);
        tables[0] = new Table(6, 4, 1);
        tables[1] = new Table(6, 4, 1);

        players = new Player[2];
        for(int i = 0; i < 2; i++) {
            if(difficultyForm.playersTypes[i] == 0) {
                players[i] = new Player(difficultyForm.playersNames[i], 1);
            } else if (difficultyForm.playersTypes[i] == 1) {
                players[i] = new DuelCPU(0, 6, 4);
            } else if (difficultyForm.playersTypes[i] == 2) {
                players[i] = new DuelCPU(50, 6, 4);
            } else if (difficultyForm.playersTypes[i] == 3) {
                players[i] = new DuelCPU(100, 6, 4);
            } else {
                throw new RuntimeException("Invalid player type given at index " + i);
            }
        }
    }

    @Override
    public void StartGame() {
        currentlyPlayingPlayerIndex = 0;
        tables[0].HideAllCards();
        tables[1].HideAllCards();
    }

    // Returns true when both tables have a single card opened
    @Override
    public boolean OpenCard(Point selection) {
        Card openedCard = tables[currentlyPlayingPlayerIndex].OpenCard(selection);
        if(openedCard != null) {
            GetCurrentPlayingPlayer().AlertAboutOpenCard(selection, openedCard);
            if(!isSecondMove) {
                NextPlayer();
                GetCurrentPlayingPlayer().AlertAboutTheIdentityOfOpenedCard(selection, openedCard);
            }
            return !(isSecondMove = !isSecondMove);
        }
        throw new RuntimeException("Something went terribly wrong");
    }

    @Override
    public void VerifyOpenCards() {
        Card openedCardOnFirstTable = tables[0].SameOpenCards();
        Card openedCardOnSecondTable = tables[1].SameOpenCards();
        if (openedCardOnFirstTable.equals(openedCardOnSecondTable)) {
            players[0].AlertAboutFoundCards(openedCardOnFirstTable, tables[0].GetOpenCardsCoordinates());
            players[1].AlertAboutFoundCards(openedCardOnSecondTable, tables[1].GetOpenCardsCoordinates());
            tables[0].RemoveOpenCards();
            tables[1].RemoveOpenCards();
            GetCurrentPlayingPlayer().IncrementScore();
            if(tables[0].GetRemainingCards() == 0) {
                FinishedGame();
            }
        } else {
            tables[0].HideAllCards();
            tables[1].HideAllCards();
        }
    }

    /**
     * @return Οι κάρτες πάνω στο αριστερό ταμπλό
     */
    public Card[][] GetLeftBoard() {
        return tables[0].GetAllCards();
    }

    /**
     * @return Οι κάρτες πάνω στο δεξί ταμπλό
     */
    public Card[][] GetRightBoard() {
        return tables[1].GetAllCards();
    }
}
