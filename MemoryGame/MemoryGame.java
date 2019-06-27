package MemoryGame;

import UserInterfaces.GraphicsComponents.Mode;

import java.awt.*;

/**
 * Η κλάση υλοποιεί το ίδιο το παιχνίδι με έναν ή πολλούς παίκτες.
 * Λειτουργεί ως μια διασύνδεση μεταξύ του UI και του ταμπλό
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public abstract class MemoryGame {

    Player[] players;
    int currentlyPlayingPlayerIndex = -1;
    private Mode mode;
    private String winner;

    /**
     * Προετοιμάζει ένα καινούργιο παιχνίδι με την επιλεγμένη δυσκολία και αριθμό παικτών.
     * Το ταμπλό παραμένει ανοιχτό μέχρι την κλήση της StartGame
     *
     * @param mode Το είδος του παιχνιδιού (Απλό, Διπλό, Τρίο)
     */
    public MemoryGame(Mode mode) {
        this.mode = mode;
    }

    /**
     * Χρησιμοποιείται για να γίνουν οι απαραίτητες διαδικασίες για να ξεκινήσει το παιχνίδι όπως
     * το κρύψιμο όλων των καρτών και το ρύθμισμα του παίκτη που παίζει πρώτος
     */
    public abstract void StartGame();

    /**
     * Ανοίγει μια κάρτα ο υπολογιστής που παίζει αυτή τι στιγμή
     * @return Αν ο υπολογιστής που έπαιξε τελείωσε την σειρά του
     */
    public boolean NextCpuTurn() {
        CPU cpuPlayer = (CPU) GetCurrentPlayingPlayer();
        return OpenCard(cpuPlayer.GetNextMove());
    }

    /**
     * Ανοίγει η κάρτα στις συντεταγμένες που δόθηκαν από τον παίκτη ή υπολογιστή που παίζει αυτή τι στιγμή
     * @param selection Οι συντεταγμένες της κάρτας που πρέπει να ανοίξει
     * @return Αν ο παίκτης που έπαιξε τελείωσε την σειρά του
     */
    public abstract boolean OpenCard(Point selection);


    /**
     * @return Το όνομα του νικητή ή null άμα το game δεν έχει τελειώσει
     */
    public String GetWinnerName() {
        return winner;
    }

    /**
     * Χρησιμοποιείται στο τέλος μιας κίνησης για να γίνουν οι απαραίτητοι ελέγχοι και να παίξει ο επόμενος παίκτης
     */
    public abstract void VerifyOpenCards();

    /**
     * @return Τον παίκτη που παίζει αυτή τι στιγμή ή null άμα δεν έχει ξεκινήσει το παιχνίδι
     */
    public Player GetCurrentPlayingPlayer() {
        if(currentlyPlayingPlayerIndex == -1) {
            return null;
        }
        return players[currentlyPlayingPlayerIndex];
    }

    /**
     * Δίνει την τωρινή κατάσταση των σκορ ( ζευγαριών από κάρτες που έχει βρει ο κάθε παίκτης ).
     * @return Ένας δισδιάστατος πίνακας που περιέχει το όνομα και το σκορ του κάθε παίκτη
     */
    public String[][] GetAllPlayerScores() {
        String[][] playerScores = new String[players.length][3];
        for(int i = 0; i < players.length; i++) {
            playerScores[i][0] = players[i].GetName();
            playerScores[i][1] = String.valueOf(players[i].GetScore());
            playerScores[i][2] = String.valueOf(players[i].GetMoves());
        }
        return playerScores;
    }

    /**
     * Η μέθοδος επιστρέφει όλους τους παίκτες.
     *
     * @return Όλους τους παίκτες.
     */
    public Player[] GetAllPlayers() { return players; }

    /**
     * Η μέθοδος εξετάζει αν υπάρχει ισοπαλία ή ισοβαθμία μεταξύ των παικτών.
     *
     * @return  true: Αν υπάρχει ισοπαλία ή ισοβαθμία.
     *          false: Αν δεν υπάρχει ισοπαλία ή ισοβαθμία.
     */
    public boolean GetDraw() {
        String[][] scores = GetAllPlayerScores();
        int tempScore = Integer.valueOf(scores[0][1]);
        for(int i = 1; i < scores.length; i++) {
            if(Integer.valueOf(scores[i][1]) == tempScore) {
                return true;
            }
        }
        return false;
    }

    /**
     * Η μέθοδος αποθηκεύει το όνομα του παίχτη που κέρδισε στο παιχνίδι και αποθηκεύει τα δεδομένα του νικητή (όνομα,
     * βήματα) στο αντίστοιχο αρχείο.
     */
    void FinishedGame() {
        int maxScore = -1;
        int moves = -1;
        String name = "";
        if(players.length == 1) {
            for(Player player : players) {
                moves = player.GetMoves();
                name = player.GetName();
            }
            WriteData(name, moves);
        } else {
            for (Player player : players) {
                if (player.GetScore() > maxScore) {
                    maxScore = player.GetScore();
                    moves = player.GetMoves();
                    name = player.GetName();
                }
            }
            if (!name.equals("CPU") && !GetDraw()) {
                WriteData(name, moves);
            }
        }
        winner = name;
    }

    /**
     * Η μέθοδος αποθηκεύει το όνομα του παίκτη και τα βήματα του στο αρχείο που αντιστοιχεί το παιχνίδι που έπαιξε.
     *
     * @param name Το όνομα του παίκτη.
     * @param moves Τα βήματα του παίκτη.
     */
    private void WriteData(String name, int moves) {
        String fileName;
        String[] data = new String[2];
        data[0] = name;
        data[1] = String.valueOf(moves);
        if(mode == Mode.Basic) {
            if(players.length == 1) {
                fileName = "data0.bin";
            } else {
                fileName = "data3.bin";
            }
        } else if(mode == Mode.Double) {
            if(players.length == 1) {
                fileName = "data1.bin";
            } else {
                fileName = "data4.bin";
            }
        } else if(mode == Mode.Triple) {
            if(players.length == 1) {
                fileName = "data2.bin";
            } else {
                fileName = "data5.bin";
            }
        } else {
            fileName = "data6.bin";
        }
        FileManager file = new FileManager(fileName, "MemoryGameData/");
        file.SetData(data);
    }

    void NextPlayer() {
        currentlyPlayingPlayerIndex++;
        if(currentlyPlayingPlayerIndex == players.length) {
            currentlyPlayingPlayerIndex = 0;
        }
    }
}
