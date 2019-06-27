package i18n;

import java.util.ListResourceBundle;

/**
 * Η κλάση περιέχει το περιεχόμενο των κειμένων στα αγγλικά.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class MessageListBundle_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"frameTitle", "Memory Game"},
            {"homePanelTitle", "Memory Game"},
            {"homePanelStartB", "Start"},
            {"homePanelScoreB", "Score"},
            {"homePanelAboutB", "Credits"},
            {"homePanelHelpB", "Help"},
            {"homePanelExitB", "Exit"},
            {"homePanelChangeLanguage", "Change Language:"},
            {"aboutPanelTitle", "About"},
            {"aboutPanelText", "This is a project created by Dimitris and Themis."},
            {"helpPanelTitle", "Help"},
            {"helpPanelText", "Press Start button to start the game."},
            {"scorePanelTitle", "Choose what score do you want to see:"},
            {"genericBackButton", "Back"},
            {"cpuMode1", "Solo"},
            {"cpuMode2", "Goldfish"},
            {"cpuMode3", "Kangaroo"},
            {"cpuMode4", "Elephant"},
            {"difficultyPanelTitle", "Choose Difficulty"},
            {"difficultyPanelPlayers", "Choose Players:"},
            {"difficultyPanelSubTitle1", "Player"},
            {"difficultyPanelSubTitle2", "Cpu"},
            {"difficultyPanelCTitle", "Duel Mode:"},
            {"difficultyPanelCOn", "Enabled"},
            {"difficultyPanelCOff", "Disabled"},
            {"difficultyPanelGM", "Game Mode"},
            {"difficultyPanelDM", "Starting a duel"},
            {"difficultyPanelGM1", "2 copies of 12 different cards"},
            {"difficultyPanelGM2", "2 copies of 24 different cards"},
            {"difficultyPanelGM3", "3 copies of 12 different cards"},
            {"scorePanelS0", "Rank"},
            {"scorePanelS1", "Names"},
            {"scorePanelS2", "Best Steps"},
            {"scorePanelM0", "Rank"},
            {"scorePanelM1", "Names"},
            {"scorePanelM2", "Wins"},
            {"gamePanelCP", "Currently playing:"},
            {"gamePanelCPDB", "Currently playing in blue table:"},
            {"gamePanelCPDR", "Currently playing in red table:"},
            {"gamePanelSt", "Steps"},
            {"gamePanelSc", "Score"},
            {"gamePanelWinS", "The game is finished!"},
            {"gamePanelWinD", "The game is draw!"},
            {"gamePanelWinM", "The winner is"},
            {"scorePanelSB0", "Solo mode -> 2 copies of 12 different cards"},
            {"scorePanelSB1", "Solo mode -> 2 copies of 24 different cards"},
            {"scorePanelSB2", "Solo mode -> 3 copies of 12 different cards"},
            {"scorePanelSB3", "Multiplayer mode -> 2 copies of 12 different cards"},
            {"scorePanelSB4", "Multiplayer mode -> 2 copies of 24 different cards"},
            {"scorePanelSB5", "Multiplayer mode -> 3 copies of 12 different cards"},
            {"scorePanelSB6", "Duel mode"}
    };
}
