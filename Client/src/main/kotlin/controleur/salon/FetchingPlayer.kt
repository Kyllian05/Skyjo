package controleur.salon

import javafx.concurrent.Task
import kotlinx.coroutines.runBlocking
import modele.Jeu

class FetchingPlayer(val jeu : Jeu,val vue : vue.Salon) {
    fun startWaiting(){
        val task = object : Task<Unit>() {
            override fun call() {
                while (jeu.listeJoueur.size != jeu.maxPlayerPartie){
                    runBlocking{jeu.updateListeJoueur()}
                    Thread.sleep(3000)
                }
            }
        }
        val thread = Thread(task)
        thread.start()
    }
}