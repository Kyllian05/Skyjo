package controleur.game

import modele.Jeu
import vue.Game
import java.io.FileInputStream

class UpdateGameState(private val vue: Game, private val model: Jeu) {
    fun update() {
        val state = model.currentState
        if (state != null) {
            // Créer toutes les cartes à partir du state
            for (i in 0..state.plateaux.size) {
                for (j in 0..state.plateaux[i].colonnes.size) {
                    for (k in 0..state.plateaux[i].colonnes[j].size) {
                        val card = createCard(state.plateaux[i].colonnes[j][k].valeur)
                        // TODO:
                        //  - Remplacer les cartes dans la vue
                    }
                }
            }
            // Update la défausse
            val defausse = createCard(state.carteSommetDefausse.valeur)
            // TODO:
            //  - Replacer les cartes de la pioche

            // TODO:
            //  - Afficher des pop-up en fonction du choix du joueur : piocher...
        }
    }

    private fun createCard(n: String): FileInputStream {
        if (n == "x") {
            return FileInputStream("images/cartes/carteSKYJO.png")
        }
        return FileInputStream("images/cartes/carte$n.png")
    }
}