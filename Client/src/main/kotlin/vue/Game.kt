package vue

import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.ReadOnlyStringProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.beans.property.StringPropertyBase
import javafx.beans.value.ObservableStringValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import java.io.FileInputStream

class Game(nbJoueur: Int) : GridPane() {
    val PanneauCentre = VBox(10.0)

    val PanneauOpponent = VBox(10.0)
    var nbJoueur : Int
    val plateaux: Array<Array<Rectangle>> = arrayOf()
    val param : Array<String>
    var playersName = arrayOf<StringProperty>()
    var playerCount = 0
    val currentPlayer : StringProperty = SimpleStringProperty("Ce n'est pas à vous de jouer")
    val Pilejoueur = createPileCarte()
    val PileOpponent = createPileCarte()

    init {
        this.nbJoueur = nbJoueur
        for(i in 0..<nbJoueur){
            playersName += SimpleStringProperty("Thomas")
        }

        val Titre = GridPane()
        val Titre1 = GridPane()
        this.style = "-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #6600CC, #3366FF);"

        this.param = arrayOf("Titre", "Titre1", "Titre2", "Titre3", "Titre4", "Titre5", "Titre6")
        var posAccueil = 5.0
        for (i in 0 until 7) {
            val label = Label("SKYJO")
            val label1 = Label("SKYJO")
            Titre.add(label, 0, 0)
            Titre1.add(label1, 0, 0)
            label.padding = Insets(posAccueil, 0.0, 0.0, 0.0)
            label.styleClass.add(param[i])
            label1.padding = Insets(posAccueil, 0.0, 0.0, 0.0)
            label1.styleClass.add(param[i])
            posAccueil += 15.0
        }
        val currentPlayerLabel = Label("")
        currentPlayerLabel.textProperty().bind(currentPlayer)

        if (nbJoueur == 0) {
            val cartes: Array<Button> = Array(12) { Button() }

            val innerGrid = GridPane()
            innerGrid.hgap = 10.0
            innerGrid.vgap = 10.0

            var index = 0

            for (i in 0 until 3) {
                for (j in 0 until 4) {
                    val carte = Button("")
                    innerGrid.add(carte, j, i)
                    carte.maxWidth = 30.0
                    carte.maxHeight = 50.0
                    carte.styleClass.add("carteBouton")

                    cartes[index] = carte
                    index++
                }
            }


            this.add(innerGrid, 0, 0)


            this.padding = Insets(10.0)
        }

        if (nbJoueur == 2) {

            setupCenterPanel()
            val PanneauOpponnent = setupOpponentPanel("right")

            val PanneauPiocheDefausse = HBox()

            PanneauPiocheDefausse.children.addAll(Titre, PileOpponent, Pilejoueur, Titre1,currentPlayerLabel)
            PanneauPiocheDefausse.spacing = 300.0
            PanneauPiocheDefausse.alignment = Pos.CENTER

            GridPane.setMargin(PanneauOpponnent, Insets(0.0, 0.0, 0.0, -60.0))
            GridPane.setMargin(PanneauCentre, Insets(0.0, -180.0, 0.0, 0.0))


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

            this.vgap = 20.0
            this.alignment = Pos.CENTER

        }

        if (nbJoueur == 3) {
            val joueur1 = setupOpponentPanel("right")
            val joueur2 = setupOpponentPanel("left")

            setupCenterPanel()

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur,currentPlayerLabel)
            PanneauPiocheDefausse.spacing = 100.0

            this.add(joueur1, 0, 1, 1, 1)
            this.add(joueur2, 2, 1, 1, 1)
            this.add(PanneauPiocheDefausse, 1, 1, 1, 1)
            this.add(PanneauCentre, 1, 2, 1, 1)

            GridPane.setMargin(PanneauPiocheDefausse, Insets(100.0,0.0,0.0,170.0))
            GridPane.setMargin(PanneauCentre, Insets(0.0,0.0,0.0,70.0))



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


        if (nbJoueur == 4) {
            val joueur1 = setupOpponentPanel("right")
            val joueur2 = setupOpponentPanel("right")
            val joueur3 = setupOpponentPanel("left")

            setupCenterPanel()

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur,currentPlayerLabel)
            PanneauPiocheDefausse.spacing = 100.0


            this.add(joueur1, 1, 0, 1, 1)
            this.add(joueur2, 0, 1, 1, 1)
            this.add(PanneauPiocheDefausse, 1, 1, 1, 1)
            this.add(joueur3, 2, 1, 1, 1)
            this.add(PanneauCentre, 1, 2, 1, 1)



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



        if (nbJoueur == 5) {
            val joueur1 = setupOpponentPanel("right")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("right")
            val joueur4 = setupOpponentPanel("left")
            setupCenterPanel()

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur,currentPlayerLabel)
            PanneauPiocheDefausse.spacing = 100.0

            this.add(joueur1, 0, 0, 4, 1)
            this.add(joueur2, 0, 0, 4, 1)
            this.add(PanneauPiocheDefausse, 1, 1, 4, 1)
            this.add(joueur3, 3, 2, 1, 1)
            this.add(joueur4, 0, 2, 1, 1)
            this.add(PanneauCentre, 2, 2, 1, 1)

            GridPane.setMargin(joueur1, Insets(0.0, 800.0,0.0,0.0))
            GridPane.setMargin(joueur2, Insets(0.0, 0.0,0.0,800.0))
            GridPane.setMargin(PanneauPiocheDefausse, Insets(0.0, 0.0,0.0,170.0))
            GridPane.setMargin(PanneauCentre, Insets(0.0,50.0,0.0,0.0))


            PanneauPiocheDefausse.spacing = 200.0

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

        if (nbJoueur == 6) {
            val joueur1 = setupOpponentPanel("left")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("right")
            val joueur4 = setupOpponentPanel("right")
            val joueur5 = setupOpponentPanel("right")
            setupCenterPanel()

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur,currentPlayerLabel)
            PanneauPiocheDefausse.spacing = 100.0

            this.add(joueur1, 0, 0)
            this.add(joueur3, 1, 0)
            this.add(joueur4, 2, 0)
            this.add(joueur2, 0, 2)
            this.add(joueur5, 2, 2)
            this.add(PanneauPiocheDefausse, 1, 1)
            this.add(PanneauCentre, 1, 2)

            GridPane.setMargin(joueur3, Insets(0.0,110.0,0.0,0.0))
            GridPane.setMargin(PanneauCentre, Insets(0.0,0.0,0.0,70.0))
            GridPane.setMargin(PanneauPiocheDefausse, Insets(40.0,0.0,0.0,170.0))


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

        if (nbJoueur == 7) {
            val joueur1 = setupOpponentPanel("left")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("left")
            val joueur4 = setupOpponentPanel("right")
            val joueur5 = setupOpponentPanel("right")
            val joueur6 = setupOpponentPanel("right")
            setupCenterPanel()

            val PanneauPiocheDefausse = HBox()
            PanneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur,currentPlayerLabel)
            PanneauPiocheDefausse.spacing = 100.0

            this.add(joueur1, 0, 0)
            this.add(joueur4, 2, 0)
            this.add(joueur2, 0, 1)
            this.add(joueur5, 2, 1)
            this.add(joueur3, 0, 2)
            this.add(joueur6, 2, 2)
            this.add(PanneauPiocheDefausse, 1, 1)
            this.add(PanneauCentre, 1, 2)

            GridPane.setMargin(joueur1, Insets(10.0,0.0,10.0,0.0))
            GridPane.setMargin(joueur2, Insets(10.0,0.0,10.0,0.0))
            GridPane.setMargin(joueur3, Insets(0.0,0.0,20.0,0.0))
            GridPane.setMargin(joueur6, Insets(0.0,0.0,20.0,0.0))

            GridPane.setMargin(PanneauPiocheDefausse, Insets(60.0,0.0,0.0,110.0))

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

        if (nbJoueur == 8) {

        }

    }

