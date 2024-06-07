package controleur

import controleur.game.LinkName
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
                println("cacakipu1")
                game = Game(jeu.maxPlayerPartie!!)
                println("cacakipu2")
                LinkName(game!!,jeu)
                println("cacakipu3")
                stage.scene.root = game
            }
        }
        val thread = Thread(task)
        thread.start()
    }
}