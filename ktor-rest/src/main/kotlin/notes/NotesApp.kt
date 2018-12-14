package notes

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
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

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("NotesAppKt"), module = Application::module).start()
}
