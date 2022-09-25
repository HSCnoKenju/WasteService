package hu.wasteservice.statusgui;

import unibo.comm22.coap.CoapConnection;
import unibo.comm22.utils.ColorsOut;

public class CoapUtils {


    /*
     *
     * This method try to create one CoapConnection (one attempt, based on  policy of CoapConnection's constructor)
     * and attach an coapObserver listening on that created connection. the observer will prompt the message on the
     * websocket channel, and the name of the observer (which is the same as the actor) is included in the message
     *
     * */
    public static void connectWithCoap(String ipaddr, int port, String ctx, String actor ){

        try {
            String coapResourceAddressFormat = "%s:%d";
            String coapResourcePathFormat ="%s/%s";
            String address = String.format(coapResourceAddressFormat,ipaddr,port);
            String path = String.format(coapResourcePathFormat,ctx,actor);
            new CoapConnection(address,path).observeResource(new CoapObserver(actor)) ;
        }catch (Exception e){
            ColorsOut.outerr("WasteServiceController | CoapConnection ERROR:"+e.getMessage());
        }
    }

    /*
     *
     * Let this be of the following format, unchecked
     *
     * %s:%d:%s/%s
     * */
    public static  void connectWithCoap(String coapLocation){

        ColorsOut.outappl("left: "+coapLocation,ColorsOut.BgGreen);
        String ipaddr = coapLocation.substring(0,coapLocation.indexOf(':'));
        coapLocation = coapLocation.substring(coapLocation.indexOf(':')+1);

        ColorsOut.outappl("left: "+coapLocation,ColorsOut.BgGreen);
        int port = Integer.parseInt(coapLocation.substring(0,coapLocation.indexOf(":")));
        coapLocation = coapLocation.substring(coapLocation.indexOf(':')+1);

        ColorsOut.outappl("left: "+coapLocation,ColorsOut.BgGreen);
        String ctx = coapLocation.substring(0,coapLocation.indexOf('/'));
        String actor = coapLocation.substring(coapLocation.indexOf('/')+1);

        connectWithCoap(ipaddr,port,ctx,actor);



    }


}
