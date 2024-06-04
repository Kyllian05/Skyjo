package modele

import javafx.collections.FXCollections
import kotlinx.coroutines.runBlocking
import modele.data.Party

class Jeu(private val server: Server) {
    private var id: Int? = null
    private var myPlayer: Joueur? = null
    private var joined: Boolean = false
    val partyListe = FXCollections.observableArrayList<Party>()
    val pioche: Pioche = Pioche(this.server)
    val defausse: Defausse = Defausse(this.server)

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
        var joueurs = IntArray(8)
        runBlocking {
            joueurs = this@Jeu.server.getAllPlayers()
        }
        return false
    }

    /**
     * Contacte le serveur toutes les 5sec pour découvrir les nouvelles parties
     */
    fun discoverParty() {
        while (!joined) {
            val parties = runBlocking {
                return@runBlocking this@Jeu.server.getAllParties()
            }
            this.partyListe.remove(0, this.partyListe.size)
           for (p in parties) {
               val state = runBlocking {
                   return@runBlocking this@Jeu.server.getPartieState(p)
               }
               val max = state.nbJoueursMax
               val joined = state.plateaux.size
               this.partyListe.add(Party(max, p, joined))
           }
            Thread.sleep(5000)
        }
    }
}