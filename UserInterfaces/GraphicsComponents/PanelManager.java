package UserInterfaces.GraphicsComponents;


import UserInterfaces.GraphicsComponents.Panels.Panel;
import UserInterfaces.GraphicsComponents.Panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * Υλοποιεί το αντικείμενο που είναι υπεύθυνο για την εναλλαγή μεταξύ των διαφορετικών menu (panel).
 * Είναι υπεύθυνο στο να παρέχει global αντικείμενα όπως το bundle και τα genericComponents σε όλα τα panels.
 * Από εδώ μπορεί επίσης να επιλεχτεί η γλώσσα της εφαρμογής.
 * Ουσιαστικά είναι ένα βοηθητικό singleton που η βασική του δουλειά είναι η διεπαφή με το CardLayout του JFrame.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class PanelManager {

    private GenericComponents genericComponents;
    private ResourceBundle bundle;
    private CardLayout cardLayout;
    private JFrame jFrame;
    private final Panel[] panels;


    /**
     * Δημιουργία του singleton.
     * Μπαίνει το CardLayout με όλα τα panels στο frame.
     * Όλα τα panels εκτός από αυτά του παιχνιδιού δημιουργούνται κατευθείαν στην μνήμη.
     * Προετοιμάζεται επίσης το GenericComponents singleton
     *
     * @param jFrame Το παράθυρο της εφαρμογής
     * @param bundle Το default bundle της εφαρμογής
     */
    public PanelManager(JFrame jFrame, ResourceBundle bundle) {
        CardLayout cl = new CardLayout();
        jFrame.setLayout(cl);
        this.cardLayout = cl;
        this.jFrame = jFrame;
        this.bundle = bundle;
        this.genericComponents = new GenericComponents(this);
        this.panels = new Panel[]{
            new HomePanel(this),
            new AboutPanel(this),
            new ScorePanel(this),
            new HelpPanel(this),
            new DifficultyPanel(this)
        };
        AddPanelsToCardLayout();
        RenderAllPanels();
        SwitchDisplayedPanel(PanelName.HOME);
    }

    /**
     * Ξανα δημιουργεί όλα τα panels για να φανούν αλλαγές όπως αλλαγή γλώσσας.
     */
    public void RenderAllPanels() {
        for (Panel panel : panels) {
            panel.Render();
        }
    }

    /**
     * @return Το bundle για το i18n
     */
    public ResourceBundle GetBundle() {
        return bundle;
    }

    /**
     * Ενημερώνει το bundle (για αλλαγή γλώσσας).
     * Για να φανεί η νέα γλώσσα πρέπει να γίνουν rerendered όλα τα panels.
     * @param bundle Το νεό bundle
     */
    public void UpdateBundle(ResourceBundle bundle) {
        this.bundle = bundle;
        jFrame.setTitle(bundle.getString("frameTitle"));
    }

    /**
     * @return Το singleton γενικευμένων components
     */
    public GenericComponents GetGenericComponents() {
        return genericComponents;
    }

    /**
     * Αλλάζει το panel/παράθυρο που χρησιμοποιείται αυτή τι στιγμή
     * @param Panel Το όνομα του panel
     */
    public void SwitchDisplayedPanel(PanelName Panel) {
        cardLayout.show(jFrame.getContentPane(), Panel.toString());
    }

    /**
     * Ξεκινάει ένα νέο παιχνίδι. Άμα υπήρχε ήδη ένα παιχνίδι στην μνήμη, χάνεται.
     *
     * @param difficultyForm Η φόρμα με τα όνοματα και το είδος του κάθε παίκτη
     * @param mode Το είδος του παιχνιδιού
     */
    void StartGame(DifficultyForm difficultyForm, Mode mode) {
        GamePanel gamePanel;
        if(mode != Mode.Duel) {
            gamePanel = new GameClassicPanel(this);
        } else {
            gamePanel = new GameDuelPanel(this);
        }
        gamePanel.Initialize(difficultyForm, mode);

        jFrame.add(gamePanel, gamePanel.GetPanelName().toString());
        SwitchDisplayedPanel(gamePanel.GetPanelName());
    }

    private void AddPanelsToCardLayout() {
        for (Panel panel : panels) {
            jFrame.add(panel, panel.GetPanelName().toString());
        }
    }

}
