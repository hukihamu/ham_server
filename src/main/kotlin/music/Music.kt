package music

import io.ktor.routing.*

fun Routing.music(routing: Route.() -> Unit){
    route("music"){
        routing()
    }
}