package upmc.akka.ppc

import akka.actor.{Props,  Actor,  ActorRef,  ActorSystem}

object Concert extends App {
  import ConductorActor._
  import ProviderActor._
  import DataBaseActor._
  import PlayerActor._
  // println("starting Mozart's game")

  val system = ActorSystem("TheSystem")
  val db = system.actorOf(Props(new DataBaseActor()), "DataBaseActor")
  val player = system.actorOf(Props(new PlayerActor()),"PlayerActor")

  val provider = system.actorOf(Props(new ProviderActor(db)),"ProviderActor")
  val conductor = system.actorOf(Props(new ConductorActor(provider, player)),"ConductorActor")
  conductor ! StartGame()

 }
