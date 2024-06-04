package controleur.accueil

import javafx.event.ActionEvent
import javafx.stage.Stage

class ControleurFermerAppli(val stage: Stage) : javafx.event.EventHandler<ActionEvent> {
    override fun handle(e: ActionEvent) {
        stage.close()
    }
}