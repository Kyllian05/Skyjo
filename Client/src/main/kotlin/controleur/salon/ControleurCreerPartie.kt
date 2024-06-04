package controleur.salon

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import vue.VueCrée

class ControleurCreerPartie(val vue: VueCrée, val stage: Stage): EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent) {
        stage.scene.root = vue
    }
}