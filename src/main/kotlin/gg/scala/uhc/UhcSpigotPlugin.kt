package gg.scala.uhc

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.lemon.Lemon
import gg.scala.uhc.board.UhcBoardProvider
import gg.scala.uhc.command.UhcCommand
import gg.scala.uhc.configuration.parser.ConfigEntryParser
import gg.scala.uhc.nametag.UhcNametagProvider
import gg.scala.uhc.schedule.ScheduledExecutionHandler
import gg.scala.uhc.schedule.impl.FinalHealEventExecutor
import gg.scala.uhc.schedule.impl.PvPEnableEventExecutor
import gg.scala.uhc.visibility.UhcVisibilityProvider
import net.evilblock.cubed.command.manager.CubedCommandManager
import net.evilblock.cubed.nametag.NametagHandler
import net.evilblock.cubed.scoreboard.ScoreboardHandler
import net.evilblock.cubed.visibility.VisibilityHandler

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
class UhcSpigotPlugin : ExtendedScalaPlugin()
{
    companion object
    {
        @JvmStatic
        lateinit var instance: UhcSpigotPlugin
    }

    override fun enable()
    {
        instance = this

        syncLoadCosmetics()
        syncLoadResources()
        syncLoadCommands()
    }

    private fun syncLoadCommands()
    {
        val manager = CubedCommandManager(this)

        Lemon.instance.registerCompletionsAndContexts(manager)
        Lemon.instance.registerCommandsInPackage(manager, "gg.scala.uhc.command")
    }

    private fun syncLoadResources()
    {
        ConfigEntryParser.initialLoad()

        ScheduledExecutionHandler.register(PvPEnableEventExecutor)
        ScheduledExecutionHandler.register(FinalHealEventExecutor)
    }

    private fun syncLoadCosmetics()
    {
        ScoreboardHandler.configure(UhcBoardProvider)

        NametagHandler.registerProvider(UhcNametagProvider)
        VisibilityHandler.registerAdapter("uhc", UhcVisibilityProvider)

        logger.info("Loaded cosmetic functionality")
    }
}
