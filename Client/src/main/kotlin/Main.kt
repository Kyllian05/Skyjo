import controleur.accueil.*
import controleur.rejoindrePartie.Actualiser
import controleur.rejoindrePartie.ControleurBoutonRejoindrePartie
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import modele.Jeu
import modele.Server
import vue.*
import java.io.FileInputStream


class Main(): Application() {
    val IP = arrayOf("http://172.26.82.23", "http://172.26.82.18", "http://172.26.82.13")

    override fun start(primaryStage: Stage) {
        // Création du serveur
        val server = Server(this.IP[0])

        // Vues
        val accueil = Accueil()
        val salon = Salon()
        val creer = CreationPartie()
        val rejoindre = Rejoindre()
        val game : Game? = null

        // Model
        val jeu = Jeu(server)
        salon.ListeJoueurs.items = jeu.listeJoueur

        // Fetch
        val fetchGames = Actualiser(jeu, rejoindre, server).fetch()

        // Controllers
        accueil.fixeListener(accueil.submitBtn, ControleurBouttonNom(accueil, jeu))
        accueil.fixeListener(accueil.ExitBtn, ControleurFermerAppli(primaryStage))
        accueil.fixeListener(accueil.JoinBtn, ControleurRejoindre(rejoindre, primaryStage, jeu, fetchGames))
        accueil.fixeListener(accueil.CreateBtn, controleur.accueil.ControleurCreerPartie(creer, primaryStage))

        creer.boutonCree.onAction = controleur.creationPartie.ControleurCreerPartie(creer,jeu,primaryStage,salon,game)
        creer.boutonRetour.onAction = BoutonRetour(primaryStage,accueil)

        rejoindre.boutonRetour.onAction = BoutonRetour(primaryStage,accueil)
        rejoindre.boutonRejoindre.onAction = ControleurBoutonRejoindrePartie(primaryStage,salon,jeu,game,rejoindre)

        // Scène
        val scene = Scene(accueil, 2000.0, 1000.0)

        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene = scene
        primaryStage.isMaximized = true
        primaryStage.icons.add(Image(FileInputStream("images/logo.png")))
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}
