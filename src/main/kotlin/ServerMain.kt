import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import toaru.toaru

fun main() {
    dbConnect()
    val server = embeddedServer(Netty,8080){
        routing {
            get("/"){
                call.respondText("OK")
            }
            toaru()

        }
    }
    server.start(wait = true)
}

val Route.api: Route
    get() = this.route("api"){}