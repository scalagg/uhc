package gg.scala.uhc.command

import gg.scala.lemon.player.LemonPlayer
import gg.scala.uhc.configuration.UhcConfigMenu
import net.evilblock.cubed.acf.BaseCommand
import net.evilblock.cubed.acf.CommandHelp
import net.evilblock.cubed.acf.ConditionFailedException
import net.evilblock.cubed.acf.annotation.*
import net.evilblock.cubed.util.CC
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
@CommandAlias("uhc")
@CommandPermission("uhc.command.uhc")
class UhcCommand : BaseCommand()
{
    @Default
    @HelpCommand
    fun onHelp(help: CommandHelp)
    {
        help.showHelp()
    }

    @Subcommand("config")
    @Description("View and/or edit the UHC config.")
    fun onConfig(player: Player)
    {
        UhcConfigMenu().openMenu(player)
    }

    @Subcommand("host")
    @CommandPermission("uhc.command.uhc.subcommand.host")
    @Description("Set yourself, or another player as the UHC host.")
    fun onHost(player: Player, @Optional target: LemonPlayer?)
    {
        val host = if (target != null) target.bukkitPlayer!! else player

        if (!player.hasPermission("uhc.command.uhc.subcommand.host.other") && target != null)
        {
            throw ConditionFailedException("You don't have permission to set other players as the UHC host!")
        }

        player.sendMessage("${CC.PRI}${host.name}${CC.SEC} is now the game host.")
    }
}
