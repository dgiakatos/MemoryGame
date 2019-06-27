package UserInterfaces.GraphicsComponents.Panels;

import UserInterfaces.GraphicsComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/**
 * Η κλάση υλοποιεί το panel που περιέχει τις ρυθμίσεις για να ξεκινήσει κάποιο από τα επτά διαφορετικά είδη παιχνιδιού.
 *
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Θεμιστοκλής Χατζηεμμανουήλ
 * @version 1.0.0
 */
public class DifficultyPanel extends Panel {

    private boolean duelMode = false;
    private int selectedPlayers;
    private final JRadioButton[][] playersTypes = new JRadioButton[4][4];
    private final JTextField[] playersNames = new JTextField[4];

    private final JPanel playerOptions = new JPanel(new GridBagLayout());
    private final JPanel[] playerOptionsPanels = new JPanel[4];

    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public DifficultyPanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public PanelName GetPanelName() {
        return PanelName.DIFFICULTY;
    }

    @Override
    public void Render() {
        GeneratePlayerOptionsPanels();

        JComponent[] components;
        Insets[] insets = new Insets[8];
        if(!duelMode) {
            components = new JComponent[] {
                HeadTitle(),
                NumberOfPlayers(),
                SubTitles(),
                playerOptions,
                DuelTitle(),
                TitleGameMode(),
                GameButtons(),
                panelManager.GetGenericComponents().BackButton()
            };

            insets[2] = new Insets(0, 0, 0, 0);
            insets[3] = new Insets(0, 0, 0, 0);
        } else {
            components = new JComponent[] {
                HeadTitle(),
                SubTitles(),
                playerOptions,
                DuelTitle(),
                DuelGameButton(),
                panelManager.GetGenericComponents().BackButton()
            };
        }

        RenderRowsOfComponents(components, insets);
        ChangeRenderedPlayerOptions(duelMode ? 2 : 1);
    }

    /**
     * Η μέθοδος εμφανίζει το χώρο για να τοποθετηθούν οι ρυθμίσεις για τους παίχτες που έχει επιλέξει.
     *
     * @param players Το πλήθος των παιχτών.
     */
    private void ChangeRenderedPlayerOptions(int players) {
        selectedPlayers = players;
        GridBagConstraints gridBag = new GridBagConstraints();
        playerOptions.removeAll();
        for(int i = 0; i < players; i++) {
            gridBag.gridx = 1;
            gridBag.gridy = i;
            gridBag.insets = new Insets(0,0,0,0);
            playerOptions.add(playerOptionsPanels[i], gridBag);
            playerOptions.updateUI();
        }
    }

    /**
     * Η μέθοδος δημιουργεί τις ρυθμίσεις για μέχρι 4 παίκτες.
     * Οι ρυθμίσεις δεν εμφανίζονται στην οθόνη από προεπιλογή.
     * Χρειάζεται η κλήση της ChangeRenderedPlayerOptions για αυτόν τον σκοπό
     */
    private void GeneratePlayerOptionsPanels() {
        for(int n = 0; n < 4; n++) {
            JPanel panel = new JPanel();
            JTextField input = new JTextField("Player " + (n + 1));
            input.setFont(new Font("Verdana", Font.PLAIN, 20));
            input.setForeground(Color.BLACK);
            input.setPreferredSize(new Dimension(100, 28));
            JLabel label = new JLabel(": ");
            label.setFont(new Font("Verdana", Font.PLAIN, 20));
            label.setForeground(Color.BLACK);
            playersNames[n] = input;
            panel.add(input);
            panel.add(label);

            JRadioButton[] buttons = new JRadioButton[4];
            final String[] labels = {
                panelManager.GetBundle().getString("cpuMode1"),
                panelManager.GetBundle().getString("cpuMode2"),
                panelManager.GetBundle().getString("cpuMode3"),
                panelManager.GetBundle().getString("cpuMode4")
            };
            ButtonGroup group = new ButtonGroup();
            for(int i = 0; i < 4; i++) {
                buttons[i] = new JRadioButton(labels[i]);
                if(i == 0) {
                    buttons[i].setSelected(true);
                    buttons[i].setForeground(new Color(255,69,0));
                } else {
                    if(n == 0) {
                        buttons[i].setEnabled(false);
                    }
                    buttons[i].setForeground(Color.BLUE);
                }
                buttons[i].setFont(new Font("Verdana", Font.PLAIN, 20));
                group.add(buttons[i]);
                panel.add(buttons[i]);
                final int row = n;
                if(i > 0) {
                    buttons[i].addActionListener(e -> {
                        playersNames[row].setEditable(false);
                        playersNames[row].setText("Player " + (row + 1));
                    });
                } else {
                    buttons[i].addActionListener(e -> playersNames[row].setEditable(true));
                }
            }

            playersTypes[n] = buttons;
            playerOptionsPanels[n] = panel;
        }
    }

    /**
     * Η μέθοδος δημιουργεί τον τίτλο του panel.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο.
     */
    private JComponent HeadTitle() {
        JLabel label = new JLabel(panelManager.GetBundle().getString("difficultyPanelTitle"));
        label.setFont(new Font("Verdana", Font.PLAIN, 45));
        label.setForeground(Color.BLACK);
        return label;
    }

