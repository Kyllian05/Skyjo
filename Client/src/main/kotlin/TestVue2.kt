import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import vue.Accueil
import vue.Game
import vue.Salon


class TestVue2(): Application() {
    override fun start(primaryStage: Stage) {


        val Accueil = Game(4,primaryStage.height,primaryStage.width)

        val scene = Scene(Accueil, 2000.0, 1000.0)

        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.isMaximized =true
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(TestVue2::class.java)
}