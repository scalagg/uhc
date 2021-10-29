package gg.scala.uhc

import gg.scala.cookie.adventure.KyoriBridge
import gg.scala.lemon.handler.PlayerHandler
import net.evilblock.cubed.util.bukkit.FancyMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
fun sendToWhomHasEnabled(message: String, value: String)
{
    Bukkit.getOnlinePlayers()
        .filter {
            PlayerHandler.findPlayer(it).orElse(null)?.hasMetadata(value) == false
        }
        .forEach {
            it.sendMessage(message)
        }
}

fun sendToWhomHasEnabled(message: Component, value: String)
{
    Bukkit.getOnlinePlayers()
        .filter {
            PlayerHandler.findPlayer(it).orElse(null)?.hasMetadata(value) == false
        }
        .forEach { player ->
            KyoriBridge.adventure().player(player).sendMessage(message)
        }
}

fun sendToWhomHasEnabled(message: FancyMessage, value: String)
{
    Bukkit.getOnlinePlayers()
        .filter {
            PlayerHandler.findPlayer(it).orElse(null)?.hasMetadata(value) == false
        }
        .forEach { player ->
            message.sendToPlayer(player)
        }
}

fun killsOf(player: Player): Int
{
    return if (player.hasMetadata("kills"))
    {
        val metadata = player.getMetadata("kills")

        if (metadata.isEmpty())
        {
            return 0
        }

        player.getMetadata("kills")[0].asInt()
    } else
    {
        0
    }
}

fun <T> MutableList<T>.add(vararg values: T)
{
    for (value in values)
    {
        add(value)
    }
}

fun <T> LinkedList<T>.add(vararg values: T)
{
    for (value in values)
    {
        add(value)
    }
}
