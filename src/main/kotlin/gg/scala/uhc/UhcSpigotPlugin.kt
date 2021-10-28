package gg.scala.uhc

import gg.scala.commons.ExtendedScalaPlugin

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
class UhcSpigotPlugin : ExtendedScalaPlugin()
{
    companion object
    {
        @JvmStatic
        lateinit var instance: UhcSpigotPlugin
    }

    override fun enable()
    {
        instance = this
    }
}
