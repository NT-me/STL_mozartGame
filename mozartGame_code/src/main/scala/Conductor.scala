package upmc.akka.ppc

import math._

import javax.sound.midi._
import javax.sound.midi.ShortMessage._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

import akka.actor.{Props, Actor, ActorRef, ActorSystem}

import DataBaseActor._

object ConductorActor {
  case class giveMeasure(chords: List [Chord])
  case class StartGame()
}

//////////////////////////////////////////////////

class ConductorActor (provider: ActorRef, player: ActorRef) extends Actor{
  import ConductorActor._
  import ProviderActor._
  import PlayerActor._

  def receive = {
    case StartGame () => {
      val r = scala.util.Random
      provider ! getMeasure(r.nextInt(11))
    }

    case giveMeasure(chords: List [Chord]) =>{
      player ! Measure(chords)
      Thread.sleep(1800)
      context.self ! StartGame()
    }

  }
}
