package pt.ulusofona.lp2.deisiJungle

import java.sql.DriverManager.println
import java.util.ArrayList

enum class CommandType {GET, POST}

fun router() : (CommandType) -> ((GameManager, ArrayList<String>) -> String?)? {
    return ::getCommandType
}

fun getCommandType(tipoComando : CommandType) : (Function2<GameManager, ArrayList<String>, String?>)?{
   when(tipoComando){
        CommandType.GET -> return ::getFunctionByArgsWithGET
        CommandType.POST -> return ::getFunctionByArgsWithGET
        else -> {
            println("Comando Invalido")
        }
    }
    return null
}

fun getFunctionByArgsWithGET(manager: GameManager, args: ArrayList<String>): String?{
    when(args[0]){
        "PLAYER_INFO" -> return "Test"
        "PLAYERS_BY_SPECIE" -> return "Test"
        "MOST_TRAVELED" -> return "Test"
        "TOP_ENERGETIC_OMNIVORES" -> return "Test"
        "CONSUMED_FOODS" -> return "Test"
        "MOVE" -> return "Test"
    }
    return null
}

fun getFunctionByArgsWithPOST(manager: GameManager, args: ArrayList<String>): String?{
    when(args[0]){
        "MOVE" -> return "Test"
    }
    return null
}