package gg.scala.uhc.configuration.defaults.validate.dimention

import com.cryptomorin.xseries.XMaterial
import gg.scala.uhc.configuration.UhcConfigEntry

/**
 * @author GrowlyX
 * @since 10/29/2021
 */
object NetherConfig : UhcConfigEntry(
    "Nether", arrayOf(),
    XMaterial.NETHERRACK,
    true, Boolean::class.java,
)
