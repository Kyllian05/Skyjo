package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import vue.Accueil
import vue.VueCrée

class ControleurBoutonCreerPartieRedirection(val vue: VueCrée,val stage: Stage):EventHandler<ActionEvent> {
    override fun handle(p0: ActionEvent) {
        stage.scene.root = vue
    }
}