package vue

import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.stage.Stage
class Trois : BorderPane() {
    val PanneauCentre = VBox(10.0)


    init {
        this.style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"

        setupCenterPanel()


        this.center = PanneauCentre
    }



    private fun setupCenterPanel() {
        PanneauCentre.alignment = Pos.CENTER
        PanneauCentre.spacing = 20.0
        PanneauCentre.padding = javafx.geometry.Insets(10.0)

        val gridPane = createGridPane(3, 4, 75.0, 100.0)
        val playerLabel = Label("Vous")
        playerLabel.font = Font("Arial", 20.0)
        playerLabel.textFill = Color.WHITE
        val scoreLabel = Label("Score:")
        scoreLabel.font = Font("Arial", 20.0)
        scoreLabel.textFill = Color.WHITE

        val quitButton = Button("Quitter la Partie")

        PanneauCentre.children.addAll(playerLabel, scoreLabel, gridPane, quitButton)
        VBox.setMargin(quitButton, javafx.geometry.Insets(20.0, 0.0, 0.0, 0.0))
    }

    private fun createGridPane(rows: Int, cols: Int, tileWidth: Double, tileHeight: Double): GridPane {
        val gridPane = GridPane()
        gridPane.hgap = 10.0
        gridPane.vgap = 10.0
        gridPane.alignment = Pos.CENTER

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val tile = Rectangle(tileWidth, tileHeight)
                tile.styleClass.add("carte")
                tile.fill = Color.WHITE
                gridPane.add(tile, col, row)
            }
        }
        return gridPane
    }
}
