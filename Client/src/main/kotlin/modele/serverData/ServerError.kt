package modele.serverData

import io.ktor.http.*

class ServerException(val code: HttpStatusCode, msg: String): Exception(msg) {
}
