package modele

import kotlinx.coroutines.runBlocking

class Jeu(private val server: Server) {
    private var id: Int? = null
    private var myPlayer: Joueur? = null
    private var joined: Boolean = false
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

}