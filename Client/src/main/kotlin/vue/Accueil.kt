package vue

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color


class Accueil : GridPane() {

    private val PanneauHaut : GridPane
    private val PanneauLeft : VBox
    private val PanneauRight : AnchorPane
    val CreateBtn : Button
    val JoinBtn : Button
    val SettingsBtn : Button
    val ExitBtn : Button
    val Titre : Label
    val Titre1 : Label
    val Titre2 : Label
    val Titre3 : Label
    val Titre4 : Label
    val Titre5 : Label
    val Titre6 : Label



    init {
        this.PanneauHaut = GridPane()
        this.PanneauLeft = VBox()
        this.PanneauRight = AnchorPane()
        this.CreateBtn = Button("Crée une partie")
        this.JoinBtn = Button("Rejoindre une partie")
        this.SettingsBtn = Button("Paramètres")
        this.ExitBtn = Button("Quitter")
        this.Titre = Label("SKYJO")
        this.Titre1 = Label("SKYJO")
        this.Titre2 = Label("SKYJO")
        this.Titre3 = Label("SKYJO")
        this.Titre4= Label("SKYJO")
        this.Titre5 = Label("SKYJO")
        this.Titre6 = Label("SKYJO")



        val row1 = RowConstraints()
        row1.percentHeight = 30.0  // 20% de la hauteur pour le panneau haut
        val row2 = RowConstraints()
        row2.percentHeight = 70.0  // 80% de la hauteur pour les panneaux gauche et droit
        this.rowConstraints.addAll(row1, row2)

        val col1 = ColumnConstraints()
        col1.percentWidth = 60.0  // 80% de la largeur pour le panneau gauche
        val col2 = ColumnConstraints()
        col2.percentWidth = 40.0  // 20% de la largeur pour le panneau droit
        this.columnConstraints.addAll(col1, col2)

        CreateBtn.setPrefSize(400.0, 100.0)
        JoinBtn.setPrefSize(400.0, 100.0)
        SettingsBtn.setPrefSize(400.0, 100.0)
        ExitBtn.setPrefSize(400.0, 100.0)

        this.Titre.styleClass.add("Titre")
        this.Titre1.styleClass.add("Titre1")
        this.Titre2.styleClass.add("Titre2")
        this.Titre3.styleClass.add("Titre3")
        this.Titre4.styleClass.add("Titre4")
        this.Titre5.styleClass.add("Titre5")
        this.Titre6.styleClass.add("Titre6")

        GridPane.setMargin(Titre, Insets(5.0, 0.0, 0.0, 300.0))
        GridPane.setMargin(Titre1, Insets(20.0, 0.0, 0.0, 300.0))
        GridPane.setMargin(Titre2, Insets(35.0, 0.0, 0.0, 300.0))
        GridPane.setMargin(Titre3, Insets(50.0, 0.0, 0.0, 300.0))
        GridPane.setMargin(Titre4, Insets(65.0, 0.0, 0.0, 300.0))
        GridPane.setMargin(Titre5, Insets(80.0, 0.0, 0.0, 300.0))
        GridPane.setMargin(Titre6, Insets(95.0, 0.0, 0.0, 300.0))

        this.add(PanneauHaut, 0, 0, 2, 1)
        this.add(PanneauLeft, 0, 1)
        this.add(PanneauRight, 1, 1)

        PanneauHaut.background = Background(BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY))
        PanneauLeft.background = Background(BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY))
        PanneauRight.background = Background(BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY))

        PanneauLeft.children.addAll(CreateBtn, JoinBtn, SettingsBtn, ExitBtn)
        PanneauHaut.children.addAll(Titre, Titre1, Titre2, Titre3, Titre4, Titre5, Titre6)

    }

}