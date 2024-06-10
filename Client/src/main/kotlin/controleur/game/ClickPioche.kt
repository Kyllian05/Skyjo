package controleur.game

import javafx.event.ActionEvent
import javafx.event.Event
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import modele.Jeu
import vue.Game

class ClickPioche (val game : Game,val jeu : Jeu) : EventHandler<MouseEvent>{
    override fun handle(e: MouseEvent) {
        if(e.eventType == MouseEvent.MOUSE_CLICKED){
            if(!jeu.myturnToPlay){
                return
            }
            //TODO
        }
    }
}