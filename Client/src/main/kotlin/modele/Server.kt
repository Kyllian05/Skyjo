package modele

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import modele.serverData.NotExistingPlayerException
import modele.serverData.Plateau
import modele.serverData.Player
import modele.serverData.ServerException

class Server(IP: String) {
    private val client = HttpClient(CIO)
    private var IP = IP
    var idPartie : Int? = null
    private var idPlayer : Int? = null

    fun verifyDef() {
        if (this.idPartie == null) {
            throw Exception("No party joined")
        }
        if (this.idPlayer == null) {
            throw Exception("Player undefined")
        }
    }

    suspend fun verifyResponse(res: HttpResponse) {
        if (res.status.value !in 200..299) {
            throw ServerException(res.status, res.body<String>())
        }
    }

    suspend fun getAllPlayers():IntArray {
        @Serializable
        class Result(val idJoueurs : IntArray)

        val response = client.get("$IP/joueur")
        verifyResponse(response)

        val result : Result = Json.decodeFromString(response.body<String>())
        return result.idJoueurs
    }

    suspend fun createPlayer(name : String): Int{
        @Serializable
        class Result(val idJoueur : Int)

        val response = client.get("$IP/joueur/nouveau/$name")
        verifyResponse(response)

        val result : Result = Json.decodeFromString(response.body<String>())
        this.idPlayer = result.idJoueur
        return result.idJoueur
    }

    suspend fun createPartie(nbJoueur: Int): Int{
        @Serializable
        class Result(val idNouvellePartie : Int)

        if (this.idPlayer == null) {
            throw Exception("Player is undefined")
        }
        val response = client.get("$IP/partie/nouvelle/$idPlayer/$nbJoueur")
        verifyResponse(response)

        val result : Result = Json.decodeFromString(response.body<String>())
        this.idPartie = result.idNouvellePartie
        return result.idNouvellePartie
    }

    suspend fun getName(id : Int): String {
        @Serializable
        class Result(val nom:String)

        val response = client.get("$IP/joueur/$id")
        verifyResponse(response)

        val result : Result = Json.decodeFromString(response.body<String>())
        return result.nom
    }

    suspend fun joinPartie(idPartie : Int) {
        if (this.idPartie != null) {
            throw Exception("Party already joined")
        }
        if (this.idPlayer == null) {
            throw Exception("Player is undefined")
        }

        val response = client.get("$IP/partie/$idPartie/$idPlayer/rejoint")
        verifyResponse(response)

        this.idPartie = idPartie
    }

    suspend fun pioche():Carte{
        @Serializable
        class Result2(val valeur : Int,val couleur : String)
        @Serializable
        class Result1(val cartePiochee : Result2)

        verifyDef()
        val response = client.get("$IP/partie/$idPartie/$idPlayer/pioche")
        verifyResponse(response)
        val result : Result1 = Json.decodeFromString(response.body<String>())
        return Carte(result.cartePiochee.valeur)
    }

    suspend fun echangePioche(colonneCarteEchangee : Int, ligneCarteEchangee : Int){
        verifyDef()
        val response = client.get("$IP/partie/$idPartie/$idPlayer/echangepioche/$colonneCarteEchangee/$ligneCarteEchangee")
        verifyResponse(response)
    }

    suspend fun defaussePioche(colonneCarteEchangee : Int, ligneCarteEchangee : Int){
        verifyDef()
        val response = client.get("$IP/partie/$idPartie/$idPlayer/defaussepioche/$colonneCarteEchangee/$ligneCarteEchangee")
        verifyResponse(response)
        val result : Plateau = Json.decodeFromString(response.body<String>())
    }

    suspend fun echangedefausse(colonneCarteEchangee : Int, ligneCarteEchangee : Int){
        verifyDef()
        val response = client.get("$IP/partie/$idPartie/$idPlayer/echangedefausse/$colonneCarteEchangee/$ligneCarteEchangee")
        verifyResponse(response)
    }

    suspend fun getAllParties():IntArray{
        @Serializable
        class Result(val idParties : IntArray)

        val response = client.get("$IP/partie")
        verifyResponse(response)

        val result : Result = Json.decodeFromString(response.body<String>())
        return result.idParties
    }

    suspend fun getPartieState(id: Int? = this.idPartie): modele.serverData.Plateau {
        if (id == null) {
            throw Exception("Aucune partie donnée")
        }
        val response = client.get("$IP/partie/$id")
        verifyResponse(response)
        return Json.decodeFromString<Plateau>(response.body<String>())
    }

    suspend fun getScore(): HashMap<Int, Int> {
        if (this.idPartie == null) {
            throw Exception("No party joined")
        }
        @Serializable
        class Result(val idJoueur: Int, val score: Int)

        val response = client.get("$IP/partie/$idPartie/score")
        verifyResponse(response)
        val result: List<Result> = Json.decodeFromString(response.body<String>())

        // Convertir la liste de résultats en HashMap
        val scoresMap = hashMapOf<Int, Int>()
        for (res in result) {
            scoresMap[res.idJoueur] = res.score
        }
        return scoresMap
    }

    suspend fun getCard(idPlayer: Int,colonne : Int,line : Int):Carte{
        if (this.idPartie == null) {
            throw Exception("No party joined")
        } else {
            var result = getPartieState(idPartie!!)
            for (i in 0..result.plateaux.size){
                if(result.plateaux[i].idJoueur == idPlayer){
                    return Carte((result.plateaux[i].colonnes[colonne][line].valeur as Int))
                }
            }
            throw NotExistingPlayerException(idPlayer)
        }
    }

    suspend fun getAllPlayersInPartie():IntArray{
        var result = intArrayOf()
        var state = getPartieState()
        for(i in 0..state.plateaux.size-1){
            result += state.plateaux[i].idJoueur
        }
        return result
    }

    fun changeIP(newIp : String){
        this.IP = newIp
    }
}