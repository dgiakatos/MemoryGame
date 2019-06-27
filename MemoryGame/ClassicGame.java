package MemoryGame;

import UserInterfaces.GraphicsComponents.DifficultyForm;
import UserInterfaces.GraphicsComponents.Mode;

import java.awt.*;

/**
 * Η κλάση υλοποιεί το κλασικό παιχνίδι με έναν ή πολλούς παίκτες.
 * Λειτουργεί ως μια διασύνδεση μεταξύ του UI και του ταμπλό
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public class ClassicGame extends MemoryGame {

    private Table table;
    private int copies;

    /**
     * Προετοιμάζει ένα καινούργιο παιχνίδι με την επιλεγμένη δυσκολία και αριθμό παικτών.
     * Το ταμπλό παραμένει ανοιχτό μέχρι την κλήση της StartGame
     *
     * @param difficultyForm Η φόρμα με τα ονόματα και το είδος του κάθε παίκτη
     * @param mode Το είδος του παιχνιδιού (Απλό, Διπλό, Τρίο)
     */
    public ClassicGame(DifficultyForm difficultyForm, Mode mode) {
        super(mode);
        int width, height;
        if(mode == Mode.Basic) {
            copies = 2;
            width = 6;
            height = 4;
        } else if (mode == Mode.Double) {
            copies = 2;
            width = 8;
            height = 6;
        } else if (mode == Mode.Triple) {
            copies = 3;
            width = height = 6;
        } else {
            throw new RuntimeException("Invalid Mode. Duel is implemented in class DuelGame");
        }
        table = new Table(width, height, copies);

        players = new Player[difficultyForm.playersTypes.length];
        for(int i = 0; i < difficultyForm.playersTypes.length; i++) {
            if(difficultyForm.playersTypes[i] == 0) {
                players[i] = new Player(difficultyForm.playersNames[i], copies);
            } else if (difficultyForm.playersTypes[i] == 1) {
                players[i] = new ClassicCPU(0, copies, width, height);
            } else if (difficultyForm.playersTypes[i] == 2) {
                players[i] = new ClassicCPU(50, copies, width, height);
            } else if (difficultyForm.playersTypes[i] == 3) {
                players[i] = new ClassicCPU(100, copies, width, height);
            } else {
                throw new RuntimeException("Invalid player type given at index " + i);
            }
        }
    }

    @Override
    public void StartGame() {
        currentlyPlayingPlayerIndex = 0;
        table.HideAllCards();
    }

    @Override
    public boolean OpenCard(Point selection) {
        Card openedCard = table.OpenCard(selection);
        if(openedCard != null) {
            GetCurrentPlayingPlayer().AlertAboutTheIdentityOfOpenedCard(selection, openedCard);
            for (Player player : players) {
                player.AlertAboutOpenCard(selection, openedCard);
            }
            GetCurrentPlayingPlayer().IncrementOpenCards();
            return GetCurrentPlayingPlayer().GetOpenedCards() == copies;
        }
        throw new RuntimeException("Something went terribly wrong");
    }

    @Override
    public void VerifyOpenCards() {
        boolean isPlayingAgain = true;
        Card openCard = table.SameOpenCards();
        GetCurrentPlayingPlayer().IncrementMoves();
        if (openCard != null) {
            for (Player player : players) {
                player.AlertAboutFoundCards(openCard, table.GetOpenCardsCoordinates());
            }
            table.RemoveOpenCards();
            GetCurrentPlayingPlayer().IncrementScore();
            if(table.GetRemainingCards() == 0) {

                FinishedGame();
            }
        } else {
            isPlayingAgain = false;
            table.HideAllCards();
        }
        GetCurrentPlayingPlayer().ResetOpenedCards();
        if(!isPlayingAgain) {
            NextPlayer();
        }
    }

    /**
     * @return Οι κάρτες πάνω στο ταμπλό
     */
    public Card[][] GetCurrentBoardState() {
        return table.GetAllCards();
    }

}
