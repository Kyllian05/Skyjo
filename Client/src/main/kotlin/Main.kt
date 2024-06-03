import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import vue.VueCrée


class Main: Application() {

    override fun start(primaryStage: Stage) {
        val Accueil = VueCrée()
        val scene = Scene(Accueil, Double.MAX_VALUE, Double.MAX_VALUE)
        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.show()


    }
}
fun main(args: Array<String>) {
    Application.launch(Main::class.java)
}
