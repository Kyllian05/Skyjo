package modele

import kotlinx.coroutines.runBlocking

class Plateau(serveur : Server,playerId : Int): StockCarte() {
    private var contenu: HashMap<Int, Carte> = hashMapOf<Int,Carte>()
    private val server = serveur
    private val playerId = playerId

    fun reveler(xy: Int): Boolean {
        runBlocking {
            contenu[xy] = server.getCard(playerId,xy%100-xy%10,xy%10)
        }
        return true
    }

    fun remplacer(xy: Int, c: Carte) {
        TODO()
    }

    private fun verifColonne() {
        TODO()
    }

    fun calculerPoints(): Int {
        TODO()
    }

    fun  plateauDecouvert(): Boolean {
        TODO()
    }

}