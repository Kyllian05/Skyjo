package modele

import javafx.collections.FXCollections
import javafx.collections.ObservableArrayBase
import javafx.collections.ObservableList
import javafx.scene.control.ListView
import kotlinx.coroutines.runBlocking

class Jeu(private val server: Server) {
    private var id: Int? = null
    private var myPlayer: Joueur? = null
    private var joined: Boolean = false
    val pioche: Pioche = Pioche(this.server)
    val defausse: Defausse = Defausse(this.server)
    var listeJoueur = FXCollections.observableArrayList<Int>()
    var maxPlayerPartie : Int? = null

    fun createPlayer(name: String) {
        this.myPlayer = Joueur(name, this.server, this.pioche, this.defausse)
    }

    fun start() {
        if (joined) {
            TODO()
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
            maxPlayerPartie = server.getPartieState().nbJoueursMax
            var joueurs = IntArray(maxPlayerPartie!!)
            joueurs = this@Jeu.server.getAllPlayers()
        }
        return false
    }

    suspend fun updateListeJoueur(){
        var list = server.getAllPlayersInPartie()
        this.listeJoueur.clear()
        for(i in 0..list.size-1){
            this.listeJoueur.add(list[i])
        }
    }
}