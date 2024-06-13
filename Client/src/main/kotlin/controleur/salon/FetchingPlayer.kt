package controleur

import controleur.game.*
import javafx.concurrent.Task
import javafx.scene.control.Alert
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
                    javafx.application.Platform.runLater { vue.updatePlayer(jeu.maxPlayerPartie!!) }
                    Thread.sleep(3000)
                }
            }
        }

        task.setOnSucceeded {
            game = Game(jeu.maxPlayerPartie!!)
            LinkName(game!!,jeu)
            GameBackgound(jeu, game!!, stage).startWaiting()
            ClickCard(game!!.myCards!!,jeu,game!!)
            game!!.pileDefausse.onMouseClicked = ClickDefausse(game!!,jeu)
            game!!.pilePioche.onMouseClicked = ClickPioche(game!!,jeu)
            stage.scene.root = game
        }

        task.setOnFailed {
            val error = Alert(Alert.AlertType.ERROR)
            error.title = "Erreur"
            error.headerText = "Une erreur est survenue"
            error.show()
        }

        val thread = Thread(task)
        thread.start()
    }
}