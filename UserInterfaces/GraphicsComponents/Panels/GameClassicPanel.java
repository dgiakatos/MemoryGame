package UserInterfaces.GraphicsComponents.Panels;

import MemoryGame.CPU;
import MemoryGame.ClassicGame;
import UserInterfaces.GraphicsComponents.DifficultyForm;
import UserInterfaces.GraphicsComponents.Mode;
import UserInterfaces.GraphicsComponents.PanelManager;
import UserInterfaces.GraphicsComponents.PanelName;

import javax.swing.*;
import java.awt.*;

/**
 * Η κλάση υλοποιεί το panel που περιέχει το ταμπλό του παιχνιδιού με τις κάρτες.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class GameClassicPanel extends GamePanel {

    private int columnLength;
    private boolean canSelectCard = false;

    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public GameClassicPanel(PanelManager panelManager) {
        super(panelManager);
    }

    /**
     * Η μέθοδος ξεκινάει το παιχνίδι.
     * Αφότου δημιουργηθεί το ταμπλό περιμένει 5 δευτερόλεπτα με τις κάρτες ανοιχτές και στην συνέχεια τις κρύβει
     *
     * @param difficultyForm Αντικείμενο από τη κλάση DifficultyForm.
     * @param mode Το είδος του παιχνιδιού.
     */
    @Override
    public void Initialize(DifficultyForm difficultyForm, Mode mode) {
        memoryGame = new ClassicGame(difficultyForm, mode);
        Render(); // Render with cards displayed
        Timer timer = new Timer(5000, e -> {
            memoryGame.StartGame();
            Render(); // Game started. All cards hidden
            canSelectCard = true;
        });

        columnLength = ((ClassicGame) memoryGame).GetCurrentBoardState()[0].length;

        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public PanelName GetPanelName() {
        return PanelName.GAME;
    }

    @Override
    public void Render() {
        if(memoryGame == null) {
            return;
        }
        setCurrentlyPlaying();
        RenderRowsOfComponents(
            new JComponent[] {
                HeadTitle(),
                PScore(),
                CurrentlyPlaying(),
                panelManager.GetGenericComponents().GenerateTable(
                    ((ClassicGame) memoryGame).GetCurrentBoardState(),
                    e -> OnCardSelected(Integer.parseInt(e.getActionCommand()))
                ),
                panelManager.GetGenericComponents().BackButton()
            },
            new Insets[] {
                new Insets(0, 0, 0, 0)
            }
        );
    }

    /**
     * Η μέθοδος τρέχει όταν τελείωσε η σειρά αυτού που έπαιζε τώρα.
     * Περίμενει 2 δευτερόλεπτα για να δουν οι παίκτες τις κάρτες που ανοίξε ο προηγούμενος παίκτης.
     * Είναι υπεύθυνση για να ελέγξει αν τελείωσε όλο το παιχνίδι και να βάλει τον επόμενο παίκτη να παίξει.
     */
    private void HandleMoveFinish() {
        Timer timer = new Timer(2000, e -> {
            memoryGame.VerifyOpenCards();
            if(memoryGame.GetWinnerName() != null) {
                RenderWinner();
                return;
            }
            Render();
            if(memoryGame.GetCurrentPlayingPlayer() instanceof CPU) {
                HandleCpuTurn();
            } else {
                canSelectCard = true;
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Η μέθοδος ανοίγει μία κάρτα που επέλεξε ένας παίκτης.
     *
     * @param buttonIndex Ο αριθμός της κάρτας που επιλέχθηκε.
     */
    private void OnCardSelected(int buttonIndex) {
        try {
            if(canSelectCard && memoryGame.OpenCard(ConvertIndexToPoint(buttonIndex))) {
                canSelectCard = false;
                HandleMoveFinish();
            }
            Render();
        } catch(RuntimeException e) {
            //Error doesn't matter
        }
    }

    /**
     * Η μέθοδος ζητάει από τον υπολογιστή να κάνει την κίνηση του.
     * Ανοίγει μια κάρτα κάθε 1 δευτερόλεπτο για να προλαβαίνουν οι παίκτες να βλέπουν τι άνοιξε
     */
    private void HandleCpuTurn() {
        Timer timer = new Timer(1000, null);
        timer.addActionListener(e -> {
            if(memoryGame.NextCpuTurn()) {
                timer.stop();
                HandleMoveFinish();
            }
            Render();
        });
        timer.start();
    }

    /**
     * Η μέθοδος μετατρέπει τον ακέραιο αριθμό (του κουμπιού στο ταμπλό) σε συντεταγμένες.
     *
     * @param index Ακέραιος αριθμός
     * @return Συντεταγμένες
     */
    private Point ConvertIndexToPoint(int index) {
        return new Point(index % columnLength + 1, index / columnLength + 1);
    }
}
