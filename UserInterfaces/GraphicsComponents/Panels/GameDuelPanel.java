package UserInterfaces.GraphicsComponents.Panels;

import MemoryGame.CPU;
import MemoryGame.DuelGame;
import UserInterfaces.GraphicsComponents.DifficultyForm;
import UserInterfaces.GraphicsComponents.Mode;
import UserInterfaces.GraphicsComponents.PanelManager;
import UserInterfaces.GraphicsComponents.PanelName;
import javax.swing.*;
import java.awt.*;

/**
 * Η κλάση υλοποιεί το panel που περιέχει το ταμπλό του παιχνιδιού με τις κάρτες για το είδος της μονομαχίας.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class GameDuelPanel extends GamePanel {

    private int canSelectCardOnSide = -2; // -2 for future left. -1 for future right. 0 for left side. 1 for right side.

    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public GameDuelPanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public PanelName GetPanelName() {
        return PanelName.GAME_DUEL;
    }

    @Override
    public void Render() {
        setCurrentlyPlaying();
        RenderRowsOfComponents(
            new JComponent[] {
                HeadTitle(),
                PScore(),
                CurrentlyPlaying(),
                GenerateTable(),
                panelManager.GetGenericComponents().BackButton()
            },
            new Insets[] {
                new Insets(0, 0, 0, 0)
            }
        );
    }

    /**
     * Η μέθοδος ξεκινάει το παιχνίδι.
     * Αφότου δημιουργηθούν τα ταμπλό περιμένει 5 δευτερόλεπτα με τις κάρτες ανοιχτές και στην συνέχεια τις κρύβει
     *
     * @param difficultyForm Αντικείμενο από τη κλάση DifficultyForm.
     * @param mode Το είδος του παιχνιδιού.
     */
    @Override
    public void Initialize(DifficultyForm difficultyForm, Mode mode) {
        memoryGame = new DuelGame(difficultyForm, mode);
        Render(); // Render with cards displayed
        Timer timer = new Timer(5000, e -> {
            memoryGame.StartGame();
            Render(); // Game started. All cards hidden
            canSelectCardOnSide = 0;
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Η μέθοδος δημιουργεί τα δύο ταμπλό του παιχνιδιού.
     *
     * @return Το αντικείμενο που περιλαμβάνει τα ταμπλό.
     */
    private JComponent GenerateTable() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 70, 0));
        JComponent leftSubPanel = panelManager.GetGenericComponents().GenerateTable(
            ((DuelGame) memoryGame).GetLeftBoard(),
            e -> OnCardSelected(Integer.parseInt(e.getActionCommand()), 0)
        );
        JComponent rightSubPanel = panelManager.GetGenericComponents().GenerateTable(
            ((DuelGame) memoryGame).GetRightBoard(),
            e -> OnCardSelected(Integer.parseInt(e.getActionCommand()), 1)
        );
        leftSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10, true));
        panel.add(leftSubPanel);
        rightSubPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 10, true));
        panel.add(rightSubPanel);
        return panel;
    }

    /**
     * Η μέθοδος τρέχει όταν τελείωσε η σειρά αυτού που έπαιζε τώρα.
     * Περίμενει 2 δευτερόλεπτα για να δουν οι παίκτες τις κάρτες που ανοίξε ο προηγούμενος παίκτης.
     * Είναι υπεύθυνση για να ελέγξει αν τελείωσε όλο το παιχνίδι και να βάλει τον παίκτη που έπαιξε δεύτερος να παίξει
     * τώρα πρώτος.
     */
    private void HandleMoveFinish() {
        Timer timer = new Timer(2000, e -> {
            memoryGame.VerifyOpenCards();
            if(memoryGame.GetWinnerName() != null) {
                RenderWinner();
                return;
            }
            if(memoryGame.GetCurrentPlayingPlayer() instanceof CPU) {
                HandleCpuTurn();
                Render();
            } else {
                Render();
                canSelectCardOnSide ^= -2; // Make it possible to select on correct side
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Η μέθοδος ανοίγει μία κάρτα που επέλεξε ένας παίκτης.
     *
     * @param buttonIndex Ο αριθμός της κάρτας που επιλέχθηκε.
     * @param panel Το ταμπλό που θα ανοιχθεί η κάρτα.
     */
    private void OnCardSelected(int buttonIndex, int panel) {
        try {
            if(canSelectCardOnSide == panel) {
                if(memoryGame.OpenCard(ConvertIndexToPoint(buttonIndex))) {
                    canSelectCardOnSide ^= -2; //Disable inputs
                    Render();
                    HandleMoveFinish();
                } else {
                    Render();
                    SetCanSelectCardOnSideToTrue(canSelectCardOnSide ^ 1); //Switch active side
                }
            }
        } catch(RuntimeException e) {
            //Error doesn't matter
        }

    }

    /**
     * Η μέθοδος επιτρέπει στον υπολογιστή να ανοίξει μία κάρτα.
     */
    private void HandleCpuTurn() {
        if(memoryGame.NextCpuTurn()) {
            canSelectCardOnSide = -2;
            Render();
            HandleMoveFinish();
        } else {
            Render();
            SetCanSelectCardOnSideToTrue(0);
        }
    }

    private void SetCanSelectCardOnSideToTrue(int selection) {
        if(selection == 0) {
            canSelectCardOnSide = 0;
        } else if(selection == 1) {
            if(memoryGame.GetCurrentPlayingPlayer() instanceof CPU) {
                HandleCpuTurn();
            } else {
                canSelectCardOnSide = 1;
            }

        }
    }

    /**
     * Η μέθοδος μετατρέπει τον ακέραιο αριθμό (του κουμπιού στο ταμπλό) σε συντεταγμένες.
     *
     * @param index Ακέραιος αριθμός
     * @return Συντεταγμένες
     */
    private Point ConvertIndexToPoint(int index) {
        return new Point(index % 6 + 1, index / 6 + 1);
    }

}
