package pt.ulusofona.lp2.deisiJungle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Food> getFoods() {return foods;}

    public String[][] getSpecies() {
        return new String[][]{
                {"E","Elefante","elephant.png","180","4","10","1..6"},
                {"L","Leão","lion.png","80","2","10","4..6"},
                {"T","Tartaruga","turtle.png","150","1","5","1..3"},
                {"P","Pássaro","bird.png","70","4","50","5..6"},
                {"U","Unicórnio","unicorn.png","200","8","20","3..6"},
                {"Z","Tarzan","tarzan.png","70","2","20","1..6"}
        };
    }

    public String[][] getFoodTypes(){
        return new String[][]{
                {"e","Erva","grass.png"},
                {"a","Agua","water.png"},
                {"b","Bananas","bananas.png"},
                {"c","Carne","meat.png"},
                {"m","Cogumelo Magico","mushroom.png"}
        };
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) throws InvalidInitialJungleException{
        createInitialJungle(jungleSize,playersInfo);
        String[][] foods = getFoodTypes();
        this.foods = new ArrayList<>();
        for (int i=0; i<foodsInfo.length; i++) {
            String foodId = foodsInfo[i][0];
            String foodPosition = foodsInfo[i][1];
            try {Integer.parseInt(foodPosition);} catch(NumberFormatException e) {throw new InvalidInitialJungleException("A posição não é válida","food");}
            if (Integer.parseInt(foodPosition)<=1 || Integer.parseInt(foodPosition)>=jungleSize) {throw new InvalidInitialJungleException("Existe um alimento fora dos limites do terreno","food");}
            boolean hasFoodVerified = false;
            for (int k=0; k<foods.length; k++) {
                String defaultFoodId = foods[k][0];
                if (defaultFoodId.equals(foodId) && !hasFoodVerified) {
                    hasFoodVerified = true;
                }
                if (k == foods.length - 1 && !hasFoodVerified) {throw new InvalidInitialJungleException("Existe um alimento que não é válido","food");}
            }
            for (int k=0;k<foods.length;k++) {
                if (foods[k][0].equals(foodId)) {
                    if(foods[k][0].equals("m")) {
                        this.foods.add(new CogumeloMagico(foodId, foodPosition, foods[k][1], foods[k][2]));
                    }
                    if(foods[k][0].equals("b")) {
                        this.foods.add(new CachoBananas(foodId,foodPosition,foods[k][1],foods[k][2]));
                    }
                    if(foods[k][0].equals("e")) {
                        this.foods.add(new Erva(foodId,foodPosition,foods[k][1],foods[k][2]));
                    }
                    if(foods[k][0].equals("a")) {
                        this.foods.add(new Agua(foodId,foodPosition,foods[k][1],foods[k][2]));
                    }
                    if(foods[k][0].equals("c")) {
                        this.foods.add(new Carne(foodId,foodPosition,foods[k][1],foods[k][2]));
                    }
                }
            }
        }
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException{
        resetVariables();
        if (playersInfo == null) {throw new InvalidInitialJungleException("Players info é null","");}
        if (playersInfo.length <2 || playersInfo.length > 4) { throw new InvalidInitialJungleException("Numero de jogadores invalido",""); }
        if (jungleSize < playersInfo.length * 2) { throw new InvalidInitialJungleException("O mapa não tem duas posições para cada jogador",""); }
        ArrayList<String> playerIds = new ArrayList<>(); //Creates an arraylist to later verify if there are repeated playerIds
        String[][] speciesData = getSpecies();
        ArrayList<String> speciesCompeting = new ArrayList<>(); //Creates to verify if Tarzan is repeated
        for (int i = 0; i < playersInfo.length; i++) { //Iterates the players data
            String playerId = playersInfo[i][0]; String playerName = playersInfo[i][1]; String playerSpecieId = playersInfo[i][2];
            if (playerName == null || playerName.equals("")) {throw new InvalidInitialJungleException("O nome de um jogador não é váldio","player");}
            try {Integer.parseInt(playerId);} catch(NumberFormatException e) {throw new InvalidInitialJungleException("O id de um jogador não é válido","player");}
            if (playerIds.contains(playerId)) { throw new InvalidInitialJungleException("O id de um jogador está repetido","player"); }
            playerIds.add(playerId); //As playerId is not repeated adds it to the playerIds arraylist
            boolean isSpecieValid = false;
            for (int k = 0; k < speciesData.length; k++) {
                String defaultSpecieId = speciesData[k][0]; //Gets the default specie id
                if (speciesCompeting.contains("Z") && playerSpecieId.equals("Z")) { throw new InvalidInitialJungleException("O Tarzan já foi selecionado","player"); }
                if (defaultSpecieId.equals(playerSpecieId) && !isSpecieValid) { //Verifies if the playerSpecieId matches the default specieIds
                    Type type = null;
                    isSpecieValid = true;
                    speciesCompeting.add(playerSpecieId);
                    if(playerSpecieId.equals("T") || playerSpecieId.equals("Z") || playerSpecieId.equals("P")){type = new Omnivoro("O");}
                    if(playerSpecieId.equals("L")){type = new Carnivoro("C");}
                    if(playerSpecieId.equals("E")){type = new Herbivoro("H");}
                    if(playerSpecieId.equals("U")){type = new Mitologico("M");}
                    speciesData[k][1] = Normalizer.normalize(speciesData[k][1], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                    //specieName = species[k][1]; specieImage = species[k][2]; specieEnergy = species[k][3]; specieEnergyConsume = species[k][4]; specieEnergyGain = species[k][5]; specieSpeed = species[k][6];
                    this.players.add(new Player(playerId, playerName, new Specie(playerSpecieId, speciesData[k][1], speciesData[k][2], new Energy(speciesData[k][3], speciesData[k][4], speciesData[k][5]), speciesData[k][6], type))); //Adds the player to the created/game players list
                }
                if (k == speciesData.length - 1 && !isSpecieValid) { throw new InvalidInitialJungleException("Existe uma espécie que não é válida","player"); } //If is in the last row and playerSpecieId hasn't been verified yet, the player specie is not valid
            }
        }
        int lowestPlayerId = Integer.parseInt(this.players.get(0).getId()); //Finding player with lower ID
        Player playerWithLowestId = this.players.get(0);
        for (int i = 1; i < this.players.size(); i++) {
            if (Integer.parseInt(this.players.get(i).getId()) < lowestPlayerId) {lowestPlayerId = Integer.parseInt(this.players.get(i).getId());playerWithLowestId = this.players.get(i);}
        }
        this.actualPlayer = playerWithLowestId; this.finalPosition = jungleSize;
    }

    public int[] getPlayerIds(int squareNr) {
        ArrayList<Integer> playerIds = new ArrayList<>();
        for (Player player : this.players) {
            if (player.getSquareNr() == squareNr) { //Verifies if the player is in the squareNr past as argument
                playerIds.add(Integer.parseInt(player.getId())); //The playerId will be added to the arraylist
            }
        }
        if (playerIds.size() == 0) { //If there isn't any player in the specified squareNr, creates an empty array
            return new int[0];
        } else {
            int[] output = new int[playerIds.size()]; //Converts the playersIds arraylist to array
            for (int i=0;i<playerIds.size();i++) {
                output[i] = playerIds.get(i);
            }
            return output;
        }
    }

    public String[] getSquareInfo(int squareNr) {
        String[] squareInfo = new String[3]; //Creates the array to save the square data
        if(squareNr < 1 || squareNr > this.finalPosition){return null;} //Invalid squareNr
        if (squareNr == this.finalPosition) {squareInfo[0] = "finish.png";squareInfo[1] = "Meta";}
        else {
            if (squareNr % 3 == 0) {squareInfo[0] = "image3.png"; squareInfo[1] = "Vazio";}
            else if (squareNr % 2 == 0){squareInfo[0] = "image2.png";squareInfo[1] = "Vazio";}
            else {squareInfo[0] = "image1.png";squareInfo[1] = "Vazio";}
        }
        String playerIds = "";
        for (Player player : this.players) {
            if (player.getSquareNr() == squareNr) { //Verifies if the player is in the squareNr past as argument
                if (playerIds.equals("")) {playerIds = player.getId();} //The playerId will be added to the arraylist
                else {playerIds += "," + player.getId();}
            }
        }
        squareInfo[2] = playerIds; //Saves the playerIds string in the square
        boolean updatedCarne = false;
        if (this.foods!=null) { //Insert foods for tooltip
            for (Food food: this.foods) {
                if (Integer.parseInt(food.getSquareNr()) == squareNr) {
                    squareInfo[0] = food.getFileName();
                    if (this.roundNr == 12 && !updatedCarne) {
                        updatedCarne = true;
                        for (Player player : this.players) {
                            player.getSpecie().getType().updateMeatStatus();
                        }
                    }
                    if (food.getId().equals("e")) {squareInfo[1] = "Erva : +- 20 energia";} //Erva
                    if (food.getId().equals("a")) {squareInfo[1] = "Agua : + 15U|20% energia";} //Agua
                    if (food.getId().equals("b")) {squareInfo[1] = "Bananas : " + food.getNumber() + " : + 40 energia";} //Bananas
                    if (food.getId().equals("c") && this.actualPlayer.getSpecie().getType().canGetMeatStatus()) { //Carne
                        if (this.roundNr <= 12) {squareInfo[1] = "Carne : + 50 energia : " + this.roundNr + " jogadas";} else {squareInfo[1] = "Carne toxica";}
                    }
                    if (food.getId().equals("m")) {squareInfo[1] = "Cogumelo Magico : +- " + food.getNumber() + "% energia";}
                }
            }
        }
        return squareInfo;
    }

    public String[] getPlayerInfo(int playerId) {
        for (Player player : this.players) {
            if (Integer.parseInt(player.getId()) == playerId) { //Verifies if exists the player
                return new String[]{
                        player.getId(),
                        player.getName(),
                        player.getSpecie().getId(),
                        player.getSpecie().getEnergy().getActual(),
                        player.getSpecie().getSpeed()
                };
            }
        }
        return null;
    }

    public String[] getCurrentPlayerInfo() {
        return new String[]{
                this.actualPlayer.getId(),
                this.actualPlayer.getName(),
                this.actualPlayer.getSpecie().getId(),
                this.actualPlayer.getSpecie().getEnergy().getActual(),
                this.actualPlayer.getSpecie().getSpeed()
        };
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        return new String[]{
                String.valueOf(Integer.parseInt(this.actualPlayer.getSpecie().getEnergy().getConsume()) * Math.abs(nrPositions)),
                String.valueOf(Integer.parseInt(this.actualPlayer.getSpecie().getEnergy().getGain()))
        };
    }

    public String[][] getPlayersInfo() {
        String[][] playersData = new String[this.players.size()][5]; //Creates the array to store the playerData
        for (int i = 0; i < this.players.size(); i++) { //Iterates the players to later return data
            Player player = this.players.get(i);
            playersData[i][0] = player.getId();
            playersData[i][1] = player.getName();
            playersData[i][2] = player.getSpecie().getId();
            playersData[i][3] = player.getSpecie().getEnergy().getActual();
            playersData[i][4] = player.getSpecie().getSpeed();
        }
        return playersData;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        this.roundNr += 1; Player currentPlayer = this.actualPlayer; //Gets the current player
        int actualPlayerId = Integer.parseInt(currentPlayer.getId()); int nextBiggerPlayerId;
        ArrayList<Integer> playerIds = new ArrayList<>(); //Adds the playerIds to the arraylist
        for (Player value : this.players) { playerIds.add(Integer.parseInt(value.getId())); } //Sorts the playerIds in order
        Collections.sort(playerIds); //Gets the index of the actualPlayer in the arraylist
        int indexActualPlayerId = playerIds.indexOf(actualPlayerId); //Verifies if the actualPlayerId is already the biggest one or not
        if (indexActualPlayerId == playerIds.size()-1) {nextBiggerPlayerId = playerIds.get(0);}
        else { nextBiggerPlayerId = playerIds.get(indexActualPlayerId + 1); } //Search for the player with the new id and sets them as the next actualPlayer
        for (int i=0;i<this.players.size();i++) { if (Integer.parseInt(this.players.get(i).getId()) == nextBiggerPlayerId) {this.actualPlayer = this.players.get(i); } }
        if (nrSquares!=0) { //Verifies if the dice number is valid
            if ((nrSquares < -6 || nrSquares > 6 || Math.abs(nrSquares) < Character.getNumericValue(currentPlayer.getSpecie().getSpeed().charAt(0))
                    || Math.abs(nrSquares) > Character.getNumericValue(currentPlayer.getSpecie().getSpeed().charAt(3))) || currentPlayer.getSquareNr() + nrSquares < 0) {
                if (currentPlayer.getSquareNr() + nrSquares < 0) {return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);}
                else { if(!bypassValidations) { return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null); }}
            }
        }
        String energy = currentPlayer.getSpecie().getEnergy().getActual(); currentPlayer.getSpecie().getEnergy().updateMovementEnergy(nrSquares,true);
        if (Integer.parseInt(currentPlayer.getSpecie().getEnergy().getActual()) < 0) { currentPlayer.getSpecie().getEnergy().updateMovementEnergy(Integer.parseInt(energy),false);
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }
        int currentSquare = currentPlayer.getSquareNr(); //Gets the current square of the player
        if (currentSquare + nrSquares >= this.finalPosition) {currentPlayer.updateSquareNr(this.finalPosition); this.winner = currentPlayer; }
        else { if(currentSquare+nrSquares < 1) {currentPlayer.updateSquareNr(1);}
        else {currentPlayer.updateSquareNr(currentSquare+nrSquares);} } currentSquare = currentPlayer.getSquareNr();
        if(currentPlayer.getSquareNr() - Math.abs(nrSquares) > 0) {currentPlayer.updateTravelledDistance(Math.abs(nrSquares));}
        else {currentPlayer.updateTravelledDistance(currentPlayer.getSquareNr());}
        ArrayList<Integer> positionPlayers = new ArrayList<>(); //Iterates the players
        for (int i=0;i<this.players.size();i++) {positionPlayers.add(this.players.get(i).getSquareNr());}
        Collections.sort(positionPlayers);
        if(positionPlayers.get(positionPlayers.size()-1) - positionPlayers.get(positionPlayers.size()-2) > this.finalPosition/2) { ArrayList<Player> possibleWinners = new ArrayList<>();
            for (int i=0;i<this.players.size();i++){if(this.players.get(i).getSquareNr() == positionPlayers.get(positionPlayers.size()-2)) {possibleWinners.add(this.players.get(i));}}
            Player possibleWinner = possibleWinners.get(0);
            if(possibleWinners.size()>1) { //Se tiver mais que um jogador na mesma casa
                for(int i=1;i<possibleWinners.size();i++){if(Integer.parseInt(possibleWinners.get(i).getId()) < Integer.parseInt(possibleWinner.getId())) {possibleWinner = possibleWinners.get(i);}}
            }
            if (this.winner == null) { this.winner = possibleWinner; }
        }
        int middlePosition = -1; if(finalPosition % 2 == 0){middlePosition = finalPosition / 2; } else { middlePosition = (finalPosition / 2) + 1; }
        boolean inPositionToWin = false; ArrayList<Player> playersEnergy = new ArrayList<>();
        for(int i = 0; i < this.players.size(); i++){ if(this.players.get(i).getSquareNr() == middlePosition){ playersEnergy.add(this.players.get(i)); }
            if(this.players.get(i).getSquareNr() > middlePosition){ inPositionToWin = true; }
        }
        if(inPositionToWin && playersEnergy.size() > 1){ Player withMoreEnergy = playersEnergy.get(0);
            for(int k = 1; k < playersEnergy.size(); k++){ if(Integer.parseInt(playersEnergy.get(k).getSpecie().getEnergy().getActual()) > Integer.parseInt(withMoreEnergy.getSpecie().getEnergy().getActual())){ withMoreEnergy = playersEnergy.get(k);}}
            if (this.winner == null) { this.winner = withMoreEnergy; }
        }
        if(this.foods!=null) {
            for (int i = 0; i < this.foods.size(); i++) { Food food = this.foods.get(i);
                if (Integer.parseInt(food.getSquareNr()) == currentSquare) {
                    if(!currentPlayer.getSpecie().getType().getName().equals("M")){
                        if ((!currentPlayer.getSpecie().getType().canGetMeatStatus() && food.getId().equals("c"))) { //Change here
                        } else { food.eatFood(currentPlayer.getSpecie(),this.roundNr); return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + food.getName()); }
                    } else { return new MovementResult(MovementResultCode.VALID_MOVEMENT, null); }
                }
            }
        }
        if(currentPlayer.getSpecie().getType().getName().equals("M")){ currentPlayer.getSpecie().getEnergy().updateMovementEnergyMitologic(); } return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
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
                            if (temporaryWinner.getSquareNr() < players.get(i+1).getSquareNr()) {
                                temporaryWinner = players.get(i+1);
                            } else if (temporaryWinner.getSquareNr() == players.get(i+1).getSquareNr()) {
                                if(Integer.parseInt(temporaryWinner.getId()) > Integer.parseInt(players.get(i+1).getId())) {
                                    //ganha o que tem id mais pequeno
                                    temporaryWinner = players.get(i+1);
                                }
                            }
                        }
                    }
                }
                //Gets the string with the winner
                String name = temporaryWinner.getSpecie().getName(); //sem acentos
                String result = "#" + (resultadosJogo.size() + 1) + " " + temporaryWinner.getName() + ", " +
                        name + ", " + temporaryWinner.getSquareNr() + ", " +
                        + temporaryWinner.getTravelledDistance() + ", " + temporaryWinner.getSpecie().getEatenFoodNr();

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
        JPanel panel = new JPanel();
        panel.add(new JLabel("<html>Developed by:<br/>- António Antunes<br/>- " +
                "João Serralha<br/><br/>Lusófona Informática</html>"));
        return panel;
    }

    public String whoIsTaborda() {
        return "professional wrestling";
    }

    public void resetVariables(){
        this.players = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.actualPlayer = null;
        this.winner = null;
        this.finalPosition = 0;
        this.roundNr = 0;
    }

}