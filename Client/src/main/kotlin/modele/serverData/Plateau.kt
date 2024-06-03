package modele.serverData

import kotlinx.serialization.Serializable

@Serializable
class Carte (val valeur : String,val couleur : String)

@Serializable
class Player (val idJoueur : Int,val colonnes : Array<Array<Carte>>)

@Serializable
class Plateau (val nbJoueursMax : Int,
               val etape : String,
               val carteSommetDefausse : Carte,
               val indexJoueurCourant : Int,
               val plateaux : Array<Player>,
               val indexJoueurTerminant : Int,
               val cartePiochee : Carte?
)