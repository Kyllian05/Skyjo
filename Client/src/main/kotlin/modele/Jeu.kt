package modele

import kotlinx.coroutines.runBlocking
import modele.serverData.Plateau

class Jeu(private val server: Server, player: Joueur) {
    private var id: Int? = null
    private val myPlayer: Joueur
    private var joined: Boolean = false
    private var pioche: Pioche? = null
    private var defausse: Defausse? = null

    init {
        this.myPlayer = player
    }

    fun start() {
        if (this.joined && this.id != null) {
            // Création de la pioche et de la défausse
            this.pioche = Pioche(this.server)
            runBlocking {
                val topCard = Carte(this@Jeu.server.getPartieState().carteSommetDefausse.valeur.toInt())
                this@Jeu.defausse = Defausse(this@Jeu.server, topCard)
            }

            // TODO:
            //  - Début du jeu get le player qui doit commencer
            //  - Faire une fonction qui fait jouer un joueur ?
        }
        throw Exception("No party joined or id is null")
    }

    fun creerPartie(nbJoueur: Int) {
        if (joined) {
            throw Exception("Party already joined")
        }
        if (nbJoueur < 2 || nbJoueur > 8) {
            throw Exception("Number of player invalid")
        }
        // Création de la partie avec le serveur
        runBlocking {
            this@Jeu.id = this@Jeu.server.createPartie(this@Jeu.myPlayer.id)
        }
        this.joined = true
    }

    fun rejoindrePartie(idParty: Int) {
        if (joined) {
            throw Exception("Party already joined")
        }
        runBlocking {
            this@Jeu.server.joinPartie(idParty)
        }
        this.id = idParty
        this.joined = true
    }

    fun getGameSate(): Plateau {
        return  runBlocking {
            return@runBlocking this@Jeu.server.getPartieState()
        }
    }
}