package gg.scala.uhc.scenario.impl

import gg.scala.uhc.scenario.Scenario
import org.bukkit.event.Listener

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
class CutCleanScenario : Scenario(
    "NoClean", arrayOf(
        "Food is pre-removed, and ores",
        "are pre-smelted thus removing",
        "the need for furnaces."
    )
), Listener
{
    /**
     * Some scenarios may require other
     * scenarios to be disabled.
     *
     * @return Boolean whether the scenario
     * can be enabled or not.
     */
    override fun canBeEnabled(): Boolean = true

    override fun initialize()
    {
        // TODO: 10/28/2021 handle no clean?
        // really have no clue what no clean even is LOL
    }
}
