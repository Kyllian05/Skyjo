package modele

import javafx.scene.chart.CategoryAxis
import kotlinx.coroutines.runBlocking

class Joueur(nom: String, server: Server) {
    val nom: String
    val currentPlayer: Boolean
    private val server: Server
    private var plateau : Plateau? = null
    var id: Int

    init {
        this.nom = nom
        this.currentPlayer = false
        this.server = server
        // Création du joueur avec le serveur
        runBlocking {
            this@Joueur.id = this@Joueur.server.createPlayer(this@Joueur.nom)
        }

        this.plateau = Plateau(server,this.id)
    }

    fun jouer(choix : String): Boolean {
        TODO()
    }
}