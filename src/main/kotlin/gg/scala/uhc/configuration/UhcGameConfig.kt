package gg.scala.uhc.configuration

import gg.scala.uhc.configuration.defaults.*
import gg.scala.uhc.configuration.defaults.border.*
import gg.scala.uhc.configuration.defaults.validate.*
import gg.scala.uhc.configuration.defaults.validate.dimention.*
import gg.scala.uhc.configuration.defaults.validate.effect.*

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
object UhcGameConfig
{
    val entries = hashSetOf<UhcConfigEntry>()

    fun initialLoad()
    {
        entries.addAll(
            listOf(
                BorderIntervalConfig,
                BorderSizeConfig,
                EndConfig,
                NetherConfig,
                RegenConfig,
                SpeedConfig,
                GodAppleConfig,
                GoldenHeadConfig,
                FinalHealConfig,
                GracePeriodConfig,
                TeamSizeConfig,
            )
        )
    }
}
