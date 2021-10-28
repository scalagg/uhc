package gg.scala.uhc.listener

import gg.scala.uhc.handler.UhcDataHandler
import gg.scala.uhc.handler.UhcPlayerHandler
import gg.scala.uhc.player.UhcPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object PlayerListener : Listener
{
    @EventHandler
    fun onAsyncPreLogin(event: AsyncPlayerPreLoginEvent)
    {
        UhcDataHandler.playerLayer.fetchEntryByKey(
            event.uniqueId.toString()
        ).thenAccept {
            if (it == null)
            {
                val uhcPlayer = UhcPlayer(event.uniqueId, event.name)
                UhcPlayerHandler.setup(uhcPlayer)
            } else
            {
                it.username = event.name
                UhcPlayerHandler.setup(it)
            }
        }
    }

    @EventHandler
    fun onLogout(event: PlayerQuitEvent)
    {
        UhcPlayerHandler.saveAndForget(event.player)
    }
}
