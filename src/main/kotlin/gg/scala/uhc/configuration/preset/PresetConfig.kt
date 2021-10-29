package gg.scala.uhc.configuration.preset

import com.cryptomorin.xseries.XMaterial

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
interface PresetConfig
{
    fun name(): String
    fun description(): String
    fun xMaterial(): XMaterial

    fun details(): List<String>

    fun apply()
}
