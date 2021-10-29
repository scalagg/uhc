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
    // These act as persistent statistics;

    // Local/current-game statistics should
    // be stored using Player#setMetaData.
    var kills = 0
    var deaths = 0
    var wins = 0

    override fun save(): CompletableFuture<Void>
    {
        return UhcDataHandler.playerLayer.saveEntry(uniqueId.toString(), this)
    }
}
