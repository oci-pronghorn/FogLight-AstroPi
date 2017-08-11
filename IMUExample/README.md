
This application demonstrates the use of accelerometer and gyroscope. The sensor measures and prints out the x,y,z values of acceleration in g (1g = 9.8 m/s^2) and the x,y,z values of the gyroscope in dps (degrees per second)


```java
package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig.AstroPi;

import com.ociweb.iot.maker.*;

public class Project003 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        c.connect(AstroPi.GetAccel);
        c.connect(AstroPi.GetGyro);
        c.connect(AstroPi.GetMag);
        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new IMUBehavior(runtime));

    }
          
}
```



```java
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
public class IMUBehavior implements AccelListener,GyroListener,StartupListener {
    
    FogCommandChannel ch;
    IMUTransducer sensor;
    
    public IMUBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new IMUTransducer(ch,this);
    }
    
    @Override
    public void startup() {
        sensor.setAccelScale(2);
        sensor.setGyroScale(245);
        sensor.begin(true, true, false);
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
    
}
```

