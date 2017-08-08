/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.Behavior;
import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.EnvSensorTransducer;
import com.ociweb.iot.astropi.listeners.HumidityListener;
import com.ociweb.iot.astropi.listeners.TemperatureListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/**
 *
 * @author huydo
 */
public class HumiditySensorBehavior implements Behavior,HumidityListener,StartupListener,TemperatureListener{
    FogCommandChannel ch;
    EnvSensorTransducer sensor;
    
    public HumiditySensorBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new EnvSensorTransducer(ch,this);
    }
    @Override
    public void startup() {
        sensor.begin(true, false);
    }
    
    @Override
    public void humidityValues(double humidity) {
        System.out.println("The humidity is: "+humidity+" rH.");
    }

    @Override
    public void tempValFromPressureSensor(double temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tempValFromHumiditySensor(double temp) {
        System.out.println("The temperature is: "+temp+" Celsius.");
    }
}
