package vue

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment
import java.io.FileInputStream

class Game(nbJoueur: Int, height: Double, width: Double) : GridPane() {
    // Propriétés
    private val defaultImg = Image(FileInputStream("images/cartes/carteSKYJO.png"))
    private var nbJoueur : Int = nbJoueur
    var plateaux = arrayOf<Array<ObjectProperty<Image>>>()
    var defausse = arrayOf<ObjectProperty<Image>>()
    var pioche = arrayOf<ObjectProperty<Image>>()
    var playersName = arrayOf<StringProperty>()
    var playersLabel = arrayOf<Label>()
    var scoreLabels = arrayOf<StringProperty>()
    private var playerCount = 0
    private var scoreCount = 0
    val currentPlayerLabel = Label("")

    var coef:Double = ((width+height)*2)/((1920+1080)/2)/3




     // UI
     private val PanneauCentre = VBox(10.0*coef)
    val pileDefausse = createPileCarte(isDefausse = true)
    val pilePioche = createPileCarte()
    var myCards : GridPane? = null

    init {
        // Background
        println("$width, $height")
        this.style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"

        // Players name
        for(i in 0..<this.nbJoueur){
            playersName += SimpleStringProperty("")
        }

        // Score labels
        for(i in 0..<this.nbJoueur){
            this.scoreLabels += SimpleStringProperty("")
        }

        currentPlayerLabel.style = "-fx-font-size: ${20*coef}px; -fx-font-weight: bolder; -fx-text-fill: #ffffff;"
        currentPlayerLabel.isWrapText = true
        currentPlayerLabel.maxWidth = 300.0
        currentPlayerLabel.textAlignment = TextAlignment.CENTER

        // Titres



        /**
         * Vue pour 2 joueurs
         */
        if (nbJoueur == 2) {
            setupCenterPanel()

            val PanneauOpponnent = setupOpponentPanel("right")

            val PanneauPiocheDefausse = HBox()
            if (width >= 1920) {
                val param : Array<String>
                val Titre = GridPane()
                val Titre1 = GridPane()
                param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")
                var posAccueil = 5.0*coef
                for (i in 0 until 7) {
                    val label = Label("SKYJO")
                    val label1 = Label("SKYJO")
                    Titre.add(label, 0, 0)
                    Titre1.add(label1, 0, 0)
                    label.padding = Insets(posAccueil, 0.0*coef, 0.0*coef, 0.0*coef)
                    label.styleClass.add(param[i])
                    label1.padding = Insets(posAccueil, 0.0*coef, 0.0*coef, 0.0*coef)
                    label1.styleClass.add(param[i])
                    posAccueil += 15.0*coef
                }
                PanneauPiocheDefausse.children.addAll(Titre, pilePioche, currentPlayerLabel, pileDefausse, Titre1)
            }else{
                PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            }
            PanneauPiocheDefausse.spacing = 150.0*coef
            PanneauPiocheDefausse.alignment = Pos.CENTER

            GridPane.setMargin(PanneauCentre, Insets(0.0*coef, 40.0*coef, 0.0*coef, 0.0*coef))
            GridPane.setMargin(PanneauCentre, Insets(0.0*coef, 0.0*coef, 0.0*coef, 180.0*coef))

            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }

            this.add(PanneauOpponnent, 0, 0)
            this.add(PanneauPiocheDefausse, 0, 1)
            this.add(PanneauCentre, 0, 2)

            VBox.setVgrow(PanneauOpponnent, Priority.ALWAYS)
            VBox.setVgrow(PanneauCentre, Priority.ALWAYS)
            VBox.setVgrow(PanneauPiocheDefausse, Priority.ALWAYS)

            this.vgap = 20.0*coef
            this.alignment = Pos.CENTER
        }

