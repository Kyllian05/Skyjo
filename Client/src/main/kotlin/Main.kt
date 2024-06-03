import javafx.application.Application
import javafx.geometry.Rectangle2D
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import vue.VueCrée

class Main : Application() {
    override fun start(primaryStage: Stage) {
        val vue = VueCrée()

        val screenSize: Rectangle2D = Screen.getPrimary().visualBounds
        val scene = Scene(vue, screenSize.width, screenSize.height)

        primaryStage.scene = scene
        primaryStage.isResizable = false
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}
