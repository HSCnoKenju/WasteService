package it.unibo.radarSystem22.domain.interfaces;

public interface ILedBlink {

    public void turnOn();

    public void turnOff();

    public void blink();

    public int getState();
}
