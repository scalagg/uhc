package gg.scala.uhc.configuration.defaults

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object TeamSizeConfig : UhcConfigEntry(
    "Team Size", arrayOf(),
    XMaterial.CHEST,
    2, Int::class.java
)
