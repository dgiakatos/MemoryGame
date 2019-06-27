package UserInterfaces;

import UserInterfaces.GraphicsComponents.PanelManager;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Η κλάση δημιουργεί το κεντρικό παράθυρο του παιχνιδιού και εκκινεί τον PanelManager.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class Frame {

    private JFrame frame;

    /**
     * Η μέθοδος δημιουργεί το κεντρικό παράθυρο του παιχνιδιού και τις βασικές του ρυθμίσεις.
     */
    public Frame() {
        Locale locale = new Locale("en", "US");
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.MessageListBundle", locale);
        frame = new JFrame(bundle.getString("frameTitle"));
        frame.setSize(1600, 1000);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        new PanelManager(frame, bundle);
    }

    /**
     * Η μέθοδος εμφανίζει το κετρικό παράθυρο.
     */
    public void Display() {
        frame.setVisible(true);
    }
}

