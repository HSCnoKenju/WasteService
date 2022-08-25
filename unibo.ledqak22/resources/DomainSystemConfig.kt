import org.json.JSONObject
import java.io.File
import java.io.FileNotFoundException

object DomainSystemConfig {


    /*
    *
    *  Come default restituisco simulation=true se non trovo il file
    *
    * */

    fun isSimulation(   ) : Boolean{

        return try {

            val config = File("../DomainSystemConfig.json").readText(Charsets.UTF_8)
            val jsonObject   = JSONObject( config )
            jsonObject.getBoolean("simulation")
        } catch (e : Exception) {

            println(" ${this.javaClass.name}  | ${e.localizedMessage}, activate simulation by default")

            true;

        }

    }
}