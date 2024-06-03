package modele

import kotlinx.coroutines.runBlocking

class Defausse(server: Server,c: Carte): StockCarte() {

    private var server = server
    private var cards : Carte?

    init{
        runBlocking { cards = Carte(server.getPartieState().carteSommetDefausse.valeur.toInt()) }
    }

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