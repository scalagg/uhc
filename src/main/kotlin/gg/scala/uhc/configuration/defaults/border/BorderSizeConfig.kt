package gg.scala.uhc.configuration.defaults.border

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object BorderSizeConfig : UhcConfigEntry(
    "Border Size", arrayOf(),
    XMaterial.BEDROCK,
    500, Int::class.java
)
