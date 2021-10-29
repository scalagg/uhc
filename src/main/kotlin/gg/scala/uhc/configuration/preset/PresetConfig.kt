package gg.scala.uhc.configuration.preset

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
interface PresetConfig
{
    fun name(): String
    fun description(): String

    fun details(): List<String>

    fun apply()
}
