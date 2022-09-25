/* Generated by AN DISI Unibo */ 
package it.unibo.led

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Led ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var Active = false	
				
				var Continus = true
				
				 
				
				
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("LED | START ")
					}
					 transition( edgeName="goto",targetState="waitingMessages", cond=doswitch() )
				}	 
				state("waitingMessages") { //this:State
					action { //it:State
						println("LED | waiting ")
					}
					 transition(edgeName="t16",targetState="parseCmd",cond=whenDispatch("cmdLed"))
					transition(edgeName="t17",targetState="handleGetState",cond=whenRequest("getState"))
					transition(edgeName="t18",targetState="parseTrigger",cond=whenEvent("ledtrigger"))
				}	 
				state("handleGetState") { //this:State
					action { //it:State
						answer("getState", "ledState", "ledState($Active)"   )  
					}
					 transition( edgeName="goto",targetState="waitingMessages", cond=doswitch() )
				}	 
				state("parseCmd") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("cmd(COMAND)"), Term.createTerm("cmd(COMAND)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
											
												if (payloadArg(0).equals("turnOn")){
													
													Active = true
													Continus = true
													
													}
												
												else if (payloadArg(0).equals("turnOff"))
													{
													Active = false
													Continus = true
												
												
												}
												else
												{
													
												}
													 
												
								println("LED | EXEC, CURRENT STATE = $Active")
						}
					}
					 transition( edgeName="goto",targetState="waitingMessages", cond=doswitch() )
				}	 
				state("parseTrigger") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("led(CMD)"), Term.createTerm("led(CMD)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
											
											if (payloadArg(0).equals("on"))
												{	
													Active = true
													Continus = true
												}
												else if (payloadArg(0).equals("off"))
												{	
													Active = false
													Continus = true
												}
												else if (payloadArg(0).equals("blink"))
												{
													Active = true
													Continus = false
												}
													 
											
												
								println("LED | TRIGGER, CURRENT STATE ACTIVE = $Active CONTINUS = $Continus")
						}
					}
					 transition( edgeName="goto",targetState="waitingMessages", cond=doswitch() )
				}	 
			}
		}
}
