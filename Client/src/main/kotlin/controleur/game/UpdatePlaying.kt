package controleur.game

import io.ktor.util.*
import modele.Jeu
import org.controlsfx.tools.Platform
import vue.Game

class UpdatePlaying(val vue : Game,val state : Boolean,val jeu: Jeu) {
    init {
        if(state && jeu.playingText.value != "C'est à vous de jouer"){
            jeu.changePlayingText()
        }else if(!state && jeu.playingText.value != "Ce n'est pas à vous de jouer"){
            jeu.changePlayingText()
        }
    }
}