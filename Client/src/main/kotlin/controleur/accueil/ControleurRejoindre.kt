package controleur.accueil

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import vue.Rejoindre

class ControleurRejoindre(val vue: Rejoindre, val stage: Stage): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        stage.scene.root = vue
    }
}