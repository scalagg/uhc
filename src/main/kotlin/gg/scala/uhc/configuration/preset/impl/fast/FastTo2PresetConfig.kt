package gg.scala.uhc.configuration.preset.impl.fast

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.defaults.FinalHealConfig
import gg.scala.uhc.configuration.defaults.GracePeriodConfig
import gg.scala.uhc.configuration.defaults.TeamSizeConfig
import gg.scala.uhc.configuration.defaults.border.BorderIntervalConfig
import gg.scala.uhc.configuration.defaults.border.BorderSizeConfig
import gg.scala.uhc.configuration.preset.PresetConfig
import gg.scala.uhc.handler.UhcGameHandler
import gg.scala.uhc.model.UhcGameType
import net.evilblock.cubed.util.time.Duration

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object FastTo2PresetConfig : PresetConfig
{
    override fun name(): String = "Fast TO2"
    override fun description(): String = "Fast-paced duos game."
    override fun xMaterial(): XMaterial = XMaterial.SUGAR

    override fun details(): List<String> = listOf(
        "1000x1000 Border Size",
        "20 minute Border Time",
        "10 minute Grace Period",
        "5 minute Final Heal",
    )

    override fun apply()
    {
        UhcGameHandler.type = UhcGameType.TEAMS
        TeamSizeConfig.currentValue = 2

        BorderSizeConfig.currentValue = 1000
        GracePeriodConfig.currentValue = Duration.parse("10m")
        FinalHealConfig.currentValue = Duration.parse("5m")
        BorderIntervalConfig.currentValue = Duration.parse("20m")
    }
}
