package modele

class Carte(nombre: Int) {
    val nombre: Int
    val couleur: String

    init {
        this.nombre = nombre
        this.couleur = "#000000"
    }

}