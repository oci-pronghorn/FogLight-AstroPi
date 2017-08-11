
This application demonstrates the use of the magnetic sensor in AstroPi. It measures the angle that the left side of the device is pointing relative to the Earth's magnetic North.


```java
package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig.AstroPi;

import com.ociweb.iot.maker.*;

public class Project009 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        c.connect(AstroPi.GetMag);
        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new CompassBehavior(runtime));

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
import com.ociweb.iot.astropi.IMUTransducer;
import com.ociweb.iot.astropi.listeners.*;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/**
 *
 * @author huydo
 */
public class CompassBehavior implements MagListener,Behavior {
    
    FogCommandChannel ch;
    IMUTransducer sensor;
    
    public CompassBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new IMUTransducer(ch,this);
    }

    @Override
    public void magneticValues(double x, double y, double z) {

        double heading = 180*Math.atan2(y, x)/3.14;
        heading = (heading<0)?(heading+360):heading;
        System.out.println("heading: "+heading);
    }

    
}
```

