package controleur.game

import modele.Jeu
import vue.Game

class LinkName(val vue : Game,val jeu:Jeu) {
    init{
        var result = arrayOf<String>()
        for (i in jeu.listeJoueur.size-1 downTo 0){
            result +=(jeu.listeJoueur[i])
        }
        vue.defineName(result)
    }
}