package modele.serverData

import kotlinx.serialization.Serializable

@Serializable
class VraiCarte (val valeur : Int,val couleur : String)
@Serializable
class CartePlateaux (val valeur : String,val couleur : String)

@Serializable
class Player (val idJoueur : Int,val colonnes : Array<Array<CartePlateaux>>)

@Serializable
class Plateau (val nbJoueursMax : Int,
               val etape : String,
               val carteSommetDefausse : CartePlateaux,
               val indexJoueurCourant : Int,
               val plateaux : Array<Player>,
               val indexJoueurTerminant : Int,
               val cartePiochee : CartePlateaux?
)