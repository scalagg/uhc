package gg.scala.uhc.configuration.defaults

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry
import net.evilblock.cubed.util.time.Duration

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object BorderConfig : UhcConfigEntry(
    "Border", arrayOf(),
    XMaterial.BEDROCK,
    500, Int::class.java
)
