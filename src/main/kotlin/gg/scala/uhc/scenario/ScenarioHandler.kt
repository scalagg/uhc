package gg.scala.uhc.scenario

import gg.scala.uhc.UhcSpigotPlugin
import net.evilblock.cubed.util.ClassUtils

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object ScenarioHandler
{
    val scenarios = hashMapOf<String, Scenario>()
    val selected = hashSetOf<String>()

    fun applyAllSelectedScenarios()
    {
        selected.forEach {
            val scenario = scenarios[it]
                ?: return@forEach

            scenario.initialize()
        }
    }

    fun initialLoad()
    {
        ClassUtils.getClassesInPackage(
            UhcSpigotPlugin.instance,
            "gg.scala.scenario.impl"
        ).forEach {
            try
            {
                val scenario = it.newInstance() as Scenario
                scenarios[scenario.name] = scenario
            } catch (ignored: Exception)
            {
                UhcSpigotPlugin.instance.logger.info(
                    "Failed to load scenario: ${it.name}"
                )
            }
        }
    }
}