        /**
         * Vue pour 3 joueurs
         */
        if (nbJoueur == 3) {
            setupCenterPanel()

            val joueur1 = setupOpponentPanel("right")
            val joueur2 = setupOpponentPanel("left")

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            PanneauPiocheDefausse.spacing = 75.0*coef

            this.add(joueur1, 0, 1, 1, 1)
            this.add(joueur2, 2, 1, 1, 1)
            this.add(PanneauPiocheDefausse, 1, 1, 1, 1)
            this.add(PanneauCentre, 1, 2, 1, 1)

            currentPlayerLabel.padding = Insets(100.0*coef,0.0*coef,0.0*coef,0.0*coef)
            HBox.setMargin(currentPlayerLabel, Insets(0.0*coef, 0.0*coef, 0.0*coef, 30.0*coef))

            GridPane.setMargin(PanneauPiocheDefausse, Insets(100.0*coef,0.0*coef,0.0*coef,60.0*coef))
            GridPane.setMargin(PanneauCentre, Insets(0.0*coef,0.0*coef,0.0*coef,130.0*coef))

            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }

            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
        }

        /**
         * Vue pour 4 joueurs
         */
        if (nbJoueur == 4) {
            setupCenterPanel()

            val joueur1 = setupOpponentPanel("right")
            val joueur2 = setupOpponentPanel("right")
            val joueur3 = setupOpponentPanel("left")

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            PanneauPiocheDefausse.spacing = 75.0*coef


            this.add(joueur1, 0, 1, 1, 1)
            this.add(joueur2, 1, 0, 1, 1)
            this.add(PanneauPiocheDefausse, 1, 1, 1, 1)
            this.add(joueur3, 2, 1, 1, 1)
            this.add(PanneauCentre, 1, 2, 1, 1)

            currentPlayerLabel.padding = Insets(100.0*coef,0.0*coef,0.0*coef,0.0*coef)
            HBox.setMargin(currentPlayerLabel, Insets(0.0*coef, 0.0*coef, 0.0*coef, 30.0*coef))

            GridPane.setMargin(PanneauPiocheDefausse, Insets(50.0*coef,0.0*coef,0.0*coef,50.0*coef))
            GridPane.setMargin(PanneauCentre, Insets(0.0*coef,0.0*coef,0.0*coef,120.0*coef))
            GridPane.setMargin(joueur2, Insets(0.0*coef,100.0*coef,0.0*coef,0.0*coef))

            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }

            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
        }

        /**
         * Vue pour 5 joueurs
         */
        if (nbJoueur == 5) {
            setupCenterPanel()

            val joueur1 = setupOpponentPanel("left")
            val joueur2 = setupOpponentPanel("right")
            val joueur3 = setupOpponentPanel("left")
            val joueur4 = setupOpponentPanel("right")

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            PanneauPiocheDefausse.spacing = 100.0*coef



            this.add(joueur2, 0, 0, 4, 1)
            this.add(joueur3, 0, 0, 4, 1)
            this.add(PanneauPiocheDefausse, 1, 1, 4, 1)
            this.add(joueur4, 3, 2, 1, 1)
            this.add(joueur1, 0, 2, 1, 1)
            this.add(PanneauCentre, 2, 2, 1, 1)

            currentPlayerLabel.padding = Insets(100.0*coef,0.0*coef,0.0*coef,0.0*coef)
            HBox.setMargin(currentPlayerLabel, Insets(0.0*coef, 0.0*coef, 0.0*coef, 30.0*coef))

            GridPane.setMargin(joueur2, Insets(0.0*coef, 800.0*coef,0.0*coef,0.0*coef))
            GridPane.setMargin(joueur3, Insets(0.0*coef, 0.0*coef,0.0*coef,800.0*coef))
            GridPane.setMargin(PanneauCentre, Insets(0.0*coef,50.0*coef,0.0*coef,0.0*coef))

            PanneauPiocheDefausse.spacing = 75.0*coef

            // Ajoutez les contraintes de ligne
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }

            // Ajoutez les contraintes de colonne
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
        }

        /**
         * Vue pour 6 joueurs
         */
        if (nbJoueur == 6) {
            setupCenterPanel()

            val joueur1 = setupOpponentPanel("left")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("right")
            val joueur4 = setupOpponentPanel("right")
            val joueur5 = setupOpponentPanel("right")

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            PanneauPiocheDefausse.spacing = 75.0*coef


            this.add(joueur2, 0, 0)
            this.add(joueur3, 1, 0)
            this.add(joueur4, 2, 0)
            this.add(joueur1, 0, 2)


            this.add(joueur5, 2, 2)
            this.add(PanneauPiocheDefausse, 1, 1)
            this.add(PanneauCentre, 1, 2)

            currentPlayerLabel.padding = Insets(100.0*coef,0.0*coef,0.0*coef,0.0*coef)
            HBox.setMargin(currentPlayerLabel, Insets(0.0*coef, 0.0*coef, 0.0*coef, 30.0*coef))

            GridPane.setMargin(joueur3, Insets(0.0*coef,110.0*coef,0.0*coef,0.0*coef))
            GridPane.setMargin(PanneauCentre, Insets(0.0*coef,0.0*coef,0.0*coef,120.0*coef))
            GridPane.setMargin(PanneauPiocheDefausse, Insets(40.0*coef,0.0*coef,0.0*coef,50.0*coef))

            // Ajoutez les contraintes de ligne
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }

            // Ajoutez les contraintes de colonne
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
        }

        /**
         * Vue pour 7 joueurs
         */
        if (nbJoueur == 7) {
            setupCenterPanel()

            val joueur1 = setupOpponentPanel("left")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("left")
            val joueur4 = setupOpponentPanel("right")
            val joueur5 = setupOpponentPanel("right")
            val joueur6 = setupOpponentPanel("right")

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            PanneauPiocheDefausse.spacing = 50.0*coef


            this.add(joueur3, 0, 0)
            this.add(joueur4, 2, 0)
            this.add(joueur2, 0, 1)
            this.add(joueur5, 2, 1)
            this.add(joueur1, 0, 2)

            this.add(joueur6, 2, 2)
            this.add(PanneauPiocheDefausse, 1, 1)
            this.add(PanneauCentre, 1, 2)


            currentPlayerLabel.padding = Insets(100.0*coef,0.0*coef,0.0*coef,0.0*coef)
            HBox.setMargin(currentPlayerLabel, Insets(0.0*coef, 0.0*coef, 0.0*coef, 30.0*coef))

            GridPane.setMargin(joueur3, Insets(0.0*coef,0.0*coef,10.0*coef,0.0*coef))
            GridPane.setMargin(joueur4, Insets(0.0*coef,0.0*coef,10.0*coef,0.0*coef))
            GridPane.setMargin(joueur1, Insets(0.0*coef,0.0*coef,50.0*coef,0.0*coef))
            GridPane.setMargin(joueur6, Insets(0.0*coef,0.0*coef,50.0*coef,0.0*coef))

            GridPane.setMargin(PanneauPiocheDefausse, Insets(60.0*coef,0.0*coef,0.0*coef,80.0*coef))
            GridPane.setMargin(PanneauCentre, Insets(0.0*coef,0.0*coef,0.0*coef,140.0*coef))

            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }


            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
        }

        /**
         * Vue pour 8 joueurs
         */
        if (nbJoueur == 8) {
            isGridLinesVisible = true
            setupCenterPanel()

            val joueur1 = setupOpponentPanel("left")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("left")
            val joueur4 = setupOpponentPanel("right")
            val joueur5 = setupOpponentPanel("right")
            val joueur6 = setupOpponentPanel("right")
            val joueur7 = setupOpponentPanel("right")

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(pilePioche, currentPlayerLabel, pileDefausse)
            PanneauPiocheDefausse.spacing = 100.0*coef

            this.add(joueur1, 0, 2)
            this.add(joueur4, 1, 0)
            this.add(joueur2, 0, 1)
            this.add(joueur5, 2, 0)
            this.add(joueur3, 0, 0)
            this.add(joueur6, 2, 1)
            this.add(joueur7, 2, 2)
            this.add(PanneauPiocheDefausse, 1, 1)
            this.add(PanneauCentre, 1, 2)

            GridPane.setMargin(joueur1, Insets(0.0*coef,0.0*coef,50.0*coef,0.0*coef))
            GridPane.setMargin(joueur3, Insets(10.0*coef,0.0*coef,0.0*coef,0.0*coef))

            GridPane.setMargin(joueur4, Insets(10.0*coef,120.0*coef,30.0*coef,0.0*coef))

            GridPane.setMargin(joueur5, Insets(10.0*coef,0.0*coef,0.0*coef,0.0*coef))
            GridPane.setMargin(joueur7, Insets(0.0*coef,0.0*coef,50.0*coef,0.0*coef))

            GridPane.setMargin(PanneauCentre,  Insets(0.0*coef,0.0*coef,0.0*coef,120.0*coef))

            GridPane.setMargin(PanneauPiocheDefausse, Insets(70.0*coef,0.0*coef,0.0*coef,60.0*coef))


            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }
            RowConstraints().apply {
                vgrow = Priority.ALWAYS
                this@Game.rowConstraints.add(this)
            }


            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }
            ColumnConstraints().apply {
                hgrow = Priority.ALWAYS
                this@Game.columnConstraints.add(this)
            }


        }

    }

    private fun setupCenterPanel() {
        PanneauCentre.alignment = Pos.CENTER
        PanneauCentre.spacing = 20.0*coef
        PanneauCentre.padding = Insets(10.0*coef)
        val playerLabelVous = Label("Vous")
        playerLabelVous.style = "-fx-font-size: ${20*coef}px; -fx-font-weight: bolder; -fx-text-fill: #ffffff;"
        this.playersLabel += playerLabelVous
        // Score label
        val scoreLabel = Label("Points :")
        scoreLabel.textProperty().bind(scoreLabels[scoreCount])
        scoreLabel.style = "-fx-font-size: ${24*coef}px; -fx-font-weight: bolder; -fx-text-fill: #ffffff;"
        scoreCount++
        // Box des labels
        val labelVBox = VBox(10.0*coef, playerLabelVous, scoreLabel)
        labelVBox.alignment = Pos.BOTTOM_LEFT
        // GridPane pour les cartes

        val gridPane = createGridPane(3, 4, 70.0*coef, 105.0*coef, myPane = true)


        val hBox = HBox(10.0*coef, gridPane, labelVBox)
        hBox.alignment = Pos.CENTER
        PanneauCentre.children.add(hBox)
    }

    private fun setupOpponentPanel(cote : String): VBox {
        // Setup Vbox
        val panneau = VBox(10.0*coef)
        panneau.alignment = Pos.CENTER
        panneau.spacing = 20.0*coef
        panneau.padding = Insets(10.0*coef)
        // Setup cards
        val gridPane = createGridPane(3, 4, 50.0*coef, 75.0*coef)
        // Player label
        val playerLabel = Label("")
        playerLabel.textProperty().bind(playersName[playerCount])
        playerLabel.style = "-fx-font-size: ${20*coef}px; -fx-font-weight: bolder; -fx-text-fill: #ffffff;"
        this.playersLabel += playerLabel
        playerCount += 1
        // Score label
        val scoreLabel = Label("Points :")
        scoreLabel.textProperty().bind(scoreLabels[scoreCount])
        scoreLabel.style = "-fx-font-size: ${24*coef}; -fx-font-weight: bolder; -fx-text-fill: #ffffff;"
        scoreCount++

        if (cote == "right") {
            val labelVBox = VBox(10.0*coef, playerLabel, scoreLabel)
            labelVBox.alignment = Pos.CENTER_RIGHT

            val hBox = HBox(10.0*coef, labelVBox, gridPane)
            hBox.alignment = Pos.CENTER
            panneau.children.add(hBox)
        }

        if (cote == "left") {
            val labelVBox = VBox(10.0*coef, playerLabel, scoreLabel)
            labelVBox.alignment = Pos.CENTER_LEFT

            val hBox = HBox(10.0*coef, gridPane, labelVBox)
            hBox.alignment = Pos.CENTER
            panneau.children.add(hBox)
        }
        return panneau
    }

    /**
     * Permet de créer le plateau d'un joueur avec toutes ses cartes
     * @return GridPane
     */

    private fun createGridPane(rows: Int, cols: Int, tileWidth: Double, tileHeight: Double,myPane : Boolean = false): GridPane {
        // Setup stockage properties
        var currentPlateau =  arrayOf<ObjectProperty<Image>>()
        // Setup grid
        val gridPane = GridPane()
        gridPane.hgap = 10.0*coef
        gridPane.vgap = 10.0*coef
        gridPane.alignment = Pos.CENTER
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val p = SimpleObjectProperty(this.defaultImg)
                gridPane.add(createRectangleWithImage(tileWidth*coef, tileHeight*coef, p), col, row)
                currentPlateau += p
            }
        }
        this.plateaux += currentPlateau
        if(myPane)this.myCards = gridPane
        return gridPane
    }

    /**
     * Permet de créer les piles de cartes pour la défausse et la pioche
     * @return Pane
     */
    private fun createPileCarte(isDefausse: Boolean = false): Pane {
        val pane = Pane()
        // Création des properties
        var tmpPile = arrayOf<ObjectProperty<Image>>()
        for (i in 0..3) {
            tmpPile += SimpleObjectProperty(this.defaultImg)
        }
        // Création des cartes
        val rect1 = createRectangleWithImage(80.0*coef, 115.0*coef, tmpPile[0])
        val rect2 = createRectangleWithImage(80.0*coef, 115.0*coef, tmpPile[1])
        val rect3 = createRectangleWithImage(80.0*coef, 115.0*coef, tmpPile[2])
        // Stockage éventuel
        if (isDefausse) {
            this.defausse = tmpPile
        } else {
            this.pioche = tmpPile
        }
        // Positionnement
        rect1.relocate(50.0*coef, 50.0*coef)
        rect2.relocate(55.0*coef, 45.0*coef)
        rect3.relocate(60.0*coef, 40.0*coef)
        pane.children.addAll(rect3, rect2, rect1)
        return pane
    }

    /**
     * Permet de créer une carte avec une image.
     * La carte de base est la carte face cachée.
     * @return ImageView
     */
    private fun createRectangleWithImage(width: Double, height: Double, p: ObjectProperty<Image>): ImageView {
        val tile = ImageView()
        tile.imageProperty().bind(p)
        tile.fitWidth = width
        tile.fitHeight = height
        return tile
    }
}