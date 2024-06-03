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
    private var idPartie : Int = 0
    private var idPlayer : Int = 0

    suspend fun getAllPlayers():IntArray{
        @Serializable
        class Result(val idJoueurs : IntArray)

        val response = client.get("$IP/joueur").body<String>()
        val result : Result = Json.decodeFromString(response)
        return result.idJoueurs
    }

    suspend fun createPlayer(name : String): Int{
        @Serializable
        class Result(val idJoueur : Int)

        val response = client.get("$IP/joueur/nouveau/$name").body<String>()
        val result : Result = Json.decodeFromString(response)
        this.idPlayer = result.idJoueur
        return result.idJoueur
    }

    suspend fun createPartie(nbJoueur: Int): Int{
        @Serializable
        class Result(val idNouvellePartie : Int)

        val response = client.get("$IP/partie/nouvelle/$idPlayer/$nbJoueur").body<String>()
        val result : Result = Json.decodeFromString(response)
        this.idPartie = result.idNouvellePartie
        return result.idNouvellePartie
    }

    suspend fun getName(id : Int):String{
        @Serializable
        class Result(val nom:String)
        val response = client.get("$IP/joueur/$id").body<String>()
        val result : Result = Json.decodeFromString(response)
        return result.nom
    }

    suspend fun joinPartie(idPartie : Int){
        this.idPartie = idPartie
        val response = client.get("$IP/partie/$idPartie/$idPlayer/rejoint")
    }

    suspend fun pioche():Array<Any>{
        @Serializable
        class Result2(val valeur : Int,val couleur : String)
        @Serializable
        class Result1(val cartePiochee : Result2)

        val response = client.get("$IP/partie/$idPartie/$idPlayer/pioche").body<String>()
        val result : Result1 = Json.decodeFromString(response)
        return arrayOf<Any>(result.cartePiochee.valeur,result.cartePiochee.couleur)
    }

    suspend fun echangePioche(colonneCarteEchangee : Int, ligneCarteEchangee : Int){
        val response = client.get("$IP/partie/$idPartie/$idPlayer/echangepioche/$colonneCarteEchangee/$ligneCarteEchangee").body<String>()
    }

    suspend fun defaussePioche(colonneCarteEchangee : Int, ligneCarteEchangee : Int){
        val response = client.get("$IP/partie/$idPartie/$idPlayer/defaussepioche/$colonneCarteEchangee/$ligneCarteEchangee").body<String>()
    }

    suspend fun echangedefausse(colonneCarteEchangee : Int, ligneCarteEchangee : Int){
        val response = client.get("$IP/partie/$idPartie/$idPlayer/echangedefausse/$colonneCarteEchangee/$ligneCarteEchangee").body<String>()
    }

    suspend fun getAllParties():IntArray{
        @Serializable
        class Result(val idParties : IntArray)

        val response = client.get("$IP/partie").body<String>()
        val result : Result = Json.decodeFromString(response)
        return result.idParties
    }

    suspend fun getPartieState():modele.serverData.Plateau{
        val response = client.get("$IP/partie/$idPartie").body<String>()
        val result : modele.serverData.Plateau = Json.decodeFromString(response)
        return result
    }

    suspend fun getScore():Int{
        val response = client.get("$IP/partie/$idPartie/score").body<String>()
        TODO("je ne connais pas la forme des réponses")
    }
}