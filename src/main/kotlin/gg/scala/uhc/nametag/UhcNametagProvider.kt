package gg.scala.uhc.nametag

import net.evilblock.cubed.nametag.NametagInfo
import net.evilblock.cubed.nametag.NametagProvider
import net.evilblock.cubed.util.CC
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
        return if (toRefresh.hasMetadata("spectating"))
        {
            return createNametag(CC.GRAY, "")
        } else null
    }
}
