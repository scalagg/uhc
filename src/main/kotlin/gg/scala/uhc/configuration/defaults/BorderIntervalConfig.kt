package gg.scala.uhc.configuration.defaults

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry
import net.evilblock.cubed.util.time.Duration

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object BorderIntervalConfig : UhcConfigEntry(
    "Border Time", arrayOf(),
    XMaterial.CLOCK,
    Duration.parse("30m"),
    Duration::class.java
)
