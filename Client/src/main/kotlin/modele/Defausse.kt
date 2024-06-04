package modele

import kotlinx.coroutines.runBlocking

class Defausse(server: Server): StockCarte() {

    private var server = server
    private var cards : Carte? = null

    fun prendre(colonne: Int,ligne: Int): Carte{
        runBlocking {
            server.echangedefausse(colonne,ligne)
        }
        updateTopCard()
        return cards!!
    }

    fun updateTopCard() {
        runBlocking { cards = Carte(server.getPartieState().carteSommetDefausse.valeur.toInt()) }
    }
}