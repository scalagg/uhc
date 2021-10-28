package gg.scala.uhc.schedule

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object ScheduledExecutionHandler
{
    val executors = hashSetOf<ScheduledExecutor>()

    fun register(executor: ScheduledExecutor)
    {
        executors.add(executor)
    }
}
