package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

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
        InitializationError createJungle = gameManager.createInitialJungle(10,playersInfo,foodsInfo);
        //System.out.println(createJungle.getMessage());
        assertEquals(null, createJungle);
    }

    @Test
    public void test01_moveCurrentPlayer(){
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
        gameManager.createInitialJungle(10,playersInfo,foodsInfo);
        MovementResult movePlayer = gameManager.moveCurrentPlayer(7,true);
        assertEquals(new MovementResult(MovementResultCode.VALID_MOVEMENT, null), movePlayer);
    }

    @Test
    public void test02_moveCurrentPlayer(){
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
        gameManager.createInitialJungle(31,playersInfo,foodsInfo);

        //ArrayList<Player> players = new ArrayList<>();
        //players.add(new Player(playersInfo[0][0], playersInfo[0][1], new Specie("L", "Leão", "lion.png", "10", "2", "10", "4..6", "C"), 1));
        //players.add(new Player(playersInfo[1][0],playersInfo[1][1],new Specie("E","Elefante","elephant.png","180","4","10","1..6","H"), 1));



        gameManager.moveCurrentPlayer(0,false); //Lion
        gameManager.moveCurrentPlayer(16,true); //Elefante
        assertEquals(null,gameManager.getWinnerInfo());
    }
/*
    @Test
    public void test02_createInitialJungle() {
        //Test if the program will not crash with playersInfo empty
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test03_createInitialJungle() {
        //Test if the game is not created with 1 player (minimum players required not completed)
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[1][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test04_createInitialJungle() {
        //Test if the game is created correctly with 2 players
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test05_createInitialJungle() {
        //Test if the game is created correctly with 3 players
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[3][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "JogadorTeste3";
        playersInfo[2][2] = "P";
        boolean createJungle = gameManager.createInitialJungle(6,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test06_createInitialJungle() {
        //Test if the game is created correctly with 4 players
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[4][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "JogadorTeste3";
        playersInfo[2][2] = "P";

        playersInfo[3][0] = "4";
        playersInfo[3][1] = "JogadorTeste4";
        playersInfo[3][2] = "P";
        boolean createJungle = gameManager.createInitialJungle(8,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test07_createInitialJungle() {
        //Test if the game is not created with 5 players (exceeding players limit)
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[5][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "JogadorTeste3";
        playersInfo[2][2] = "9";

        playersInfo[3][0] = "4";
        playersInfo[3][1] = "JogadorTeste4";
        playersInfo[3][2] = "9";

        playersInfo[4][0] = "5";
        playersInfo[4][1] = "JogadorTeste5";
        playersInfo[4][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(8,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test08_createInitialJungle() {
        //Test if the game is not created with a negative initialEnergy
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,-5,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test09_createInitialJungle() {
        //Test if the game is not created with 0 initialEnergy
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,0,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test10_createInitialJungle() {
        //Test if the game is created with 1 initialEnergy
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,1,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test11_createInitialJungle() {
        //Test if the game is created with a different positive initialEnergy
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,8,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test12_createInitialJungle() {
        //Test if the jungleSize is not valid (should be the multiple of the players number)
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(2,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test13_createInitialJungle() {
        //Test if the jungleSize is valid (should be the multiple of the players number)
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test14_createInitialJungle() {
        //Test if the jungleSize is valid with a bigger number
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "L";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(7,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test15_createInitialJungle() {
        //Test if the game is not created with more than 1 Tarzan
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "Z";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "Z";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test16_createInitialJungle() {
        //Test if the game is created correctly with the same specie
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test17_createInitialJungle() {
        //Test if the game is created correctly with just 1 Tarzan
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "Z";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertTrue(createJungle);
    }

    @Test
    public void test18_createInitialJungle() {
        //Test if the game is not created with an invalid specie
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "A";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test19_createInitialJungle() {
        //Test if the game is not created with an invalid specie
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "K";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "E";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test20_createInitialJungle() {
        //Test if the game is not created with an invalid player name
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "B";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "";
        playersInfo[1][2] = "P";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test21_createInitialJungle() {
        //Test if the game is not created with an invalid player name
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = null;
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test22_createInitialJungle() {
        //Test if the game is not created with an invalid player id
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "a";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }

    @Test
    public void test23_createInitialJungle() {
        //Test if the game is not created with repeated playerIds
        GameManager gameManager = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "3";
        playersInfo[0][1] = "JogadorTeste1";
        playersInfo[0][2] = "P";

        playersInfo[1][0] = "3";
        playersInfo[1][1] = "JogadorTeste2";
        playersInfo[1][2] = "P";
        boolean createJungle = gameManager.createInitialJungle(4,10,playersInfo);
        assertFalse(createJungle);
    }



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
    }*/
}
