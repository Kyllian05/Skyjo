package modele

class Joueur(nom: String) {
    val nom: String
    val currentPlayer: Boolean

    init {
        this.nom = nom
        this.currentPlayer = false
    }

    fun startGame(): Int {
        TODO()
    }

    fun jouer(): Boolean {
        TODO()
    }

}