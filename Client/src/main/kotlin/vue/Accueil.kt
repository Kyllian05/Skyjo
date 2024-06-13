package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.paint.Color


class Accueil : GridPane() {

    val PanneauLeft : VBox
    private val PanneauRight : AnchorPane
    val CreateBtn : Button
    val JoinBtn : Button
    val ExitBtn : Button
    val Param : Array<String>
    val UpdateArea : TextArea
    val VersionLab : Label
    val nameLabel : Label
    val inputName: TextField
    val submitBtn: Button
    val progress: ProgressIndicator


    init {
        this.PanneauLeft = VBox()
        this.PanneauRight = AnchorPane()
        this.CreateBtn = Button("Crée une partie")
        this.JoinBtn = Button("Rejoindre une partie")
        this.ExitBtn = Button("Quitter")
        this.UpdateArea = TextArea("Infos sur les dernières nouveautés")
        this.VersionLab = Label("V1.2.1")
        this.nameLabel = Label("Entrez votre pseudo :")
        this.inputName = TextField()
        this.submitBtn = Button("Valider")
        this.progress = ProgressIndicator()

        progress.styleClass.add("Progress")

        this.Param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")
        var posAccueil = 5.0
        for (i in 0 until 7) {
            val label = Label("SKYJO")
            this.add(label, 0, 0, 2, 1)
            label.padding = Insets(posAccueil, 0.0, 0.0, 300.0)
            label.styleClass.add(Param[i])
            posAccueil += 15.0
        }

        val row1 = RowConstraints()
        row1.percentHeight = 30.0
        val row2 = RowConstraints()
        row2.percentHeight = 70.0
        this.rowConstraints.addAll(row1, row2)

        val col1 = ColumnConstraints()
        col1.percentWidth = 60.0
        val col2 = ColumnConstraints()
        col2.percentWidth = 40.0
        this.columnConstraints.addAll(col1, col2)

        this.styleClass.add("fondGeneral")

        this.add(PanneauLeft, 0, 1)
        this.add(PanneauRight, 1, 1)


        PanneauLeft.spacing = 20.0
        PanneauLeft.alignment = Pos.CENTER_LEFT
        PanneauLeft.padding = Insets(20.0,100.0, 20.0, 300.0)


        CreateBtn.setPrefSize(400.0, 100.0)
        this.CreateBtn.styleClass.add("ButtonAccueil")

        JoinBtn.setPrefSize(400.0, 100.0)
        this.JoinBtn.styleClass.add("ButtonAccueil")

        ExitBtn.setPrefSize(400.0, 100.0)
        this.ExitBtn.styleClass.add("ButtonAccueil")

        // Intput pour le Pseudo
        this.inputName.setMaxSize(400.0, 100.0)
        this.inputName.setPrefSize(400.0, 80.0)
        this.inputName.styleClass.add("input")

        submitBtn.setPrefSize(400.0, 100.0)
        this.submitBtn.styleClass.add("ButtonAccueil")

        this.nameLabel.styleClass.add("labelAccueil")


        AnchorPane.setBottomAnchor(UpdateArea, 20.0)
        AnchorPane.setRightAnchor(UpdateArea, 10.0)
        AnchorPane.setBottomAnchor(VersionLab, 0.0)
        AnchorPane.setRightAnchor(VersionLab, 10.0)
        UpdateArea.isEditable = false
        UpdateArea.setPrefSize(200.0, 250.0)
        UpdateArea.isWrapText = true
        VersionLab.style = "-fx-text-fill: darkblue;"

        PanneauLeft.children.addAll(nameLabel, inputName, submitBtn)
        PanneauRight.children.addAll(UpdateArea, VersionLab)

    }

    fun addButtons() {
        PanneauLeft.children.removeAll(nameLabel, inputName, submitBtn)
        PanneauLeft.children.addAll(CreateBtn, JoinBtn, ExitBtn)
    }

    fun fixeListener(b: Button, c: EventHandler<ActionEvent>) {
        b.addEventHandler(ActionEvent.ACTION, c)
    }

    fun showLoader() {
        PanneauLeft.children.remove(submitBtn)
        PanneauLeft.children.add(progress)
    }

    fun removeLoader() {
        PanneauLeft.children.remove(progress)
        PanneauLeft.children.add(submitBtn)
    }

}