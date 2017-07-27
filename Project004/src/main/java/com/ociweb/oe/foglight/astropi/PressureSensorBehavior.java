/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.AstroPi_EnvSensors;
import com.ociweb.iot.astropi.listeners.*;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import static com.ociweb.iot.maker.FogRuntime.I2C_WRITER;

/**
 *
 * @author huydo
 */
public class PressureSensorBehavior implements TemperatureListener,StartupListener,PressureListener {
    
    FogCommandChannel ch;
    AstroPi_EnvSensors sensor;
    
    public PressureSensorBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel(I2C_WRITER,200);
        sensor = new AstroPi_EnvSensors(ch,this);
    }

    @Override
    public void startup() {
        sensor.begin(false, true);

    }

    @Override
    public void pressureVal(int pressure) {
        System.out.println("The pressure is: "+pressure+" mBar.");
    }

    @Override
    public void tempValFromPressureSensor(double temp) {
        System.out.println("The temperature is: "+temp +" Celsius.");
    }

    @Override
    public void tempValFromHumiditySensor(double temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
