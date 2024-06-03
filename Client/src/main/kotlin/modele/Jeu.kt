package modele

import kotlinx.coroutines.runBlocking

class Jeu(private val server: Server, firstPlayer: Joueur, private val nbJoueurs: Int) {
    private val id: Int
    private var joueurs: Array<Joueur>
    private val maxPlayers = 8

    init {
        this.joueurs = arrayOf(firstPlayer)

        // Création de la partie avec le serveur
        runBlocking {
            this@Jeu.id = this@Jeu.server.createPartie(this@Jeu.joueurs[0].id, this@Jeu.nbJoueurs)
        }
    }

    fun start() {
        TODO()
    }

    fun ajouterJoueur(j: Joueur): Boolean {
        return if (this.joueurs.size < this.maxPlayers) {
            this.joueurs.plus(j)
            runBlocking {
                this@Jeu.server.joinPartie(j.id)
            }
            true
        } else {
            false
        }
    }
}