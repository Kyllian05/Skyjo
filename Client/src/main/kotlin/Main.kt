import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Jeu
import modele.Server
import vue.Accueil

class Main(): Application() {
    val IP = arrayOf("172.26.82.23", "172.26.82.18", "172.26.82.13")

    override fun start(primaryStage: Stage) {
        // Création du serveur
        val server = Server(this.IP[0])

        // Vues
        val Accueil = Accueil()

        // Scène
        val scene = Scene(Accueil, Double.MAX_VALUE, Double.MAX_VALUE)
        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="SKYJO"
        primaryStage.scene=scene
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}
