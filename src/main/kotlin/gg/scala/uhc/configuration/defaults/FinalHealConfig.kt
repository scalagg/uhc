package gg.scala.uhc.configuration.defaults

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry
import net.evilblock.cubed.util.time.Duration

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object FinalHealConfig : UhcConfigEntry(
    "Final Heal", arrayOf(),
    XMaterial.ENCHANTED_BOOK,
    Duration.parse("10m"),
    Duration::class.java
)
