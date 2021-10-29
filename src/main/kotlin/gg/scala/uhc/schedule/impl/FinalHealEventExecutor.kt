package gg.scala.uhc.schedule.impl

import gg.scala.lemon.util.task.DiminutionRunnable
import gg.scala.uhc.configuration.defaults.FinalHealConfig
import gg.scala.uhc.configuration.defaults.GracePeriodConfig
import gg.scala.uhc.listener.PvPListener
import gg.scala.uhc.schedule.ScheduledExecutor
import gg.scala.uhc.sendToWhomHasEnabled
import me.lucko.helper.Schedulers
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.Bukkit

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object FinalHealEventExecutor : ScheduledExecutor
{
    override fun start()
    {
        Schedulers.async().runRepeating(
            FinalHealRunnable(
                FinalHealConfig.currentValue as Int
            ),
            0L, 20L
        )
    }

    override fun invokeAt(): Int = 0

    class FinalHealRunnable(
        seconds: Int
    ) : DiminutionRunnable(seconds)
    {
        override fun onEnd()
        {
            Bukkit.getOnlinePlayers()
                .filter { it.hasMetadata("playing") }
                .forEach {
                    it.health = 20.0
                }

            // everyone needs to know this, so we won't
            // check for metadata like we are on onRun.
            broadcast("${CC.GREEN}The final heal has occurred!")
        }

        override fun getSeconds(): List<Int>
        {
            return listOf(600, 300, 240, 180, 120, 60, 30, 15, 10, 5, 4, 3, 2, 1)
        }

        override fun onRun()
        {
            sendToWhomHasEnabled(
                "${CC.SEC}Final Heal will occur in ${CC.PRI}${TimeUtil.formatIntoDetailedString(seconds)}${CC.SEC}.", "uhc-broadcasts"
            )
        }
    }
}
