package modele.serverData

import io.ktor.http.*

class ServerException(val code: HttpStatusCode, msg: String): Exception(msg) {
}

class NotExistingPlayerException(val id : Int): Exception("Le joueur d'id $id n'existe pas"){

}