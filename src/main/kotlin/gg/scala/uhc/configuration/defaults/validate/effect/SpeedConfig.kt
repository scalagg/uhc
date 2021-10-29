package gg.scala.uhc.configuration.defaults.validate.effect

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object SpeedConfig : UhcConfigEntry(
    "Speed", arrayOf(),
    XMaterial.POTION,
    "none", String::class.java,
    itemData = 8194
)
