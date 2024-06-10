package modele

import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlinx.coroutines.runBlocking
import modele.data.Party
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

    fun createPlayer(name: String) {
        this.myPlayer = Joueur(name, this.server, this.pioche, this.defausse)
    }

    fun start() {
        if (joined && this.id != null) {
            this.currentState = runBlocking { return@runBlocking this@Jeu.server.getPartieState() }
        } else {
            throw Exception("No party joined")
        }
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

    fun rejoindrePartie(idParty: Int): Boolean {
        if (joined) {
            throw Exception("Party already joined")
        }
        runBlocking {
            server.joinPartie(idParty)
            maxPlayerPartie = server.getPartieState().nbJoueursMax
            var joueurs = IntArray(maxPlayerPartie!!)
            joueurs = this@Jeu.server.getAllPlayers()
            joined = true
        }
        return false
    }

    suspend fun updateListeJoueur(){
         var list = server.getAllPlayersInPartie()
         var result = FXCollections.observableArrayList<String>()
         for(i in 0..list.size-1){
             result.add(server.getName(list[i]))
         }
         this@Jeu.listeJoueur.clear()
         Platform.runLater { this@Jeu.listeJoueur.addAll(result) }
    }

    suspend fun getPartieState(): modele.serverData.Plateau{
        return server.getPartieState()
    }
}