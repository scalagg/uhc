package gg.scala.uhc.board

import gg.scala.lemon.util.QuickAccess
import gg.scala.uhc.add
import gg.scala.uhc.handler.UhcGameHandler
import gg.scala.uhc.model.UhcGameState
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

                if (ScenarioHandler.selected.isNotEmpty())
                {
                    board.add(
                        "",
                        "Scenarios (${CC.PRI}/scenarios${CC.RESET}):"
                    )

                    ScenarioHandler.selected.forEach {
                        board.add("${CC.GRAY}• ${CC.PRI}$it")
                    }
                }

                if (UhcGameHandler.state == UhcGameState.GENERATING)
                {
                    board.add(
                        "", "${CC.GOLD}World generating..."
                    )
                }
            }
        }

        board.add("")
        board.add("${CC.SEC}www.pvp.bar")
        board.add("${CC.GRAY}${CC.STRIKE_THROUGH}-------------------")
    }

    override fun getTitle(player: Player): String = "${CC.B_PRI}Bar ${CC.GRAY}❘${CC.WHITE} UHC"
}
