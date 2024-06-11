package controleur.game

import modele.Jeu
import vue.Game

class LinkName(val vue : Game,val jeu:Jeu) {
    init{
        // Détecter la position du joueur dans la liste
        var pos = 0
        for (i in 0 until jeu.listeJoueur.size) {
            if (jeu.listeJoueur[i] == jeu.myPlayer!!.nom) {
                pos = i
                break
            }
        }
        // Placer les noms des joueurs après le nôtre
        var index = 0
        for (i in pos+1 until jeu.listeJoueur.size){
            vue.playersName[index].value = jeu.listeJoueur[i]
            index++
        }
        // Placer les noms des joueurs avant le nôtre
        for (i in 0 until pos){
            vue.playersName[index].value = jeu.listeJoueur[i]
            index++
        }
    }
}