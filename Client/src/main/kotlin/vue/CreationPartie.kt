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
    val labelTop2 : Label
    val labelTop3 : Label
    val labelTop1 : Label
    val labelTop4 : Label
    val labelTop5 : Label
    val labelTop6 : Label
    val labelTop7 : Label
    val labelComboBox1:Label
    val comboBox = ComboBox<Int>()


    init {
        panneauCentre.padding = Insets(30.0)
        panneauCentre.alignment = Pos.CENTER

        this.labelComboBox1 = Label("Nombre de joueurs :")
        comboBox.getItems().addAll(2,3, 4, 5, 6, 7, 8)
        comboBox.setValue(2);
        comboBox.styleClass.add("textarea")

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

        //Parmètrage Titre
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

        this.style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"
    }

}
