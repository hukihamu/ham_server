package toaru.controller

import toaru.entity.Image
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import toaru.Toaru
import java.io.BufferedOutputStream
import java.io.File
import java.io.InputStream
import java.util.*
import javax.imageio.ImageIO

private const val IMAGE_DIR = "./toaru/image"

fun Route.image(){
    route("image"){
        upload()
        getImage()
    }
}

private fun Route.getImage(){
    get("get/{imageId}"){
        call.parameters["imageId"]?.let {
            Toaru.transaction {
                Image[UUID.fromString(it)].path
            }.let {
                call.respondBytes(File(it).readBytes(), ContentType.Image.JPEG)
            }
        }
    }
}

private fun Route.upload(){
    post("upload") {
        val multipart = call.receiveMultipart()
        val uuid = UUID.randomUUID()
        val parentFile = File(IMAGE_DIR)
        if (!parentFile.isDirectory) parentFile.mkdirs()
        var imageFile: File? = null

        multipart.forEachPart { part ->
            if (part is PartData.FileItem){
                val ext = File(part.originalFileName).extension
                val file = File(parentFile,"$uuid.$ext")
                part.streamProvider().use { input ->
                    input.copyToSuspend(file)
                }
                imageFile = file
            }
            part.dispose()
        }
        imageFile?.let { Toaru.transaction { Image.new(uuid){
            path = it.path
        } } }
    }
}

private suspend fun InputStream.copyToSuspend(file: File){
    withContext(Dispatchers.IO){
        val image = ImageIO.read(this@copyToSuspend)
        ImageIO.write(image,"jpg",file)
    }
}