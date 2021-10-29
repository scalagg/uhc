package gg.scala.uhc.configuration.defaults.validate

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object GoldenHeadConfig : UhcConfigEntry(
    "Golden Heads", arrayOf(),
    XMaterial.GOLDEN_APPLE,
    false, Boolean::class.java
)
