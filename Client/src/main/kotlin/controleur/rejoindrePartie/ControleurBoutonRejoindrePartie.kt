package controleur.rejoindrePartie

import controleur.salon.FetchingPlayer
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import modele.Jeu
import vue.Game
import vue.Rejoindre
import vue.Salon

class ControleurBoutonRejoindrePartie(val stage : Stage,val salon : Salon,val jeu : Jeu,val game : Game?,val vue : Rejoindre): EventHandler<ActionEvent> {
    override fun handle(e: ActionEvent) {
        var partieChoice = vue.ListePartie.selectionModel.selectedItem
        if(partieChoice == null){
            return
        }
        jeu.rejoindrePartie(partieChoice.id)
        salon.ListeJoueurs.items = jeu.listeJoueur
        stage.scene.root = salon
        FetchingPlayer(jeu,salon,game,stage).startWaiting()
    }
}