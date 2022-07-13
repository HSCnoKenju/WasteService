import java.nio.file.Files
import java.nio.file.Path

object filechecker {


    fun fileExists(Inmapname: String): Boolean {
       return  Files.exists(Path.of("$Inmapname.txt"))
    }
}