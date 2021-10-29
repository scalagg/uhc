package gg.scala.uhc.configuration.preset.impl.regular

import com.cryptomorin.xseries.XMaterial
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
object RegularFfaPresetConfig : PresetConfig
{
    override fun name(): String = "Regular FFA"
    override fun description(): String = "Classic CutClean FFA."
    override fun xMaterial(): XMaterial = XMaterial.GOLDEN_APPLE

    override fun details(): List<String> = listOf(
        "1500x1500 Border Size",
        "30 minute Border Time",
        "15 minute Grace Period",
        "10 minute Final Heal",
    )

    override fun apply()
    {
        BorderSizeConfig.currentValue = 1500
        GracePeriodConfig.currentValue = Duration.parse("15m")
        FinalHealConfig.currentValue = Duration.parse("10m")
        BorderIntervalConfig.currentValue = Duration.parse("30m")
    }
}
