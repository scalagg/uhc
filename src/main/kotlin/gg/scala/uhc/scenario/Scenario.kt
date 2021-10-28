package gg.scala.uhc.scenario

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
abstract class Scenario(
    val name: String,
    val description: Array<String>
)
{
    /**
     * Some scenarios may require other
     * scenarios to be disabled.
     *
     * @return Boolean whether the scenario
     * can be enabled or not.
     */
    abstract fun canBeEnabled(): Boolean

    abstract fun initialize()
}
