import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import modele.Defausse
import modele.Jeu
import modele.Pioche
import vue.VueCrée
import modele.Server
import vue.Accueil
import vue.Salon


class TestVue(): Application() {
    override fun start(primaryStage: Stage) {


        // Vues
        val salon = Accueil()


        // Scène
        val scene = Scene(salon, 2000.0, 1000.0)

        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.isMaximized = true
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(TestVue::class.java)
}
