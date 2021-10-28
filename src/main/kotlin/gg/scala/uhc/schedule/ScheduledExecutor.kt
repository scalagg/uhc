package gg.scala.uhc.schedule

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
interface ScheduledExecutor
{
    fun start()
    fun invokeAt(): Int
}
