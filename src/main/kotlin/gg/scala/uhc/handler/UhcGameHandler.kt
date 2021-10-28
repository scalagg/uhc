package gg.scala.uhc.handler

import gg.scala.uhc.model.UhcGameState

/**
 * @author GrowlyX
 * @since 10/27/2021
 */
object UhcGameHandler
{
    // We'll track "remaining" players and
    // spectators through Player#hasMetaData

    // The metadata key for a player is: `playing`
    // The metadata key for a spectator is: `spectating`

    // Make sure to remove the `playing`
    // metadata key if a player enters spectator mode.
    var state = UhcGameState.WAITING
}
