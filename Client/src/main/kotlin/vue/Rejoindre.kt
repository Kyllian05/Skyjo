package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.control.ListView
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight

class Rejoindre : BorderPane() {
    private val panneauCentre: VBox = VBox(15.0)
    private val panneauDroite: AnchorPane = AnchorPane()

    val boutonRejoindre: Button = Button("Rejoindre la Partie")
    val boutonRetour : Button = Button("<")
    val labelTop2 : Label
    val labelTop3 : Label
    val labelTop1 : Label
    val labelTop4 : Label
    val labelTop5 : Label
    val labelTop6 : Label
    val labelTop7 : Label
    val labelPartie:Label
    val ListePartie = ListView<String>()


    init {
        panneauCentre.padding = Insets(30.0)
        panneauCentre.alignment = Pos.CENTER_LEFT

        this.labelPartie = Label("Parties:")


        val vbox1 = VBox(5.0, labelPartie, ListePartie)
        vbox1.alignment = Pos.CENTER_LEFT

        //Paramètrage ComboBox et son Label
        this.labelPartie.styleClass.add("labelAccueil")
        ListePartie.styleClass.add("ButtonAccueil")

        panneauCentre.children.addAll(vbox1)
        panneauCentre.padding = Insets(0.0, 150.0, 75.0, 150.0)

        //Parametrage Boutton Crée
        boutonRejoindre.setPrefSize(400.0, 100.0)
        boutonRejoindre.setMaxSize(400.0, 80.0)
        AnchorPane.setRightAnchor(boutonRejoindre, 100.0)
        AnchorPane.setBottomAnchor(boutonRejoindre, 100.0)
        panneauDroite.children.add(boutonRejoindre)
        this.boutonRejoindre.styleClass.add("ButtonAccueil")

        val stackPaneTop = StackPane()
        stackPaneTop.padding = Insets(0.0, 0.0, 0.0, 0.0)
        stackPaneTop.alignment = Pos.TOP_LEFT

        //Paramètrage Boutton Retour
        boutonRetour.setPrefSize(50.0, 50.0)
        boutonRetour.setMaxSize(40.0, 40.0)
        stackPaneTop.children.add(boutonRetour)
        this.boutonRetour.styleClass.add("ButtonAccueil")
        StackPane.setMargin(boutonRetour, Insets(10.0, 0.0, 0.0, 10.0))

        //Parmètrage Titre
        this.labelTop1 = Label("Rejoindre une partie")
        this.labelTop2 = Label("Rejoindre une partie")
        this.labelTop3 = Label("Rejoindre une partie")
        this.labelTop4 = Label("Rejoindre une partie")
        this.labelTop5 = Label("Rejoindre une partie")
        this.labelTop6 = Label("Rejoindre une partie")
        this.labelTop7 = Label("Rejoindre une partie")

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

}
