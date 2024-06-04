package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight

class VueCrée : BorderPane() {
    private val panneauCentre: VBox = VBox(15.0)
    private val panneauDroite: AnchorPane = AnchorPane()
    val boutonCree: Button = Button("Crée la Partie")
    val labelTop2 : Label
    val labelTop3 : Label
    val labelTop1 : Label
    val labelTop4 : Label
    val labelTop5 : Label
    val labelTop6 : Label
    val labelTop7 : Label
    val labelComboBox1:Label
    val comboBox1 = ComboBox<String>()

    init {
        panneauCentre.padding = Insets(30.0)
        panneauCentre.alignment = Pos.CENTER_LEFT

        this.labelComboBox1 = Label("Nombre de joueurs :")
        comboBox1.items.addAll("2", "3", "4", "5", "6", "7", "8")

        val inputStyle = "-fx-max-width: 300px; -fx-max-height: 100px;"
        comboBox1.style = inputStyle


        val vbox1 = VBox(5.0, labelComboBox1, comboBox1)


        vbox1.alignment = Pos.CENTER_LEFT

        this.labelComboBox1.styleClass.add("label")
        this.boutonCree.styleClass.add("btn")

        panneauCentre.children.addAll(vbox1)
        panneauCentre.padding = Insets(0.0, 150.0, 75.0, 150.0)

        boutonCree.style = "-fx-background-radius: 5px; -fx-background-color: white; -fx-text-fill: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF); -fx-max-width: 300px; -fx-max-height: 100px;"
        AnchorPane.setRightAnchor(boutonCree, 100.0)
        AnchorPane.setBottomAnchor(boutonCree, 100.0)
        panneauDroite.children.add(boutonCree)

        val stackPaneTop = StackPane()
        stackPaneTop.padding = Insets(20.0, 0.0, 0.0, 50.0)
        stackPaneTop.alignment = Pos.TOP_LEFT

        this.labelTop1 = Label("Crée une Partie")
        this.labelTop2 = Label("Crée une Partie")
        this.labelTop3 = Label("Crée une Partie")
        this.labelTop4 = Label("Crée une Partie")
        this.labelTop5 = Label("Crée une Partie")
        this.labelTop6 = Label("Crée une Partie")
        this.labelTop7 = Label("Crée une Partie")

        this.labelTop1.styleClass.add("Titre")
        this.labelTop2.styleClass.add("Titre1")
        this.labelTop3.styleClass.add("Titre2")
        this.labelTop4.styleClass.add("Titre3")
        this.labelTop5.styleClass.add("Titre4")
        this.labelTop6.styleClass.add("Titre5")
        this.labelTop7.styleClass.add("Titre6")

        StackPane.setMargin(labelTop1, Insets(50.0, 0.0, 0.0, 150.0))
        StackPane.setMargin(labelTop2, Insets(50.0, 0.0, 0.0, 155.0))
        StackPane.setMargin(labelTop3, Insets(50.0, 0.0, 0.0, 160.0))
        StackPane.setMargin(labelTop4, Insets(50.0, 0.0, 0.0, 165.0))
        StackPane.setMargin(labelTop5, Insets(50.0, 0.0, 0.0, 170.0))
        StackPane.setMargin(labelTop6, Insets(50.0, 0.0, 0.0, 175.0))
        StackPane.setMargin(labelTop7, Insets(50.0, 0.0, 0.0, 180.0))

        stackPaneTop.children.addAll(labelTop1, labelTop2, labelTop3, labelTop4, labelTop5, labelTop6, labelTop7)

        this.top = stackPaneTop
        this.center = panneauCentre
        this.right = panneauDroite

        this.style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"
    }

    private fun createColoredLabel(text: String, color: Color, style: String): Label {
        val label = Label(text)
        label.font = Font.font("Sans", FontWeight.BOLD, FontPosture.REGULAR, 48.0)
        label.textFill = color
        label.style = style
        return label
    }
}
