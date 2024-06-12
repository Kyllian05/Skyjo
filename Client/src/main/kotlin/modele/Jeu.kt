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
    private var id: Int? = null
    var myPlayer: Joueur? = null
    var joined: Boolean = false
    val partyListe = FXCollections.observableArrayList<Party>()
    val pioche: Pioche = Pioche(this.server)
    val defausse: Defausse = Defausse(this.server)
    var listeJoueur = FXCollections.observableArrayList<String>()
    var maxPlayerPartie : Int? = null
    var currentState: Plateau? = null
    var myturnToPlay : Boolean = false
    var playingChoice : String? = null
    var playingText : StringProperty = SimpleStringProperty("Ce n'est pas à vous de jouer")

    fun createPlayer(name: String) {
        this.myPlayer = Joueur(name, this.server, this.pioche, this.defausse)
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
             result += server.getName(element)
         }
         Platform.runLater { this@Jeu.listeJoueur.clear() }
         Platform.runLater { this@Jeu.listeJoueur.addAll(result) }
    }

    suspend fun getPartieState(): modele.serverData.Plateau? {
        this.currentState = server.getPartieState()
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
}