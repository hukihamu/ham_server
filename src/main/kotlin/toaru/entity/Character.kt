package toaru.entity

import toaru.entity.child.AttributeTable
import org.jetbrains.exposed.dao.id.UUIDTable
import toaru.entity.child.SideTable

object CharacterTable : UUIDTable("Character_table"){
    val name = text("name")
    val secondName = text("second_name")
    val side = reference("side", SideTable)
    val attribute = reference("attribute", AttributeTable)
    val charType = text("char_type")
    val rarity = text("rarity")
    val attackType = text("attack_type").nullable()
    val attackDirection = text("attack_direction").nullable()
    val skill1 = text("skill1")
    val skill2 = text("skill2").nullable()
    val potential1 = text("potential_1")
    val potential2 = text("potential_2")

}