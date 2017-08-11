
This application demonstrates the use of the humidity sensor inside the AstroPi. It measures and prints out the humidity and temperature of the environment.


```java
package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig.AstroPi;

import com.ociweb.iot.maker.*;

public class Project005 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        //c.connect(AstroPi.CalibrateHumiditySensor);
        c.connect(AstroPi.GetHumidity);
        c.connect(AstroPi.GetTempFromHumiditySensor);

        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new HumiditySensorBehavior(runtime));

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
```

