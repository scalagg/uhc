package gg.scala.uhc.model

import gg.scala.uhc.handler.GameHandler

/**
 * Defines what state the [GameHandler]
 * game is currently in.
 *
 * @author GrowlyX
 * @since 10/27/2021
 */
enum class UhcGameState
{
    WAITING, GENERATING, SCATTERING,
    STARTING, IN_GAME, ENDED
}
