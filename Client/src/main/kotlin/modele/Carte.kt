package modele

import java.io.FileInputStream

/**
 * Carte :
 * @param nombre la valeur de la carte
 * @property nombre la valeur de la carte
 * @property file l'image de la carte associée
 */
class Carte(nombre: Int) {
    private val path = "images/cartes/"
    val nombre: Int
    val file: FileInputStream

    init {
        this.nombre = nombre
        this.file = FileInputStream("${this.path}carte${this.nombre}.png")
    }
}