package toaru

import H2_DRIVER
import H2_URL
import api
import io.ktor.application.*
import toaru.entity.ImageTable
import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import toaru.controller.image
import toaru.entity.child.SideTable
import toaru.view.imageView

fun Routing.toaru(){
    api.route("toaru"){
        image()
    }
    route("toaru"){
        get("image"){
            call.imageView()
        }
    }
}

object Toaru {
    private lateinit var dbToaru: Database

    fun schemaCreate(){
        dbToaru = Database.connect("${H2_URL}/toaru", H2_DRIVER)
        transaction {//TODO
            SchemaUtils.create(ImageTable)
            SchemaUtils.create(SideTable)
        }
    }
    fun<T> transaction(statement: Transaction.() -> T): T =
        org.jetbrains.exposed.sql.transactions.transaction(dbToaru, statement)
}