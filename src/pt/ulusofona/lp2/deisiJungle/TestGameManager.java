package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameManager {
    @Test
    public void test01_createInitialJungle() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        InitializationError createJungle = gameManager.createInitialJungle(10, playersInfo, foodsInfo);
        assertEquals(null, createJungle);
    }

    @Test
    public void test02_createInitialJungle() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        InitializationError createJungle = gameManager.createInitialJungle(10, playersInfo);
        assertEquals(null, createJungle);
    }

    @Test
    public void test01_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(10, playersInfo, foodsInfo);
        MovementResult movePlayer = gameManager.moveCurrentPlayer(7, true);
        assertEquals(new MovementResult(MovementResultCode.VALID_MOVEMENT, null), movePlayer);
    }

    @Test
    public void test02_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[3][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "JogadorTeste3";
        playersInfo[2][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(0, false); //Bird
        gameManager.moveCurrentPlayer(18, true); //Elefante
        assertEquals("1", gameManager.winner.getId());
    }

    @Test
    public void test03_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        MovementResult outputMovement = gameManager.moveCurrentPlayer(-7, false);
        gameManager.moveCurrentPlayer(18, true); //Elefante
        assertEquals(new MovementResult(MovementResultCode.INVALID_MOVEMENT, null), outputMovement);
    }

    @Test
    public void test04_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(6, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(-6, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(6, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(-6, false); //Lion
        gameManager.moveCurrentPlayer(2, true); //Elefante
        gameManager.moveCurrentPlayer(6, false); //Lion
        gameManager.moveCurrentPlayer(2, true); //Elefante
        gameManager.moveCurrentPlayer(-6, false); //Lion
        gameManager.moveCurrentPlayer(2, true); //Elefante
        MovementResult outputMovement = gameManager.moveCurrentPlayer(6, false); //Lion
        assertEquals(new MovementResult(MovementResultCode.NO_ENERGY, null), outputMovement);
    }

    @Test
    public void test05_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "T";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "L";

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "5";

        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(5, false); //Turtle
        MovementResult outputMovement = gameManager.moveCurrentPlayer(4, false); //Lion
        assertEquals(new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne"), outputMovement);
    }

    @Test
    public void test01_getGameResults() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "27";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(4, false); //Elefante
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(4, false); //Elefante
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(4, false); //Elefante
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(4, true); //Elefante

        ArrayList<String> gameResults = gameManager.getGameResults();
        String finalGameResults = "";
        finalGameResults += gameResults.get(0);
        finalGameResults += ", ";
        finalGameResults += gameResults.get(1);

        assertEquals("#1 JogadorTeste1, Leao, 1, 0, 0, #2 JogadorTeste2, Elefante, 17, 16, 0", finalGameResults);
    }
    @Test
    public void getAuthorsPanel() {
        GameManager gameManager = new GameManager();
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("<html>Developed by:<br/>- António Antunes<br/>- " +
                "João Serralha<br/><br/>Lusófona Informática</html>");
        panel.add(jlabel);

        assertEquals(panel.toString(), gameManager.getAuthorsPanel().toString());
    }

    @Test
    public void whoIsTaborda() {
        GameManager gameManager = new GameManager();

        assertEquals("professional wrestling", gameManager.whoIsTaborda());
    }
}