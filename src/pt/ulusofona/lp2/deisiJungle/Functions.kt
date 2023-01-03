package pt.ulusofona.lp2.deisiJungle

import java.sql.DriverManager.println
import java.util.ArrayList

enum class CommandType {GET, POST}

fun router() : (CommandType) -> ((GameManager, List<String>) -> String?)? {
    return ::getCommandType
}

fun getCommandType(tipoComando : CommandType) : (Function2<GameManager, List<String>, String?>)?{
   when(tipoComando){
        CommandType.GET -> return ::getFunctionByArgsWithGET
        CommandType.POST -> return ::getFunctionByArgsWithPOST
        else -> {
            println("Comando Invalido")
        }
    }
    return null
}

fun getFunctionByArgsWithGET(manager: GameManager, args: List<String>): String?{
    when(args[0]){
        "PLAYER_INFO" -> {
            for(i in 0..manager.players.size - 1){
                val player = manager.players[i]
                if(args[1] == player.name){
                    return "${player.id} | ${player.name} | ${player.specie.name} | ${player.specie.energy.actual} | ${player.squareNr}"
                }
            }
            return "Inexistent player"
        }
        "PLAYERS_BY_SPECIE" -> {
            var result = ""
            var namesInDescendingOrder1 : ArrayList<String> = ArrayList()
            var namesInDescendingOrder2 : ArrayList<String> = ArrayList()
            for(i in 0..manager.players.size - 1){
                val player = manager.players[i]
                if(args[1] == player.specie.id){
                    namesInDescendingOrder1.add(player.name)
                }
            }
            for(l in 0..namesInDescendingOrder1.size - 1){
                var nameOfFirstPlayer = namesInDescendingOrder1[0]

                for(k in 1..namesInDescendingOrder1.size - 1){
                    if(nameOfFirstPlayer[0] < namesInDescendingOrder1[k][0]){
                        nameOfFirstPlayer = namesInDescendingOrder1[k]
                    }
                }
                namesInDescendingOrder2.add(nameOfFirstPlayer)
                namesInDescendingOrder1.remove(nameOfFirstPlayer)
            }
            for(m in 0..namesInDescendingOrder2.size - 1){
                if(result == ""){
                    result += namesInDescendingOrder2[m]
                } else {
                    result += ",${namesInDescendingOrder2[m]}"
                }
            }
            return result
        }
        "MOST_TRAVELED" -> {
            var result = ""
            var totalTravelDistance = 0
            var playersWithDistanceInDescendingOrder : ArrayList<Player> = ArrayList()
            for(i in 0..manager.players.size - 1){
                val player = manager.players[i]
                playersWithDistanceInDescendingOrder.add(player)
            }
            for(k in 0..playersWithDistanceInDescendingOrder.size - 2){
                if(playersWithDistanceInDescendingOrder[k].travelledDistance < playersWithDistanceInDescendingOrder[k + 1].travelledDistance){
                    val temp = playersWithDistanceInDescendingOrder[k]
                    playersWithDistanceInDescendingOrder[k] = playersWithDistanceInDescendingOrder[k + 1]
                    playersWithDistanceInDescendingOrder[k + 1] = temp
                }
            }
            for(m in 0..playersWithDistanceInDescendingOrder.size - 1){
                val player = playersWithDistanceInDescendingOrder[m]
                totalTravelDistance += player.travelledDistance
                result += "${player.name}:${player.specie.id}:${player.travelledDistance}\n"
            }
            result += "Total:${totalTravelDistance}"
            return result
        }
        "TOP_ENERGETIC_OMNIVORES" -> {
            var result = ""
            var count = args[1].toInt()
            val omnivorePlayers : ArrayList<Player> = ArrayList()
            val omnivorePlayerByOrder: ArrayList<Player> = ArrayList()
            for (i in 0..manager.players.size-1) {
                if(manager.players.get(i).specie.type.name == "O") {
                    omnivorePlayers.add(manager.players.get(i))
                }
            }
            for (i in 0..omnivorePlayers.size-1) {
                var playerWithMoreEnergy = omnivorePlayers.get(0)
                for(k in 1..omnivorePlayers.size-1) {
                    if(omnivorePlayers.get(k).specie.energy.actual > playerWithMoreEnergy.specie.energy.actual) {
                        playerWithMoreEnergy = omnivorePlayers.get(k);
                    }
                }
                omnivorePlayerByOrder.add(playerWithMoreEnergy)
                omnivorePlayers.remove(playerWithMoreEnergy)
            }
            for (i in 0..omnivorePlayerByOrder.size-1) {
                if(count>0) {
                    count--
                    if (result == "") {
                        result = "${omnivorePlayerByOrder.get(i).name}:${omnivorePlayerByOrder.get(i).specie.energy.actual}"
                    } else {
                        result += "\n${omnivorePlayerByOrder.get(i).name}:${omnivorePlayerByOrder.get(i).specie.energy.actual}"
                    }
                }
            }
            return result
        }
        "CONSUMED_FOODS" -> {
            var result = ""
            for (i in 0..manager.foods.size-1) {
                if (manager.foods.get(i).consumedTimes > 0) {
                    if (result == "") {
                        result = manager.foods.get(i).name
                    } else {
                        result + "\n${manager.foods.get(i).name}"
                    }
                }
            }
            return result
        }
    }
    return null
}

fun getFunctionByArgsWithPOST(manager: GameManager, args: List<String>): String?{
    when(args[0]){
        "MOVE" -> {
            val movementResult = manager.moveCurrentPlayer(args[1].toInt(),true)
            if (movementResult.code == MovementResultCode.INVALID_MOVEMENT) {
                return "Movimento invalido"
            }
            if (movementResult.code == MovementResultCode.NO_ENERGY) {
                return "Sem energia"
            }
            if (movementResult.code == MovementResultCode.CAUGHT_FOOD) {
                return "Apanhou comida"
            }
            if (movementResult.code == MovementResultCode.VALID_MOVEMENT) {
                return "OK"
            }
        }
    }
    return null
}