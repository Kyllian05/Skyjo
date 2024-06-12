package controleur.game

import io.ktor.util.*
import modele.Jeu
import org.controlsfx.tools.Platform
import vue.Game

class UpdatePlaying(private val jeu: Jeu) {
    private val ALL_ACTIONS = arrayOf("AJOUT_DES_JOUEURS", "PIOCHE_CARTE_OU_ECHANGE_CARTE_DEFAUSSE", "DEFAUSSE_OU_ECHANGE_CARTE_PIOCHEE", "PARTIE_TERMINEE")

    fun update(state : String, isMyTurn: Boolean) {
        if (state !in ALL_ACTIONS) {
            throw Exception("Invalid state")
        }
        if(!isMyTurn && jeu.playingText.value != "Ce n'est pas à vous de jouer"){
            jeu.playingText.value = "Ce n'est pas à vous de jouer"
            return
        }
        when (state) {
            ALL_ACTIONS[0] ->  {
                return
            }
            ALL_ACTIONS[1] ->  {
                jeu.playingText.value = "Piocher une carte ou prenez la carte de la défausse"
                return
            }
            ALL_ACTIONS[2] ->  {
                jeu.playingText.value = "Défaussez la carte piochée ou échangez-là avec une de vos cartes"
                return
            }
            ALL_ACTIONS[3] ->  {
                jeu.playingText.value = "La partie est terminée"
                return
            }
        }
    }
}