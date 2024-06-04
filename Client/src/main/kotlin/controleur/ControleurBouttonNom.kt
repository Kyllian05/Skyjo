package controleur

import io.ktor.client.plugins.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import modele.Defausse
import modele.Jeu
import modele.Pioche
import modele.serverData.ServerException
import vue.Accueil

class ControleurBouttonNom(private val vue: Accueil, private val j: Jeu): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        val info = Alert(Alert.AlertType.INFORMATION)
        val error = Alert(Alert.AlertType.ERROR)
        if (this.vue.inputName.text.isNotBlank()) {
            try {
                j.createPlayer(this.vue.inputName.text)
                this.vue.addButtons()
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
            info.headerText = "Pseudo invalide"
            info.contentText = "Vôtre pseudo ne peut pas être vide"
            info.show()
        }
    }
}