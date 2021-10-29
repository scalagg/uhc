package gg.scala.uhc.configuration.preset.impl.regular

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
object RegularTo2PresetConfig : PresetConfig
{
    override fun name(): String = "Regular TO2"
    override fun description(): String = "Classic CutClean Duos."

    override fun details(): List<String> = listOf(
        "1500x1500 Border Size",
        "25 minute Border Time",
        "15 minute Grace Period",
        "10 minute Final Heal",
    )

    override fun apply()
    {
        UhcGameHandler.type = UhcGameType.TEAMS
        TeamSizeConfig.currentValue = 2

        BorderSizeConfig.currentValue = 1000
        GracePeriodConfig.currentValue = Duration.parse("15m")
        FinalHealConfig.currentValue = Duration.parse("10m")
        BorderIntervalConfig.currentValue = Duration.parse("25m")
    }
}
