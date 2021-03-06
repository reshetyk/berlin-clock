package com.ubs.opsit.interviews.domain;

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

    @Override
    public String toString() {
        return state.name();
    }
}
