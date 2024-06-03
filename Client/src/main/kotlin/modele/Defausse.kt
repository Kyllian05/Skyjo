package modele

import kotlinx.coroutines.runBlocking

class Defausse(server: Server,c: Carte): StockCarte() {

    private var server = server

    fun prendre(colonne: Int,ligne: Int){
        runBlocking {
            server.echangedefausse(colonne,ligne)
        }
    }

    fun defausser(colonne : Int,ligne : Int) {
        runBlocking {
            server.defaussePioche(colonne,ligne)
        }
    }
}