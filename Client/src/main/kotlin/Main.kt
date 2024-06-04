import controleur.ControleurBouttonNom
import controleur.ControleurFermerAppli
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import vue.VueCrée
import modele.Server
import vue.Accueil


class Main(): Application() {
    val IP = arrayOf("172.26.82.23", "172.26.82.18", "172.26.82.13")

    override fun start(primaryStage: Stage) {
        // Création du serveur
        val server = Server(this.IP[0])

        // Vues
        val accueil = Accueil()

        // Controllers
        val controleurBouttonNom = ControleurBouttonNom(accueil)
        accueil.fixeListener(accueil.submitBtn, controleurBouttonNom)
        accueil.fixeListener(accueil.ExitBtn,ControleurFermerAppli(primaryStage))

        // Scène
        val scene = Scene(accueil, 2000.0, 1000.0)
        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.isMaximized = true
        // Icon de l'application (ne fonctionne pas)
        //primaryStage.icons.add(Image("/images/cartes/carteSKYJO.png"))
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}
