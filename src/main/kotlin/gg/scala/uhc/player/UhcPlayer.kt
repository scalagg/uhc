package gg.scala.uhc.player

import gg.scala.common.Savable
import gg.scala.uhc.handler.UhcDataHandler
import java.util.*
import java.util.concurrent.CompletableFuture

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
class UhcPlayer(
    val uniqueId: UUID,
    var username: String
) : Savable
{
    override fun save(): CompletableFuture<Void>
    {
        return UhcDataHandler.playerLayer.saveEntry(uniqueId.toString(), this)
    }
}
