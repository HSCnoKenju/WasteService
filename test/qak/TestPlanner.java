package qak;


import static org.junit.Assert.*;

import it.unibo.ctxwaste.MainCtxwasteKt;
import it.unibo.kactor.*;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapObserveRelation;
import org.junit.*;
import unibo.comm22.utils.CommUtils;

public class TestPlanner {

    private CoapObserveRelation relation = null;

    private static String ipaddr      = "localhost:8078" ;		//5683 default
    private static String context     = "ctxpathplanner" ;
    private static String destactor   = "pathplanner" ;
    private static CoapClient client = null;


    static ConnTcp connTcp;
    public static void main(String[] args) {


        String goingStr = CommUtils.buildRequest("test","destination","dest(1, 3)","pathplanner").toString();


        String goingStr_2 = CommUtils.buildRequest("test","destination","dest(6, 4)","pathplanner").toString();

        try {
            connTcp = new ConnTcp("localhost",8078);

            String reply = connTcp.request(goingStr);

            System.out.println(reply);

            String reply_2 = connTcp.request(goingStr_2);

            System.out.println(reply_2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
