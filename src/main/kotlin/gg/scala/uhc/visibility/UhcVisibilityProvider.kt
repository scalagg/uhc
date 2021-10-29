package gg.scala.uhc.visibility

import net.evilblock.cubed.visibility.VisibilityAction
import net.evilblock.cubed.visibility.VisibilityAdapter
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcVisibilityProvider : VisibilityAdapter
{
    override fun getAction(toRefresh: Player, refreshFor: Player): VisibilityAction
    {
        if (toRefresh.hasMetadata("spectating") && refreshFor.hasMetadata("spectating"))
        {
            val scoreboard = toRefresh.scoreboard
                  ?: Bukkit.getScoreboardManager().newScoreboard

            val ghostTeam = scoreboard.getTeam("ghost")

            if (!ghostTeam.hasEntry(toRefresh.name)) {
                ghostTeam.addEntry(toRefresh.name)
            }
            if (!ghostTeam.hasEntry(refreshFor.name)) {
                ghostTeam.addEntry(refreshFor.name)
            }
        } else if (toRefresh.hasMetadata("spectating") && !refreshFor.hasMetadata("spectating"))
        {
            return VisibilityAction.HIDE
        }

        return VisibilityAction.NEUTRAL
    }
}
