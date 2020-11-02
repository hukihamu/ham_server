package toaru.entity

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object ImageTable : UUIDTable("image_table"){


    val path: Column<String> = text("path")
}

class Image (id: EntityID<UUID>) : UUIDEntity(id){
    companion object : UUIDEntityClass<Image>(ImageTable)

    var path by ImageTable.path
}