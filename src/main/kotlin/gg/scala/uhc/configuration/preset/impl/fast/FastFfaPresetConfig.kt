package gg.scala.uhc.configuration.preset.impl.fast

import gg.scala.uhc.configuration.defaults.FinalHealConfig
import gg.scala.uhc.configuration.defaults.GracePeriodConfig
import gg.scala.uhc.configuration.defaults.border.BorderIntervalConfig
import gg.scala.uhc.configuration.defaults.border.BorderSizeConfig
import gg.scala.uhc.configuration.preset.PresetConfig
import net.evilblock.cubed.util.time.Duration

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object FastFfaPresetConfig : PresetConfig
{
    override fun name(): String = "Fast FFA"
    override fun description(): String = "Fast-paced free-for-all game."

    override fun details(): List<String> = listOf(
        "1000x1000 Border Size",
        "20 minute Border Time",
        "10 minute Grace Period",
        "5 minute Final Heal",
    )

    override fun apply()
    {
        BorderSizeConfig.currentValue = 1000
        GracePeriodConfig.currentValue = Duration.parse("10m")
        FinalHealConfig.currentValue = Duration.parse("5m")
        BorderIntervalConfig.currentValue = Duration.parse("20m")
    }
}
