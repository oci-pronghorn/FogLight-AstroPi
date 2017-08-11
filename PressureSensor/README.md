
This application demonstrates the use of the pressure sensor inside the AstroPi. It measures and prints out the pressure and temperature of the environment.


```java
package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig.AstroPi;
import com.ociweb.iot.hardware.impl.test.TestHardware;

import com.ociweb.iot.maker.*;

public class Project004 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        c.connect(AstroPi.GetTempFromPressureSensor);
        c.connect(AstroPi.GetPressure);
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new PressureSensorBehavior(runtime));

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
import com.ociweb.iot.astropi.EnvSensorTransducer;
import com.ociweb.iot.astropi.listeners.*;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
/**
 *
 * @author huydo
 */
public class PressureSensorBehavior implements TemperatureListener,StartupListener,PressureListener {
    
    FogCommandChannel ch;
    EnvSensorTransducer sensor;
    
    public PressureSensorBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new EnvSensorTransducer(ch,this);
    }

    @Override
    public void startup() {
        sensor.begin(false, true);

    }

    @Override
    public void pressureValues(int pressure) {
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
```

