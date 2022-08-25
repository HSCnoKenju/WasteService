import it.unibo.kactor.ActorBasic
import it.unibo.kactor.ApplMessage
import it.unibo.kactor.IApplMessage
import kotlinx.coroutines.delay
import it.unibo.kactor.MsgUtil
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class ledConcrete (name: String) : ActorBasic(name) {


    override suspend fun actorBody(msg: IApplMessage) {

		
		
       // println("$tt $name | received  $msg "  )  //RICEVE GLI EVENTI!!!

        if(!msg.isEvent()) {

            if (msg.msgId() == "ledon") {


                try {
                    Runtime.getRuntime().exec("sudo bash led25GpioTurnOn.sh")

                } catch (e: Exception) {
                    println("WARNING: ledConcrete does not find led25GpioTurnOn.sh")
                }


            } else if (msg.msgId() == "ledblink") {

                try {
                    Runtime.getRuntime().exec("sudo bash led25GpioBlink.sh")

                } catch (e: Exception) {
                    println("WARNING: ledConcrete does not find led25GpioBlink.sh")
                }

            } else /* led off*/ {

                try {
                    Runtime.getRuntime().exec("sudo bash led25GpioTurnOff.sh")

                } catch (e: Exception) {
                    println("WARNING: ledConcrete does not find led25GpioTurnOff.sh")
                }

            }
        }
    }
}