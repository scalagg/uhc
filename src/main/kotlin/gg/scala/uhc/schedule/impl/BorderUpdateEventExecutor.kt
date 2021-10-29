package gg.scala.uhc.schedule.impl

import gg.scala.lemon.util.task.DiminutionRunnable
import gg.scala.uhc.border.UhcBorderHandler
import gg.scala.uhc.configuration.defaults.border.BorderSizeConfig
import gg.scala.uhc.schedule.ScheduledExecutor
import gg.scala.uhc.sendToWhomHasEnabled
import me.lucko.helper.Schedulers
import me.lucko.helper.scheduler.Task
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.time.TimeUtil

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
class BorderUpdateEventExecutor : ScheduledExecutor
{
    var currentUpdateTask: Task? = null

    private val oneHundredIntervals = arrayOf(50, 25, 10)
    private var indexForHundreds = -1

    var currentInterval = 0

    override fun start()
    {
        currentInterval = BorderSizeConfig.currentValue as Int

        currentUpdateTask = Schedulers.async().runRepeating(
            BorderUpdateRunnable(2700, currentInterval),
            0L, 20L
        )
    }

    fun calculateNextBorder()
    {
        if (currentInterval > 100)
        {
            currentInterval -= 100
        } else
        {
            if (currentInterval > 10)
            {
                indexForHundreds++
                currentInterval = oneHundredIntervals[indexForHundreds]
            }
        }
    }

    override fun invokeAt(): Int = 0

    private inner class BorderUpdateRunnable(
        seconds: Int, val next: Int
    ) : DiminutionRunnable(seconds)
    {
        override fun getSeconds(): List<Int>
        {
            return listOf(18000, 14400, 10800, 7200, 3600, 2700, 1800, 900, 600, 300, 240, 180, 120, 60, 50, 40, 30, 15, 10, 5, 4, 3, 2, 1)
        }

        override fun onEnd()
        {
            broadcast(
                "${CC.SEC}The border has shrunk to ${CC.PRI}$next${CC.SEC}."
            )

            // handles border update for
            // the current border interval
            UhcBorderHandler.setBorder(next)

            // retrieves the next border (if there is any)
            calculateNextBorder()

            // checks if this runnable is not
            // handling the last border update
            if (currentInterval != next)
            {
                currentUpdateTask = Schedulers.async().runRepeating(
                    BorderUpdateRunnable(2700, currentInterval),
                    0L, 20L
                )
            }
        }

        override fun onRun()
        {
            sendToWhomHasEnabled(
                "${CC.SEC}The border will shrink to ${CC.PRI}$next${CC.SEC} in ${CC.PRI}${TimeUtil.formatIntoDetailedString(seconds)}${CC.SEC}.", "uhc-broadcasts"
            )
        }
    }
}
