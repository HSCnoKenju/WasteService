/* Generated by AN DISI Unibo */ 
package it.unibo.thresholdchecker

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Thresholdchecker ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		val interruptedStateTransitions = mutableListOf<Transition>()
		
			
			 
			val DLIMIT = 40
			
			var HigherThanLimit = false
				
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
							CoapObserverSupport(myself, "192.168.126.33","8061","ctxsonarqak22","sonarqak22")
					//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t02",targetState="handleUpdate",cond=whenDispatch("coapUpdate"))
				}	 
				state("handleUpdate") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("coapUpdate(RESOURCE,VALUE)"), Term.createTerm("coapUpdate(RESOURCE,VALUE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
											
												var Distance = payloadArg(1).toInt()
												
												HigherThanLimit = (Distance >= DLIMIT) 
											
												
						}
						if( checkMsgContent( Term.createTerm("coapUpdate(RESOURCE,VALUE)"), Term.createTerm("coapUpdate(RESOURCE,VALUE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 MsgUtil.outgreen("applobserver OBSERVES: ${payloadArg(1)} FROM ${payloadArg(0)} ")  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="broadcastResume", cond=doswitchGuarded({ HigherThanLimit  
					}) )
					transition( edgeName="goto",targetState="broadcastStop", cond=doswitchGuarded({! ( HigherThanLimit  
					) }) )
				}	 
				state("broadcastStop") { //this:State
					action { //it:State
						emit("stop", "stop(stop)" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t03",targetState="handleUpdate",cond=whenDispatch("coapUpdate"))
				}	 
				state("broadcastResume") { //this:State
					action { //it:State
						emit("resume", "resume(resume)" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t04",targetState="handleUpdate",cond=whenDispatch("coapUpdate"))
				}	 
			}
		}
}
