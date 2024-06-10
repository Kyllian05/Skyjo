package controleur.game

import modele.Jeu
import vue.Game

class LinkName(val vue : Game,val jeu:Jeu) {
    init{
        for (i in 0..<jeu.listeJoueur.size){
            vue.playersName[i].value = jeu.listeJoueur[i]
        }
    }
}