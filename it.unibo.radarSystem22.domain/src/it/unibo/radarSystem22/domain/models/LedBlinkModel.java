package it.unibo.radarSystem22.domain.models;

import it.unibo.radarSystem22.domain.interfaces.ILedBlink;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public abstract class LedBlinkModel implements ILedBlink {

    private int state = 0;


    public static ILedBlink create(){

        ILedBlink led = null;

    //    if (DomainSystemConfig.simulation)

        return  led;
    }


    public static ILedBlink createLedMock(){


        return  null;

    }



}
