package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import java.io.FileInputStream

class CreationPartie : BorderPane() {
    private val panneauCentre: VBox = VBox(15.0)
    val boutonCree: Button = Button("Crée la Partie")
    val boutonRetour : Button = Button("")
    val Param : Array<String>
    val labelComboBox1:Label
    val comboBox = ComboBox<Int>()


    init {
        panneauCentre.padding = Insets(30.0)
        panneauCentre.alignment = Pos.CENTER

        this.labelComboBox1 = Label("Nombre de joueurs :")
        comboBox.getItems().addAll(2,3, 4, 5, 6, 7, 8)
        comboBox.setValue(2);

        val vbox1 = VBox(10.0, labelComboBox1, comboBox)
        vbox1.alignment = Pos.CENTER

        //Paramètrage ComboBox et son Label
        this.labelComboBox1.styleClass.add("labelAccueil")
        comboBox.setMaxSize(400.0, 100.0)
        comboBox.setPrefSize(400.0, 80.0)
        comboBox.styleClass.add("ButtonAccueil")

        panneauCentre.children.addAll(vbox1)
        panneauCentre.padding = Insets(0.0, 150.0, 75.0, 150.0)

        //Parametrage Boutton Crée
        boutonCree.setPrefSize(400.0, 100.0)
        boutonCree.setMaxSize(400.0, 80.0)
        this.boutonCree.styleClass.add("ButtonAccueil")
        panneauCentre.children.add(boutonCree)
        panneauCentre.spacing = 50.0

        val stackPaneTop = StackPane()
        stackPaneTop.padding = Insets(0.0, 0.0, 0.0, 0.0)
        stackPaneTop.alignment = Pos.TOP_LEFT

        //Paramètrage Boutton Retour
        val img_retour = ImageView(Image(FileInputStream("images/icone-fleche-gauche-noir.png")))
        img_retour.fitWidth = 40.0
        img_retour.fitHeight = 40.0
        boutonRetour.setGraphic(img_retour)
        boutonRetour.setMaxSize(40.0, 40.0)
        stackPaneTop.children.add(boutonRetour)
        this.boutonRetour.styleClass.add("ButtonAccueil")
        StackPane.setMargin(boutonRetour, Insets(10.0, 0.0, 0.0, 10.0))

        //Mise en place du Label "Crée une Partie" de la vue
        this.Param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")
        var posCreate = 150.0
        for (i in 0 until 7) {
            val label = Label("Crée une Partie")
            stackPaneTop.children.addAll(label)
            label.padding = Insets(50.0, 0.0, 0.0, posCreate)
            label.styleClass.add(Param[i])
            posCreate += 5.0
        }

        this.top = stackPaneTop
        this.center = panneauCentre

        this.styleClass.add("fondGeneral")
    }

}
