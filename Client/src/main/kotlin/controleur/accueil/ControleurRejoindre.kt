package controleur.accueil

import controleur.rejoindrePartie.Actualiser
import io.ktor.client.plugins.*
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.data.Party
import modele.serverData.ServerException
import vue.Rejoindre

class ControleurRejoindre(val vue: Rejoindre, val stage: Stage, private val jeu: Jeu, private val fetch: Task<Unit>): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent?) {
        stage.scene.root = vue
        vue.ListePartie.items = jeu.partyListe
        if (!fetch.isRunning) {
            val t = Thread(fetch)
            t.isDaemon = true
            t.start()
        }
    }
}