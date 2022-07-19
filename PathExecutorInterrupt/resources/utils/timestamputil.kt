package utils

import java.sql.Timestamp
import java.time.Instant

object timestamputil {


    fun TimestampNow() : String{
        return Timestamp.from(Instant.now()).toString()
    }

    fun TimestampBefore(lefthand:String, argument: String): Boolean{
        return Timestamp.valueOf(lefthand).before(Timestamp.valueOf(argument));


    }

    fun TimestampAfter(lefthand:String, argument: String): Boolean{
        return Timestamp.valueOf(lefthand).after(Timestamp.valueOf(argument));
    }

}