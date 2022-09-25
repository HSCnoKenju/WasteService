package hu.wasteservice.statusgui;

import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import unibo.comm22.utils.ColorsOut;

public class CoapObserver implements CoapHandler{



    private String name = "generic" ;

    public CoapObserver(String name) {
        this.name = name;
    }


    @Override
    public void onLoad(CoapResponse response) {
        ColorsOut.outappl(name+"CoapObserver changed!" + response.getResponseText(), ColorsOut.GREEN);
        //send info over the websocket
        WebSocketConfiguration.wshandler.sendToAll("" + response.getResponseText());
        //simpMessagingTemplate.convertAndSend(WebSocketConfig.topicForTearoomstatemanager, new ResourceRep("" + HtmlUtils.htmlEscape(response.getResponseText())));
    }

    @Override
    public void onError() {
        ColorsOut.outerr("RobotCoapObserver observe error!");
    }
}
