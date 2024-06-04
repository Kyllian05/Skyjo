import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import vue.VueCrée

class TestVue2(): Application() {

    override fun start(primaryStage: Stage) {

        // Vues
        val vue = VueCrée()

        // Scène
        val scene = Scene(vue, 1920.0, 1080.0)

        scene.stylesheets.add(javaClass.getResource("style.css").toExternalForm())
        primaryStage.title="Skyjo"
        primaryStage.scene=scene
        primaryStage.isMaximized = true
        primaryStage.show()
    }
}
fun main(args: Array<String>) {
    Application.launch(TestVue2::class.java)
}