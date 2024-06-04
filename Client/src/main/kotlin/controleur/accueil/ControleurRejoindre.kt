package controleur.accueil

import io.ktor.client.plugins.*
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Jeu
import modele.data.Party
import modele.serverData.ServerException
import vue.Rejoindre

class ControleurRejoindre(val vue: Rejoindre, val stage: Stage, private val jeu: Jeu): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        stage.scene.root = vue
        vue.ListePartie.items = jeu.partyListe

        // Thread
        val task = object : Task<Unit>() {
            override fun call() {
                try {
                    jeu.discoverParty()
                } catch (e: Exception) {
                    throw e
                }
            }
        }

        task.setOnFailed {
            val error = Alert(Alert.AlertType.ERROR)
            val e = task.exception
            if (e is ServerException) {
                error.title = "Erreur serveur"
                error.headerText = "Code : ${e.code}, ${e.message}"
                error.contentText = "Veuillez réssayer"
                error.show()
            } else if (e is HttpRequestTimeoutException) {
                error.title = "Erreur serveur"
                error.headerText = "Impossible de se connecter au serveur"
                error.contentText = "Vérifier votre connexion au serveur"
                error.show()
            } else {
                error.title = "Erreur"
                error.headerText = "Une erreur inconnue s'est produite"
                error.contentText = e?.message ?: "Erreur inconnue"
                error.show()
            }
        }

        // Démarrer la tâche dans un autre thread
        val thread = Thread(task)
        thread.isDaemon = true
        thread.start()
    }
}