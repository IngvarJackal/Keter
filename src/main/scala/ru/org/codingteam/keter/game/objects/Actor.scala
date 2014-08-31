package ru.org.codingteam.keter.game.objects

import ru.org.codingteam.keter.game.GameState
import ru.org.codingteam.keter.game.actions.Action

import scala.concurrent.Future

abstract class Actor(override val name: String, override val tile: String) extends GameObject {

  def playerControllable: Boolean
  val enabled = true
  def getNextAction(state: GameState): Future[Action]

}
