package gg.scala.uhc

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.lemon.Lemon
import gg.scala.uhc.board.UhcBoardProvider
import gg.scala.uhc.command.UhcCommand
import gg.scala.uhc.configuration.UhcGameConfig
import gg.scala.uhc.configuration.parser.ConfigEntryParser
import gg.scala.uhc.configuration.preset.PresetConfigHandler
import gg.scala.uhc.handler.UhcDataHandler
import gg.scala.uhc.handler.UhcUpdateHandler
import gg.scala.uhc.listener.PlayerListener
import gg.scala.uhc.listener.PvPListener
import gg.scala.uhc.nametag.UhcNametagProvider
import gg.scala.uhc.scenario.ScenarioHandler
import gg.scala.uhc.schedule.ScheduledExecutionHandler
import gg.scala.uhc.schedule.impl.FinalHealEventExecutor
import gg.scala.uhc.schedule.impl.PvPEnableEventExecutor
import gg.scala.uhc.visibility.UhcVisibilityProvider
import net.evilblock.cubed.command.manager.CubedCommandManager
import net.evilblock.cubed.nametag.NametagHandler
import net.evilblock.cubed.scoreboard.ScoreboardHandler
import net.evilblock.cubed.visibility.VisibilityHandler
import org.bukkit.Bukkit
import org.bukkit.event.Listener

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

        syncLoadResources()
        syncLoadCosmetics()
        syncLoadCommands()
        syncLoadListeners()

        // Setting the whitelist to true
        Bukkit.setWhitelist(true)
    }

    private fun internalListenerLoad(
        vararg listeners: Listener
    )
    {
        for (listener in listeners)
        {
            server.pluginManager.registerEvents(
                listener, this
            )
        }
    }

    private fun syncLoadListeners()
    {
        internalListenerLoad(
            PvPListener, PlayerListener
        )

        logger.info("Loaded all listeners.")
    }

    private fun syncLoadCommands()
    {
        val manager = CubedCommandManager(this)

        Lemon.instance.registerCompletionsAndContexts(manager)
        Lemon.instance.registerCommandsInPackage(manager, "gg.scala.uhc.command")

        logger.info("Registered all commands.")
    }

    private fun syncLoadResources()
    {
        UhcDataHandler.initialLoad()

        PresetConfigHandler.initialLoad()
        ConfigEntryParser.initialLoad()
        UhcUpdateHandler.initialLoad()
        ScenarioHandler.initialLoad()
        UhcGameConfig.initialLoad()

        ScheduledExecutionHandler.register(PvPEnableEventExecutor)
        ScheduledExecutionHandler.register(FinalHealEventExecutor)

        logger.info("Scheduled in-game events.")
    }

    private fun syncLoadCosmetics()
    {
        ScoreboardHandler.configure(UhcBoardProvider)

        NametagHandler.registerProvider(UhcNametagProvider)
        VisibilityHandler.registerAdapter("uhc", UhcVisibilityProvider)

        logger.info("Loaded cosmetic functionality.")
    }
}
