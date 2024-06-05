package modele

import javafx.collections.FXCollections
import kotlinx.coroutines.runBlocking
import modele.data.Party
import modele.serverData.ServerException

class Jeu(private val server: Server) {
    private var id: Int? = null
    private var myPlayer: Joueur? = null
    var joined: Boolean = false
    val partyListe = FXCollections.observableArrayList<Party>()
    val pioche: Pioche = Pioche(this.server)
    val defausse: Defausse = Defausse(this.server)
    var listeJoueur = FXCollections.observableArrayList<String>()
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
            server.joinPartie(idParty)
            maxPlayerPartie = server.getPartieState().nbJoueursMax
            var joueurs = IntArray(maxPlayerPartie!!)
            joueurs = this@Jeu.server.getAllPlayers()
            joined = true
        }
        return false
    }

    /**
     * Contacte le serveur toutes les 5sec pour découvrir les nouvelles parties
     */
    suspend fun discoverParty() {
        val parties = this.server.getAllParties()
        this.partyListe.remove(0, this.partyListe.size)
        for (p in parties) {
            val state = this.server.getPartieState(p)
            val max = state.nbJoueursMax
            val joined = state.plateaux.size
            if (max == joined) {
                continue
            }
            try {
                val createdBy = this.server.getName(state.plateaux[0].idJoueur)
                this.partyListe.add(Party(max, p, joined, createdBy))
            } catch (e: ServerException) {
                if (e.code.value == 404) {
                    continue
                } else {
                    throw e
                }
            }
        }
    }

    suspend fun updateListeJoueur(){
        var list = server.getAllPlayersInPartie()
        this.listeJoueur.clear()
        for(i in 0..list.size-1){
            this.listeJoueur.add(server.getName(list[i]))
        }
    }
}