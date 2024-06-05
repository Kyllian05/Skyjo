package controleur.rejoindrePartie

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Jeu
import vue.Rejoindre

class ControleurActualiser(private val jeu: Jeu, private val vue: Rejoindre): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        Actualiser(jeu, vue).fetch()
    }
}