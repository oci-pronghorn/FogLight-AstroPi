
This application demonstrates the functionalities of the LEDScreenTransducer. The graphics on screen can be set up by using an 8x8x3 matrix .


```java
package com.ociweb.oe.foglight.astropi;


import static com.ociweb.iot.grove.GroveTwig.*;

import com.ociweb.iot.maker.*;
import static com.ociweb.iot.maker.Port.*;

public class Project002 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        c.useI2C();
        c.setTimerPulseRate(200);
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new LEDMatrixBehavior(runtime));
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
import com.ociweb.gl.api.TimeListener;
import com.ociweb.iot.astropi.LEDScreenTransducer;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/**
 *
 * @author huydo
 */
public class LEDMatrixBehavior implements StartupListener,TimeListener{
    
    private final FogCommandChannel ch;
    private final LEDScreenTransducer screen;
    
    LEDMatrixBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        screen = new LEDScreenTransducer(ch);
    }
        int [] R = {63,0,0};
        int [] G = {0,63,0};
        int [] B = {0,0,63};
        int [] W = {0,0,0};
    
        int[][][] bitmap = {{W,W,W,W,W,W,W,W},
                            {W,R,R,W,W,R,R,W},
                            {R,R,R,R,R,R,R,R},
                            {R,R,R,R,R,R,R,R},
                            {W,R,R,R,R,R,R,W},
                            {W,W,R,R,R,R,W,W},
                            {W,W,W,R,R,W,W,W},
                            {W,W,W,W,W,W,W,W}};
        
    @Override
    public void startup() {

        screen.clear();
        screen.setPixels(bitmap);
    }


private int blink = 0;
private int[] O = {34, 5, 0};
    @Override
    public void timeEvent(long l, int i) {
        if(blink == 1){
            screen.setRotation(90);
            blink = 0;
        }else{
            screen.setRotation(90);
            blink = 1;
        }
    }

    
    
    
    
}```

