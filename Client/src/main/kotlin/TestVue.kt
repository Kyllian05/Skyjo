import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import vue.*


class TestVue(): Application() {
    override fun start(primaryStage: Stage) {

        // Vues

        val salon = Classement(linkedMapOf(1 to -3, 2 to 57, 4 to 89), hashMapOf(1 to "ekhdjghfurhgurhg", 2 to "bar", 4 to "boo"))


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