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
    private val panneauCentre: VBox = VBox(10.0)
    private val boutoncree: Button = Button("Crée la partie")

    init {
        panneauCentre.padding = Insets(20.0)
        panneauCentre.alignment = Pos.CENTER

        val labelComboBox1 = Label("Nombre de Joueurs:")
        val comboBox1 = ComboBox<String>()
        comboBox1.items.addAll("2", "3", "4", "5", "6", "7", "8")

        val labelComboBox2 = Label("Temp par tour:")
        val comboBox2 = ComboBox<String>()
        comboBox2.items.addAll("5s", "10s", "15s", "20s")

        val labelTextField = Label("Nom de la Partie:")
        val textField: TextField = TextField()

        val labelStyle = "-fx-font-size: 14px;"
        labelComboBox1.style = labelStyle
        labelComboBox2.style = labelStyle
        labelTextField.style = labelStyle

        val comboBoxStyle = "-fx-pref-width: 150px;"
        comboBox1.style = comboBoxStyle
        comboBox2.style = comboBoxStyle

        panneauCentre.children.addAll(
            labelComboBox1, comboBox1,
            labelComboBox2, comboBox2,
            labelTextField, textField,
            boutoncree
        )

        val stackPaneTop = StackPane()
        stackPaneTop.padding = Insets(20.0, 50.0, 5.0, 0.0)
        stackPaneTop.alignment = Pos.TOP_CENTER

        // Styles pour les labels en haut
        val labelTopStyle = "-fx-font-size: 24px; -fx-font-weight: bold;"
        val labelTop1 = createColoredLabel("Crée une Partie", Color.RED, labelTopStyle)
        val labelTop2 = createColoredLabel("Crée une Partie", Color.ORANGE, labelTopStyle)
        val labelTop3 = createColoredLabel("Crée une Partie", Color.YELLOW, labelTopStyle)
        val labelTop4 = createColoredLabel("Crée une Partie", Color.GREEN, labelTopStyle)
        val labelTop5 = createColoredLabel("Crée une Partie", Color.BLUE, labelTopStyle)
        val labelTop6 = createColoredLabel("Crée une Partie", Color.CYAN, labelTopStyle)
        val labelTop7 = createColoredLabel("Crée une Partie", Color.WHITE, labelTopStyle)

        stackPaneTop.children.addAll(labelTop1, labelTop2, labelTop3, labelTop4, labelTop5, labelTop6, labelTop7)

        boutoncree.alignment = Pos.BOTTOM_CENTER
        boutoncree.style = "-fx-background-radius: 5px; -fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"

        this.top = stackPaneTop
        this.center = panneauCentre

        this.style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"
    }

    private fun createColoredLabel(text: String, color: Color, style: String): Label {
        val label = Label(text)
        label.font = Font.font("Sans", FontWeight.BLACK, FontPosture.REGULAR, 24.0)
        label.textFill = color
        label.style = style
        return label
    }
}
