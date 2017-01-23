package com.example.tacademy.callfinder.evt;

import com.squareup.otto.Bus;

/**
 * Created by Tacademy on 2017-01-23.
 */
public class OTTOBus {
    private static OTTOBus ourInstance = new OTTOBus();

    public static OTTOBus getInstance() {
        return ourInstance;
    }

    private OTTOBus() {
    }
    Bus bus = new Bus();

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
