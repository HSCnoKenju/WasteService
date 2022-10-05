/* Generated by AN DISI Unibo */ 
package it.unibo.transporttrolley

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Transporttrolley ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		val interruptedStateTransitions = mutableListOf<Transition>()
		
				resourceappender.initResource()
				var CurrentType = ""
				var PositionsMap: Map<String, Pair<Int,Int>> = emptyMap()
				//var CurrentPosition: Pair<Int,Int>
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("		TRANSPORT TROLLEY | STARTED")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t123",targetState="gotPositions",cond=whenDispatch("all_position"))
				}	 
				state("gotPositions") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("coordinates(HOMEX,HOMEY,INDOORX,INDOORY,PLASTICX,PLASTICY,GLASSX,GLASSY)"), Term.createTerm("coordinates(HOMEX,HOMEY,INDOORX,INDOORY,PLASTICX,PLASTICY,GLASSX,GLASSY)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												PositionsMap = mapOf ("HOME" to Pair(payloadArg(0).toInt(),payloadArg(1).toInt()), "INDOOR" to Pair(payloadArg(2).toInt(),payloadArg(3).toInt()), "PLASTIC" to Pair(payloadArg(4).toInt(),payloadArg(5).toInt()), "GLASS" to Pair(payloadArg(6).toInt(),payloadArg(7).toInt())  )
												
						}
							println("mappa ${PositionsMap.toString()}")  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="accepting", cond=doswitch() )
				}	 
				state("accepting") { //this:State
					action { //it:State
						 resourceappender.initResource()	 
						println("$name in ${currentState.stateName} | $currentMsg")
						println("		TRANSPORT TROLLEY | NEW WORK")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t024",targetState="goingIndoor",cond=whenRequest("goal"))
				}	 
				state("goingIndoor") { //this:State
					action { //it:State
						resourceappender.add("ACCEPTED") 
						updateResourceRep(resourceappender.getStr() 
						)
						if( checkMsgContent( Term.createTerm("destination(CONTAINER)"), Term.createTerm("destination(CONTAINER)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
											
											CurrentType = payloadArg(0)
												
						}
						
								var DestX =  PositionsMap["INDOOR"]?.first
								var DestY = PositionsMap["INDOOR"]?.second
						request("destination", "dest($DestX,$DestY)" ,"pathplanner" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t125",targetState="arriveIndoor",cond=whenReply("arrived"))
				}	 
				state("arriveIndoor") { //this:State
					action { //it:State
						resourceappender.add("INDOOR") 
						updateResourceRep(resourceappender.getStr() 
						)
						emit("pickup", "info($CurrentType)" ) 
						println("		TRANSPORT TROLLEY | INDOOR")
						
								var DestX =  PositionsMap[CurrentType.uppercase()]?.first
								var DestY = PositionsMap[CurrentType.uppercase()]?.second
						request("destination", "dest($DestX,$DestY)" ,"pathplanner" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t126",targetState="arriveContainer",cond=whenReply("arrived"))
				}	 
				state("arriveContainer") { //this:State
					action { //it:State
						resourceappender.add("$CurrentType") 
						updateResourceRep(resourceappender.getStr() 
						)
						answer("goal", "workdone", "info(done)"   )  
						println("		TRANSPORT TROLLEY | $CurrentType")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
				 	 		//sysaction { //it:State
				 	 		  stateTimer = TimerActor("timer_arriveContainer", 
				 	 			scope, context!!, "local_tout_transporttrolley_arriveContainer", 500.toLong() )
				 	 		//}
					}	 	 
					 transition(edgeName="t127",targetState="goingHome",cond=whenTimeout("local_tout_transporttrolley_arriveContainer"))   
					transition(edgeName="t128",targetState="goingIndoor",cond=whenRequest("goal"))
				}	 
				state("goingHome") { //this:State
					action { //it:State
						
								var DestX =  PositionsMap["HOME"]?.first
								var DestY = PositionsMap["HOME"]?.second
						request("destination", "dest($DestX,$DestY)" ,"pathplanner" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t129",targetState="arriveHome",cond=whenReply("arrived"))
				}	 
				state("arriveHome") { //this:State
					action { //it:State
						resourceappender.add("HOME") 
						updateResourceRep(resourceappender.getStr() 
						)
						emit("robotState", "info("true","false","false",123456789)" ) 
						println("		TRANSPORT TROLLEY | going HOME")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="accepting", cond=doswitch() )
				}	 
			}
		}
}
