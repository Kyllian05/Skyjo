package controleur.accueil

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage

class BoutonRetour(val stage : Stage,val vue : vue.Accueil) : EventHandler<ActionEvent>{
    override fun handle(p0: ActionEvent) {
        stage.scene.root = vue
    }
}