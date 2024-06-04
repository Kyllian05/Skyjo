package modele.data

/**
 * Classe pour stocker les infos d'une partie pour les afficher dans la page pour rejoindre une partie
 */
class Party(val nbJoueur: Int, val id: Int, var joined: Int) {
    override fun toString(): String {
        return "$joined/$nbJoueur | id : $id"
    }
}