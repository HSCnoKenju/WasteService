package utils;

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant

object timestamputil {

    val format = "yyyyMMddHHmmssSSS";

    val dateFormat = SimpleDateFormat(format);


    fun TimestampNow() : String{


        return dateFormat.format(Timestamp.from(Instant.now()))
    }

    fun TimestampMin() : String {
        return  dateFormat.format(Timestamp.from(Instant.MIN))
    }

    fun TimestampBefore(lefthand:String, argument: String): Boolean{

        println("lefthand "+ dateFormat.parse(lefthand))
        return dateFormat.parse(lefthand).before(dateFormat.parse(argument))

    }

    fun TimestampAfter(lefthand:String, argument: String): Boolean{
        return dateFormat.parse(lefthand).after(dateFormat.parse(argument))

    }



}