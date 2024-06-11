package controleur.game

import javafx.beans.property.SimpleObjectProperty
import javafx.concurrent.Task
import javafx.scene.image.Image
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.serverData.Plateau
import vue.Game
import java.io.FileInputStream

class GameBackgound(val jeu : Jeu,val game : Game) {
    fun startWaiting(){
        val task = object : Task<Unit>() {
            override fun call() {
                val gameState = UpdateGameState(game, jeu)
                do{
                    var data : Plateau?
                    runBlocking {
                        data = jeu.getPartieState()
                        if(data!!.plateaux[data!!.indexJoueurCourant].idJoueur == jeu.myPlayer!!.id){
                            javafx.application.Platform.runLater { UpdatePlaying(game, true, jeu) }
                            jeu.myturnToPlay = true
                        }else{
                            javafx.application.Platform.runLater { UpdatePlaying(game, false, jeu) }
                            jeu.myturnToPlay = false
                        }
                        gameState.update()
                    }
                    Thread.sleep(3000)
                } while (data!!.etape != "PARTIE_TERMINEE")
            }
        }
        game.currentPlayerLabel.textProperty().bind(jeu.playingText)
        val thread = Thread(task)
        thread.start()
    }
}