import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import vue.*


class TestVue(): Application() {
    override fun start(primaryStage: Stage) {

        // Vues

        val salon = Game(5)


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