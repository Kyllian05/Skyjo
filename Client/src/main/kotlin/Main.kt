import controleur.accueil.BoutonRetour
import controleur.accueil.ControleurBouttonNom
import controleur.creationPartie.ControleurCreerPartie
import controleur.accueil.ControleurFermerAppli
import controleur.accueil.ControleurRejoindre
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Jeu
import vue.CreationPartie
import modele.Server
import vue.Accueil
import vue.Rejoindre
import vue.Salon


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

        // Model
        val jeu = Jeu(server)

        // Controllers
        accueil.fixeListener(accueil.submitBtn, ControleurBouttonNom(accueil, jeu))
        accueil.fixeListener(accueil.ExitBtn, ControleurFermerAppli(primaryStage))
        accueil.fixeListener(accueil.JoinBtn, ControleurRejoindre(rejoindre, primaryStage))
        accueil.fixeListener(accueil.CreateBtn, controleur.accueil.ControleurCreerPartie(creer, primaryStage))

        creer.boutonCree.onAction = ControleurCreerPartie(creer,jeu)
        creer.boutonRetour.onAction = BoutonRetour(primaryStage,accueil)
        rejoindre.boutonRetour.onAction = BoutonRetour(primaryStage,accueil)

        // Scène
        val scene = Scene(accueil, 2000.0, 1000.0)

        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene = scene
        primaryStage.isMaximized = true
        // Icon de l'application (ne fonctionne pas)
        //primaryStage.icons.add(Image("/images/cartes/carteSKYJO.png"))
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}
