package gg.scala.uhc.library

import gg.scala.commons.library.model.Library
import gg.scala.lemon.library.LemonLibraryImpl

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcLibraryImpl
{
    @JvmStatic
    val CONFIG_API = LemonLibraryImpl.CONFIG_API

    @JvmStatic
    val PAPER_LIB = Library("io.papermc", "paperlib", "1.0.6")
        .repository("https://papermc.io/repo/repository/maven-public/")
}
