package modele

import io.ktor.client.engine.*
import kotlinx.coroutines.runBlocking

class Pioche(serveur : Server): StockCarte() {
    private var serveur = serveur

    fun piocher(): Carte {
        var carte : modele.serverData.VraiCarte?
        runBlocking{
             carte = serveur.pioche()
        }
        return Carte(carte!!.valeur)
    }
}