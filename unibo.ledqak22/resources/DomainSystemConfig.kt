import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException

object DomainSystemConfig {

     private var simulation : Boolean = true
     private var sonarTimeout : Long = 10000
    init {


        try {
            val config = File("../DomainSystemConfig.json").readText(Charsets.UTF_8)
            val jsonObject   = JSONObject( config )
            simulation= jsonObject.getBoolean("simulation")
            sonarTimeout = jsonObject.getLong("sonarTimeout")
        } catch (e : Exception) {
            println(" ${this.javaClass.name}  | ${e.localizedMessage}, activate simulation by default")
        }
    }


    /*
    *
    *  Come default restituisco simulation=true se non trovo il file
    *
    * */

    fun isSimulation() : Boolean{
        return simulation
    }

    fun getSonarTimeout() : Long {
        return sonarTimeout
    }
}