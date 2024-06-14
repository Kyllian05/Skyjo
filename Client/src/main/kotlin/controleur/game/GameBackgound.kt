package controleur.game

import controleur.accueil.ControleurFermerAppli
import javafx.beans.property.SimpleObjectProperty
import javafx.concurrent.Task
import javafx.scene.image.Image
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.serverData.Plateau
import vue.Classement
import vue.Game
import java.io.FileInputStream
import java.lang.IndexOutOfBoundsException

class GameBackgound(val jeu : Jeu,val game : Game, val stage: Stage) {
    fun startWaiting(){
        val task = object : Task<Unit>() {
            override fun call() {
                val gameState = UpdateGameState(game, jeu)
                val updatePlaying = UpdatePlaying(jeu)
                do{
                    val data : Plateau? = runBlocking {
                        return@runBlocking jeu.getPartieState()
                    }
                    val p = jeu.myPlayer
                    try {
                        if (data != null && p != null) {
                            if(data.plateaux[data.indexJoueurCourant].idJoueur == p.id){
                                javafx.application.Platform.runLater { updatePlaying.update(data.etape, true) }
                                jeu.myturnToPlay = true
                            }else{
                                javafx.application.Platform.runLater { updatePlaying.update(data.etape, false) }
                                jeu.myturnToPlay = false
                            }
                        }
                        gameState.update()
                    } catch (e: IndexOutOfBoundsException) {
                        println(e)
                    }
                    Thread.sleep(3000)
                } while (data?.etape != "PARTIE_TERMINEE")
            }
        }

        task.setOnSucceeded {
            val state = runBlocking {
                return@runBlocking jeu.getPartieState()
            }
            if (state != null) {
                if (state.etape == "PARTIE_TERMINEE") {
                    runBlocking { jeu.scores() }
                    val localScore = jeu.scores
                    if (localScore != null) {
                        val classement = localScore.toList().sortedBy { (_, score) -> score }.toMap(LinkedHashMap())
                        val vue = Classement(classement, jeu.playerListMap)
                        vue.fixeListener(ControleurFermerAppli(stage))
                        stage.scene.root = vue
                    }
                }
            }
        }

        game.currentPlayerLabel.textProperty().bind(jeu.playingText)
        val thread = Thread(task)
        thread.isDaemon = true
        thread.start()
    }
}