package vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.control.ListView
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import modele.data.Party
import java.io.FileInputStream

class Rejoindre : BorderPane() {
    private val panneauCentre: VBox = VBox(15.0)
    private val panneauDroite: AnchorPane = AnchorPane()

    val boutonRejoindre: Button = Button("Rejoindre la Partie")
    val boutonRetour : Button = Button("")
    val loader: ProgressIndicator = ProgressIndicator()
    val Param : Array<String>
    val labelPartie:Label
    val ListePartie = ListView<Party>()
    val vbox1: VBox
    //modif kyllian
    val searchLabel = Label("Rejoindre avec un ID :")
    val searchGame = TextField("")
    val chargeBtn = Button("Rechercher des parties")


    init {
        panneauCentre.padding = Insets(30.0)
        panneauCentre.alignment = Pos.CENTER_LEFT

        this.labelPartie = Label("Parties:")

        searchGame.promptText = "Entrée un ID"
        searchGame.style = "-fx-font-size: 20px; -fx-font-weight: bolder;"
        searchGame.prefHeight = 50.0
        searchGame.maxWidth = 200.0

        val search = HBox()
        search.children.add(searchGame)
        chargeBtn.styleClass.add("ButtonAccueil")
        chargeBtn.prefHeight = 50.0
        chargeBtn.style = "-fx-font-size: 20px"

        loader.styleClass.add("Progress")
        boutonRejoindre.setPrefSize(300.0, 50.0)
        this.ListePartie.opacity = 0.5
        this.vbox1 = VBox(30.0, searchLabel, search, labelPartie, ListePartie, chargeBtn)
        vbox1.alignment = Pos.CENTER_LEFT


        this.labelPartie.styleClass.add("labelAccueil")
        this.searchLabel.styleClass.add("labelAccueil")
        ListePartie.styleClass.add("ButtonAccueil")

        panneauCentre.children.addAll(vbox1)
        panneauCentre.padding = Insets(0.0, 150.0, 75.0, 150.0)

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
        val img_retour = ImageView(Image(FileInputStream("images/icone-fleche-gauche-noir.png")))
        img_retour.fitWidth = 40.0
        img_retour.fitHeight = 40.0
        boutonRetour.setGraphic(img_retour)
        boutonRetour.setMaxSize(40.0, 40.0)
        stackPaneTop.children.add(boutonRetour)
        this.boutonRetour.styleClass.add("ButtonAccueil")
        StackPane.setMargin(boutonRetour, Insets(10.0, 0.0, 0.0, 10.0))

        //Mise en place du Label "Rejoindre une partie" de la vue
        this.Param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")
        var posJoin = 150.0
        for (i in 0 until 7) {
            val label = Label("Rejoindre une partie")
            stackPaneTop.children.addAll(label)
            label.padding = Insets(50.0, 0.0, 0.0, posJoin)
            label.styleClass.add(Param[i])
            posJoin += 5.0
        }



        this.top = stackPaneTop
        this.center = panneauCentre
        this.right = panneauDroite

        this.styleClass.add("fondGeneral")
    }

    fun hideLoader() {
        this.vbox1.children.remove(this.loader)
    }

    fun showLoader() {
        this.vbox1.children.remove(this.chargeBtn)
        this.vbox1.children.add(this.loader)
    }
}

