/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.Behavior;
import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.*;
import com.ociweb.iot.astropi.listeners.*;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import static com.ociweb.iot.maker.FogRuntime.I2C_WRITER;

/**
 *
 * @author huydo
 */
public class IMUBehavior implements AccelListener,GyroListener,MagListener,StartupListener {
    
    FogCommandChannel ch;
    AstroPi_IMU sensor;
    
    public IMUBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel(I2C_WRITER,1000);
        sensor = new AstroPi_IMU(ch,this,this,this);
    }
    @Override
    public void accelEvent(double x, double y, double z) {
        System.out.println("___accel value in g (1g = 9.8 m/s2)___");
        System.out.println("x: "+x);
        System.out.println("y: "+y);
        System.out.println("z: "+z);
    }

    @Override
    public void gyroEvent(double x, double y, double z) {
        System.out.println("___gyro___");
        System.out.println("x: "+x);
        System.out.println("y: "+y);
        System.out.println("z: "+z);

    }

    @Override
    public void magEvent(double x, double y, double z) {
        System.out.println("___mag value in gauss___");
        System.out.println("x: "+x);
        System.out.println("y: "+y);
        System.out.println("z: "+z);
    }

    @Override
    public void startup() {
        sensor.setAccelScale(16);
        sensor.setGyroScale(2000);
        sensor.setMagScale(16);
        sensor.begin(true, true, true);
    }
    
}
