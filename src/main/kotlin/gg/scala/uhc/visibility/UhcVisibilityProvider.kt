package gg.scala.uhc.visibility

import net.evilblock.cubed.visibility.VisibilityAction
import net.evilblock.cubed.visibility.VisibilityAdapter
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcVisibilityProvider : VisibilityAdapter
{
    override fun getAction(toRefresh: Player, refreshFor: Player): VisibilityAction
    {
        if (toRefresh.hasMetaData("spectating") && !refreshFor.hasMetaData("spectating"))
        {
            return VisibilityAction.HIDE
        } else if (toRefresh.hasMetaData("host") && !refreshFor.hasMetaData("host"))
        {
            return VisibilityAction.HIDE
        }
        
        return VisibilityAction.NEUTRAL
    }
}
