package UserInterfaces.GraphicsComponents;

import MemoryGame.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Υλοποιήση κάποιων components που χρησιμοποιούνται σε πάνω από ένα panel.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class GenericComponents {

    private PanelManager panelManager;

    private final ImageIcon[] cardImages = new ImageIcon[33];

    /**
     * Ετοιμάζει το αντικείμενο για παραγωγή γραφικών components.
     * Φορτώνει τις εικόνες από τον δίσκο
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager
     */
    GenericComponents(PanelManager panelManager) {
        this.panelManager = panelManager;
        LoadImages();
    }

    /**
     * @return Κουμπί που οδηγεί πίσω στην κεντρική οθόνη
     */
    public JComponent BackButton() {
        JButton button = new JButton(panelManager.GetBundle().getString("genericBackButton"));
        button.setFont(new Font("Verdana", Font.PLAIN, 20));
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> panelManager.SwitchDisplayedPanel(PanelName.HOME));
        return button;
    }

    /**
     * Δημιουργεί το ταμπλό για εμφάνιση σε γραφικό περιβάλλον. Οι κάρτες είναι υλοποιημένες σαν κουμπιά και το actionCommand
     * είναι ο αριθμός της κάρτας πάνω στο ταμπλό ξεκινώντας την μέτρηση από πάνω αριστερά.
     *
     * @param cards Οι κάρτες πάνω στο ταμπλό
     * @param listener Ο listener που θα έχει η κάθε κάρτα
     * @return Το panel που περιέχει το ταμπλό
     */
    public JComponent GenerateTable(Card[][] cards, ActionListener listener) {
        JPanel panel = new JPanel(new GridLayout(cards.length, cards[0].length, 10, 10));
        for(int row = 0; row < cards.length; row++) {
            for(int column = 0; column < cards[0].length; column++) {
                JButton currentCardButton = GenerateCardButton(String.valueOf(row * cards[0].length + column), listener);
                if(cards[row][column] != null) {
                    if(!cards[row][column].display) {
                        currentCardButton.setIcon(cardImages[0]);
                        currentCardButton.setFocusPainted(true);
                        currentCardButton.setBorderPainted(true);
                    } else {
                        currentCardButton.setIcon(cardImages[cards[row][column].id + 1]);
                    }
                } else {
                    currentCardButton.setIcon(null);
                    currentCardButton.removeActionListener(listener);
                }
                panel.add(currentCardButton);
            }
        }
        return panel;
    }

//https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
    private void LoadImages() {
        for(int i = 0; i < 33; i++) {
            URL imgUrl = getClass().getResource("images/" + i + ".png");
            cardImages[i] = new ImageIcon(
                new ImageIcon(imgUrl).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)
            );
        }
    }

    private JButton GenerateCardButton(String actionCommand, ActionListener listener) {
        JButton button = new JButton();
        button.setFont(new Font("Verdana", Font.PLAIN, 25));
        button.setForeground(Color.BLACK);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        return button;
    }
}
