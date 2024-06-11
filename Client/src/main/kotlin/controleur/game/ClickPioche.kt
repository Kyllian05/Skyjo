package controleur.game

import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent
import kotlinx.coroutines.runBlocking
import modele.Jeu
import vue.Game
import java.io.FileInputStream

class ClickPioche (val game : Game,val jeu : Jeu) : EventHandler<MouseEvent>{
    override fun handle(e: MouseEvent) {
        if(e.eventType == MouseEvent.MOUSE_CLICKED){
            if(!jeu.myturnToPlay){
                return
            }
            if(jeu.playingChoice == null){
                jeu.playingChoice = "Pioche"
                runBlocking {
                    game.pioche[0].value = Image(jeu.piocher().file)
                }
                return
            }
            //TODO
        }
    }
}