package gg.scala.uhc.board

import net.evilblock.cubed.scoreboard.ScoreboardAdapter
import net.evilblock.cubed.util.CC
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

    }

    override fun getTitle(player: Player): String = "${CC.B_PRI}Bar ${CC.GRAY}❘${CC.WHITE} UHC"
}
