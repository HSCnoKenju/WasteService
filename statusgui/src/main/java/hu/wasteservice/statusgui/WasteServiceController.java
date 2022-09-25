package hu.wasteservice.statusgui;


import jdk.internal.module.IllegalAccessLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import unibo.comm22.coap.CoapConnection;
import unibo.comm22.utils.ColorsOut;

import static hu.wasteservice.statusgui.CoapUtils.connectWithCoap;

/*
<!-- trolleyStateDisplay -->
<!-- ledStateDisplay -->
<!-- positionDisplay -->
<!-- weightDisplay -->
<!-- infoDisplay -->
*/
@Controller
public class WasteServiceController {

    protected  String mainPage = "service_manager_gui";
    @Value("${waste.resources.ledstate}")
    String ledStateResourceURL;
    @Value("${waste.resources.position}")
    String positionResourceURL;
    @Value("${waste.resources.weight}")
    String weightResourceURL;
    @Value("${waste.resources.trolleystate}")
    String trolleyStateResourceURL;
    protected String buildThePage(Model viewmodel){

        connectWithCoap(ledStateResourceURL);
        connectWithCoap(positionResourceURL);
        connectWithCoap(weightResourceURL);
        connectWithCoap(trolleyStateResourceURL);
        return mainPage;
    }

    @GetMapping("/")

    public String entrance(Model viewmodel) {

        return buildThePage(viewmodel);
    }

    @ExceptionHandler
    public ResponseEntity handle(Exception ex) {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity(
                "BaseController ERROR " + ex.getMessage(),
                responseHeaders, HttpStatus.CREATED);
    }

}

