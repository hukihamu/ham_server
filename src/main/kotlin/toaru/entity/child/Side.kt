package toaru.entity.child

import org.jetbrains.exposed.dao.id.IdTable

object SideTable : IdTable<String>("SIDE_TABLE"){

    override val id = varchar("id",64).entityId()
    val text = text("text")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}