/* Generated by AN DISI Unibo */ 
package it.unibo.ctxrpi
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	QakContext.createContexts(
	        "localhost", this, "sprint_0.pl", "sysRules.pl","ctxrpi"
	)
}

