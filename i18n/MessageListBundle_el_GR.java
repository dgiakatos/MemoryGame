package i18n;

import java.util.ListResourceBundle;

/**
 * Η κλάση περιέχει το περιεχόμενο των κειμένων στα ελληνικά.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class MessageListBundle_el_GR extends ListResourceBundle{

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"frameTitle", "Παιχνίδι μνήμης"},
            {"homePanelTitle", "Παιχνίδι μνήμης"},
            {"homePanelStartB", "Έναρξη"},
            {"homePanelScoreB", "Βαθμολογία"},
            {"homePanelAboutB", "Πιστώσεις"},
            {"homePanelHelpB", "Βοήθεια"},
            {"homePanelExitB", "Έξοδος"},
            {"homePanelChangeLanguage", "Αλλαγή γλώσσας:"},
            {"aboutPanelTitle", "Σχετικά με το παιχνίδι"},
            {"aboutPanelText", "Πρόκειται για ένα έργο που δημιούργησε ο Δημήτρης και ο Θέμης."},
            {"aboutPanelBackB", "Πίσω"},
            {"helpPanelTitle", "Βοήθεια"},
            {"helpPanelText", "Πατήστε το κουμπί Έναρξη για να ξεκινήσετε το παιχνίδι."},
            {"helpPanelBackB", "Πίσω"},
            {"scorePanelTitle", "Επιλέξτε ποιο βαθμολογία θέλετε να δείτε:"},
            {"genericBackButton", "Πίσω"},
            {"cpuMode1", "Ένας παίκτης"},
            {"cpuMode2", "Χρυσόψαρο"},
            {"cpuMode3", "Καγκουρό"},
            {"cpuMode4", "Ελέφαντας"},
            {"difficultyPanelTitle", "Επιλέξτε Δυσκολία"},
            {"difficultyPanelPlayers", "Επιλέξτε Παίκτες:"},
            {"difficultyPanelSubTitle1", "Παίχτης"},
            {"difficultyPanelSubTitle2", "Υπολογιστής"},
            {"difficultyPanelCTitle", "Μονομαχία:"},
            {"difficultyPanelCOn", "Ενεργοποιημένο"},
            {"difficultyPanelCOff", "Απενεργοποιημένο"},
            {"difficultyPanelGM", "Λειτουργία παιχνιδιού"},
            {"difficultyPanelDM", "Έναρξη μονομαχίας"},
            {"difficultyPanelGM1", "2 αντίγραφα των 12 διαφορετικών καρτών"},
            {"difficultyPanelGM2", "2 αντίγραφα των 24 διαφορετικών καρτών"},
            {"difficultyPanelGM3", "3 αντίγραφα των 12 διαφορετικών καρτών"},
            {"scorePanelS0", "Σειρά"},
            {"scorePanelS1", "Ονόματα"},
            {"scorePanelS2", "Καλύτερα Βήματα"},
            {"scorePanelM0", "Σειρά"},
            {"scorePanelM1", "Ονόματα"},
            {"scorePanelM2", "Νίκες"},
            {"gamePanelCP", "Παίζει τώρα:"},
            {"gamePanelCPDB", "Παίζει τώρα στο μπλε τραπέζι ο/η:"},
            {"gamePanelCPDR", "Παίζει τώρα στο κόκκινο τραπέζι ο/η:"},
            {"gamePanelSt", "Βήματα"},
            {"gamePanelSc", "Βαθμολογία"},
            {"gamePanelWinS", "Το παιχνίδι τελείωσε!"},
            {"gamePanelWinD", "Το παιχνίδι είναι ισοπαλία!"},
            {"gamePanelWinM", "Ο/Η νικητής/τρια είναι ο/η"},
            {"scorePanelSB0", "Ατομική -> 2 αντίγραφα των 12 διαφορετικών καρτών"},
            {"scorePanelSB1", "Ατομική -> 2 αντίγραφα των 24 διαφορετικών καρτών"},
            {"scorePanelSB2", "Ατομική -> 3 αντίγραφα των 12 διαφορετικών καρτών"},
            {"scorePanelSB3", "Πολλαπλών παικτών -> 2 αντίγραφα των 12 διαφορετικών καρτών"},
            {"scorePanelSB4", "Πολλαπλών παικτών -> 2 αντίγραφα των 24 διαφορετικών καρτών"},
            {"scorePanelSB5", "Πολλαπλών παικτών -> 3 αντίγραφα των 12 διαφορετικών καρτών"},
            {"scorePanelSB6", "Μονομαχία"}
    };
}
