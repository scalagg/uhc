package gg.scala.uhc.configuration.preset

import gg.scala.uhc.configuration.preset.impl.fast.*
import gg.scala.uhc.configuration.preset.impl.regular.*

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object PresetConfigHandler
{
    val handle = mutableMapOf<String, PresetConfig>()

    fun initialLoad()
    {
        handle["fast-to2"] = FastTo2PresetConfig
        handle["fast-ffa"] = FastFfaPresetConfig

        handle["regular-ffa"] = RegularFfaPresetConfig
        handle["regular-to2"] = RegularTo2PresetConfig
    }
}
