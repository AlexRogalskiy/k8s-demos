package notes

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.content.defaultResource
import io.ktor.http.content.static
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import java.util.*

data class Note(val note: String, val time: String = Date().toString())

val notes = mutableListOf(Note("first note!"))

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON
        }
    }
    routing {
        static("/") {
            defaultResource("index.html")
        }

        get("/notes") {
            call.respond(notes)
        }

        get("/note") {
            call.parameters["id"]?.let {
                call.respond(notes[it.toInt()])
            }
        }

        post("/note") {
            notes.add(call.receive())
        }
    }
}

fun main() {
    embeddedServer(Netty, 8080, watchPaths = listOf("NotesAppKt"), module = Application::module).start()
}
