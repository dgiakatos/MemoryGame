package UserInterfaces.GraphicsComponents.Panels;

import MemoryGame.FileManager;
import UserInterfaces.GraphicsComponents.PanelManager;
import UserInterfaces.GraphicsComponents.PanelName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Η κλάση υλοποιεί το panel που περιέχει τη βαθμολογία του παιχνιδιού.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class ScorePanel extends Panel {

    private int countSB = 0;

    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public ScorePanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public PanelName GetPanelName() {
        return PanelName.SCORE;
    }

    @Override
    public void Render() {
        RenderRowsOfComponents(
            new JComponent[] {
                HeadTitle(),
                MainText(),
                panelManager.GetGenericComponents().BackButton()
            }
        );
    }

    /**
     * Η μέθοδος δημιουργεί τον τίτλο του panel και μία λίστα ώστε να μπορεί να επιλέξει πιο από τα επτά βαθμολογίες θέλει να δει.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο και τη λίστα.
     */
    private JComponent HeadTitle() {
        JPanel panel = new JPanel();
        JLabel scoreLabel = new JLabel(panelManager.GetBundle().getString("scorePanelTitle"));
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        scoreLabel.setForeground(Color.BLACK);
        panel.add(scoreLabel);
        JComboBox<String> scoreBox = new JComboBox<>();
        for(int i = 0; i < 7; i++) {
            scoreBox.addItem(panelManager.GetBundle().getString("scorePanelSB" + i));
        }
        scoreBox.setFont(new Font("Verdana", Font.PLAIN, 25));
        scoreBox.setForeground(Color.BLACK);
        for(int i = 0; i < 7; i++) {
            if(countSB == i) {
                scoreBox.setSelectedIndex(i);
            }
        }
        scoreBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                for(int i = 0; i < 7; i++) {
                    if (e.getItem().equals(panelManager.GetBundle().getString("scorePanelSB" + i))) {
                        countSB = i;
                        Render();
                    }
                }
            }
        });
        panel.add(scoreBox);
        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί το πίνακα με τη βαθμολογία.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον πίνακα.
     */
    private JComponent MainText() {
        JPanel panel = new JPanel();
        GridLayout scoreTable = new GridLayout(11, 3, 150, 10);
        panel.setLayout(scoreTable);
        GenerateScoreTable(panel);
        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί το πίνακα με τη βαθμολογία ανάλογα με το είδος του παιχνιδιού που επέλεξε.
     *
     * @param panel Το αντικείμενο που περιλαμβάνει τον πίνακα.
     */
    private void GenerateScoreTable(JPanel panel) {
        if(countSB < 3) {
            SoloScore(panel);
        } else {
            MultiplayerScore(panel);
        }
    }

    /**
     * Η μέθοδος δημιουργεί τον πίνακα της βαθμολογίας βάση των βημάτων στα είδη των παιχνιδιών που παίζει μόνο ένας παίχτης.
     *
     * @param panel Το αντικείμενο που περιλαμβάνει τον πίνακα.
     */
    private void SoloScore(JPanel panel) {
        FileManager file = new FileManager("data" + countSB + ".bin", "MemoryGameData/");
        String[] str = file.ExportStepsToStringMatrix();
        JLabel[] textLabel = new JLabel[33];
        int j = 0;
        int rankCount = 1;
        for(int i = 0; i < 33; i++) {
            if(i < 3) {
                textLabel[i] = new JLabel(panelManager.GetBundle().getString("scorePanelS" + i));
                textLabel[i].setFont(new Font("Verdana", Font.PLAIN, 30));
            } else if(i%3 == 0) {
                textLabel[i] = new JLabel(String.valueOf(rankCount));
                textLabel[i].setFont(new Font("Verdana", Font.PLAIN, 20));
                rankCount++;
            } else {
                if(j < str.length) {
                    textLabel[i] = new JLabel(str[j]);
                    textLabel[i].setFont(new Font("Verdana", Font.PLAIN, 20));
                    j++;
                } else {
                    textLabel[i] = new JLabel("");
                }
            }
            textLabel[i].setForeground(Color.BLACK);
            textLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(textLabel[i]);
        }
    }

    /**
     * Η μέθοδος δημιουργεί τον πίνακα της βαθμολογίας βάση των νικών στα είδη των παιχνιδιών που παίζουν δύο ή παραπάνω παίχτες.
     *
     * @param panel Το αντικείμενο που περιλαμβάνει τον πίνακα.
     */
    private void MultiplayerScore(JPanel panel) {
        FileManager file = new FileManager("data" + countSB + ".bin", "MemoryGameData/");
        String[] str = file.ExportWinsToStringMatrix();
        JLabel[] textLabel = new JLabel[33];
        int j = 0;
        int rankCount = 1;
        for(int i = 0; i < 33; i++) {
            if(i < 3) {
                textLabel[i] = new JLabel(panelManager.GetBundle().getString("scorePanelM" + i));
                textLabel[i].setFont(new Font("Verdana", Font.PLAIN, 30));
            } else if(i%3 == 0) {
                textLabel[i] = new JLabel(String.valueOf(rankCount));
                textLabel[i].setFont(new Font("Verdana", Font.PLAIN, 20));
                rankCount++;
            } else {
                if(j < str.length) {
                    textLabel[i] = new JLabel(str[j]);
                    textLabel[i].setFont(new Font("Verdana", Font.PLAIN, 20));
                    j++;
                } else {
                    textLabel[i] = new JLabel("");
                }
            }
            textLabel[i].setForeground(Color.BLACK);
            textLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(textLabel[i]);
        }
    }
}
