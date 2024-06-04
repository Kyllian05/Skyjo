package vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.ProgressIndicator
import javafx.scene.layout.*


class Salon : GridPane() {
    val Salons: Array<Label>
    val Param : Array<String>
    val Loader : ProgressIndicator
    val ListeJoueurs = ListView<Int>()

    init {
        this.style = "-fx-background-color : linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"

        this.vgap = 10.0

        this.Salons = arrayOf()
        this.Param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")

        var posSalon = 95.0
        for (i in 0 until 7) {
            val label = Label("Salon")
            this.add(label, 0, 0, 2, 1)
            label.padding = Insets(posSalon, 0.0, 0.0, 300.0)
            label.styleClass.add(Param[i])
            this.Salons.plus(label)
            posSalon -= 15.0
        }

        //Mise en place du Loader dans une Hbox et Ajout de contrainte
        this.Loader = ProgressIndicator()

        val PanneauProgress = HBox()
        PanneauProgress.children.add(Loader)
        HBox.setHgrow(Loader, Priority.ALWAYS)
        PanneauProgress.alignment = Pos.CENTER

        this.add(PanneauProgress, 0, 1)
        GridPane.setHalignment(PanneauProgress, HPos.CENTER)
        GridPane.setMargin(PanneauProgress, Insets(100.0, 0.0, 0.0,0.0))

        val columnConstraints = ColumnConstraints()
        columnConstraints.hgrow = Priority.ALWAYS
        this.columnConstraints.add(columnConstraints)

        Loader.styleClass.add("Progress")

        val labelAttente = Label("En attente de joueurs")
        labelAttente.style = "-fx-text-fill: white;"
        this.add(labelAttente, 0, 3)
        GridPane.setHalignment(labelAttente, HPos.CENTER)

        //Mise en place du ListView

        val stackPane = StackPane()
        stackPane.maxWidth = 500.0
        stackPane.maxHeight = 200.0

        stackPane.children.add(ListeJoueurs)
        StackPane.setAlignment(ListeJoueurs, Pos.CENTER)

        this.add(stackPane, 0, 4)
        GridPane.setHalignment(stackPane, HPos.CENTER)
        GridPane.setMargin(stackPane, Insets(75.0,0.0,0.0,0.0))

    }
}