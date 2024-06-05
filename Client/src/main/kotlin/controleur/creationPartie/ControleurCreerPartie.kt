package controleur.creationPartie

import controleur.salon.FetchingPlayer
import io.ktor.client.plugins.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Jeu
import modele.serverData.ServerException
import vue.CreationPartie
import vue.Game
import vue.Salon

class ControleurCreerPartie(val vue : CreationPartie, val jeu : Jeu,val stage : Stage,val salon : Salon,val game : Game?): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        val info = Alert(Alert.AlertType.INFORMATION)
        val error = Alert(Alert.AlertType.ERROR)
        try {
            jeu.creerPartie(vue.comboBox.value)
            salon.ListeJoueurs.items = jeu.listeJoueur
            stage.scene.root = salon
            FetchingPlayer(jeu,salon,game,stage).startWaiting()
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
    }
}