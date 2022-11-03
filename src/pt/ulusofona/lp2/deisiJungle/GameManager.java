package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    ArrayList<Player> players = new ArrayList<>();
    Player actualPlayer;
    Player winner;
    int finalPosition;

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

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) { //Verified
        //Initially is going to verify all possible cases to return false

        //Verifies if the players data is not null
        if (playersInfo == null) {
            return false;
        }

        //Verifies if the game has a minimum of 2 players and a maximum of 4 players
        if (playersInfo.length <2 || playersInfo.length > 4) {
            return false;
        }

        //Verifies if the players' energy is not lower than 1
        if (initialEnergy < 1) {
            return false;
        }

        //Verifies if the map has at least 2 positions for each player playing
        if (jungleSize < playersInfo.length*2) {
            return false;
        }

        //At this point we have playersInfo validated to test

        //Creates an arraylist to later verify if there are repeated playerIds
        ArrayList<String> playerIds = new ArrayList<>();

        //Creates an arraylist to later verify if Tarzan specie has been selected more than one time
        ArrayList<String> speciesCompeting = new ArrayList<>();

        //Creates this variable to later verify if the player data has a valid specie
        boolean hasSpecieVerified = false;

        //Iterates the players data
        for (int i=0;i<playersInfo.length;i++) {
            String[] info = playersInfo[i];
            //Gets the players data
            String playerId = info[0];
            String playerName = info[1];
            String playerSpecieId = info[2];

            //Gets the available species data
            String[][] species = getSpecies();

            //Iterates the species
            for (int k = 0; k < species.length; k++) {
                //Gets the default specie id
                String defaultSpecieId = species[k][0];

                //Verifies if the Tarzan specie hasn't been selected more than one time
                if (speciesCompeting.contains("Z") && playerSpecieId.equals("Z")) {
                    return false;
                }

                //Verifies if the playerSpecieId matches the default specieIds
                if (defaultSpecieId.equals(playerSpecieId) && !hasSpecieVerified) {
                    hasSpecieVerified = true;
                    speciesCompeting.add(playerSpecieId);
                }

                //If is in the last row and playerSpecieId hasn't been verified yet, the player specie is not valid
                if (k == species.length - 1 && !hasSpecieVerified) {
                    return false;
                }
            }

            //Resets the variable
            if (hasSpecieVerified) {
                hasSpecieVerified = false;
            }

            //Verifies if the players names are valid
            if (playerName == null || playerName.equals("")) {
                return false;
            }

            //Verifies if the playerId is valid (not lower than 1) and if the playerId is not repeated
            if (Integer.parseInt(playerId) < 1 || playerIds.contains(playerId)) {
                return false;
            }

            //As playerId is not repeated adds it to the playerIds arraylist
            playerIds.add(playerId);

            //At this point the player data is verified and creates the player object
            Player player = new Player(playerId, playerName, playerSpecieId, initialEnergy, 1);

            //Adds the player to the created/game players list
            this.players.add(player);
        }

        //Finds out the player with the lowest id to start the game and saves that in actualPlayer
        int lowestPlayerId = Integer.parseInt(this.players.get(0).getId());
        Player playerWithLowestId = this.players.get(0);
        for (int i=1;i<this.players.size();i++) {
            Player player = this.players.get(i);
            if (Integer.parseInt(player.getId()) < lowestPlayerId) {
                lowestPlayerId = Integer.parseInt(player.getId());
                playerWithLowestId = player;
            }
        }
        this.actualPlayer = playerWithLowestId;

        //Saves the position of the finish
        this.finalPosition = jungleSize;

        return true;
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
        //Saves the playerIds string in the square
        squareInfo[2] = result;
        return squareInfo;
    }

    public String[] getPlayerInfo(int playerId) { //Verified
        //Iterates the players
        for (Player player : this.players) {
            //Verifies if there is a player with such playerId and returns their data
            if (Integer.parseInt(player.getId()) == playerId) {
                String[] playerData = new String[4];
                playerData[0] = player.getId();
                playerData[1] = player.getName();
                playerData[2] = player.getSpecieId();
                playerData[3] = String.valueOf(player.getEnergy());
                return playerData;
            }
        }
        return null;
    }

    public String[] getCurrentPlayerInfo() { //Verified
        //Gets the data of the current player and returns it
        Player player = this.actualPlayer;
        String[] playerData = new String[4];
        playerData[0] = player.getId();
        playerData[1] = player.getName();
        playerData[2] = player.getSpecieId();
        playerData[3] = String.valueOf(player.getEnergy());
        return playerData;
    }

    public String[][] getPlayersInfo() { //Verified
        //Creates the array to store the playerData
        String[][] playersData = new String[this.players.size()][4];

        //Iterates the players and later return the data of everyone
        for (int i = 0; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            playersData[i][0] = player.getId();
            playersData[i][1] = player.getName();
            playersData[i][2] = player.getSpecieId();
            playersData[i][3] = String.valueOf(player.getEnergy());
        }
        return playersData;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        //Gets the current player
        Player currentPlayer = this.actualPlayer;

        //Verifies if the dice number is valid
        if ((nrSquares < 1 || nrSquares > 6) && !bypassValidations) {
            return false;
        }

        //Verifies if the player has enough energy to move. If it has, decreases the energy
        if (currentPlayer.getEnergy() <= 0) {
            return false;
        } else {
            currentPlayer.updateEnergy();
        }

        //Gets the current square of the player
        int currentSquare = currentPlayer.getSquareId();

        //Verifies if the new squareId is over the finish or not and updates with the new data
        if (currentSquare + nrSquares >= this.finalPosition) {
            this.actualPlayer.updateSquareId(this.finalPosition);
            //Gets the data of the winner
            this.winner = this.actualPlayer;
            getWinnerInfo();
        } else {
            this.actualPlayer.updateSquareId(currentSquare + nrSquares);
        }

        boolean someoneHasEnergy = false;
        //Iterates the players
        for (int i=0;i<this.players.size();i++) {
            //Verifies if there is still a player with energy
            if (this.players.get(i).getEnergy() > 0 && !someoneHasEnergy) {
                someoneHasEnergy = true;
            }
        }

        //If no one else has energy, the game will pick the winners
        if (!someoneHasEnergy) {
            //By default, selects one winner
            Player winnerPlayer = this.players.get(0);

            //Iterates the players
            for (int i=0;i<this.players.size();i++) {
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

        //Gets the playerId of the actualPlayer in the arraylist
        int actualPlayerId = Integer.parseInt(currentPlayer.getId());
        int nextBiggerPlayerId;

        //Creates a playerIds arraylist to save all available ids
        ArrayList<Integer> playerIds = new ArrayList<>();

        //Adds the playerIds to the arraylist
        for (Player value : this.players) {
            playerIds.add(Integer.parseInt(value.getId()));
        }

        //Sorts the playerIds in order
        Collections.sort(playerIds);

        //Gets the index of the actualPlayer in the arraylist
        int indexActualPlayerId = playerIds.indexOf(actualPlayerId);

        //Verifies if the actualPlayerId is already the biggest one or not
        if (indexActualPlayerId == playerIds.size()-1) {
            nextBiggerPlayerId = playerIds.get(0);
        } else {
            nextBiggerPlayerId = playerIds.get(indexActualPlayerId + 1);
        }

        //Search for the player with the new id and sets them as the next actualPlayer
        for (int i=0;i<this.players.size();i++) {
            if (Integer.parseInt(this.players.get(i).getId()) == nextBiggerPlayerId) {
                this.actualPlayer = this.players.get(i);
            }
        }

        return true;
    }

    public String[] getWinnerInfo() {
        //Verifies if there is a winner
        if (this.winner != null) {
            String[] playerData = new String[4];
            playerData[0] = this.winner.getId();
            playerData[1] = this.winner.getName();
            playerData[2] = this.winner.getSpecieId();
            playerData[3] = String.valueOf(this.winner.getEnergy());
            return playerData;
        }
        return null;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }

    public String whoIsTaborda() { //Verified
        return "Tarzan";
    }
}
