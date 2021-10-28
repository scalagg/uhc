package gg.scala.uhc.command

import net.evilblock.cubed.acf.BaseCommand
import net.evilblock.cubed.acf.CommandHelp
import net.evilblock.cubed.acf.annotation.CommandAlias
import net.evilblock.cubed.acf.annotation.CommandPermission
import net.evilblock.cubed.acf.annotation.Default
import net.evilblock.cubed.acf.annotation.HelpCommand

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
}
