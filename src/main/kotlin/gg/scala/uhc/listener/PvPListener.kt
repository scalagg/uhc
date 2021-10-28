package gg.scala.uhc.listener

import gg.scala.uhc.handler.UhcGameHandler
import me.lucko.helper.Events
import org.bukkit.event.entity.EntityDamageByEntityEvent

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object PvPListener
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

    fun initialLoad()
    {
        Events.subscribe(EntityDamageByEntityEvent::class.java).handler {
            // TODO: 10/28/2021 add pvp logic here
        }
    }
}
