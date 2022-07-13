/* Generated by AN DISI Unibo */ 
package it.unibo.waste_truck_mock

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Waste_truck_mock ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("		WASTE_TRUCK GENERATOR | STARTED")
					}
				}	 
				state("newLoad") { //this:State
					action { //it:State
						println("		WASTE_TRUCK GENERATOR | NEW REQUEST")
						request("waste", "details(Glass,2)" ,"waste_service" )  
					}
					 transition(edgeName="t07",targetState="accepted",cond=whenReply("loadAccept"))
					transition(edgeName="t08",targetState="rejected",cond=whenReply("loadRejected"))
				}	 
				state("accepted") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
					}
					 transition( edgeName="goto",targetState="end", cond=doswitch() )
				}	 
				state("rejected") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
					}
					 transition( edgeName="goto",targetState="end", cond=doswitch() )
				}	 
				state("end") { //this:State
					action { //it:State
						println("		WASTE_TRUCK GENERATOR | END")
					}
				}	 
			}
		}
}
