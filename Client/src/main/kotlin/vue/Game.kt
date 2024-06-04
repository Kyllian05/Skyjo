package vue

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.GridPane

class Game(val nbJoueur: Int) : GridPane() {
    val Cartes : Array<Button>
    init {
        this.Cartes = arrayOf()

        for (i in 0 until 13) {
            val carte : Button
            label.padding = Insets(posAccueil, 0.0, 0.0, 300.0)
            label.styleClass.add(Param[i])
            this.Cartes.plus(carte)

        }
    }
}