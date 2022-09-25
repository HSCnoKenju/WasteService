package hu.wasteservice.statusgui;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCoapController {


    protected String mainPage = "coaptest";


    @Value("${test.resources.robot}")
    String robotLocation;

    protected String buildThePage(Model viewModel){

        CoapUtils.connectWithCoap(robotLocation);

        return mainPage;
    }
    @GetMapping("/Test")

    public String entrance(Model viewModel){

        return buildThePage(viewModel) ;
    }

}
