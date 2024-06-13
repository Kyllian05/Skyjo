package controleur.rejoindrePartie

import controleur.FetchingPlayer
import io.ktor.http.*
import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Jeu
import modele.serverData.ServerException
import vue.Game
import vue.Rejoindre
import vue.Salon
import java.lang.NumberFormatException

class ControleurBoutonRejoindrePartie(val stage : Stage, val salon : Salon, val jeu : Jeu, val game : Game?, val vue : Rejoindre): EventHandler<ActionEvent> {
    override fun handle(e: ActionEvent) {
        // Préparation de l'alerte
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Skyjo"
        alert.headerText = "Impossilbe de rejoindre une partie"
        // Choix de la partie
        var partieChoice = vue.ListePartie.selectionModel.selectedItem
        if(partieChoice == null && vue.searchGame.text.isBlank()) {
            alert.contentText = "Vous n'avez pas rentré d'ID de partie ou vous avez sélectionné aucune partie."
            alert.show()
            return
        }
        var id: Int? = null
        if(vue.searchGame.text.isNotBlank()) {
            try {
                id = vue.searchGame.text.toInt()
            } catch (e: NumberFormatException) {
                alert.contentText = "L'ID que vous avez entré est invalide. Veuillez saisir un nombre."
                alert.show()
            }
        } else if (partieChoice != null) {
            id = partieChoice.id
        }

        if (id != null) {
            try {
                jeu.rejoindrePartie(id)
                stage.scene.root = salon
                salon.idPartie.text = "Id : ${jeu.id}"
                var classe = FetchingPlayer(jeu,this.salon,game,stage)
                classe.startWaiting()
            } catch (e: ServerException) {
                if (e.code == HttpStatusCode.MethodNotAllowed) {
                    alert.contentText = "La partie que vous tentez de rejoindre est pleine."
                    alert.show()
                } else if (e.code == HttpStatusCode.NotFound) {
                    alert.contentText = "La partie que vous tentez de rejoindre n'éxiste pas."
                    alert.show()
                } else {
                    alert.alertType = Alert.AlertType.ERROR
                    alert.contentText = "Une erreur est survenue."
                    alert.show()
                    throw e
                }
            }
        }
    }
}