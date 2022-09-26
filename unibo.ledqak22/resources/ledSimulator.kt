import it.unibo.kactor.ActorBasic
import it.unibo.kactor.ApplMessage
import it.unibo.kactor.IApplMessage
import kotlinx.coroutines.delay
import it.unibo.kactor.MsgUtil
import kotlinx.coroutines.runBlocking

class ledSimulator (name :String) : ActorBasic (name) {

    override suspend fun actorBody(msg: IApplMessage) {
      //  println("$tt $name | received  $msg ")  //RICEVE GLI EVENTI!!!

        if (!msg.isEvent()) {
            if (msg.msgId() == "ledon") {


                unibo.comm22.utils.ColorsOut.outappl(
                    "$name - on", unibo.comm22.utils.ColorsOut.MAGENTA
                )

            } else if (msg.msgId() == "ledblink") {

                unibo.comm22.utils.ColorsOut.outappl(
                    "$name - blink", unibo.comm22.utils.ColorsOut.MAGENTA
                )


            } else /* led off*/ {

                unibo.comm22.utils.ColorsOut.outappl(
                    "$name - off", unibo.comm22.utils.ColorsOut.MAGENTA
                )


            }


        }

    }
}