    private fun setupCenterPanel() {
        PanneauCentre.alignment = Pos.CENTER
        PanneauCentre.spacing = 20.0
        PanneauCentre.padding = Insets(10.0)

        val gridPane = createGridPane(3, 4, 80.0, 115.0)
        val playerLabelVous = Label("Vous")
        playerLabelVous.font = Font.font("Arial", FontWeight.BOLD, 20.0)
        playerLabelVous.textFill = Color.WHITE
        val scoreLabel = Label("Score:")
        scoreLabel.font = Font.font("Arial", FontWeight.BOLD, 30.0)
        scoreLabel.textFill = Color.WHITE

        val labelVBox = VBox(10.0, playerLabelVous, scoreLabel)
        labelVBox.alignment = Pos.BOTTOM_LEFT

        val hBox = HBox(10.0, gridPane, labelVBox)
        hBox.alignment = Pos.CENTER

        PanneauCentre.children.add(hBox)
    }

    private fun setupOpponentPanel(coté : String): VBox {
        val panneau = VBox(10.0)
        panneau.alignment = Pos.CENTER
        panneau.spacing = 20.0
        panneau.padding = Insets(10.0)
        val gridPane = createGridPane(3, 4, 60.0, 85.0)
        var plateau: Array<Rectangle> = arrayOf()
        this.plateaux.plus(plateau)
        val playerLabel = Label("")
        playerLabel.textProperty().bind(playersName[playerCount])

        playerCount += 1
        playerLabel.font = Font.font("Arial", FontWeight.BOLD, 20.0)
        playerLabel.textFill = Color.WHITE
        val scoreLabel = Label("Score:")
        scoreLabel.font = Font.font("Arial", FontWeight.BOLD, 30.0)
        scoreLabel.textFill = Color.WHITE

        if (coté == "right") {
            val labelVBox = VBox(10.0, playerLabel, scoreLabel)
            labelVBox.alignment = Pos.CENTER_RIGHT

            val hBox = HBox(10.0, labelVBox, gridPane)
            hBox.alignment = Pos.CENTER
            panneau.children.add(hBox)
        }

        if (coté == "left") {
            val labelVBox = VBox(10.0, playerLabel, scoreLabel)
            labelVBox.alignment = Pos.CENTER_LEFT
            val hBox = HBox(10.0, gridPane, labelVBox)
            hBox.alignment = Pos.CENTER
            panneau.children.add(hBox)
        }
        return panneau
    }

