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
