package UserInterfaces.GraphicsComponents.Panels;

import UserInterfaces.GraphicsComponents.PanelManager;
import UserInterfaces.GraphicsComponents.PanelName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Η κλάση υλοποιεί το panel που περιέχει το μενού του παιχνιδιού.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class HomePanel extends Panel {


    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public HomePanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public PanelName GetPanelName() {
        return PanelName.HOME;
    }

    @Override
    public void Render() {
        RenderRowsOfComponents(
            new JComponent[] {
                GameTitle(),
                StartButton(),
                ScoreButton(),
                AboutButton(),
                HelpButton(),
                ExitButton(),
                ChangeLanguage()
            }, new Insets[] {
                null,
                new Insets(50, 0, 0, 0)
            }
        );
    }

    /**
     * Η μέθοδος δημιουργεί τον τίτλο του panel.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο.
     */
    private JComponent GameTitle() {
        JLabel headLabel = new JLabel(panelManager.GetBundle().getString("homePanelTitle"));
        headLabel.setFont(new Font("Verdana", Font.PLAIN, 45));
        headLabel.setForeground(Color.BLACK);
        headLabel.setVerticalAlignment(SwingConstants.CENTER);
        return headLabel;
    }

    /**
     * Η μέθοδος δημιουργεί το κουμπί για τη έναρξη του παιχνιδιού, την επιλογή των παιχτών και των αντίστοιχων ρυθμίσεων.
     *
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent StartButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("homePanelStartB"));
        button.setPreferredSize(new Dimension(230, 50));
        button.setFont(new Font("Verdana", Font.PLAIN, 30));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> panelManager.SwitchDisplayedPanel(PanelName.DIFFICULTY));
        return button;
    }

    /**
     * Η μέθοδος δημιουργεί το κουμπί για τη βαθμολογία.
     *
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent ScoreButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("homePanelScoreB"));
        button.setPreferredSize(new Dimension(230, 50));
        button.setFont(new Font("Verdana", Font.PLAIN, 30));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> panelManager.SwitchDisplayedPanel(PanelName.SCORE));
        return button;
    }

    /**
     * Η μέθοδος δημιουργεί το κουμπί για τη περιγραφή.
     *
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent AboutButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("homePanelAboutB"));
        button.setPreferredSize(new Dimension(230, 50));
        button.setFont(new Font("Verdana", Font.PLAIN, 30));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> panelManager.SwitchDisplayedPanel(PanelName.ABOUT));
        return button;
    }

    /**
     * Η μέθοδος δημιουργεί το κουμπί για τη βοήθεια.
     *
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent HelpButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("homePanelHelpB"));
        button.setPreferredSize(new Dimension(230, 50));
        button.setFont(new Font("Verdana", Font.PLAIN, 30));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> panelManager.SwitchDisplayedPanel(PanelName.HELP));
        return button;
    }

    /**
     * Η μέθοδος δημιουργεί το κουμπί για την έξοδο από το παιχνίδι.
     *
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent ExitButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("homePanelExitB"));
        button.setPreferredSize(new Dimension(230, 50));
        button.setFont(new Font("Verdana", Font.PLAIN, 30));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> System.exit(0));
        return button;
    }

    /**
     * Η μέθοδος δημιουργεί την επιλογή για την αλλαγή της γλώσσας.
     *
     * @return Το αντικείμενο που περιλαμβάνει το panel για την αλλαγή της γλώσσας.
     */
    private JComponent ChangeLanguage() {
        JPanel languagePanel = new JPanel();
        JLabel languageLabel = new JLabel(panelManager.GetBundle().getString("homePanelChangeLanguage") + " ");
        languageLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        languageLabel.setForeground(Color.BLACK);
        languagePanel.add(languageLabel);
        JComboBox<String> languageBox = new JComboBox<>();
        languageBox.addItem("English");
        languageBox.addItem("Ελληνικά");
        languageBox.setFont(new Font("Verdana", Font.PLAIN, 20));
        languageBox.setForeground(Color.BLACK);
        languageBox.setSelectedIndex(panelManager.GetBundle().getLocale().equals(new Locale("el", "GR")) ? 1 : 0);
        languageBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                Locale locale;
                if (e.getItem().equals("Ελληνικά")) {
                    locale = new Locale("el", "GR");
                } else {
                    locale = new Locale("en", "US");
                }
                panelManager.UpdateBundle(ResourceBundle.getBundle("i18n.MessageListBundle", locale));
                panelManager.RenderAllPanels();
            }
        });
        languagePanel.add(languageBox);
        return languagePanel;
    }
}
