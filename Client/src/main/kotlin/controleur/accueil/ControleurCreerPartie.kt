package controleur.accueil

import io.ktor.client.plugins.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import modele.Jeu
import modele.serverData.ServerException
import vue.CreationPartie

class ControleurCreerPartie(val vue : CreationPartie, val jeu : Jeu): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val info = Alert(Alert.AlertType.INFORMATION)
        val error = Alert(Alert.AlertType.ERROR)
        if (this.vue.comboBox1.value.isNotBlank()) {
            try {
                jeu.creerPartie(vue.comboBox1.value.toInt())
            } catch (e: ServerException) {
                error.title = "Erreur serveur"
                error.headerText = "Code : ${e.code}, ${e.message}"
                error.contentText = "Veuillez réssayer"
                error.show()
            } catch (e: HttpRequestTimeoutException) {
                error.title = "Erreur serveur"
                error.headerText = "Impossible de se connecter au serveur"
                error.contentText = "Vérifier votre connexion au serveur"
                error.show()
            }
        } else {
            info.title = "Skyjo"
            info.headerText = "Nombre de joueurs invalide"
            info.contentText = "Il doit y avoir au moins deux joueurs dans une partie"
            info.show()
        }
    }
}