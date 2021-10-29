package gg.scala.uhc.board

import gg.scala.lemon.util.QuickAccess
import gg.scala.uhc.add
import gg.scala.uhc.handler.UhcDataHandler
import gg.scala.uhc.handler.UhcGameHandler
import gg.scala.uhc.handler.UhcUpdateHandler
import gg.scala.uhc.killsOf
import gg.scala.uhc.model.UhcGameState
import gg.scala.uhc.model.UhcGameType
import gg.scala.uhc.scenario.ScenarioHandler
import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import net.evilblock.cubed.util.CC
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcBoardProvider : ScoreboardAdapter()
{
    override fun getLines(board: LinkedList<String>, player: Player)
    {
        board.add("${CC.GRAY}${CC.STRIKE_THROUGH}-------------------")

        when (UhcGameHandler.state)
        {
            UhcGameState.WAITING, UhcGameState.GENERATING ->
            {
                board.add(
                    "Players: ${CC.PRI}${Bukkit.getOnlinePlayers().size}/${Bukkit.getMaxPlayers()}",
                    "Mode: ${CC.PRI}${UhcGameHandler.type.formatted}",
                    "Host: ${
                        if (UhcGameHandler.host == null)
                        {
                            "${CC.RED}N/A"
                        } else
                        {
                            QuickAccess.coloredName(UhcGameHandler.host!!)
                        }
                    }"
                )

                board.add(
                    "",
                    "Scenarios (${CC.PRI}/scenarios${CC.RESET}):"
                )

                if (ScenarioHandler.selected.isNotEmpty())
                {
                    ScenarioHandler.selected.forEach {
                        board.add("${CC.GRAY}• ${CC.PRI}$it")
                    }
                } else
                {
                    board.add(
                        "${CC.RED}No scenarios are",
                        "${CC.RED}currently selected."
                    )
                }
            }
            UhcGameState.SCATTERING ->
            {
                board.add(
                    "${CC.PRI}Scattering:",
                    "Scattered: ${CC.PRI}${
                        UhcUpdateHandler.scattered
                    }/${
                        UhcUpdateHandler.remaining
                    }",
                    // TODO: 10/29/2021 update
                    "Starting In: ${CC.PRI}"
                )
            }
            UhcGameState.STARTING ->
            {
                board.add(
                    // TODO: 10/29/2021 update
                    "Starting In: ${CC.PRI}",
                    "Remaining: ${CC.PRI}${
                        UhcUpdateHandler.remaining
                    }/${
                        UhcGameHandler.initial
                    }",
                    "Kills: ${CC.PRI}${
                        killsOf(player)
                    }",
                    // TODO: 10/29/2021 update
                    "Border: ${CC.PRI}${
                        killsOf(player)
                    }"
                )
            }
        }

        board.add("")
        board.add("${CC.SEC}www.pvp.bar")
        board.add("${CC.GRAY}${CC.STRIKE_THROUGH}-------------------")
    }

    override fun getTitle(player: Player): String {
        return "${CC.B_PRI}${
            when (UhcGameHandler.type)
            {
                UhcGameType.FFA -> "UHC"
                UhcGameType.TEAMS -> {
                    "UHC "
                }
            }
        }"
    }
}
