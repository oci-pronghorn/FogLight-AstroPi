
This application demonstrates the use of LED matrix and the joystick: the movement of the dot on screen can be controlled using
the joystick.


```java
package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig;
import static com.ociweb.iot.grove.GroveTwig.*;
import com.ociweb.iot.hardware.impl.test.TestHardware;

import com.ociweb.iot.maker.*;
import static com.ociweb.iot.maker.Port.*;

public class Project001 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////

        c.connect(AstroPiTwig.AstroPi.GetJoystick,300);

        if(c instanceof TestHardware){
            byte[] dummy ={0};
            ((TestHardware) c).setI2CValueToRead((byte)70,dummy,1);
        }
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new JoyStickBehavior(runtime));
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
import com.ociweb.iot.astropi.LEDScreenTransducer;
import com.ociweb.iot.astropi.listeners.JoyStickListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/**
 *
 * @author huydo
 */

public class JoyStickBehavior implements StartupListener,JoyStickListener{
    
    private final FogCommandChannel ch;
    private final LEDScreenTransducer screen;
    
    JoyStickBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        screen = new LEDScreenTransducer(ch,this);
    }
    int [] R = {63,0,0};
    int [] G = {0,63,0};
    int [] B = {0,0,63};
    int [] W = {0,0,0};
    int curRow = 0;
    int curCol = 0;
    @Override
    public void startup() {
        
        screen.clear();
        
        screen.setPixel(curRow,curCol,R);
    }
    
    @Override
    public void joystickEvent(int up, int down, int left, int right, int push) {
        System.out.println("hello");
        if(up==1){
            screen.setPixel(curRow, curCol, W);
            curRow = curRow -1;
            screen.setPixel(curRow,curCol,R);
        }else if(down == 1){
            screen.setPixel(curRow,curCol,W);
            curRow = curRow +1;
            screen.setPixel(curRow,curCol,R);
        }else if(left == 1){
            screen.setPixel(curRow,curCol,W);
            curCol = curCol -1;
            screen.setPixel(curRow,curCol,R);
        }else if(right ==1){
            screen.setPixel(curRow,curCol,W);
            curCol = curCol +1;
            screen.setPixel(curRow,curCol,R);
        }
    }
    
    
}```

