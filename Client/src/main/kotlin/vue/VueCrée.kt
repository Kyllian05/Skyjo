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
    private val boutonCree: Button = Button("Crée la Partie")

    init {
        panneauCentre.padding = Insets(30.0)
        panneauCentre.alignment = Pos.CENTER_LEFT

        val labelComboBox1 = Label("Nombre de joueurs :")
        val comboBox1 = ComboBox<String>()
        comboBox1.items.addAll("2", "3", "4", "5", "6", "7", "8")

        val labelComboBox2 = Label("Temps par tour :")
        val comboBox2 = ComboBox<String>()
        comboBox2.items.addAll("5s", "10s", "15s", "20s")

        val labelTextField = Label("Nom de la Partie :")
        val textField: TextField = TextField()

        val labelStyle = "-fx-font-size: 20px; -fx-text-fill: white;"
        labelComboBox1.style = labelStyle
        labelComboBox2.style = labelStyle
        labelTextField.style = labelStyle

        val inputStyle = "-fx-pref-width: 300px; -fx-pref-height: 40px;"
        comboBox1.style = inputStyle
        comboBox2.style = inputStyle
        textField.style = inputStyle

        val vbox1 = VBox(5.0, labelComboBox1, comboBox1)
        val vbox2 = VBox(5.0, labelComboBox2, comboBox2)
        val vbox3 = VBox(5.0, labelTextField, textField)

        vbox1.alignment = Pos.CENTER_LEFT
        vbox2.alignment = Pos.CENTER_LEFT
        vbox3.alignment = Pos.CENTER_LEFT

        panneauCentre.children.addAll(vbox1, vbox2, vbox3)
        panneauCentre.padding = Insets(100.0, 150.0, 100.0, 150.0)

        boutonCree.style = "-fx-background-radius: 5px; -fx-background-color: white; -fx-text-fill: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF); -fx-pref-width: 250px; -fx-pref-height: 40px;"
        AnchorPane.setRightAnchor(boutonCree, 150.0)
        AnchorPane.setBottomAnchor(boutonCree, 100.0)
        panneauDroite.children.add(boutonCree)

        val stackPaneTop = StackPane()
        stackPaneTop.padding = Insets(20.0, 0.0, 0.0, 50.0)
        stackPaneTop.alignment = Pos.TOP_LEFT

        // Styles pour les labels en haut
        val labelTopStyle = "-fx-font-size: 48px; -fx-font-weight: bold;"
        val labelTop1 = createColoredLabel("Crée une Partie", Color.RED, labelTopStyle)
        val labelTop2 = createColoredLabel("Crée une Partie", Color.ORANGE, labelTopStyle)
        val labelTop3 = createColoredLabel("Crée une Partie", Color.YELLOW, labelTopStyle)
        val labelTop4 = createColoredLabel("Crée une Partie", Color.GREEN, labelTopStyle)
        val labelTop5 = createColoredLabel("Crée une Partie", Color.BLUE, labelTopStyle)
        val labelTop6 = createColoredLabel("Crée une Partie", Color.CYAN, labelTopStyle)
        val labelTop7 = createColoredLabel("Crée une Partie", Color.WHITE, labelTopStyle)

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
