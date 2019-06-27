package UserInterfaces.GraphicsComponents.Panels;

import MemoryGame.MemoryGame;
import UserInterfaces.GraphicsComponents.*;
import javax.swing.*;
import java.awt.*;

/**
 * Η κλάση υλοποιεί το panel που περιέχει το ταμπλό του παιχνιδιού με τις κάρτες.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public abstract class GamePanel extends Panel {

    MemoryGame memoryGame;

    private String currentlyPlaying;
    private String winnerPlayer;

    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public GamePanel(PanelManager panelManager) {
        super(panelManager);
    }

    /**
     * Η μέθοδος δημιουργεί το ταμπλό με τις κάρτες.
     *
     * @param difficultyForm Αντικείμενο από τη κλάση DifficultyForm.
     * @param mode Το είδος του παιχνιδιού.
     */
    public abstract void Initialize(DifficultyForm difficultyForm, Mode mode);

    /**
     * Η μέθοδος ελέγχει αν το παιχνίδι είναι ισοπαλία ή αν έχει νικητή και εμφανίζει το αντίστοιχό μήνυμα στο panel.
     */
    void RenderWinner() {
        String winner = memoryGame.GetWinnerName();
        if(memoryGame.GetAllPlayerScores().length == 1) {
            winnerPlayer = panelManager.GetBundle().getString("gamePanelWinS");
        } else {
            if(memoryGame.GetDraw()) {
                winnerPlayer = panelManager.GetBundle().getString("gamePanelWinD");
            } else {
                winnerPlayer = panelManager.GetBundle().getString("gamePanelWinM") + " " + winner + ".";
            }
        }
        RenderRowsOfComponents(
            new JComponent[] {
                WinnerBox(),
                panelManager.GetGenericComponents().BackButton()
            },
            new Insets[] {
                new Insets(0, 0, 0, 0)
            }
        );
        panelManager.RenderAllPanels();
    }

    /**
     * Η μέθοδος ορίζει σε μία μεταβλητή ποιος παίχτης παίζει κάθε φορά.
     */
    void setCurrentlyPlaying() {
        String name = null;
        if(memoryGame != null && memoryGame.GetCurrentPlayingPlayer() != null) {
            name = memoryGame.GetCurrentPlayingPlayer().GetName();
        }

        if(name == null) {
            currentlyPlaying = "";
        } else {
            if(GetPanelName() == PanelName.GAME_DUEL) {
                if(name.equals(memoryGame.GetAllPlayers()[0].GetName())) {
                    currentlyPlaying = panelManager.GetBundle().getString("gamePanelCPDB") + " " + name;
                } else {
                    currentlyPlaying = panelManager.GetBundle().getString("gamePanelCPDR") + " " + name;
                }
            } else {
                currentlyPlaying = panelManager.GetBundle().getString("gamePanelCP") + " " + name;
            }
        }
    }

    /**
     * Η μέθοδος δημιουργεί το πλαίσιο που εμφανίζει το μήνυμα όταν τελειώνει το παιχνίδι.
     *
     * @return Το αντικείμενο που περιλαμβάνει το πλαίσιο.
     */
    private JComponent WinnerBox() {
        JLabel winnerBox = new JLabel(winnerPlayer);
        winnerBox.setFont(new Font("Verdana", Font.PLAIN, 35));
        winnerBox.setForeground(Color.BLACK);
        return winnerBox;
    }

    /**
     * Η μέθοδος δημιουργεί τον τίτλο του panel.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο.
     */
    JComponent HeadTitle() {
        JLabel headLabel = new JLabel();
        if(memoryGame.GetAllPlayerScores().length == 1) {
            headLabel.setText(panelManager.GetBundle().getString("gamePanelSt"));
        } else {
            headLabel.setText(panelManager.GetBundle().getString("gamePanelSc"));
        }
        headLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        headLabel.setForeground(Color.BLACK);
        return headLabel;
    }

    /**
     * Η μέθοδος δημιουργεί το πλαίσιο που περιλαμβάνει τον τίτλο για τη βαθμολογία ή τα βήματα.
     *
     * @return Το αντικείμενο που περιλαμβάνει το πλαίσιο.
     */
    JComponent PScore() {
        String[][] playerScores = memoryGame.GetAllPlayerScores();
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        for(String[] playerScore : playerScores) {
            panel.add(PNScore(playerScore[0], playerScore[1], playerScore[2]));
        }
        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί το πλαίσιο που περιλαμβάνει τη βαθμολογία ή τα βήματα και το όνομα του παίχτη.
     *
     * @param name Το όνομα του παίχτη.
     * @param score Η βαθμολογία ή τα βήματα του παίχτη.
     * @return Το αντικείμενο που περιλαμβάνει το πλαίσιο.
     */
    private JComponent PNScore(String name, String score, String steps) {
        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        nameLabel.setForeground(Color.BLACK);
        if(nameLabel.getPreferredSize().width > 150){
            nameLabel.setPreferredSize(new Dimension(150, 25));
        }
        panel.add(nameLabel);

        JLabel scoreLabel = new JLabel();
        if (memoryGame.GetAllPlayerScores().length == 1) {
            scoreLabel.setText(": " + steps);
        } else {
            scoreLabel.setText(": " + score);
        }
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        scoreLabel.setForeground(Color.BLACK);
        panel.add(scoreLabel);


        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί το πλαίσιο που περιλαμβάνει το μήνυμα που εμφανίζει ποιος παίχτης παίζει αυτό τον γύρο.
     *
     * @return Το αντικείμενο που περιλαμβάνει το πλαίσιο.
     */
    JComponent CurrentlyPlaying() {
        JLabel label = new JLabel(currentlyPlaying);
        label.setPreferredSize(new Dimension(1000, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 25));
        label.setForeground(Color.BLACK);
        return label;
    }
}
