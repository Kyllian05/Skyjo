package controleur.rejoindrePartie

import io.ktor.client.plugins.*
import javafx.concurrent.Task
import javafx.scene.control.Alert
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.serverData.ServerException
import vue.Rejoindre
class Actualiser(private val jeu: Jeu, private val vue: Rejoindre) {
    private val error = Alert(Alert.AlertType.ERROR)
    fun fetch() {
        vue.hideActualiser()
        val task = object : Task<Unit>() {
            override fun call() {
                try {
                    runBlocking{ jeu.discoverParty() }
                } catch (e: Exception) {
                    throw e
                }
            }
        }

        task.setOnSucceeded { vue.showActualiser() }

        task.setOnFailed {
            val e = task.exception
            if (e is ServerException) {
                vue.showActualiser()
                error.title = "Erreur serveur"
                error.headerText = "Code : ${e.code}, ${e.message}"
                error.contentText = "Veuillez réssayer"
                error.show()
            } else if (e is HttpRequestTimeoutException) {
                vue.showActualiser()
                error.title = "Erreur serveur"
                error.headerText = "Impossible de se connecter au serveur"
                error.contentText = "Vérifier votre connexion au serveur"
                error.show()
            } else {
                vue.showActualiser()
                error.title = "Erreur"
                error.headerText = "Une erreur inconnue s'est produite"
                error.contentText = e?.message ?: "Erreur inconnue"
                error.show()
            }
        }

        val thread = Thread(task)
        thread.start()
    }
}

