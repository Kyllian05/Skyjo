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
            val Pilejoueur = createPileCarte()
            val PileOpponent = createPileCarte()
            val currentPlayerLabel = Label("")
            currentPlayerLabel.textProperty().bind(currentPlayer)
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

            val panneauOpponent1 = setupOpponentPanel("right")
            val panneauOpponent2 = setupOpponentPanel("left")
            setupCenterPanel()

            val panneauPiocheDefausse = HBox()
            panneauPiocheDefausse.alignment = Pos.CENTER
            val Pilejoueur = createPileCarte()
            val PileOpponent = createPileCarte()
            panneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur)
            panneauPiocheDefausse.spacing = 300.0
            panneauPiocheDefausse.alignment = Pos.CENTER

            val gridPane = GridPane().apply {

                rowConstraints.addAll(
                    RowConstraints().apply { vgrow = Priority.ALWAYS },
                    RowConstraints().apply { vgrow = Priority.ALWAYS },
                   RowConstraints().apply { vgrow = Priority.ALWAYS }
                )
                columnConstraints.addAll(
                   ColumnConstraints().apply { hgrow = Priority.ALWAYS },
                   ColumnConstraints().apply { hgrow = Priority.ALWAYS },
                   ColumnConstraints().apply { hgrow = Priority.ALWAYS }
                )


                add(panneauOpponent1, 2, 1)
                add(panneauPiocheDefausse, 1, 1)
                add(panneauOpponent2, 0, 1)
                add(PanneauCentre, 1, 2)



            }

            VBox.setVgrow(panneauOpponent1, Priority.ALWAYS)
            VBox.setVgrow(panneauOpponent2, Priority.ALWAYS)
            VBox.setVgrow(PanneauCentre, Priority.ALWAYS)
            VBox.setVgrow(panneauPiocheDefausse, Priority.ALWAYS)


            this.apply {
                children.add(gridPane)
                vgap = 20.0
                alignment = Pos.CENTER
            }


        }



        if (nbJoueur == 4) {
            setupCenterPanel()

            val panneauOpponent1 = setupOpponentPanel("right")
            val panneauOpponent2 = setupOpponentPanel("right")
            val panneauOpponent3 = setupOpponentPanel("left")


            val panneauPiocheDefausse = HBox()
            panneauPiocheDefausse.alignment = Pos.CENTER
            val Pilejoueur = createPileCarte()
            val PileOpponent = createPileCarte()
            panneauPiocheDefausse.children.addAll(Titre, PileOpponent, Pilejoueur, Titre1)
            panneauPiocheDefausse.spacing = 300.0
            panneauPiocheDefausse.alignment = Pos.CENTER

            val gridPane1 = GridPane().apply {
                rowConstraints.addAll(
                    RowConstraints().apply { vgrow = Priority.ALWAYS },
                    RowConstraints().apply { vgrow = Priority.ALWAYS },
                    RowConstraints().apply { vgrow = Priority.ALWAYS }
                )
                columnConstraints.addAll(
                    ColumnConstraints().apply { hgrow = Priority.ALWAYS },
                    ColumnConstraints().apply { hgrow = Priority.ALWAYS },
                    ColumnConstraints().apply { hgrow = Priority.ALWAYS }
                )


                add(panneauOpponent1, 0, 0)
                add(panneauPiocheDefausse, 0, 1)
                add(PanneauCentre, 0, 2)
                add(panneauOpponent2, 2, 1)
                add(panneauOpponent3, 0, 1)


                GridPane.setMargin(panneauOpponent1, Insets(0.0, 0.0, 0.0, 0.0))
                //GridPane.setMargin(PanneauCentre, Insets(0.0, 0.0, 0.0, 0.0))
            }


            VBox.setVgrow(panneauOpponent1, Priority.ALWAYS)
            VBox.setVgrow(panneauPiocheDefausse, Priority.ALWAYS)
            VBox.setVgrow(PanneauCentre, Priority.ALWAYS)
            VBox.setVgrow(panneauOpponent2, Priority.ALWAYS)
            VBox.setVgrow(panneauOpponent3, Priority.ALWAYS)


            this.apply {
                children.add(gridPane1)
                vgap = 20.0
                alignment = Pos.CENTER
            }
        }


        if (nbJoueur == 5) {
            val joueur1 = setupOpponentPanel("right")
            val joueur2 = setupOpponentPanel("left")
            val joueur3 = setupOpponentPanel("right")
            val joueur4 = setupOpponentPanel("left")
            setupCenterPanel()

            val PanneauPiocheDefausse = HBox()
            val Pilejoueur = createPileCarte()
            val PileOpponent = createPileCarte()
            PanneauPiocheDefausse.children.addAll(PileOpponent, Pilejoueur)
            PanneauPiocheDefausse.spacing = 100.0


            this.add(joueur1, 1, 0,1,1)
            this.add(joueur2, 3, 0,1,1)
            this.add(PanneauPiocheDefausse,2,1,1,1)
            this.add(joueur3, 4, 2,1,1)
            this.add(joueur4, 0, 2,1,1)
            this.add(PanneauCentre,2,2,1,1)
            this.isGridLinesVisible = true


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

            // Ajoutez les contraintes de colonne pour centrer le panneau de pioche
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