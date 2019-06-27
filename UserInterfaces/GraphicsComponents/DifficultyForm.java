package UserInterfaces.GraphicsComponents;

import javax.swing.*;

/**
 * Κλάση που κρατάει τις εισόδους που δώθηκαν στην οθόνη επιλογής δυσκολίας.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @author Χατζηεμμανουήλ Θεμιστοκλής
 * @version 1.0.0
 */
public class DifficultyForm {

    public int[] playersTypes;
    public String[] playersNames;

    /**
     * Δημιουργία της νέας φόρμας.
     * @param playerLength Ο αριθμός των παικτών για τον καθορισμό μεγέθους της φόρμας
     */
    public DifficultyForm(int playerLength) {
        playersTypes = new int[playerLength];
        playersNames = new String[playerLength];
    }

    /**
     * Δημιουργία της φόρμας κάνοντας εξαγωγή δεδομένων απευθείας από τα components εισαγωγής.
     * @param playerLength Ο αριθμός των παικτών
     * @param playersTypes Τα RadioButtons επιλογής τύπου παικτών
     * @param playersNames Τα TextField που έχουν τα ονόματα των παικτών
     * @return Την φόρμα με τα δεδομένα
     */
    public static DifficultyForm ParseData(int playerLength, JRadioButton[][] playersTypes, JTextField[] playersNames) {
        DifficultyForm form = new DifficultyForm(playerLength);
        for(int i = 0; i < playerLength; i++) {
            form.playersNames[i] = playersNames[i].getText();
            for(int j = 0; j < 4; j++) {
                if(playersTypes[i][j].isSelected()) {
                    form.playersTypes[i] = j;
                    break;
                }
            }
        }
        return form;
    }

}
