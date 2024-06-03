package modele

import kotlinx.coroutines.runBlocking

class Joueur(nom: String, server: Server) {
    val nom: String
    val currentPlayer: Boolean
    private val server: Server
    var id: Int

    init {
        this.nom = nom
        this.currentPlayer = false
        this.server = server

        // Création du joueur avec le serveur
        runBlocking {
            this@Joueur.id = this@Joueur.server.createPlayer(this@Joueur.nom)
        }
    }

    fun startGame(): Int {
        TODO()
    }

    fun jouer(choix : String): Boolean {
        TODO()
    }

}