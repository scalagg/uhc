package gg.scala.uhc.nametag

import net.evilblock.cubed.nametag.NametagInfo
import net.evilblock.cubed.nametag.NametagProvider
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcNametagProvider : NametagProvider(
    "uhc", 10
)
{
    override fun fetchNametag(toRefresh: Player, refreshFor: Player): NametagInfo?
    {
        TODO("Not yet implemented")
    }
}
