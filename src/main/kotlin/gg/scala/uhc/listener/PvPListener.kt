package gg.scala.uhc.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object PvPListener : Listener
{
    private var enabled = false

    fun enable()
    {
        enabled = true
    }

    fun disable()
    {
        enabled = false
    }

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent)
    {
        // TODO: Handle PvP logic here (all UHC modes)
    }
}
