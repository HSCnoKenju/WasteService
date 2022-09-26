/* Generated by AN DISI Unibo */ 
package it.unibo.ledqak22

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Ledqak22 ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		val interruptedStateTransitions = mutableListOf<Transition>()
		 val simulate = DomainSystemConfig.isSimulation()  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("$name STARTS")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="interpreterTrigger",cond=whenEvent("ledtrigger"))
				}	 
				state("interpreterTrigger") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("led(CMD)"), Term.createTerm("led(CMD)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 var Cmd = payloadArg(0)  
								if(  Cmd=="on"  
								 ){if(  simulate 
								 ){forward("ledon", "info(on)" ,"ledsimulator" ) 
								}
								else
								 {forward("ledon", "info(on)" ,"ledconcrete" ) 
								 }
								updateResourceRep("ledState(on)" 
								)
								}
								else
								 {if(  Cmd=="blink" 
								  ){if(  simulate 
								  ){forward("ledblink", "info(blink)" ,"ledsimulator" ) 
								 }
								 else
								  {forward("ledblink", "info(blink)" ,"ledconcrete" ) 
								  }
								 updateResourceRep("ledState(blink)" 
								 )
								 }
								 else
								  {if(  simulate 
								   ){forward("ledoff", "info(off)" ,"ledsimulator" ) 
								  }
								  else
								   {forward("ledoff", "info(off)" ,"ledconcrete" ) 
								   }
								  updateResourceRep("ledState(off)" 
								  )
								  }
								 }
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t01",targetState="interpreterTrigger",cond=whenEvent("ledtrigger"))
				}	 
			}
		}
}
