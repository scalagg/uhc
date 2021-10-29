package gg.scala.uhc.configuration

import com.cryptomorin.xseries.XMaterial

/**
 * @author GrowlyX
 * @since 10/28/2021
 */
open class UhcConfigEntry(
    val title: String,
    val description: Array<String>,
    val indicator: XMaterial,
    var currentValue: Any,
    val type: Class<*>
)
