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
		
				resourceappender.initResource()
				var CurrentType = ""
				var PositionsMap: Map<String, Pair<Int,Int>> = emptyMap()
				//var CurrentPosition: Pair<Int,Int>
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("		TRANSPORT TROLLEY | STARTED")
					}
					 transition(edgeName="t10",targetState="gotPositions",cond=whenDispatch("all_position"))
				}	 
				state("gotPositions") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("coordinates(HOMEX,HOMEY,INDOORX,INDOORY,PLASTICX,PLASTICY,GLASSX,GLASSY)"), Term.createTerm("coordinates(HOMEX,HOMEY,INDOORX,INDOORY,PLASTICX,PLASTICY,GLASSX,GLASSY)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
												PositionsMap = mapOf ("HOME" to Pair(payloadArg(0).toInt(),payloadArg(1).toInt()), "INDOOR" to Pair(payloadArg(2).toInt(),payloadArg(3).toInt()), "PLASTIC" to Pair(payloadArg(4).toInt(),payloadArg(5).toInt()), "GLASS" to Pair(payloadArg(6).toInt(),payloadArg(7).toInt())  )
												
						}
							println("mappa ${PositionsMap.toString()}")  
					}
					 transition( edgeName="goto",targetState="accepting", cond=doswitch() )
				}	 
				state("accepting") { //this:State
					action { //it:State
						 resourceappender.initResource()	 
						println("$name in ${currentState.stateName} | $currentMsg")
						println("		TRANSPORT TROLLEY | NEW WORK")
					}
					 transition(edgeName="t01",targetState="goingIndoor",cond=whenRequest("goal"))
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
					}
					 transition(edgeName="t12",targetState="arriveIndoor",cond=whenReply("arrived"))
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
					}
					 transition(edgeName="t13",targetState="arriveContainer",cond=whenReply("arrived"))
				}	 
				state("arriveContainer") { //this:State
					action { //it:State
						resourceappender.add("$CurrentType") 
						updateResourceRep(resourceappender.getStr() 
						)
						answer("goal", "workdone", "info(done)"   )  
						println("		TRANSPORT TROLLEY | $CurrentType")
						stateTimer = TimerActor("timer_arriveContainer", 
							scope, context!!, "local_tout_transporttrolley_arriveContainer", 500.toLong() )
					}
					 transition(edgeName="t14",targetState="goingHome",cond=whenTimeout("local_tout_transporttrolley_arriveContainer"))   
					transition(edgeName="t15",targetState="goingIndoor",cond=whenRequest("goal"))
				}	 
				state("goingHome") { //this:State
					action { //it:State
						
								var DestX =  PositionsMap["HOME"]?.first
								var DestY = PositionsMap["HOME"]?.second
						request("destination", "dest($DestX,$DestY)" ,"pathplanner" )  
					}
					 transition(edgeName="t16",targetState="arriveHome",cond=whenReply("arrived"))
				}	 
				state("arriveHome") { //this:State
					action { //it:State
						resourceappender.add("HOME") 
						updateResourceRep(resourceappender.getStr() 
						)
						println("		TRANSPORT TROLLEY | going HOME")
					}
					 transition( edgeName="goto",targetState="accepting", cond=doswitch() )
				}	 
			}
		}
}
