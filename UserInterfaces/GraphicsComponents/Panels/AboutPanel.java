package UserInterfaces.GraphicsComponents.Panels;

import UserInterfaces.GraphicsComponents.PanelManager;
import UserInterfaces.GraphicsComponents.PanelName;

import javax.swing.*;
import java.awt.*;

/**
 * Η κλάση υλοποιεί το panel που περιέχει τη περιγραφή του παιχνιδιού.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class AboutPanel extends Panel {

    /**
     * Χρησιμοποιεί τον constructor της κλάσης που κληρονομεί.
     *
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public AboutPanel(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public PanelName GetPanelName() {
        return PanelName.ABOUT;
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
     * Η μέθοδος δημιουργεί τον τίτλο του panel.
     *
     * @return Το αντικείμενο που περιλαμβάνει τον τίτλο.
     */
    private JComponent HeadTitle() {
        JLabel aboutLabel = new JLabel(panelManager.GetBundle().getString("aboutPanelTitle"));
        aboutLabel.setFont(new Font("Verdana", Font.PLAIN, 45));
        aboutLabel.setForeground(Color.BLACK);
        return aboutLabel;
    }

    /**
     * Η μέθοδος δημιουργεί το πλαίσιο που περιλαμβάνει το κείμενο με τη περιγραφή του παιχνιδιού.
     *
     * @return Το αντικείμενο που περιλαμβάνει το πλαίσιο με τη περιγραφή του παιχνιδιού.
     */
    private JComponent MainText() {
        JLabel textLabel = new JLabel(panelManager.GetBundle().getString("aboutPanelText"));
        textLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        textLabel.setForeground(Color.BLACK);
        return textLabel;
    }
}
