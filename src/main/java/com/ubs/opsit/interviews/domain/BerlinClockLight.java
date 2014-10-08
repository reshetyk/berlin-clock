package com.ubs.opsit.interviews.domain;

/**
 * Created by Alexey on 07.10.2014.
 */
public class BerlinClockLight {
    public enum State {
        RED, YELLOW, OFF
    }

    private State state;


    public BerlinClockLight(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
