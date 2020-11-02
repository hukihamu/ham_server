package toaru.view

import io.ktor.application.*
import io.ktor.html.*
import kotlinx.html.*

suspend fun ApplicationCall.imageView() = respondHtml {
    head {
        title { +"ImageUpload" }
    }
    body {
        form(action = "/api/toaru/image/upload", encType = FormEncType.multipartFormData, method = FormMethod.post) {
            input(InputType.file, name = "image_file")
            input(InputType.submit) { +"送信" }
        }
    }

}