package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    ArrayList<Player> players;
    ArrayList<Food> foods;
    Player actualPlayer;
    Player winner;
    int finalPosition;
    int nrJogada = 0;

    public GameManager() {}

    public String[][] getSpecies() {
        //Creates the default species data
        String[][] species = new String[5][7];

        //SpecieId
        //SpecieName
        //SpecieImageFileName
        species[0][0] = "E";
        species[0][1] = "Elefante";
        species[0][2] = "elephant.png";
        species[0][3] = "180";
        species[0][4] = "4";
        species[0][5] = "10";
        species[0][6] = "1..6";

        species[1][0] = "L";
        species[1][1] = "Leão";
        species[1][2] = "lion.png";
        species[1][3] = "80";
        species[1][4] = "2";
        species[1][5] = "10";
        species[1][6] = "4..6";

        species[2][0] = "T";
        species[2][1] = "Tartaruga";
        species[2][2] = "turtle.png";
        species[2][3] = "150";
        species[2][4] = "1";
        species[2][5] = "5";
        species[2][6] = "1..3";

        species[3][0] = "P";
        species[3][1] = "Pássaro";
        species[3][2] = "bird.png";
        species[3][3] = "70";
        species[3][4] = "4";
        species[3][5] = "50";
        species[3][6] = "5..6";

        species[4][0] = "Z";
        species[4][1] = "Tarzan";
        species[4][2] = "tarzan.png";
        species[4][3] = "70";
        species[4][4] = "2";
        species[4][5] = "20";
        species[4][6] = "1..6";

        return species;
    }

    public String[][] getFoodTypes(){
        //Creates the default for food types
        String[][] food = new String[5][3];

        //FoodID
        //FoodName
        //FoodImageFileName
        food[0][0] = "e";
        food[0][1] = "Erva";
        food[0][2] = "grass.png";

        food[1][0] = "a";
        food[1][1] = "Agua";
        food[1][2] = "water.png";

        food[2][0] = "b";
        food[2][1] = "Cacho de bananas";
        food[2][2] = "bananas.png";

        food[3][0] = "c";
        food[3][1] = "Carne";
        food[3][2] = "meat.png";

        food[4][0] = "m";
        food[4][1] = "Cogumelos magicos";
        food[4][2] = "mushroom.png";

        return food;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) { //Verified
        createInitialJungle(jungleSize, playersInfo);
        //Initially is going to verify all possible cases to return false
        this.players = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.actualPlayer = null;
        this.winner = null;
        //Verifies if the players data is not null
        if (playersInfo == null) {return new InitializationError("Players info é null");}
        //Verifies if the game has a minimum of 2 players and a maximum of 4 players
        if (playersInfo.length <2 || playersInfo.length > 4) { return new InitializationError("Numero de jogadores invalido"); }
        //Verifies if the map has at least 2 positions for each player playing
        if (jungleSize < playersInfo.length * 2) { return new InitializationError("O mapa não tem duas posições para cada jogador"); }
        //At this point we have playersInfo validated to test
        //Creates an arraylist to later verify if there are repeated playerIds
        ArrayList<String> playerIds = new ArrayList<>();
        //Creates an arraylist to later verify if Tarzan specie has been selected more than one time
        ArrayList<String> speciesCompeting = new ArrayList<>();
        //Creates this variable to later verify if the player data has a valid specie
        boolean hasSpecieVerified = false;
        boolean hasFoodVerified = false;
        //Iterates the players data
        for (int i = 0; i < playersInfo.length; i++) {
            String[] info = playersInfo[i];
            //Gets the players data
            String playerId = info[0];
            String playerName = info[1];
            String playerSpecieId = info[2]; //[0]
            String specieName = ""; //[1]
            String specieImage = ""; //[2]
            String specieEnergy = ""; //[3]
            String specieEnergyConsume = ""; //[4]
            String specieEnergyGain = ""; //[5]
            String specieSpeed = ""; //[6]
            String specieType = ""; //[7]
            String[][] species = getSpecies(); //Gets the available species data
            //Iterates the species
            for (int k = 0; k < species.length; k++) {
                //Gets the default specie id
                String defaultSpecieId = species[k][0];
                //Verifies if the Tarzan specie hasn't been selected more than one time
                if (speciesCompeting.contains("Z") && playerSpecieId.equals("Z")) { return new InitializationError("O Tarzan já foi selecionado"); }
                //Verifies if the playerSpecieId matches the default specieIds
                if (defaultSpecieId.equals(playerSpecieId) && !hasSpecieVerified) {
                    specieName = species[k][1];
                    specieImage = species[k][2];
                    specieEnergy = species[k][3];
                    specieEnergyConsume = species[k][4];
                    specieEnergyGain = species[k][5];
                    specieSpeed = species[k][6];
                    hasSpecieVerified = true;
                    if(playerSpecieId.equals("T") || playerSpecieId.equals("Z") || playerSpecieId.equals("P")){
                        specieType = "O";
                    }
                    if(playerSpecieId.equals("L")){
                        specieType = "C";
                    }
                    if(playerSpecieId.equals("E")){
                        specieType = "H";
                    }
                    speciesCompeting.add(playerSpecieId);
                }
                //If is in the last row and playerSpecieId hasn't been verified yet, the player specie is not valid
                if (k == species.length - 1 && !hasSpecieVerified) { return new InitializationError("Existe uma espécie que não é válida"); }
            }
            //Resets the variable
            if (hasSpecieVerified) { hasSpecieVerified = false; }
            //Verifies if the players names are valid
            if (playerName == null || playerName.equals("")) {return new InitializationError("O nome de um jogador não é váldio");}
            try {Integer.parseInt(playerId);} catch(NumberFormatException e) {return new InitializationError("O id de um jogador não é válido");}
            //Verifies if the playerId is not repeated
            if (playerIds.contains(playerId)) { return new InitializationError("O id de um jogador está repetido"); }
            //As playerId is not repeated adds it to the playerIds arraylist
            playerIds.add(playerId);
            //At this point the player data is verified and creates the player object
            Player player = new Player(playerId, playerName, new Specie(playerSpecieId, specieName, specieImage, specieEnergy, specieEnergyConsume, specieEnergyGain, specieSpeed, specieType), 1);
            //Adds the player to the created/game players list
            this.players.add(player);
        }
        String[][] foods = getFoodTypes();
        for (int i = 0; i< foodsInfo.length; i++) {
            String[] info = foodsInfo[i];
            String foodId = info[0];
            String foodPosition = info[1];
            if (Integer.parseInt(foodPosition)<=1 || Integer.parseInt(foodPosition)>=jungleSize) {
                return new InitializationError("Existe um alimento fora dos limites do terreno");
            }
            for (int k = 0; k < foods.length; k++) {
                String defaultFoodId = foods[k][0];
                if (defaultFoodId.equals(foodId) && !hasFoodVerified) {
                    hasFoodVerified = true;
                }
                if (k == foods.length - 1 && !hasFoodVerified) {
                    return new InitializationError("Existe um alimento que não é válido");
                }
            }
            hasFoodVerified = false;
            for (int k=0;k<foods.length;k++) {
                String[] infoFood = foods[k];
                if (infoFood[0].equals(foodId)) {
                    this.foods.add(new Food(foodId,foodPosition,infoFood[1],infoFood[2]));
                }
            }
        }
        //Finds out the player with the lowest id to start the game and saves that in actualPlayer
        int lowestPlayerId = Integer.parseInt(this.players.get(0).getId());
        Player playerWithLowestId = this.players.get(0);
        for (int i = 1; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            if (Integer.parseInt(player.getId()) < lowestPlayerId) {
                lowestPlayerId = Integer.parseInt(player.getId());
                playerWithLowestId = player;
            }
        }
        this.actualPlayer = playerWithLowestId;
        this.finalPosition = jungleSize; //Saves the position of the finish
        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) { //Verified
        //Initially is going to verify all possible cases to return false
        this.players = new ArrayList<>();
        this.actualPlayer = null;
        this.winner = null;
        //Verifies if the players data is not null
        if (playersInfo == null) {return new InitializationError("Players info é null");}
        //Verifies if the game has a minimum of 2 players and a maximum of 4 players
        if (playersInfo.length <2 || playersInfo.length > 4) { return new InitializationError("Numero de jogadores invalido"); }
        //Verifies if the map has at least 2 positions for each player playing
        if (jungleSize < playersInfo.length * 2) { return new InitializationError("O mapa não tem duas posições para cada jogador"); }
        //At this point we have playersInfo validated to test
        //Creates an arraylist to later verify if there are repeated playerIds
        ArrayList<String> playerIds = new ArrayList<>();
        //Creates an arraylist to later verify if Tarzan specie has been selected more than one time
        ArrayList<String> speciesCompeting = new ArrayList<>();
        //Creates this variable to later verify if the player data has a valid specie
        boolean hasSpecieVerified = false;
        //Iterates the players data
        for (int i = 0; i < playersInfo.length; i++) {
            String[] info = playersInfo[i];
            //Gets the players data
            String playerId = info[0];
            String playerName = info[1];
            String playerSpecieId = info[2]; //[0]
            String specieName = ""; //[1]
            String specieImage = ""; //[2]
            String specieEnergy = ""; //[3]
            String specieEnergyConsume = ""; //[4]
            String specieEnergyGain = ""; //[5]
            String specieSpeed = ""; //[6]
            String specieType = ""; //[7]
            String[][] species = getSpecies(); //Gets the available species data
            //Iterates the species
            for (int k = 0; k < species.length; k++) {
                //Gets the default specie id
                String defaultSpecieId = species[k][0];
                //Verifies if the Tarzan specie hasn't been selected more than one time
                if (speciesCompeting.contains("Z") && playerSpecieId.equals("Z")) { return new InitializationError("O Tarzan já foi selecionado"); }
                //Verifies if the playerSpecieId matches the default specieIds
                if (defaultSpecieId.equals(playerSpecieId) && !hasSpecieVerified) {
                    specieName = species[k][1];
                    specieImage = species[k][2];
                    specieEnergy = species[k][3];
                    specieEnergyConsume = species[k][4];
                    specieEnergyGain = species[k][5];
                    specieSpeed = species[k][6];
                    hasSpecieVerified = true;
                    if(playerSpecieId.equals("T") || playerSpecieId.equals("Z") || playerSpecieId.equals("P")){
                        specieType = "O";
                    }
                    if(playerSpecieId.equals("L")){
                        specieType = "C";
                    }
                    if(playerSpecieId.equals("E")){
                        specieType = "H";
                    }
                    speciesCompeting.add(playerSpecieId);
                }
                //If is in the last row and playerSpecieId hasn't been verified yet, the player specie is not valid
                if (k == species.length - 1 && !hasSpecieVerified) { return new InitializationError("Existe uma espécie que não é válida"); }
            }
            //Resets the variable
            if (hasSpecieVerified) { hasSpecieVerified = false; }
            //Verifies if the players names are valid
            if (playerName == null || playerName.equals("")) {return new InitializationError("O nome de um jogador não é váldio");}
            try {Integer.parseInt(playerId);} catch(NumberFormatException e) {return new InitializationError("O id de um jogador não é válido");}
            //Verifies if the playerId is not repeated
            if (playerIds.contains(playerId)) { return new InitializationError("O id de um jogador está repetido"); }
            //As playerId is not repeated adds it to the playerIds arraylist
            playerIds.add(playerId);
            //At this point the player data is verified and creates the player object
            Player player = new Player(playerId, playerName, new Specie(playerSpecieId, specieName, specieImage, specieEnergy, specieEnergyConsume, specieEnergyGain, specieSpeed, specieType), 1);
            //Adds the player to the created/game players list
            this.players.add(player);
        }
        //Finds out the player with the lowest id to start the game and saves that in actualPlayer
        int lowestPlayerId = Integer.parseInt(this.players.get(0).getId());
        Player playerWithLowestId = this.players.get(0);
        for (int i = 1; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            if (Integer.parseInt(player.getId()) < lowestPlayerId) {
                lowestPlayerId = Integer.parseInt(player.getId());
                playerWithLowestId = player;
            }
        }
        this.actualPlayer = playerWithLowestId;
        this.finalPosition = jungleSize; //Saves the position of the finish
        return null;
    }

    public int[] getPlayerIds(int squareNr) { //Verified
        ArrayList<Integer> playerIds = new ArrayList<>();

        //Iterates the players
        for (Player player : this.players) {
            //Verifies if the player is in the squareNr past as argument
            if (player.getSquareId() == squareNr) {
                //The playerId will be added to the arraylist
                playerIds.add(Integer.parseInt(player.getId()));
            }
        }

        //If there isn't any player in the specified squareNr, creates an empty array
        if (playerIds.size() == 0) {
            return new int[0];
        }

        //Converts the playersIds arraylist to array
        int[] array = new int[playerIds.size()];
        for (int i=0;i<playerIds.size();i++) {
            array[i] = playerIds.get(i);
        }
        return array;
    }

    public String[] getSquareInfo(int squareNr) { //Verified
        //Creates the array to save the square data
        String[] squareInfo = new String[3];

        if(squareNr < 1 || squareNr > this.finalPosition){
            return null;
        }

        //Verifies if the square past as argument is the finalPosition or not to select the correct data
        if (squareNr == this.finalPosition) {
            squareInfo[0] = "finish.png";
            squareInfo[1] = "Meta";
        } else {
            if (squareNr % 3 == 0) {
                squareInfo[0] = "image3.png";
                squareInfo[1] = "Vazio";
            } else if (squareNr % 2 == 0){
                squareInfo[0] = "image2.png";
                squareInfo[1] = "Vazio";
            } else {
                squareInfo[0] = "image1.png";
                squareInfo[1] = "Vazio";
            }
        }
        String result = "";

        //Iterates the players
        for (Player player : this.players) {
            //Verifies if the playerSquareId is the same past as argument
            if (player.getSquareId() == squareNr) {
                //Saves the playerId in the string
                if (result.equals("")) {
                    result = player.getId();
                } else {
                    result += "," + player.getId();
                }
            }
        }
        for (Food food: this.foods) {
            if (Integer.parseInt(food.getPosition()) == squareNr) {
                squareInfo[0] = food.getFileName();
                if(food.getId().equals("e")){ //Done
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("O")) {
                        squareInfo[1] = "Erva : + 20 energia";
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("C")) {
                        squareInfo[1] = "Erva : - 20 energia";
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("H")) {
                        squareInfo[1] = "Erva : + 20 energia";
                    }
                }
                if(food.getId().equals("a")){ //Done
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("O")) {
                        squareInfo[1] = "Agua : + 20% energia";
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("C")) {
                        squareInfo[1] = "Agua : + 15U energia";
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("H")) {
                        squareInfo[1] = "Agua : + 15U energia";
                    }
                }
                if(food.getId().equals("b")){ //Done?
                    int countNrBananas = 0;
                    for (Player player : this.players) {
                        countNrBananas += player.getSpecie().getBananaNr();
                    }
                    countNrBananas = 3-countNrBananas;
                    if(countNrBananas==0) {
                        for (Player player : this.players) {
                            player.getSpecie().updateCanEatBanana();
                        }
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("O")) {
                        squareInfo[1] = "Bananas : " + countNrBananas + " + 40 energia";
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("C")) {
                        squareInfo[1] = "Bananas : " + countNrBananas + " + 40 energia";
                    }
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("H")) {
                        squareInfo[1] = "Bananas : " + countNrBananas + " + 40 energia";
                    }
                }
                if(food.getId().equals("c")){ //Done?
                    if(this.actualPlayer.getSpecie().getSpecieType().equals("O")) {
                        if(this.nrJogada <= 12) {
                            squareInfo[1] = "Carne : + 50 energia : " + this.nrJogada + " jogadas";
                        } else {
                            squareInfo[1] = "Carne : - 50 energia : " + this.nrJogada + " jogadas";
                        }
                        if(this.nrJogada==12) {
                            for (Player player : this.players) {
                                player.getSpecie().updateCarneToxica();
                            }
                        }
                    }
                    if(actualPlayer.getSpecie().getSpecieType().equals("C")) {
                        if(this.nrJogada <= 12) {
                            squareInfo[1] = "Carne : + 50 energia : " + this.nrJogada + " jogadas";
                        } else {
                            squareInfo[1] = "Carne : - 50 energia : " + this.nrJogada + " jogadas";
                        }
                    }
                }
                if(food.getId().equals("m")){ //Por Fazer
                    if(actualPlayer.getSpecie().getSpecieType().equals("O")) {
                        if(this.nrJogada%2==0) {
                            squareInfo[1] = "Cogumelo Magico: + <N>% energia";
                        } else {
                            squareInfo[1] = "Cogumelo Magico: - <N>% energia";
                        }
                    }
                    if(actualPlayer.getSpecie().getSpecieType().equals("C")) {
                        if(this.nrJogada%2==0) {
                            squareInfo[1] = "Cogumelo Magico: + <N>% energia";
                        } else {
                            squareInfo[1] = "Cogumelo Magico: - <N>% energia";
                        }
                    }
                    if(actualPlayer.getSpecie().getSpecieType().equals("H")) {
                        if(this.nrJogada%2==0) {
                            squareInfo[1] = "Cogumelo Magico: + <N>% energia";
                        } else {
                            squareInfo[1] = "Cogumelo Magico: - <N>% energia";
                        }
                    }
                }
            }
        }
        //Saves the playerIds string in the square
        squareInfo[2] = result;
        return squareInfo;
    }

    public String[] getPlayerInfo(int playerId) { //Verified
        //Iterates the players
        for (Player player : this.players) {
            //Verifies if there is a player with such playerId and returns their data
            if (Integer.parseInt(player.getId()) == playerId) {
                String[] playerData = new String[5];
                playerData[0] = player.getId();
                playerData[1] = player.getName();
                playerData[2] = player.getSpecie().getSpecieId();
                playerData[3] = String.valueOf(player.getSpecie().getSpecieEnergy());
                playerData[4] = player.getSpecie().getSpecieSpeed();
                return playerData;
            }
        }
        return null;
    }

    public String[] getCurrentPlayerInfo() { //Verified
        //Gets the data of the current player and returns it
        Player player = this.actualPlayer;
        String[] playerData = new String[5];
        playerData[0] = player.getId();
        playerData[1] = player.getName();
        playerData[2] = player.getSpecie().getSpecieId();
        playerData[3] = String.valueOf(player.getSpecie().getSpecieEnergy());
        playerData[4] = player.getSpecie().getSpecieSpeed();
        return playerData;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        String[] energyInfo = new String[2];
        Player player = this.actualPlayer;
        energyInfo[0] = "" + Integer.parseInt(player.getSpecie().specieEnergyConsume) * nrPositions;
        energyInfo[1] = "" + Integer.parseInt(player.getSpecie().specieEnergyGain);
        return energyInfo;
    }

    public String[][] getPlayersInfo() { //Verified
        //Creates the array to store the playerData
        String[][] playersData = new String[this.players.size()][4];

        //Iterates the players and later return the data of everyone
        for (int i = 0; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            playersData[i][0] = player.getId();
            playersData[i][1] = player.getName();
            playersData[i][2] = player.getSpecie().getSpecieId();
            playersData[i][3] = String.valueOf(player.getSpecie().getSpecieEnergy());
        }
        return playersData;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) { //Verified
        this.nrJogada += 1;
        //Gets the current player
        Player currentPlayer = this.actualPlayer;
        //Gets the playerId of the actualPlayer in the arraylist
        int actualPlayerId = Integer.parseInt(currentPlayer.getId());
        int nextBiggerPlayerId;
        //Creates a playerIds arraylist to save all available ids
        ArrayList<Integer> playerIds = new ArrayList<>();
        //Adds the playerIds to the arraylist
        for (Player value : this.players) { playerIds.add(Integer.parseInt(value.getId())); }
        //Sorts the playerIds in order
        Collections.sort(playerIds);
        //Gets the index of the actualPlayer in the arraylist
        int indexActualPlayerId = playerIds.indexOf(actualPlayerId);
        //Verifies if the actualPlayerId is already the biggest one or not
        if (indexActualPlayerId == playerIds.size()-1) {
            nextBiggerPlayerId = playerIds.get(0);
        } else { nextBiggerPlayerId = playerIds.get(indexActualPlayerId + 1); }
        //Search for the player with the new id and sets them as the next actualPlayer
        for (int i=0;i<this.players.size();i++) {
            if (Integer.parseInt(this.players.get(i).getId()) == nextBiggerPlayerId) {
                this.actualPlayer = this.players.get(i); }
        }
        //Verifies if the dice number is valid
        if ((nrSquares < -6 || nrSquares > 6) && !bypassValidations) { return new MovementResult(MovementResultCode.INVALID_MOVEMENT, ""); }
        //Verifies if the player has enough energy to move. If it has, decreases the
        String energy = currentPlayer.getSpecie().getSpecieEnergy();
        currentPlayer.getSpecie().updateEnergy(nrSquares,true);
        if (Integer.parseInt(currentPlayer.getSpecie().getSpecieEnergy()) <= 0) {
            currentPlayer.getSpecie().updateEnergy(Integer.parseInt(energy),false);
            return new MovementResult(MovementResultCode.NO_ENERGY, "");
        }
        //Gets the current square of the player
        int currentSquare = currentPlayer.getSquareId();
        //Verifies if the new squareId is over the finish or not and updates with the new data
        if (currentSquare + nrSquares >= this.finalPosition) {
            currentPlayer.updateSquareId(this.finalPosition);
            //Gets the data of the winner
            this.winner = currentPlayer;
            getWinnerInfo();
        } else {
            if(currentSquare+nrSquares < 1) {
                currentPlayer.updateSquareId(1);
            } else {
                currentPlayer.updateSquareId(currentSquare+nrSquares);
            }
        }
        currentSquare = currentPlayer.getSquareId();
        for (int i=0;i<this.foods.size();i++) {
            Food food = this.foods.get(i);
            if(Integer.parseInt(food.getPosition())==currentSquare) {
                currentPlayer.getSpecie().updateEnergyByFood(food.getId());
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + food.getName());
            }
        }
        boolean someoneHasEnergy = false;
        //Iterates the players
        for (int i=0;i<this.players.size();i++) {
            //Verifies if there is still a player with energy
            if (Integer.parseInt(this.players.get(i).getSpecie().getSpecieEnergy()) > 0 && !someoneHasEnergy) { someoneHasEnergy = true; }
        }
        //If no one else has energy, the game will pick the winners
        if (!someoneHasEnergy) {
            //By default, selects one winner
            Player winnerPlayer = this.players.get(0);
            //Iterates the players
            for (int i = 0; i < this.players.size(); i++) {
                Player player = this.players.get(i);
                //Verifies if the squareId of the player is the same of the winner and see who has minor id
                if (player.getSquareId() == winnerPlayer.getSquareId()) {
                    if (Integer.parseInt(player.getId()) < Integer.parseInt(winnerPlayer.getId())) {
                        winnerPlayer = player;
                    }
                }
                //Verifies if the player is in front of the currentWinner
                if (player.getSquareId() > winnerPlayer.getSquareId()) {
                    winnerPlayer = player;
                }
            }
            //Gets the data of the winner
            this.winner = winnerPlayer;
            getWinnerInfo();
        }
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, "");
    }

    public String[] getWinnerInfo() { //Verified
        //Verifies if there is a winner
        if (this.winner != null) {
            String[] playerData = new String[4];
            playerData[0] = this.winner.getId();
            playerData[1] = this.winner.getName();
            playerData[2] = this.winner.getSpecie().getSpecieId();
            playerData[3] = String.valueOf(this.winner.getSpecie().getSpecieEnergy());
            return playerData;
        }
        return null;
    }

    public ArrayList<String> getGameResults() { //Verified
        //Gets an arraylist with the winners sorted by order
        ArrayList<String> resultadosJogo = new ArrayList<>();

        //Verifies if there is already a winner
        if (this.winner != null) {
            //Verifies if there are still players to be added to the winners arraylist
            while (this.players.size() > 0) {
                //Selects one random winner by default
                Player winnerPlayer = this.players.get(0);
                for (int i = 0; i < this.players.size(); i++) {
                    Player player = this.players.get(i);

                    //Verifies if the squareId of the player is the same of the winner and see who has minor id
                    if (player.getSquareId() == winnerPlayer.getSquareId()) {
                        if (Integer.parseInt(player.getId()) < Integer.parseInt(winnerPlayer.getId())) {
                            winnerPlayer = player;
                        }
                    }

                    //Verifies if the player is in front of the currentWinner
                    if (player.getSquareId() > winnerPlayer.getSquareId()) {
                        winnerPlayer = player;
                    }
                }

                //Gets the string with the winner
                String result = "#" + (resultadosJogo.size() + 1) + " " + winnerPlayer.getName() + ", " +
                        winnerPlayer.getSpecie().getSpecieName() + ", " + winnerPlayer.getSquareId();

                //Removes this player from the current players and adds to the arraylist with the winners
                this.players.remove(winnerPlayer);
                resultadosJogo.add(result);
            }
        }

        return resultadosJogo;
    }

    public JPanel getAuthorsPanel() { //Verified
        //Creates the credit panel
        JPanel panel = new JPanel();
        JLabel jlabel = new JLabel("<html>Developed by:<br/>- António Antunes<br/>- " +
                "João Serralha<br/><br/>Lusófona Informática</html>");
        panel.add(jlabel);
        return panel;
    }

    public String whoIsTaborda() { //Verified
        return "professional wrestling";
    }
}
