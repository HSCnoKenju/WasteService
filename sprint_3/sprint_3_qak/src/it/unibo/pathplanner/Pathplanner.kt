/* Generated by AN DISI Unibo */ 
package it.unibo.pathplanner

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Pathplanner ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "activate"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		val interruptedStateTransitions = mutableListOf<Transition>()
		 val Inmapname   = "xxx" //"map2019"  
			   var PathTodo    =  ""  
			   var CurGoalX    = 3
			   var CurGoalY    = 3 
			   var mapExist 	= true
			   var Outmapname = "yyy"
		return { //this:ActionBasciFsm
				state("activate") { //this:State
					action { //it:State
						 mapExist = filechecker.fileExists(Inmapname) 
						if( ( mapExist 	)  
						 ){}
						else
						 {request("buildMap", "build($Inmapname)" ,"boundarymapbuilder" )  
						 }
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="readMap", cond=doswitchGuarded({ mapExist 
					}) )
					transition( edgeName="goto",targetState="dummy_wait_build", cond=doswitchGuarded({! ( mapExist 
					) }) )
				}	 
				state("dummy_wait_build") { //this:State
					action { //it:State
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t017",targetState="readMap",cond=whenReply("mapCreated"))
				}	 
				state("readMap") { //this:State
					action { //it:State
						unibo.kotlin.planner22Util.createRoomMapFromTextfile( "$Inmapname.txt"  )
						unibo.kotlin.planner22Util.initAI(  )
						unibo.kotlin.planner22Util.showCurrentRobotState(  )
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="accepting", cond=doswitch() )
				}	 
				state("accepting") { //this:State
					action { //it:State
						println(" PATH PLANNER | ACCEPTING")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t018",targetState="findAPath",cond=whenRequest("destination"))
				}	 
				state("findAPath") { //this:State
					action { //it:State
						println("PATH PLANNER | FIND A PATH")
						if( checkMsgContent( Term.createTerm("dest(DESTX,DESTY)"), Term.createTerm("dest(DESTX,DESTY)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("$name in ${currentState.stateName} | $currentMsg")
								
											
												CurGoalX = payloadArg(0).toInt()
												CurGoalY = payloadArg(1).toInt()
												
						}else{
							println("Keep searching $CurGoalX, $CurGoalY")
						}
						userContinue.userEnd(myself)
						unibo.kotlin.planner22Util.setGoal( CurGoalX, CurGoalY  )
						 PathTodo = unibo.kotlin.planner22Util.doPlan().toString()  //List<aima.core.agent.Action>  [w, w, l, w] 
									.replace(" ","")
									.replace(",","")
									.replace("[","")
									.replace("]","")
						println("Azioni pianificate: $PathTodo")
						if(  PathTodo.length == 0  
						 ){println("WARNING: nessuna azione pianificata. Il piano vuoto viene comunque eseguito")
						}
						request("dopath", "dopath($PathTodo)" ,"pathexec" )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t019",targetState="pathok",cond=whenReply("dopathdone"))
					transition(edgeName="t020",targetState="pathko",cond=whenReply("dopathfail"))
				}	 
				state("pathok") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						unibo.kotlin.planner22Util.updateMapWithPath( PathTodo  )
						unibo.kotlin.planner22Util.showCurrentRobotState(  )
						unibo.kotlin.planner22Util.saveRoomMap( "$Outmapname"  )
						answer("destination", "arrived", "info(done)"   )  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="accepting", cond=doswitch() )
				}	 
				state("pathko") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("dopathfail(ARG)"), Term.createTerm("dopathfail(P)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  val planStillTodo = payloadArg(0) 
												var p =""
												if( planStillTodo=="none"){ //l'ultimo w ha provocato il fail
													p = PathTodo.dropLast(1)
												}else{
													p = PathTodo.dropLast( planStillTodo.length+1 ) //un w ha provocato il fail
												}
												println("planStillTodo:$planStillTodo over $PathTodo done: $p")
								unibo.kotlin.planner22Util.updateMapWithPath( p  )
								unibo.kotlin.planner22Util.updateMapObstacleOnCurrentDirection(  )
						}
						unibo.kotlin.planner22Util.showCurrentRobotState(  )
						unibo.kotlin.planner22Util.saveRoomMap( "$Outmapname"  )
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="findAPath", cond=doswitch() )
				}	 
			}
		}
}