package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void test03_createInitialJungle() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "T";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        InitializationError createJungle = gameManager.createInitialJungle(10, playersInfo);
        assertEquals(null, createJungle);
    }

    @Test
    public void test01_getPlayerIds() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(4, false);

        int[] playersIds = gameManager.getPlayerIds(4);

        assertEquals(new int[0].length, playersIds.length);
    }

    @Test
    public void test02_getPlayerIds() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(4, false);

        int[] playersIds = gameManager.getPlayerIds(5);

        assertEquals(1, playersIds[0]);
    }

    @Test
    public void test01_getPlayerInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);

        String[] playerInfo = gameManager.getPlayerInfo(1);

        assertEquals("[1, JogadorTeste1, L, 80, 4..6]", Arrays.toString(playerInfo));
    }

    @Test
    public void test02_getPlayerInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);

        String[] playerInfo = gameManager.getPlayerInfo(3);

        assertEquals(null, playerInfo);
    }

    @Test
    public void test01_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "5";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "6";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "7";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(0);

        assertEquals(null, squareInfo);
    }

    @Test
    public void test02_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "5";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "6";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "7";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(30);

        assertEquals("[finish.png, Meta, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test03_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "5";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "6";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "7";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(3);

        assertEquals("[image3.png, Vazio, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test04_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[6][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "5";

        foodsInfo[2][0] = "c";
        foodsInfo[2][1] = "6";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "7";

        foodsInfo[4][0] = "b";
        foodsInfo[4][1] = "8";

        foodsInfo[5][0] = "m";
        foodsInfo[5][1] = "8";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(2);

        assertEquals("[image2.png, Vazio, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test05_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "5";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "6";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "7";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(11);

        assertEquals("[image1.png, Vazio, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test06_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "6";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "7";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "9";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        gameManager.moveCurrentPlayer(4, false);

        String[] squareInfo = gameManager.getSquareInfo(5);

        assertEquals("[image1.png, Vazio, 1]", Arrays.toString(squareInfo));
    }

    @Test
    public void test07_getSquareInfo() { //Line 238
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "6";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "7";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "9";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        gameManager.moveCurrentPlayer(4, false);
        gameManager.moveCurrentPlayer(7, true);

        String[] squareInfo1 = gameManager.getSquareInfo(5);
        String[] squareInfo2 = gameManager.getSquareInfo(8);
        String[] finalSquareInfo = new String[squareInfo1.length + squareInfo2.length];
        System.arraycopy(squareInfo1, 0, finalSquareInfo, 0, squareInfo1.length);
        System.arraycopy(squareInfo2, 0, finalSquareInfo, squareInfo1.length, squareInfo2.length);

        assertEquals("[image1.png, Vazio, 1, image2.png, Vazio, 2]", Arrays.toString(finalSquareInfo));
    }

    @Test
    public void test08_getSquareInfo() { //Line 238
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "6";

        foodsInfo[2][0] = "a";
        foodsInfo[2][1] = "7";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "9";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(4);

        assertEquals("[grass.png, Erva : +- 20 energia, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test09_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "6";

        foodsInfo[2][0] = "c";
        foodsInfo[2][1] = "7";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "9";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(4);

        assertEquals("[meat.png, Carne : + 50 energia : 0 jogadas, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test10_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "28";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "6";

        foodsInfo[2][0] = "c";
        foodsInfo[2][1] = "7";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "9";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(0, false); //Lion
        gameManager.moveCurrentPlayer(2, true); //Elefante
        gameManager.moveCurrentPlayer(2, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante
        gameManager.moveCurrentPlayer(2, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Elefante

        String[] squareInfo = gameManager.getSquareInfo(28);

        assertEquals("[meat.png, Carne : + 50 energia : 12 jogadas, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test11_getSquareInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[4][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "28";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "6";

        foodsInfo[2][0] = "b";
        foodsInfo[2][1] = "7";

        foodsInfo[3][0] = "a";
        foodsInfo[3][1] = "9";
        gameManager.createInitialJungle(30, playersInfo, foodsInfo);

        String[] squareInfo = gameManager.getSquareInfo(7);

        assertEquals("[bananas.png, Bananas : 3 : + 40 energia, ]", Arrays.toString(squareInfo));
    }

    @Test
    public void test_getCurrentPlayerInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);

        String[] playerInfo = gameManager.getCurrentPlayerInfo();

        assertEquals("[1, JogadorTeste1, L, 80, 4..6]", Arrays.toString(playerInfo));
    }

    @Test
    public void test_getCurrentPlayerEnergyInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][7];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "2";
        playersInfo[0][5] = "10";
        playersInfo[0][6] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "4";
        playersInfo[1][5] = "10";
        playersInfo[1][6] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);

        String[] playerInfo = gameManager.getCurrentPlayerEnergyInfo(2);

        assertEquals("[4, 10]", Arrays.toString(playerInfo));
    }

    @Test
    public void test_getPlayersInfo() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][5];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        playersInfo[0][3] = "80";
        playersInfo[0][4] = "4..6";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        playersInfo[1][3] = "180";
        playersInfo[1][4] = "1..6";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);

        String[][] playerInfo = gameManager.getPlayersInfo();

        assertEquals("[[1, JogadorTeste1, L, 80, 4..6], [2, JogadorTeste2, E, 180, 1..6]]", Arrays.deepToString(playerInfo));
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
        assertEquals("1", gameManager.getWinnerInfo()[0]);
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
        gameManager.moveCurrentPlayer(5, false); //Tarzan
        MovementResult outputMovement = gameManager.moveCurrentPlayer(4, false); //Turtle
        assertEquals(new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne"), outputMovement);
    }

    @Test
    public void test06_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "T";

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";

        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(4, false);
        MovementResult outputMovement = gameManager.moveCurrentPlayer(3, false);
        assertEquals(new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Agua"), outputMovement);
    }

    @Test
    public void test07_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[1][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "T";

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "4";

        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(4, false);
        MovementResult outputMovement = gameManager.moveCurrentPlayer(3, false);
        assertEquals(new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Bananas"), outputMovement);
    }

    @Test
    public void test08_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[2][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "T";

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "4";

        foodsInfo[1][0] = "b";
        foodsInfo[1][1] = "7";
        gameManager.createInitialJungle(31, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(3, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        MovementResult outputMovement = gameManager.moveCurrentPlayer(3, false); //Tarzan
        assertEquals(new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Bananas"), outputMovement);
    }

    @Test
    public void test09_moveCurrentPlayer() {
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        String[][] foodsInfo = new String[2][2];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "T";

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "c";
        foodsInfo[1][1] = "33";
        gameManager.createInitialJungle(35, playersInfo, foodsInfo);
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(4, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(4, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(4, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Tarzan
        gameManager.moveCurrentPlayer(4, false); //Lion
        gameManager.moveCurrentPlayer(2, false); //Tarzan
        MovementResult outputMovement = gameManager.moveCurrentPlayer(4, false); //Lion

        assertEquals(new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne"), outputMovement);
    }

    @Test
    public void test01_getWinnerInfo() {
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

        String[] gameResults = gameManager.getWinnerInfo();
        String finalGameResults = "";
        finalGameResults += gameResults[0] + ", " + gameResults[1] + ", " + gameResults[2] + ", " + gameResults[3] + ", " + gameResults[4];

        assertEquals("1, JogadorTeste1, L, 120, 4..6", finalGameResults);
    }

    @Test
    public void test02_getWinnerInfo() {
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

        String[] gameResults = gameManager.getWinnerInfo();

        assertEquals(null, gameResults);
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

    @Test
    public void getMessageInitializationError() {
        InitializationError error = new InitializationError("ERRO");

        assertEquals("ERRO", error.getMessage());
    }
}