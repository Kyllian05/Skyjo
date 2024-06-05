package controleur.rejoindrePartie

import controleur.salon.FetchingPlayer
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import modele.Jeu
import vue.Salon

class ControleurBoutonRejoindrePartie(val stage : Stage,val salon : Salon,val jeu : Jeu): EventHandler<ActionEvent> {
    override fun handle(e: ActionEvent) {
        //jeu.rejoindrePartie()
        salon.ListeJoueurs.items = jeu.listeJoueur
        stage.scene.root = salon
        FetchingPlayer(jeu,salon).startWaiting()
    }
}