package gg.scala.uhc.handler

import gg.scala.uhc.player.UhcPlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcPlayerHandler
{
    private val handle = mutableMapOf<UUID, UhcPlayer>()

    fun setup(uhcPlayer: UhcPlayer)
    {
        handle[uhcPlayer.uniqueId] = uhcPlayer
    }

    fun saveAndForget(player: Player)
    {
        handle.remove(player.uniqueId)?.save()
    }

    fun find(uuid: UUID): UhcPlayer?
    {
        return handle[uuid]
    }

    fun find(player: Player): UhcPlayer?
    {
        return handle[player.uniqueId]
    }

    fun find(name: String): UhcPlayer?
    {
        val bukkit = Bukkit.getPlayer(name)
            ?: return null

        return handle[bukkit.uniqueId]
    }

}
