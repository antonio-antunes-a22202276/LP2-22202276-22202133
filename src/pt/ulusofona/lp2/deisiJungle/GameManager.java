package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager implements Serializable {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();
    private Player actualPlayer = null;
    private Player winner = null;
    private int finalPosition = 0;
    private int roundNr = 0;

    public GameManager() {}

    public GameManager(ArrayList<Player> players, ArrayList<Food> foods, Player actualPlayer, Player winner, int finalPosition, int roundNr) {
        this.players = players;
        this.foods = foods;
        this.actualPlayer = actualPlayer;
        this.winner = winner;
        this.finalPosition = finalPosition;
        this.roundNr = roundNr;
    }

    public String[][] getSpecies() {
        String[][] species = new String[5][7];
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
        String[][] food = new String[5][3];
        food[0][0] = "e";
        food[0][1] = "Erva";
        food[0][2] = "grass.png";

        food[1][0] = "a";
        food[1][1] = "Agua";
        food[1][2] = "water.png";

        food[2][0] = "b";
        food[2][1] = "Bananas";
        food[2][2] = "bananas.png";

        food[3][0] = "c";
        food[3][1] = "Carne";
        food[3][2] = "meat.png";

        food[4][0] = "m";
        food[4][1] = "Cogumelo Magico";
        food[4][2] = "mushroom.png";
        return food;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) {
        this.players = new ArrayList<>(); this.foods = new ArrayList<>(); this.actualPlayer = null; this.winner = null;
        ArrayList<String> playerIds = new ArrayList<>(); //Creates an arraylist to later verify if there are repeated playerIds
        ArrayList<String> speciesCompeting = new ArrayList<>(); //Creates an arraylist to later verify if Tarzan specie has been selected more than one time
        boolean hasSpecieVerified = false; //Creates this variable to later verify if the player data has a valid specie
        boolean hasFoodVerified = false;
        if (playersInfo == null) {return new InitializationError("Players info é null");} //Verifies if the players data is not null
        if (playersInfo.length <2 || playersInfo.length > 4) { return new InitializationError("Numero de jogadores invalido"); } //Verifies if the game has a minimum of 2 players and a maximum of 4 players
        if (jungleSize < playersInfo.length * 2) { return new InitializationError("O mapa não tem duas posições para cada jogador"); } //Verifies if the map has at least 2 positions for each player playing
        for (int i = 0; i < playersInfo.length; i++) { String[] info = playersInfo[i]; //Gets the players data | At this point we have playersInfo validated to test
            String playerId = info[0]; String playerName = info[1]; String playerSpecieId = info[2]; String specieName = ""; String specieImage = "";
            String specieEnergy = ""; String specieEnergyConsume = ""; String specieEnergyGain = ""; String specieSpeed = ""; String specieType = "";
            String[][] species = getSpecies(); //Gets the available species data
            for (int k = 0; k < species.length; k++) { String defaultSpecieId = species[k][0]; //Iterates the species
                if (speciesCompeting.contains("Z") && playerSpecieId.equals("Z")) { return new InitializationError("O Tarzan já foi selecionado"); } //Verifies if the Tarzan specie hasn't been selected more than one time
                if (defaultSpecieId.equals(playerSpecieId) && !hasSpecieVerified) { //Verifies if the playerSpecieId matches the default specieIds
                    specieName = species[k][1]; specieImage = species[k][2]; specieEnergy = species[k][3];
                    specieEnergyConsume = species[k][4]; specieEnergyGain = species[k][5]; specieSpeed = species[k][6];
                    hasSpecieVerified = true;
                    if(playerSpecieId.equals("T") || playerSpecieId.equals("Z") || playerSpecieId.equals("P")){specieType = "O";}
                    if(playerSpecieId.equals("L")){specieType = "C";}
                    if(playerSpecieId.equals("E")){specieType = "H";}
                    speciesCompeting.add(playerSpecieId);
                }
                if (k == species.length - 1 && !hasSpecieVerified) { return new InitializationError("Existe uma espécie que não é válida");} //If is in the last row and playerSpecieId hasn't been verified yet, the player specie is not valid
            }
            if (hasSpecieVerified) { hasSpecieVerified = false; } //Resets the variable
            if (playerName == null || playerName.equals("")) {return new InitializationError("O nome de um jogador não é váldio");} //Verifies if the players names are valid
            try {Integer.parseInt(playerId);} catch(NumberFormatException e) {return new InitializationError("O id de um jogador não é válido");}
            if (playerIds.contains(playerId)) { return new InitializationError("O id de um jogador está repetido");} //Verifies if the playerId is not repeated
            playerIds.add(playerId); //As playerId is not repeated adds it to the playerIds arraylist
            Type a = null;
            if(specieType.equals("C")) {
                a = new Carnivoro(specieType);
            } else if(specieType.equals("H")) {
                a = new Herbivoro(specieType);
            } else if(specieType.equals("O")) {
                a = new Omnivoro(specieType);
            }
            Player player = new Player(playerId, playerName, new Specie(playerSpecieId, specieName, specieImage, new Energy(specieEnergy, specieEnergyConsume, specieEnergyGain), specieSpeed, a), 1); //At this point the player data is verified and creates the player object
            this.players.add(player); //Adds the player to the created/game players list
        }
        String[][] foods = getFoodTypes();
        for (int i = 0; i< foodsInfo.length; i++) { String[] info = foodsInfo[i];
            String foodId = info[0]; String foodPosition = info[1];
            try {Integer.parseInt(foodPosition);} catch(NumberFormatException e) {return new InitializationError("A posição não é válida");}
            if (Integer.parseInt(foodPosition)<=1 || Integer.parseInt(foodPosition)>=jungleSize) {return new InitializationError("Existe um alimento fora dos limites do terreno");}
            for (int k = 0; k < foods.length; k++) {String defaultFoodId = foods[k][0];
                if (defaultFoodId.equals(foodId) && !hasFoodVerified) {hasFoodVerified = true;}
                if (k == foods.length - 1 && !hasFoodVerified) {return new InitializationError("Existe um alimento que não é válido");}
            }
            hasFoodVerified = false;
            for (int k=0;k<foods.length;k++) { String[] infoFood = foods[k];
                if (infoFood[0].equals(foodId)) {
                    if(infoFood[0].equals("m")) {
                        this.foods.add(new CogumeloMagico(foodId, foodPosition, infoFood[1], infoFood[2]));
                    }
                    if(infoFood[0].equals("b")) {
                        this.foods.add(new CachoBananas(foodId,foodPosition,infoFood[1],infoFood[2]));
                    }
                    if(infoFood[0].equals("e")) {
                        this.foods.add(new Erva(foodId,foodPosition,infoFood[1],infoFood[2]));
                    }
                    if(infoFood[0].equals("a")) {
                        this.foods.add(new Agua(foodId,foodPosition,infoFood[1],infoFood[2]));
                    }
                    if(infoFood[0].equals("c")) {
                        this.foods.add(new Carne(foodId,foodPosition,infoFood[1],infoFood[2]));
                    }
                }
            }
        }
        int lowestPlayerId = Integer.parseInt(this.players.get(0).getId()); //Finds out the player with the lowest id to start the game and saves that in actualPlayer
        Player playerWithLowestId = this.players.get(0);
        for (int i = 1; i < this.players.size(); i++) { Player player = this.players.get(i);
            if (Integer.parseInt(player.getId()) < lowestPlayerId) { lowestPlayerId = Integer.parseInt(player.getId()); playerWithLowestId = player; }
        }
        this.actualPlayer = playerWithLowestId; this.finalPosition = jungleSize; //Saves the position of the finish
        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) {
        this.players = new ArrayList<>(); //Initially is going to verify all possible cases to return false
        this.actualPlayer = null;
        this.winner = null;
        ArrayList<String> playerIds = new ArrayList<>(); //Creates an arraylist to later verify if there are repeated playerIds
        ArrayList<String> speciesCompeting = new ArrayList<>(); //Creates an arraylist to later verify if Tarzan specie has been selected more than one time
        boolean hasSpecieVerified = false; //Creates this variable to later verify if the player data has a valid specie
        if (playersInfo == null) {return new InitializationError("Players info é null");} //Verifies if the players data is not null
        if (playersInfo.length <2 || playersInfo.length > 4) { return new InitializationError("Numero de jogadores invalido"); } //Verifies if the game has a minimum of 2 players and a maximum of 4 players
        if (jungleSize < playersInfo.length * 2) { return new InitializationError("O mapa não tem duas posições para cada jogador"); } //At this point we have playersInfo validated to test
        for (int i = 0; i < playersInfo.length; i++) { //Iterates the players data
            String[] info = playersInfo[i]; String playerId = info[0]; String playerName = info[1]; String playerSpecieId = info[2]; //[0]
            String specieName = ""; String specieImage = ""; String specieEnergy = ""; String specieEnergyConsume = "";
            String specieEnergyGain = ""; String specieSpeed = ""; String specieType = "";
            String[][] species = getSpecies(); //Gets the available species data
            for (int k = 0; k < species.length; k++) { //Iterates the species
                String defaultSpecieId = species[k][0]; //Gets the default specie id
                if (speciesCompeting.contains("Z") && playerSpecieId.equals("Z")) { return new InitializationError("O Tarzan já foi selecionado"); } //Verifies if the Tarzan specie hasn't been selected more than one time
                if (defaultSpecieId.equals(playerSpecieId) && !hasSpecieVerified) { //Verifies if the playerSpecieId matches the default specieIds
                    specieName = species[k][1]; specieImage = species[k][2]; specieEnergy = species[k][3];
                    specieEnergyConsume = species[k][4]; specieEnergyGain = species[k][5]; specieSpeed = species[k][6];
                    hasSpecieVerified = true;
                    if(playerSpecieId.equals("T") || playerSpecieId.equals("Z") || playerSpecieId.equals("P")){specieType = "O";}
                    if(playerSpecieId.equals("L")){specieType = "C";}
                    if(playerSpecieId.equals("E")){specieType = "H";}
                    speciesCompeting.add(playerSpecieId);
                }
                if (k == species.length - 1 && !hasSpecieVerified) { return new InitializationError("Existe uma espécie que não é válida"); } //If is in the last row and playerSpecieId hasn't been verified yet, the player specie is not valid
            }
            if (hasSpecieVerified) { hasSpecieVerified = false; } //Resets the variable
            if (playerName == null || playerName.equals("")) {return new InitializationError("O nome de um jogador não é váldio");} //Verifies if the players names are valid
            try {Integer.parseInt(playerId);} catch(NumberFormatException e) {return new InitializationError("O id de um jogador não é válido");}
            if (playerIds.contains(playerId)) { return new InitializationError("O id de um jogador está repetido"); } //Verifies if the playerId is not repeated
            playerIds.add(playerId); //As playerId is not repeated adds it to the playerIds arraylist
            Type a = null;
            if(specieType.equals("C")) {
                a = new Carnivoro(specieType);
            } else if(specieType.equals("H")) {
                a = new Herbivoro(specieType);
            } else if(specieType.equals("O")) {
                a = new Omnivoro(specieType);
            }
            Player player = new Player(playerId, playerName, new Specie(playerSpecieId, specieName, specieImage, new Energy(specieEnergy, specieEnergyConsume, specieEnergyGain), specieSpeed, a), 1); //At this point the player data is verified and creates the player object
            this.players.add(player); //Adds the player to the created/game players list
        }
        int lowestPlayerId = Integer.parseInt(this.players.get(0).getId()); //Finds out the player with the lowest id to start the game and saves that in actualPlayer
        Player playerWithLowestId = this.players.get(0);
        for (int i = 1; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            if (Integer.parseInt(player.getId()) < lowestPlayerId) {lowestPlayerId = Integer.parseInt(player.getId());playerWithLowestId = player;}
        }
        this.actualPlayer = playerWithLowestId; this.finalPosition = jungleSize; //Saves the position of the finish
        return null;
    }

    public int[] getPlayerIds(int squareNr) {
        ArrayList<Integer> playerIds = new ArrayList<>();
        for (Player player : this.players) { //Iterates the players
            if (player.getSquareId() == squareNr) { //Verifies if the player is in the squareNr past as argument
                playerIds.add(Integer.parseInt(player.getId())); //The playerId will be added to the arraylist
            }
        }
        if (playerIds.size() == 0) { //If there isn't any player in the specified squareNr, creates an empty array
            return new int[0];
        }
        int[] array = new int[playerIds.size()]; //Converts the playersIds arraylist to array
        for (int i=0;i<playerIds.size();i++) {
            array[i] = playerIds.get(i);
        }
        return array;
    }

    public String[] getSquareInfo(int squareNr) {
        boolean updatedCarne = false; //Creates the array to save the square data
        String[] squareInfo = new String[3];
        if(squareNr < 1 || squareNr > this.finalPosition){return null;}
        if (squareNr == this.finalPosition) {squareInfo[0] = "finish.png";squareInfo[1] = "Meta";} //Verifies if the square past as argument is the finalPosition or not to select the correct data
        else {
            if (squareNr % 3 == 0) {squareInfo[0] = "image3.png"; squareInfo[1] = "Vazio";}
            else if (squareNr % 2 == 0){squareInfo[0] = "image2.png";squareInfo[1] = "Vazio";}
            else {squareInfo[0] = "image1.png";squareInfo[1] = "Vazio";}
        }
        String result = "";
        for (Player player : this.players) { //Iterates the players
            if (player.getSquareId() == squareNr) { //Verifies if the playerSquareId is the same past as argument
                if (result.equals("")) {result = player.getId();} //Saves the playerId in the string
                else {result += "," + player.getId();}
            }
        }
        if (this.foods!=null) {
            for (Food food: this.foods) {
                if (Integer.parseInt(food.getPosition()) == squareNr) {
                    squareInfo[0] = food.getFileName();
                    if (this.roundNr == 12 && !updatedCarne) {
                        updatedCarne = true; //Está a duplicar várias vezes?
                        for (Player player : this.players) {
                            player.getSpecie().getType().updateMeatStatus(); //player.getSpecie().updateToxicMeat();
                        }
                    }
                    if (food.getId().equals("e")) {squareInfo[1] = "Erva : +- 20 energia";} //Erva
                    if (food.getId().equals("a")) {squareInfo[1] = "Agua : + 15U|20% energia";} //Agua
                    if (food.getId().equals("b")) {squareInfo[1] = "Bananas : " + food.getNumber() + " : + 40 energia";} //Bananas
                    if (food.getId().equals("c") && this.actualPlayer.getSpecie().getType().canGetMeatStatus()) { //
                        if (this.roundNr <= 12) {squareInfo[1] = "Carne : + 50 energia : " + this.roundNr + " jogadas";} else {squareInfo[1] = "Carne toxica";}
                    }
                    if (food.getId().equals("m")) {squareInfo[1] = "Cogumelo Magico : +- " + food.getNumber() + "% energia";}
                }
            }
        }
        squareInfo[2] = result; //Saves the playerIds string in the square
        return squareInfo;
    }

    public String[] getPlayerInfo(int playerId) {
        for (Player player : this.players) { //Iterates the players
            if (Integer.parseInt(player.getId()) == playerId) { //Verifies if there is a player with such playerId and returns their data
                String[] playerData = new String[5];
                playerData[0] = player.getId();
                playerData[1] = player.getName();
                playerData[2] = player.getSpecie().getId();
                playerData[3] = String.valueOf(player.getSpecie().getEnergy().getActual());
                playerData[4] = player.getSpecie().getSpeed();
                return playerData;
            }
        }
        return null;
    }

    public String[] getCurrentPlayerInfo() {
        Player player = this.actualPlayer; //Gets the data of the current player and returns it
        String[] playerData = new String[5];
        playerData[0] = player.getId();
        playerData[1] = player.getName();
        playerData[2] = player.getSpecie().getId();
        playerData[3] = String.valueOf(player.getSpecie().getEnergy().getActual());
        playerData[4] = player.getSpecie().getSpeed();
        return playerData;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        String[] energyInfo = new String[2];
        Player player = this.actualPlayer;
        energyInfo[0] = "" + Integer.parseInt(player.getSpecie().getEnergy().getConsume()) * Math.abs(nrPositions);
        energyInfo[1] = "" + Integer.parseInt(player.getSpecie().getEnergy().getGain());
        return energyInfo;
    }

    public String[][] getPlayersInfo() {
        String[][] playersData = new String[this.players.size()][5]; //Creates the array to store the playerData
        for (int i = 0; i < this.players.size(); i++) { //Iterates the players and later return the data of everyone
            Player player = this.players.get(i);
            playersData[i][0] = player.getId();
            playersData[i][1] = player.getName();
            playersData[i][2] = player.getSpecie().getId();
            playersData[i][3] = String.valueOf(player.getSpecie().getEnergy().getActual());
            playersData[i][4] = player.getSpecie().getSpeed();
        }
        return playersData;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        this.roundNr += 1;
        Player currentPlayer = this.actualPlayer; //Gets the current player
        int actualPlayerId = Integer.parseInt(currentPlayer.getId()); //Gets the playerId of the actualPlayer in the arraylist
        int nextBiggerPlayerId; //Creates a playerIds arraylist to save all available ids
        ArrayList<Integer> playerIds = new ArrayList<>(); //Adds the playerIds to the arraylist
        for (Player value : this.players) { playerIds.add(Integer.parseInt(value.getId())); } //Sorts the playerIds in order
        Collections.sort(playerIds); //Gets the index of the actualPlayer in the arraylist
        int indexActualPlayerId = playerIds.indexOf(actualPlayerId); //Verifies if the actualPlayerId is already the biggest one or not
        if (indexActualPlayerId == playerIds.size()-1) {nextBiggerPlayerId = playerIds.get(0);}
        else { nextBiggerPlayerId = playerIds.get(indexActualPlayerId + 1); } //Search for the player with the new id and sets them as the next actualPlayer
        for (int i=0;i<this.players.size();i++) { if (Integer.parseInt(this.players.get(i).getId()) == nextBiggerPlayerId) {this.actualPlayer = this.players.get(i); } }
        if (nrSquares!=0) { //Verifies if the dice number is valid
            if ((nrSquares < -6 || nrSquares > 6 || Math.abs(nrSquares) < Character.getNumericValue(currentPlayer.getSpecie().getSpeed().charAt(0))
                    || Math.abs(nrSquares) > Character.getNumericValue(currentPlayer.getSpecie().getSpeed().charAt(3))) && !bypassValidations) {
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }
        }
        String energy = currentPlayer.getSpecie().getEnergy().getActual(); //Verifies if the player has enough energy to move. If it has, decreases the
        currentPlayer.getSpecie().getEnergy().updateEnergy(nrSquares,true);
        if (Integer.parseInt(currentPlayer.getSpecie().getEnergy().getActual()) < 0) { currentPlayer.getSpecie().getEnergy().updateEnergy(Integer.parseInt(energy),false);
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }
        int currentSquare = currentPlayer.getSquareId(); //Gets the current square of the player
        if (currentSquare + nrSquares >= this.finalPosition) {currentPlayer.updateSquareId(this.finalPosition); this.winner = currentPlayer; }
        else { if(currentSquare+nrSquares < 1) {currentPlayer.updateSquareId(1);}
            else {currentPlayer.updateSquareId(currentSquare+nrSquares);}
        }
        currentSquare = currentPlayer.getSquareId();
        if(currentPlayer.getSquareId() - Math.abs(nrSquares) > 0) {currentPlayer.updateHouseNr(Math.abs(nrSquares));}
        else {currentPlayer.updateHouseNr(currentPlayer.getSquareId());}
        ArrayList<Integer> positionPlayers = new ArrayList<>(); //Iterates the players
        for (int i=0;i<this.players.size();i++) {positionPlayers.add(this.players.get(i).getSquareId());}
        Collections.sort(positionPlayers);
        if(positionPlayers.get(positionPlayers.size()-1) - positionPlayers.get(positionPlayers.size()-2) > this.finalPosition/2) {
            ArrayList<Player> possibleWinners = new ArrayList<>();
            for (int i=0;i<this.players.size();i++){
                if(this.players.get(i).getSquareId() == positionPlayers.get(positionPlayers.size()-2)) {possibleWinners.add(this.players.get(i));}
            }
            Player possibleWinner = possibleWinners.get(0);
            if(possibleWinners.size()>1) { //Se tiver mais que um jogador na mesma casa
                for(int i=1;i<possibleWinners.size();i++){
                    if(Integer.parseInt(possibleWinners.get(i).getId()) < Integer.parseInt(possibleWinner.getId())) {possibleWinner = possibleWinners.get(i);}
                }
            }
            if (this.winner == null) {
                this.winner = possibleWinner;
            }
        }
        if(this.foods!=null) {
            for (int i = 0; i < this.foods.size(); i++) {
                Food food = this.foods.get(i);
                if (Integer.parseInt(food.getPosition()) == currentSquare) {
                    if ((!currentPlayer.getSpecie().getType().canGetMeatStatus() && food.getId().equals("c"))) { //Change here
                    } else {
                        food.eatFood(currentPlayer.getSpecie(),this.roundNr);
                        return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + food.getName());
                    }
                }
            }
        }
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public String[] getWinnerInfo() {
        if (this.winner != null) { //Verifies if there is a winner
            return getPlayerInfo(Integer.parseInt(this.winner.getId()));
        }
        return null;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> resultadosJogo = new ArrayList<>(); //Gets an arraylist with the winners sorted by order
        if (this.winner != null) { //Verifies if there is already a winner
            int size = this.players.size(); //Verifies if there are still players to be added to the winners arraylist
            while (this.players.size() > 0) {
                Player temporaryWinner = this.winner;
                if(size!=this.players.size()) {
                    temporaryWinner = this.players.get(0);
                    for (int i = 0; i < this.players.size(); i++) {
                        if(i+1 < this.players.size()) { //Verifies if there are more players in the list
                            if (temporaryWinner.getSquareId() < players.get(i+1).getSquareId()) {
                                temporaryWinner = players.get(i+1);
                            } else if (temporaryWinner.getSquareId() == players.get(i+1).getSquareId()) {
                                if(Integer.parseInt(temporaryWinner.getId()) > Integer.parseInt(players.get(i+1).getId())) {
                                    //ganha o que tem id mais pequeno
                                    temporaryWinner = players.get(i+1);
                                }
                            }
                        }
                    }
                }
                //Gets the string with the winner
                String name = Normalizer.normalize(temporaryWinner.getSpecie().getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""); //sem acentos
                String result = "#" + (resultadosJogo.size() + 1) + " " + temporaryWinner.getName() + ", " +
                        name + ", " + temporaryWinner.getSquareId() + ", " +
                        + temporaryWinner.getHouseNr() + ", " + temporaryWinner.getSpecie().getFoodNr();

                //Removes this player from the current players and adds to the arraylist with the winners
                this.players.remove(temporaryWinner);
                resultadosJogo.add(result);
            }
        }
        return resultadosJogo;
    }

    public boolean saveGame(File file) {
        try {
            FileOutputStream myFile = new FileOutputStream(file);
            ObjectOutputStream myWriter = new ObjectOutputStream(myFile);
            GameManager manager = new GameManager(this.players, this.foods, this.actualPlayer, this.winner, this.finalPosition, this.roundNr);
            myWriter.writeObject(manager);
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean loadGame(File file) {
        try {
            FileInputStream myFile = new FileInputStream(file);
            ObjectInputStream myReader = new ObjectInputStream(myFile);
            GameManager manager = (GameManager)myReader.readObject();
            this.players = manager.players;
            this.foods = manager.foods;
            this.actualPlayer = manager.actualPlayer;
            this.winner = manager.winner;
            this.finalPosition = manager.finalPosition;
            this.roundNr = manager.roundNr;
            myReader.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public JPanel getAuthorsPanel() {
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