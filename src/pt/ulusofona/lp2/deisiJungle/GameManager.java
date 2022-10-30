package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {
    public GameManager() {}

    public String[][] getSpecies() {
        //Creates the default species data
        String[][] species = new String[5][3];

        //SpecieId
        //SpecieName
        //SpecieImageFileName
        species[0][0] = "E";
        species[0][1] = "Elefante";
        species[0][2] = "elephant.png";

        species[1][0] = "L";
        species[1][1] = "Leão";
        species[1][2] = "lion.png";

        species[2][0] = "T";
        species[2][1] = "Tartaruga";
        species[2][2] = "turtle.png";

        species[3][0] = "P";
        species[3][1] = "Pássaro";
        species[3][2] = "bird.png";

        species[4][0] = "Z";
        species[4][1] = "Tarzan";
        species[4][2] = "tarzan.png";

        return species;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {
        return false;
    }

    public int[] getPlayerIds(int squareNr) {
        return null;
    }

    public String[] getSquareInfo(int squareNr) {
        return null;
    }

    public String[] getPlayerInfo(int playerId) {
        return null;
    }

    public String[] getCurrentPlayerInfo() {
        return null;
    }

    public String[][] getPlayersInfo() {
        return null;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        return false;
    }

    public String[] getWinnerInfo() {
        return null;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }

    public String whoIsTaborda() {
        return null;
    }
}
