package controleur.rejoindrePartie

import io.ktor.client.plugins.*
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.scene.control.Alert
import kotlinx.coroutines.runBlocking
import modele.Jeu
import modele.Server
import modele.data.Party
import modele.serverData.ServerException
import vue.Rejoindre
class Actualiser(private val jeu: Jeu, private val vue: Rejoindre, private val server: Server) {
    private val error = Alert(Alert.AlertType.ERROR)
    fun fetch(): Task<Unit> {
        val task = object : Task<Unit>() {
            override fun call() {
                try {
                    val parties = runBlocking { return@runBlocking this@Actualiser.server.getAllParties() }
                    jeu.partyListe.remove(0, jeu.partyListe.size)
                    for (p in parties) {
                        if (this.isCancelled) {
                            return
                        }
                        val state = runBlocking { return@runBlocking this@Actualiser.server.getPartieState(p) }
                        val max = state.nbJoueursMax
                        val joined = state.plateaux.size
                        if (max == joined) {
                            continue
                        }
                        try {
                            val createdBy = runBlocking { return@runBlocking this@Actualiser.server.getName(state.plateaux[0].idJoueur) }
                            Platform.runLater { jeu.partyListe.add(Party(max, p, joined, createdBy)) }
                        } catch (e: ServerException) {
                            if (e.code.value == 404) {
                                continue
                            } else {
                                throw e
                            }
                        }
                    }
                } catch (e: Exception) {
                    throw e
                }
            }
        }

        task.setOnSucceeded { vue.hideLoader() }

        task.setOnFailed {
            val e = task.exception
            if (e is ServerException) {
                vue.hideLoader()
                error.title = "Erreur serveur"
                error.headerText = "Code : ${e.code}, ${e.message}"
                error.contentText = "Veuillez réssayer"
                error.show()
            } else if (e is HttpRequestTimeoutException) {
                vue.hideLoader()
                error.title = "Erreur serveur"
                error.headerText = "Impossible de se connecter au serveur"
                error.contentText = "Vérifier votre connexion au serveur"
                error.show()
            } else if (e is InterruptedException) {
                vue.hideLoader()
                return@setOnFailed
            } else {
                vue.hideLoader()
                error.title = "Erreur"
                error.headerText = "Une erreur inconnue s'est produite"
                error.contentText = e?.message ?: "Erreur inconnue"
                error.show()
            }
        }

        return task
    }
}

