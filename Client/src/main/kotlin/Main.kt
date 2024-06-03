import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
<<<<<<< HEAD
import vue.VueCrée

=======
import modele.Jeu
import modele.Server
import vue.Accueil
>>>>>>> 3dc0fc12b03ee336ff366e82b61c1f3c9ad1f73d

class Main(): Application() {
    val IP = arrayOf("172.26.82.23", "172.26.82.18", "172.26.82.13")

    override fun start(primaryStage: Stage) {
<<<<<<< HEAD
        val Accueil = VueCrée()
=======
        // Création du serveur
        val server = Server(this.IP[0])

        // Vues
        val Accueil = Accueil()

        // Scène
>>>>>>> 3dc0fc12b03ee336ff366e82b61c1f3c9ad1f73d
        val scene = Scene(Accueil, Double.MAX_VALUE, Double.MAX_VALUE)
        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.show()
<<<<<<< HEAD


=======
>>>>>>> 3dc0fc12b03ee336ff366e82b61c1f3c9ad1f73d
    }
}
fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}
