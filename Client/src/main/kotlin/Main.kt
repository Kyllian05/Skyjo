import modele.Server

suspend fun main(args: Array<String>) {
    val s = Server("http://172.26.82.23")
    s.getName(100)
}