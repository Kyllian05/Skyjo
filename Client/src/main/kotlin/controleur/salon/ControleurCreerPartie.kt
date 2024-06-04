package controleur.salon

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import vue.CreationPartie

class ControleurCreerPartie(val vue: CreationPartie, val stage: Stage): EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent) {
        stage.scene.root = vue
    }
}