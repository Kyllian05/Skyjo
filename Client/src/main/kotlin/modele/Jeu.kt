package modele

import kotlinx.coroutines.runBlocking

class Jeu(private val server: Server, player: Joueur) {
    private var id: Int? = null
    private val myPlayer: Joueur
    private var joined: Boolean = false

    init {
        this.myPlayer = player
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
            this@Jeu.id = this@Jeu.server.createPartie(this@Jeu.myPlayer.id)
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
    }

}