    /**
     * Η μέθοδος καλεί τον χρήστη να επιλέξει πόσους παίχτες θέλεις να τοποθετήσει στο παιχνίδι.
     *
     * @return Το αντικείμενο που περιλαμβάνει πόσους παίχτες έχει επιλέξει.
     */
    private JComponent NumberOfPlayers() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(panelManager.GetBundle().getString("difficultyPanelPlayers") + " ");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        label.setForeground(Color.BLACK);
        panel.add(label);

        ButtonGroup group = new ButtonGroup();
        for(int i = 0; i < 4; i++) {
            JRadioButton button = new JRadioButton(String.valueOf(i + 1));
            button.setFont(new Font("Verdana", Font.PLAIN, 20));
            button.setForeground(Color.BLACK);
            button.addActionListener(e -> ChangeRenderedPlayerOptions(Integer.parseInt(e.getActionCommand())));
            if(i == 0) {
                button.setSelected(true);
            }
            group.add(button);
            panel.add(button);
        }
        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί δύο υποτίτλους για τον διαχωρισμό των ρυθμίσεων μεταξύ του παίχτη και του υπολογιστή.
     *
     * @return Το αντικείμενο που περιλαμβάνει τους υποτίτλους.
     */
    private JComponent SubTitles() {
        GridBagConstraints gridBag = new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel titlePlayer = new JLabel(panelManager.GetBundle().getString("difficultyPanelSubTitle1"));
        titlePlayer.setFont(new Font("Verdana", Font.PLAIN, 30));
        titlePlayer.setForeground(new Color(255,69,0));
        gridBag.insets = new Insets(25,0,0,250);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        panel.add(titlePlayer, gridBag);

        JLabel titleCpu = new JLabel(panelManager.GetBundle().getString("difficultyPanelSubTitle2"));
        titleCpu.setFont(new Font("Verdana", Font.PLAIN, 30));
        titleCpu.setForeground(Color.BLUE);
        gridBag.insets = new Insets(25,250,0,0);
        panel.add(titleCpu, gridBag);
        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί τον τίτλο για την μονομαχία και μία λίστα δύο επιλογών, που καλείτε ο χρήστης να επιλέξει
     * αν θα θέλει να ενεργοποιήσει τη μονομαχία ή να την απενεργοποίηση.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο και την επιλογή της για το αν ο χρήστης επέλεξε τη μονομαχία ή όχι.
     */
    private JComponent DuelTitle() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(panelManager.GetBundle().getString("difficultyPanelCTitle") + " ");
        label.setFont(new Font("Verdana", Font.PLAIN, 40));
        label.setForeground(Color.BLACK);
        panel.add(label);
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem(panelManager.GetBundle().getString("difficultyPanelCOff"));
        comboBox.addItem(panelManager.GetBundle().getString("difficultyPanelCOn"));
        comboBox.setFont(new Font("Verdana", Font.PLAIN, 35));
        comboBox.setForeground(Color.BLACK);
        if(duelMode) {
            comboBox.setSelectedIndex(1);
        }
        comboBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                if(e.getItem().equals(panelManager.GetBundle().getString("difficultyPanelCOn"))) {
                    duelMode = true;
                    Render();
                } else {
                    duelMode = false;
                    Render();
                }
            }
        });
        panel.add(comboBox);
        return panel;
    }

    /**
     * Η μέθοδος δημιουργεί τον τίτλο για την επιλογή παιχνιδιού.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο.
     */
    private JComponent TitleGameMode() {
        JLabel label = new JLabel(panelManager.GetBundle().getString("difficultyPanelGM"));
        label.setFont(new Font("Verdana", Font.PLAIN, 40));
        label.setForeground(Color.BLACK);
        return label;
    }

    /**
     * Η μέθοδος δημιουργεί το panel που περιλαμβάνει τα τρία είδη παιχνιδιού.
     *
     * @return Το αντικείμενο που περιλαμβάνει τα κουμπία.
     */
    private JComponent GameButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(GameMode(0));
        buttonPanel.add(GameMode(1));
        buttonPanel.add(GameMode(2));
        return buttonPanel;
    }

    /**
     * Η μέθοδος δημιουργεί το κουμπί για τη έναρξη της μονομαχία.
     *
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent DuelGameButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("difficultyPanelDM"));
        StartGameButton(button, "duel");
        return button;
    }

    /**
     * Η μέθοδος δημιουργεί τo ένα κουμπί που ξεκινάει το παιχνίδι.
     *
     * @param i Ο αριθμός του κουμπιού.
     * @return Το αντικείμενο που περιλαμβάνει το κουμπί.
     */
    private JComponent GameMode(int i) {
        JButton button = new JButton(panelManager.GetBundle().getString("difficultyPanelGM" + (i + 1)));
        StartGameButton(button, String.valueOf(i));
        return button;
    }

    private void StartGameButton(JButton button, String s) {
        button.setFont(new Font("Verdana", Font.PLAIN, 20));
        button.setForeground(Color.BLACK);
        button.setActionCommand(s);
        button.addActionListener(
            e -> new GameModeListener(
                    panelManager,
                    DifficultyForm.ParseData(selectedPlayers, playersTypes, playersNames)
            ).actionPerformed(e)
        );
    }
}
