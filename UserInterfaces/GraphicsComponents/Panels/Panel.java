package UserInterfaces.GraphicsComponents.Panels;

import UserInterfaces.GraphicsComponents.PanelManager;
import UserInterfaces.GraphicsComponents.PanelName;

import javax.swing.*;
import java.awt.*;

/**
 * Υλοποιεί οποιοδήποτε panel/παράθυρο της εφαρμογής
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public abstract class Panel extends JPanel {
    PanelManager panelManager;

    /**
     * Δημιουργεί και ρυθμίσει το panel να χρησιμοποιεί το GridBagLayout.
     * @param panelManager Αντικείμενο της κλάσης PanelManager.
     */
    public Panel(PanelManager panelManager) {
        super(new GridBagLayout());
        this.panelManager = panelManager;
    }


    /**
     * @return Το όνομα του panel
     */
    public abstract PanelName GetPanelName();

    /**
     * Χρησιμοποιείται για να προσθέσει στο Panel τα Components που το αποτελούν.
     * Τα components αυτά ξαναδημιουργούνται με κάθε κλήση της μεθόδου για να φανούν αλλαγές στην οθόνη.
     */
    public abstract void Render();

    /**
     * Προσθέτει στο JPanel τα components που δίνονται το ένα κάτω από το άλλο.
     * @param components Τα components που πρέπει να προστεθούν στο panel
     */
    void RenderRowsOfComponents(JComponent[] components) {
        RenderRowsOfComponents(components, null);
    }

    /**
     * Προσθέτει στο JPanel τα components που δίνονται το ένα κάτω από το άλλο.
     * Επιτρέπει επίσης να δωθεί ένα offset στην θέση των components.
     * @param components Τα components που πρέπει να προστεθούν στο panel
     * @param insets Offset των components
     */
    void RenderRowsOfComponents(JComponent[] components, Insets[] insets) {
        removeAll();
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.gridx = 1;
        gridBag.gridy = 0;
        for(int i = 0; i < components.length; i++) {
            if(insets != null && i < insets.length && insets[i] != null) {
                gridBag.insets = insets[i];
            } else {
                gridBag.insets = new Insets(25, 0, 0, 0);
            }
            add(components[i], gridBag);
            gridBag.gridy++;
        }
        updateUI();
    }
}
