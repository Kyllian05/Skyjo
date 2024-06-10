package controleur.game

import vue.Game

class UpdatePlaying(val vue : Game,val state : Boolean) {
    init {
        if(state){
            vue.currentPlayer.value = "C'est à vous de jouer"
        }else{
            vue.currentPlayer.value = "Ce n'est pas à vous de jouer"
        }
    }
}