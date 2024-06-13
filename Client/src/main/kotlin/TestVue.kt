import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import vue.*


class TestVue(): Application() {
    override fun start(primaryStage: Stage) {

        // Vues )


        val root = VBox()

        // Scène
        val scene = Scene(root, 2000.0, 1000.0)

        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.isMaximized = true
        primaryStage.show()

        val salon = Game(2,scene.height,scene.width)
        scene.root = salon
    }
}
fun main(args: Array<String>) {
    Application.launch(TestVue::class.java)
}