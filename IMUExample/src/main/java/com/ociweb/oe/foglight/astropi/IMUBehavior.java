/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.IMUTransducer;
import com.ociweb.iot.astropi.listeners.*;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/**
 *
 * @author huydo
 */
public class IMUBehavior implements AccelListener,GyroListener,MagListener,StartupListener {
    
    FogCommandChannel ch;
    IMUTransducer sensor;
    
    public IMUBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new IMUTransducer(ch,this);
    }
    
    @Override
    public void accelerationValues(double x, double y, double z) {
        System.out.println("___accel value in g (1g = 9.8 m/s2)___");
        System.out.println("x: "+x);
        System.out.println("y: "+y);
        System.out.println("z: "+z);
    }

    @Override
    public void gyroscopeValues(double x, double y, double z) {
        System.out.println("___gyro___");
        System.out.println("x: "+x);
        System.out.println("y: "+y);
        System.out.println("z: "+z);

    }

    @Override
    public void magneticValues(double x, double y, double z) {

        double heading = 180*Math.atan2(y, x)/3.14;
        heading = (heading<0)?(heading+360):heading;
        System.out.println("heading: "+heading);
    }

    @Override
    public void startup() {
        sensor.setAccelScale(2);
        sensor.setGyroScale(245);
        sensor.setMagScale(2);
        sensor.begin(true, true, true);
    }
    
}
