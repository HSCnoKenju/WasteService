import java.util.*
import kotlin.io.path.Path

object resourceappender {

    lateinit var PathStr : StringJoiner


    fun  initResource(){
       PathStr =  StringJoiner(" ")
    }

    fun  add(newItem :String) {

        PathStr.add(newItem)
    }

    fun getStr() : String{

        return  PathStr.toString()

    }
}