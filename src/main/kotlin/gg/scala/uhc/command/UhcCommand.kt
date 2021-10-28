package gg.scala.uhc.command

import gg.scala.uhc.configuration.UhcConfigMenu
import net.evilblock.cubed.acf.BaseCommand
import net.evilblock.cubed.acf.CommandHelp
import net.evilblock.cubed.acf.annotation.*
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
}
