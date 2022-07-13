package qak;


import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import unibo.comm22.utils.ColorsOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TrolleyObserver {

    private CoapObserveRelation relation = null;

    private String ipaddr      = "localhost:8033" ;		//5683 default
    private String context     = "ctxwaste" ;
    private String destactor   = "transporttrolley" ;
    private CoapClient client = null;


    public TrolleyObserver(){
        ColorsOut.outappl("ResourceObserver | start", ColorsOut.GREEN);

        client = new CoapClient("coap://"+ipaddr+"/"+context+"/"+destactor);
    }


    public void  observe( ) {
        relation = client.observe(
                new CoapHandler() {
                    @Override public void onLoad(CoapResponse response) {
                        String content = response.getResponseText();
                        ColorsOut.outappl("ResourceObserver | code="+ response.getCode()+ "value=" + content, ColorsOut.GREEN);
                    }
                    @Override public void onError() {
                        ColorsOut.outerr("OBSERVING FAILED (press enter to exit)");
                    }
                });
    }

    public void waitUserEnd() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ResourceObserver | press enter to end ...");
        try { br.readLine(); } catch (IOException e) { }
        System.out.println("ResourceObserver | CANCELLATION");
        relation.proactiveCancel();
    }

    public static void main(String[] args) {
        TrolleyObserver rco = new TrolleyObserver();
        rco.observe( );
        rco.waitUserEnd();
    }

}

