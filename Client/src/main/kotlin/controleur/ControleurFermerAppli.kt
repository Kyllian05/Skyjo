package controleur

import javafx.event.ActionEvent
import javafx.stage.Stage
import modele.Jeu
import vue.VueCrée

class ControleurFermerAppli(val stage: Stage) : javafx.event.EventHandler<ActionEvent> {
    override fun handle(e: ActionEvent) {
        stage.close()
    }
}