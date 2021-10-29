package gg.scala.uhc.configuration.defaults.validate

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object GodAppleConfig : UhcConfigEntry(
    "God Apples", arrayOf(),
    XMaterial.ENCHANTED_GOLDEN_APPLE,
    false, Boolean::class.java
)
