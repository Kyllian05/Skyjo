package modele

import javafx.scene.chart.CategoryAxis
import kotlinx.coroutines.runBlocking

class Joueur(nom: String, server: Server,pioche:Pioche,defausse: Defausse) {
    val nom: String
    val currentPlayer: Boolean
    private val server: Server
    private var plateau : Plateau? = null
    private var pioche : Pioche = pioche
    private var defausse : Defausse = defausse
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

    fun startGame(): Int {
        TODO()
    }

    fun echangePioche(colonne : Int,ligne : Int){
        runBlocking {
            var card = pioche.piocher()
            this@Joueur.plateau!!.remplacer((colonne.toString()+ligne.toString()).toInt(),card)
            this@Joueur.server.echangePioche(colonne,ligne)
        }
    }

    fun defaussePioche(colonne: Int,ligne: Int){
        defausse.prendre(colonne,ligne)
        this.plateau!!.reveler((colonne.toString()+ligne.toString()).toInt())
    }

    fun echangedefausse(colonne: Int,ligne: Int){
        runBlocking {
            var card = defausse.prendre(colonne,ligne)
            this@Joueur.plateau!!.remplacer((colonne.toString()+ligne.toString()).toInt(),card)
        }
    }
}