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
    // waiting: the UHC game is being configured, and players are joining.
    
    // generating: the UHC game has been configured, players 
    // are no longer able to join (except staff), and the world's being generated.
    
    // scattering: any players who are not spectating, or are not staff
    // are scattered throughout the UHC world.
    
    // starting: the game countdown is decremented every one second.
    
    // in_game: the game is running, and the border is shrinking. all scenarios are active
    
    // ended: the game has ended, the winner/winning team has been chosen, all prizes 
    // have been distributed, and the server will shutdown.
    
    WAITING, GENERATING, SCATTERING,
    STARTING, IN_GAME, ENDED
}
