package gg.scala.uhc.handler

import gg.scala.lemon.Lemon
import me.lucko.helper.Schedulers
import org.bukkit.Bukkit

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object UhcUpdateHandler : Runnable
{
    var remaining = 0
    var spectating = 0

    fun initialLoad()
    {
        Lemon.instance.localInstance
            .metaData["game-server"] = "true"

        Lemon.instance.localInstance
            .metaData["game-server-type"] = "uhc"

        Schedulers.async().runRepeating(
            this, 0L, 20L
        )
    }

    override fun run()
    {
        remaining = Bukkit.getOnlinePlayers()
            .filter { it.hasMetadata("playing") }.size
        spectating = Bukkit.getOnlinePlayers()
            .filter { it.hasMetadata("playing") }.size

        Lemon.instance.localInstance
            .metaData["game-state"] = UhcGameHandler.state.name

        Lemon.instance.localInstance
            .metaData["remaining"] = remaining.toString()

        Lemon.instance.localInstance
            .metaData["spectating"] = spectating.toString()
    }
}
