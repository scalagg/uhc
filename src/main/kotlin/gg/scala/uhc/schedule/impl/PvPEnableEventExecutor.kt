package gg.scala.uhc.schedule.impl

import gg.scala.lemon.util.task.DiminutionRunnable
import gg.scala.uhc.listener.PvPListener
import gg.scala.uhc.schedule.ScheduledExecutor
import me.lucko.helper.Schedulers
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.time.TimeUtil

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object PvPEnableEventExecutor
    : ScheduledExecutor, DiminutionRunnable(600)
{
    override fun start()
    {
        Schedulers.async().runRepeating(this, 0L, 20L)
    }

    override fun invokeAt(): Int = 600

    override fun onEnd()
    {
        PvPListener.enable()

        broadcast("${CC.GREEN}PvP has been enabled!")
    }

    override fun getSeconds(): List<Int>
    {
        return listOf(600, 300, 240, 180, 120, 60, 30, 15, 10, 5, 4, 3, 2, 1)
    }

    override fun onRun()
    {
        broadcast("${CC.SEC}PvP will enable in ${CC.PRI}${TimeUtil.formatIntoDetailedString(seconds)}${CC.SEC}.")
    }
}
