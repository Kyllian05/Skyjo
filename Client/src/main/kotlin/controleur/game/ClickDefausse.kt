package controleur.game

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import modele.Jeu
import vue.Game

class ClickDefausse (val game : Game, val jeu : Jeu) : EventHandler<MouseEvent> {
    override fun handle(e: MouseEvent) {
        if(e.eventType == MouseEvent.MOUSE_CLICKED){
            if(!jeu.myturnToPlay){
                return
            }
            if(jeu.playingChoice == null){
                jeu.playingChoice = "Defausse"
                return
            }else if(jeu.playingChoice == "Pioche"){
                jeu.playingChoice = "DefaussePioche"
            }
        }
    }
}