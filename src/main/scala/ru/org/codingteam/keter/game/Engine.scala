package ru.org.codingteam.keter.game

import ru.org.codingteam.keter.game.actions.Action
import ru.org.codingteam.keter.game.objects.Actor
import ru.org.codingteam.rotjs.interface.EventQueue

object Engine {

  /**
   * Processes all actions from the queue before any player-controlled entity may take the turn.
   * @param state current state of the game.
   * @param queue scheduled event queue.
   * @return tuple of the new game state and an actor whose turn should be scheduled before proceeding.
   */
  def processTurn(state: GameState, queue: EventQueue): (GameState, Actor) = {
    val action = queue.get().asInstanceOf[Action]
    val nextState = action.process(state.copy(time = queue.getTime().toLong))

    val actor = action.actor
    if (actor.playerControllable) {
      // We should wait for the next turn planning.
      (nextState, actor)
    } else {
      // Process next action.
      processTurn(nextState, queue)
    }
  }

}