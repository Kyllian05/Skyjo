package modele

import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlinx.coroutines.runBlocking
import modele.data.Party
import modele.serverData.Carte
import modele.serverData.Plateau

class Jeu(private val server: Server) {
    var id: Int? = null
    var myPlayer: Joueur? = null
    var joined: Boolean = false
    val partyListe = FXCollections.observableArrayList<Party>()
    var listeJoueur = FXCollections.observableArrayList<String>()
    var playerListMap = HashMap<Int, String>()
    var maxPlayerPartie : Int? = null
    var currentState: Plateau? = null
    var myturnToPlay : Boolean = false
    var playingChoice : String? = null
    var playingText : StringProperty = SimpleStringProperty("Ce n'est pas à vous de jouer")
    var scores: HashMap<Int, Int>? = null

    fun createPlayer(name: String) {
        this.myPlayer = Joueur(name, this.server)
    }

    fun creerPartie(nbJoueur: Int) {
        if (joined) {
            throw Exception("Party already joined")
        }
        maxPlayerPartie = nbJoueur
        // Création de la partie avec le serveur
        runBlocking {
            this@Jeu.id = this@Jeu.server.createPartie(nbJoueur)
        }
        this.joined = true
    }

    fun rejoindrePartie(idParty: Int) {
        if (joined) {
            throw Exception("Party already joined")
        }
        runBlocking {
            server.joinPartie(idParty)
            maxPlayerPartie = server.getPartieState().nbJoueursMax
            this@Jeu.joined = true
            this@Jeu.id = idParty
        }
    }

    suspend fun updateListeJoueur(){
         val list = server.getAllPlayersInPartie()
         var result = arrayOf<String>()
         for(element in list){
             val tmp = server.getName(element)
             result += tmp
             this.playerListMap[element] = tmp
         }
         Platform.runLater { this@Jeu.listeJoueur.clear() }
         Platform.runLater { this@Jeu.listeJoueur.addAll(result) }
    }

    suspend fun getPartieState(): modele.serverData.Plateau? {
        this.currentState = server.getPartieState()
        println(this.currentState!!.plateaux[0].colonnes.size)
        return this.currentState
    }

    suspend fun echangedefausse(colonne : Int,row : Int){
        return server.echangedefausse(colonne,row)
    }

    suspend fun piocher(): modele.Carte {
        return this.server.pioche()
    }

    suspend fun defaussePioche(colonne : Int,ligne : Int){
        server.defaussePioche(colonne,ligne)
    }

    suspend fun echangePioche(colonne: Int,ligne : Int){
        server.echangePioche(colonne,ligne)
    }

    fun getPartyID():Int?{
        return server.idPartie
    }

    suspend fun scores() {
        this.scores = server.getScore()
    }
}