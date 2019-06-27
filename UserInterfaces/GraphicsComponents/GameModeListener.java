package UserInterfaces.GraphicsComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Το listener που χρησιμοποείται στα κουμπιά που ξεκινάν ένα νέο παιχνίδι
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class GameModeListener implements ActionListener {

    private PanelManager panelManager;
    private DifficultyForm difficultyForm;

    /**
     * @param panelManager Ένα αντικείμενο της κλάσης PanelManager
     * @param difficultyForm Η φόρμα που καθορίζει το είδος του κάθε παίκτη
     */
    public GameModeListener(PanelManager panelManager, DifficultyForm difficultyForm) {
        this.panelManager = panelManager;
        this.difficultyForm = difficultyForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "0":
                panelManager.StartGame(difficultyForm, Mode.Basic);
                break;
            case "1":
                panelManager.StartGame(difficultyForm, Mode.Double);
                break;
            case "2":
                panelManager.StartGame(difficultyForm, Mode.Triple);
                break;
            case "duel":
                panelManager.StartGame(difficultyForm, Mode.Duel);
                break;
        }
    }
}
