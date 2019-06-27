package MemoryGame;

import java.io.*;
import java.util.ArrayList;

/**
 * Η κλάση διαχειρίζεται το αρχείο που αποθηκεύονται τα δεδομένα.
 *
 * @author Δημήτριος Παντελεήμων Γιακάτος
 * @version 1.0.0
 */
public class FileManager {

    private String file;
    private String path;

    /**
     * Ο μέθοδος δημιουργεί το φάκελο που θα αποθηκευτεί το αρχείο δεδομένων.
     *
     * @param file Το όνομα του αρχείου.
     * @param path Τη διεύθυνση που θα αποθηκευτεί το αρχείο.
     */
    public FileManager(String file, String path) {
        this.file = file;
        this.path = path;

        new File(this.path).mkdir();

    }

    /**
     * Η μέθοδος αποθηκεύει τα δεδομένα στο αρχείο, αφού ελέγξει πρώτα αν τα δεδομένα που πρόκειται να αποθηκευτούν
     * είναι πολλαπλάσια του δύο. Αυτό συμβαίνει επειδή η παράμετρος που δέχεται η μέθοδος είναι ένας πίνακας
     * χαρακτήρων που περιλαμβάνει το όνομα και τα βήματα που έκανε ένα παίχτης. Αν ο παίχτης παίζει για πρώτη φορά
     * τότε οι νίκες αρχικοποιούνται σε 1, αλλιώς αυξάνονται κατά 1. Τα αποθηκεύει σε μία δομή
     * δεδομένων και τέλος εξάγει τη δομή στο αρχείο.
     *
     * @param str Πίνακας χαρακτήρων που προλαμβάνει το όνομα και τα βήματα, δηλαδή str[0]=”name” και str[1]=”2”.
     */
    void SetData(String[] str) {
        if(str.length%2 != 0) {
            return;
        }
        boolean flag;
        ArrayList<FileForm> contents = Input();
        FileForm[] listContents = new FileForm[str.length / 2];
        String[][] subStr = new String[str.length / 2][2];
        for(int i = 0; i < subStr.length; i++) {
            subStr[i][0] = str[2*i];
            subStr[i][1] = str[2*i+1];
        }
        for(int i = 0; i < subStr.length; i++) {
            flag = true;
            for (FileForm content : contents) {
                if (subStr[i][0].equals(content.getName())) {
                    content.increaseWins();
                    content.setSteps(subStr[i][1]);
                    flag = false;
                    break;
                }
            }
            if(flag) {
                listContents[i] = new FileForm();
                listContents[i].setName(subStr[i][0]);
                listContents[i].setWins("1");
                listContents[i].setSteps(subStr[i][1]);
                contents.add(listContents[i]);
            }
        }
        Output(contents);
    }

    /**
     * Η μέθοδος δέχεται ένα όνομα, ελέγχει αν υπάρχει στο αρχείο και επιστρέφει το όνομα, τις νίκες και τα βήματα του.
     *
     * @param str Το όνομα.
     * @return Ένα πίνακα χαρακτήρων μίας διάστασης που περιλαμβάνει το όνομα, τις νίκες και τα βήματα.
     */
    String[] GetSpecificData(String str) {
        ArrayList<FileForm> contents = Input();
        String[] data = new String[3];
        for (FileForm content : contents) {
            if (str.equals(content.getName())) {
                data[0] = content.getName();
                data[1] = content.getWins();
                data[2] = content.getSteps();
                break;
            }
        }
        return data;
    }

    /**
     * Η μέθοδος εξάγει τα δεδομένα που είναι αποθηκευμένα στο αρχείο σε ένα πίνακα μίας διάστασης.
     * Πιο συγκεκριμένα εξάγει τα ονόματα και τις νίκες των παιχτών.
     *
     * @return Πίνακα μιας διάστασης με τα ονόματα και τις νίκες των παιχτών.
     */
    public String[] ExportWinsToStringMatrix() {
        ArrayList<FileForm> contents = SortForWins(Input());
        String[] str = new String[contents.size() * 2];
        int j = 0;
        for (FileForm content : contents) {
            str[j] = content.getName();
            j++;
            str[j] = content.getWins();
            j++;
        }
        return str;
    }

