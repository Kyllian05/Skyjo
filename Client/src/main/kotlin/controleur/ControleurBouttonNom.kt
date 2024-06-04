package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import modele.Defausse
import modele.Jeu
import modele.Pioche
import vue.Accueil

class ControleurBouttonNom(private val vue: Accueil, private val j: Jeu): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        try {
            j.createPlayer(this.vue.inputName.text)
        } catch (e: Exception) {
            throw e
        }
        this.vue.addButtons()
    }
}