package modele

class Plateau(contenu: HashMap<Int, Carte>): StockCarte() {
    private var contenu: HashMap<Int, Carte>

    init {
        this.contenu = contenu
    }

    fun reveler(xy: Int): Boolean {
        TODO()
    }

    fun remplacer(xy: Int, c: Carte) {
        TODO()
    }

    private fun verifColonne() {
        TODO()
    }

    fun calculerPoints(): Int {
        TODO()
    }

    fun  plateauDecouvert(): Boolean {
        TODO()
    }

}