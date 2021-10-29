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
        if (toRefresh.hasMetaData("spectating") && refreshFor.hasMetaData("spectating"))
        {
            val scoreboard = toRefresh.scoreboard 
                  ?: Bukkit.createNewScoreboard(toRefresh)
                  
            val ghostTeam = scoreboard.getTeam("ghost")

            if (!ghostTeam.hasEntry(toRefresh.name)) {
                ghostTeam.addEntry(toRefresh.name)
            }
            if (!ghostTeam.hasEntry(refreshFor.name)) {
                ghostTeam.addEntry(refreshFor.name)
            }
        } else if (toRefresh.hasMetaData("spectating") && !refreshFor.hasMetaData("spectating"))
        {
            return VisibilityAction.HIDE
        }
        
        return VisibilityAction.NEUTRAL
    }
}
