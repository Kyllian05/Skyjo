package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import vue.Accueil

class ControleurBouttonNom(private val vue: Accueil): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        this.vue.addButtons()
    }
}