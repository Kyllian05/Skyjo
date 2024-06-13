package controleur.rejoindrePartie

import javafx.concurrent.Task
import javafx.event.ActionEvent
import javafx.event.EventHandler
import vue.Rejoindre

class ControleurChargerParties(private val vue: Rejoindre, private val fetch: Task<Unit>): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
        if (!fetch.isRunning) {
            vue.showLoader()
            vue.ListePartie.opacity = 1.0
            val t = Thread(fetch)
            t.isDaemon = true
            t.start()
        }
    }
}