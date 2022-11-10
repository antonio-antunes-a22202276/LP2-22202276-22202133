package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestGameManager {
    @Test
    public void test01_moveCurrentPlayer() {
        //Test if the dice number is not correct
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "3";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";

        GameManager gameManager = new GameManager();
        gameManager.createInitialJungle(15,10,playersInfo);
        boolean movePlayer = gameManager.moveCurrentPlayer(8,false);
        assertFalse(movePlayer);
    }

    @Test
    public void test02_moveCurrentPlayer() {
        //Test if the dice number can bypass
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "3";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";

        GameManager gameManager = new GameManager();
        gameManager.createInitialJungle(15,10,playersInfo);
        boolean movePlayer = gameManager.moveCurrentPlayer(8,true);
        assertTrue(movePlayer);
    }

    @Test
    public void test03_moveCurrentPlayer() {
        //Test if the dice number is valid
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "3";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";

        GameManager gameManager = new GameManager();
        gameManager.createInitialJungle(15,10,playersInfo);
        boolean movePlayer = gameManager.moveCurrentPlayer(4,false);
        assertTrue(movePlayer);
    }

    @Test
    public void test04_moveCurrentPlayer() {
        //Test if player without energy can play
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "3";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";

        GameManager gameManager = new GameManager();
        gameManager.createInitialJungle(15,10,playersInfo);
        boolean movePlayer = gameManager.moveCurrentPlayer(4,false);
        assertTrue(movePlayer);
    }
}