    /**
     * Η μέθοδος εξάγει τα δεδομένα που είναι αποθηκευμένα στο αρχείο σε ένα πίνακα μίας διάστασης.
     * Πιο συγκεκριμένα εξάγει τα ονόματα και τα βήματα των παιχτών.
     *
     * @return Πίνακα μιας διάστασης με τα ονόματα και τα βήματα των παιχτών.
     */
    public String[] ExportStepsToStringMatrix() {
        ArrayList<FileForm> contents = SortForSteps(Input());
        String[] str = new String[contents.size() * 2];
        int j = 0;
        for (FileForm content : contents) {
            str[j] = content.getName();
            j++;
            str[j] = content.getSteps();
            j++;
        }
        return str;
    }

    /**
     * Η μέθοδος εξάγει τη δομή δεδομένων που περιλαμβάνει τα ονόματα, τις νίκες και τα βήματα των παιχτών στο αρχείο.
     *
     * @param str Δομή δεδομένων που περιλαμβάνει τα ονόματα, τις νίκες και τα βήματα των παιχτών
     */
    private void Output(ArrayList<FileForm> str){
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path + file, false)))) {
            for (FileForm fileForm : str) {
                out.writeUTF(fileForm.getName());
                out.writeUTF(fileForm.getWins());
                out.writeUTF(fileForm.getSteps());
            }
        } catch (Exception ignored) {

        }
    }

    /**
     * Η μέθοδος μετράει το μέγεθος τους αρχείου.
     *
     * @return Το μέγεθος τους αρχείου.
     */
    private int FileSize() {
        int lines = 0;
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path + file)))) {
            while((in.readUTF()) != null) {
                lines++;
            }
        } catch (Exception ignored) {

        }
        return lines;
    }

    /**
     * Η μέθοδος διαβάζει το αρχείο και εισάγει τα δεδομένα, δηλαδή τα ονόματα, τις νίκες και τα βήματα των παιχτών
     * σε μία δομή.
     *
     * @return Τη δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών
     */
    private ArrayList<FileForm> Input() {
        int count = 0;
        String str;
        FileForm[] form = new FileForm[FileSize() / 3];
        ArrayList<FileForm> contents = new ArrayList<>();
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(path + file)))) {
           while((str = in.readUTF()) != null) {
               form[count] = new FileForm();
               form[count].setName(str);
               if((str = in.readUTF()) != null) {
                   form[count].setWins(str);
               } else {
                   break;
               }
               if((str = in.readUTF()) != null) {
                   form[count].setSteps(str);
               } else {
                   break;
               }
               contents.add(form[count]);
               count++;
            }
        } catch(Exception ignored) {

        }
        return contents;
    }

    /**
     * Η μέθοδος ταξινομεί τη δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών κατά φθίνουσα σειρά με
     * βάση τις νίκες.
     *
     * @param str Δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών.
     * @return Τη ταξινομημένη δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών.
     */
    private ArrayList<FileForm> SortForWins(ArrayList<FileForm> str) {
        for(int i = 0; i < str.size(); i++) {
            for(int j = 1; j < str.size() - i; j++) {
                if(Integer.valueOf(str.get(j-1).getWins()) < Integer.valueOf(str.get(j).getWins())) {
                    Swap(str, j-1, j);
                }
            }
        }
        return str;
    }

    /**
     * Η μέθοδος ταξινομεί τη δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών κατά αύξουσα σειρά με
     * βάση τα βήματα.
     *
     * @param str Δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών.
     * @return Τη ταξινομημένη δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών.
     */
    private ArrayList<FileForm> SortForSteps(ArrayList<FileForm> str) {
        for(int i = 0; i < str.size(); i++) {
            for(int j = 1; j < str.size() - i; j++) {
                if(Integer.valueOf(str.get(j-1).getSteps()) > Integer.valueOf(str.get(j).getSteps())) {
                    Swap(str, j-1, j);
                }
            }
        }
        return str;
    }

    /**
     * Η μέθοδος υλοποιεί την αντιμετάθεση μεταξύ δύο στοιχείων.
     *
     * @param str Δομή δεδομένων με τα ονόματα, τις νίκες και τα βήματα των παιχτών.
     * @param i Τη θέση του πρώτου στοιχείου στη δομή.
     * @param j Τη θέση του δεύτερου στοιχείου στη δομή.
     */
    private void Swap(ArrayList<FileForm> str, int i, int j) {
        String tempN;
        String tempW;
        String tempS;
        tempN = str.get(i).getName();
        str.get(i).setName(str.get(j).getName());
        str.get(j).setName(tempN);
        tempW = str.get(i).getWins();
        str.get(i).setWins(str.get(j).getWins());
        str.get(j).setWins(tempW);
        tempS = str.get(i).getSteps();
        str.get(i).setSteps(str.get(j).getSteps());
        str.get(j).setSteps(tempS);
    }
}
