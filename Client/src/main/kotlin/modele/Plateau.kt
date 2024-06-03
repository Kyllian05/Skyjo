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
        contenu[xy] = c
    }

    fun calculerPoints(): Int {
        var sum = 0
        for (i in 0..4){
            for (x in 0..4){
                if(this.contenu.containsKey((i.toString()+x.toString()).toInt())){
                    sum += contenu[(i.toString()+x.toString()).toInt()]!!.nombre
                }
            }
        }
        return sum
    }

    fun supprimerColonne(colonne : Int){
        for (i in 0..4){
            this.contenu.remove((i.toString()+colonne.toString()).toInt())
        }
    }
}