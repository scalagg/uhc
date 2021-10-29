package gg.scala.uhc.configuration.defaults

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry
import net.evilblock.cubed.util.time.Duration

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object GracePeriodConfig : UhcConfigEntry(
    "Grace Period", arrayOf(),
    XMaterial.POTION,
    Duration.parse("15m"),
    Duration::class.java
)
