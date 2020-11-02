package toaru.entity.child

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object AttributeTable : IdTable<String>("ATTRIBUTE_TABLE"){

    override val id: Column<EntityID<String>> = text("id").entityId()
    val text = SideTable.text("text")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}