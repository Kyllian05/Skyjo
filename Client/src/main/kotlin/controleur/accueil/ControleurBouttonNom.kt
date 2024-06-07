package controleur.accueil

import io.ktor.client.plugins.*
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.control.ProgressIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.serverData.ServerException
import vue.Accueil
import java.util.concurrent.TimeoutException

class ControleurBouttonNom(private val vue: Accueil, private val j: Jeu): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        val info = Alert(Alert.AlertType.INFORMATION)
        val error = Alert(Alert.AlertType.ERROR)

        if (this.vue.inputName.text.isNotBlank()) {
            vue.showLoader()

            // Thread : création de la tâche bloquante
            val task = object : Task<Unit>() {
                override fun call() {
                    try {
                        j.createPlayer(this@ControleurBouttonNom.vue.inputName.text)
                    } catch (e: Exception) {
                        throw e
                    }
                }
            }

            // Actions à effectuer une fois la tâche terminée
            task.setOnSucceeded {
                vue.removeLoader()
                vue.addButtons()
            }

            task.setOnFailed {
                vue.removeLoader()
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

        } else {
            info.title = "Skyjo"
            info.headerText = "Pseudo invalide"
            info.contentText = "Vôtre pseudo ne peut pas être vide"
            info.show()
        }
    }
}