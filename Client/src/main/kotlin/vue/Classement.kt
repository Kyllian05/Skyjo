package vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.ProgressIndicator
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.shape.Rectangle

class Classement : GridPane() {
    val Param : Array<String>
    init {
        this.style = "-fx-background-color : linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"

        this.Param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")

        this.vgap = 40.0

        var posSalon = 5.0
        for (i in 0 until 7) {
            val label = Label("Classement")
            this.add(label, 0, 0, 3, 1)
            label.padding = Insets(posSalon, 0.0, 0.0, 150.0)
            label.styleClass.add(Param[i])
            posSalon += 15.0
        }

        val recWinner = Rectangle()
        recWinner.width = 600.0
        recWinner.height = 200.0
        recWinner.fill = Color.TRANSPARENT
        recWinner.stroke = Color.WHITE
        recWinner.strokeWidth = 3.0
        recWinner.arcHeight = 20.0
        recWinner.arcWidth = 20.0

        val stackPane = StackPane()
        var gagnant = Label("1. Winner")
        var j2 = Label("2. . . .")
        var j3 = Label("3. . . .")
        var j4 = Label("4. . . .")
        var j5 = Label("5. . . .")

        gagnant.style = "-fx-font-size: 60px; -fx-text-fill: white;"
        j2.style = "-fx-text-fill: white;"
        j3.style = "-fx-text-fill: white;"
        j4.style = "-fx-text-fill: white;"
        j5.style = "-fx-text-fill: white;"

        stackPane.children.addAll(recWinner, gagnant)


        GridPane.setHalignment(stackPane, HPos.CENTER)
        GridPane.setHalignment(j2, HPos.CENTER)
        GridPane.setHalignment(j3, HPos.CENTER)
        GridPane.setHalignment(j4, HPos.CENTER)
        GridPane.setHalignment(j5, HPos.CENTER)

        val columnConstraints = ColumnConstraints()
        columnConstraints.hgrow = Priority.ALWAYS
        this.columnConstraints.add(columnConstraints)

        this.add(stackPane, 0, 1)
        this.add(j2, 0,3)
        this.add(j3, 0,4)
        this.add(j4, 0,5)
        this.add(j5, 0,6)



    }

}