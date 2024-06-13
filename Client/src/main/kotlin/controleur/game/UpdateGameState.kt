package controleur.game

import javafx.application.Platform
import javafx.scene.image.Image
import javafx.scene.paint.Color
import modele.Jeu
import vue.Game
import java.io.FileInputStream
import java.lang.IndexOutOfBoundsException
import java.lang.NumberFormatException

class UpdateGameState(private val vue: Game, private val model: Jeu) {
    fun update() {
        val state = model.currentState
        if (state != null) {

            // Trouver la position du joueur
            var pos = 0
            for (i in 0 until  state.plateaux.size) {
                if (state.plateaux[i].idJoueur == model.myPlayer!!.id) {
                    pos = i
                    break
                }
            }
            // Créer toutes les cartes à partir du state en partant de notre joueur
            var index = 0
            for (i in pos until state.plateaux.size) {
                var scoreValue = 0
                for (j in 0 until state.plateaux[i].colonnes.size) {
                    for (k in 0 until state.plateaux[i].colonnes[j].size) {
                        vue.plateaux[index][j+k*4].value = createCard(state.plateaux[i].colonnes[j][k].valeur)
                        try {
                            scoreValue += state.plateaux[i].colonnes[j][k].valeur.toInt()
                        } catch (e: NumberFormatException) {
                            continue
                        }
                    }
                }
                val scoreIndex = index
                Platform.runLater {
                    vue.scoreLabels[scoreIndex].value = "Points : $scoreValue"
                }
                this.stylePlayerLabel(i, state.indexJoueurCourant, index)
                index += 1
            }
            // Créer toutes les cartes à partir du state en complétant le reste des joueurs
            for (i in 0 until pos) {
                var scoreValue = 0
                for (j in 0 until state.plateaux[i].colonnes.size) {
                    for (k in 0 until state.plateaux[i].colonnes[j].size) {
                        vue.plateaux[index][j+k*4].value = createCard(state.plateaux[i].colonnes[j][k].valeur)
                        try {
                            scoreValue += state.plateaux[i].colonnes[j][k].valeur.toInt()
                        } catch (e: NumberFormatException) {
                            continue
                        }
                    }
                }
                val scoreIndex = index
                Platform.runLater {
                    vue.scoreLabels[scoreIndex].value = "Points : $scoreValue"
                }
                this.stylePlayerLabel(i, state.indexJoueurCourant, index)
                index += 1
            }
            // Update la défausse
            vue.defausse[0].value = createCard(state.carteSommetDefausse.valeur)
        }
    }

    private fun createCard(n: String): Image {
        val ALL_CARDS = arrayOf("-2", "-1", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "_")
        if (n !in ALL_CARDS) {
            throw Exception("Card does not exists")
        }
        if (n == "_") {
            return Image(FileInputStream("images/cartes/carteSKYJO.png"))
        }
        return Image(FileInputStream("images/cartes/carte$n.png"))
    }

    private fun stylePlayerLabel(i: Int, joueurCourant: Int, index: Int) {
        if (i == joueurCourant) {
            vue.playersLabel[index].style = "-fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: linear-gradient(to right bottom, #ff0000, #ff5b00, #ff8700, #ffad00, #ffd000, #f7e000, #ebef00, #dcff00, #bfff00, #9dff00, #72ff00, #22ff00);"
        } else {
            vue.playersLabel[index].style = "-fx-font-size: 20px; -fx-font-weight: bolder; -fx-text-fill: #ffffff;"
        }
    }
}