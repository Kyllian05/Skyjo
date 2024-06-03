package modele

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.util.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Server(IP:String) {
    private val client = HttpClient(CIO)
    private val partyID : Int? = null
    private val IP = IP

    @OptIn(InternalAPI::class)
    suspend fun createPlayer(name : String): Int{
        @Serializable
        class Result(val idJoueur : Int)

        val response = client.get("$IP/joueur/nouveau/$name").body<String>()
        val result : Result = Json.decodeFromString(response)
        return result.idJoueur
    }

    suspend fun createPartie(playerId : Int, nbJoueur: Int): Int{
        @Serializable
        class Result(val idNouvellePartie : Int)

        val response = client.get("$IP/partie/nouvelle/$playerId/$nbJoueur").body<String>()
        val result : Result = Json.decodeFromString(response)
        return result.idNouvellePartie
    }

    suspend fun getName(id : Int):String{
        @Serializable
        class Result(val nom:String)
        val response = client.get("$IP/joueur/$id").body<String>()
        val result : Result = Json.decodeFromString(response)
        return result.nom
    }

    fun joinPartie(idPartie : Int,idPlayer : Int){
        val response = client.get("$IP/partie/$idPartie/$idPlayer")
    }

    fun pioche(idPartie: Int,idPlayer: Int):Array<Any>{
        @Serializable
        class Result2(val valeur : Int,val couleur : String)
        @Serializable
        class Result1(val cartePiochee : Result2)

        val response = client.get("$IP/partie/$idPartie/$idPlayer/pioche").body<String>()
        val result : Result1 = Json.decodeFromString(response)
        return arrayOf<Any>(result.cartePiochee.valeur,result.cartePiochee.couleur)
    }
}