package controleur.salon

import javafx.concurrent.Task
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking
import modele.Jeu
import vue.Game

class FetchingPlayer(val jeu : Jeu,val vue : vue.Salon,var game : Game?,val stage : Stage) {
    fun startWaiting(){
        val task = object : Task<Unit>() {
            override fun call() {
                while (jeu.listeJoueur.size != jeu.maxPlayerPartie){
                    runBlocking{jeu.updateListeJoueur()}
                    Thread.sleep(3000)
                }
                stage.scene.root = game
                game = Game(jeu.maxPlayerPartie!!)
                stage.scene.root = game
            }
        }
        val thread = Thread(task)
        thread.start()
    }
}