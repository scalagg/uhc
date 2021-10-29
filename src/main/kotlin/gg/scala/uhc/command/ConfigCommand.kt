package gg.scala.uhc.command

import gg.scala.uhc.configuration.UhcConfigMenu
import net.evilblock.cubed.acf.BaseCommand
import net.evilblock.cubed.acf.annotation.CommandAlias
import net.evilblock.cubed.acf.annotation.Description
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
class ConfigCommand : BaseCommand()
{
    @CommandAlias("config|cfg")
    @Description("View and/or edit the UHC config.")
    fun onConfig(player: Player)
    {
        UhcConfigMenu().openMenu(player)
    }

    @CommandAlias("scenario|scen")
    @Description("View all UHC scenarios.")
    fun onScenario(player: Player)
    {
        // TODO: 10/29/2021 create scenario display menu
        UhcConfigMenu().openMenu(player)
    }
}
