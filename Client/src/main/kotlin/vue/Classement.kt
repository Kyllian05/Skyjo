package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.VPos
import javafx.scene.control.Button
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

class Classement(val scores: LinkedHashMap<Int, Int>, val names: HashMap<Int, String>): GridPane() {
    val Param : Array<String>
    val exit: Button
    init {
        this.styleClass.add("fondGeneral")
        this.exit = Button("Quitter")
        exit.setPrefSize(300.0, 80.0)
        exit.styleClass.add("ButtonAccueil")

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
        recWinner.width = 500.0
        recWinner.height = 100.0
        recWinner.fill = Color.TRANSPARENT
        recWinner.stroke = Color.WHITE
        recWinner.strokeWidth = 3.0
        recWinner.arcHeight = 20.0
        recWinner.arcWidth = 20.0

        val stackPane = StackPane()
        var first = true
        var i = 1
        for (el in scores) {
            if (first) {
                val lab = Label("$i. ${names[el.key]} ${el.value} points")
                lab.styleClass.add("classementName")
                stackPane.children.addAll(recWinner, lab)
                first = false
                i++
            } else {
                val lab = Label("$i. ${names[el.key]} ${el.value} points")
                lab.styleClass.add("classementName")
                GridPane.setHalignment(lab, HPos.CENTER)
                this.add(lab, 0,i+1)
                i++
            }
        }

        GridPane.setHalignment(stackPane, HPos.CENTER)
        GridPane.setHalignment(exit, HPos.CENTER)
        GridPane.setValignment(exit, VPos.BOTTOM)

        val columnConstraints = ColumnConstraints()
        columnConstraints.hgrow = Priority.ALWAYS
        this.columnConstraints.add(columnConstraints)

        this.add(stackPane, 0, 1)
        this.add(exit, 0, i+4)

    }

    fun fixeListener(handler: EventHandler<ActionEvent>) {
        this.exit.addEventHandler(ActionEvent.ACTION, handler)
    }
}