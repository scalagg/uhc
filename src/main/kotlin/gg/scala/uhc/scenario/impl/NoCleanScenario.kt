package gg.scala.uhc.scenario.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.scenario.Scenario
import org.bukkit.event.Listener

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
class NoCleanScenario : Scenario(
    "NoClean", arrayOf(
        "A timer is enabled whenever",
        "you get a kill allowing you to",
        "collect the dead player's items",
        "without being attacked."
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
        // TODO: 10/28/2021 handle no clean functionality
    }

    override fun material(): XMaterial
    {
        return XMaterial.CHEST
    }
}