    private fun createGridPane(rows: Int, cols: Int, tileWidth: Double, tileHeight: Double): GridPane {
        val gridPane = GridPane()
        gridPane.hgap = 10.0
        gridPane.vgap = 10.0
        gridPane.alignment = Pos.CENTER


        for (row in 0 until rows) {
            for (col in 0 until cols) {
//                val input = FileInputStream("images/cartes/carteSKYJO.png")
//                val img = Image(input)
//                val tile = ImageView(img)
//                tile.fitWidth = tileWidth
//                tile.fitHeight = tileHeight
                val tile = Rectangle(tileWidth, tileHeight)
                tile.setArcWidth(10.0);
                tile.setArcHeight(10.0);
                tile.fill = Color.WHITE
                gridPane.add(tile, col, row)
            }
        }
        return gridPane
    }

    private fun createPileCarte(): Pane {
        val pane = Pane()

       // val image = Image(FileInputStream("./images/cartes/carteSKYJO.png"))

        val rect1 = createRectangleWithImage(45.0, 70.0)
        val rect2 = createRectangleWithImage(45.0, 70.0)
        val rect3 = createRectangleWithImage(45.0, 70.0)

        rect1.relocate(50.0, 50.0)
        rect2.relocate(55.0, 45.0)
        rect3.relocate(60.0, 40.0)

        pane.children.addAll(rect3, rect2, rect1)

        return pane
    }

    private fun createRectangleWithImage(width: Double, height: Double): StackPane {
        val stackPane = StackPane()

        val rectangle = Rectangle(width, height)
        rectangle.arcWidth = 10.0
        rectangle.arcHeight = 10.0
        rectangle.fill = Color.WHITE

        //val imageView = ImageView(image)
        //imageView.fitWidth = width
        //imageView.fitHeight = height

        stackPane.children.addAll(rectangle)

        return stackPane
    }
}