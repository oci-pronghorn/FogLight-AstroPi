package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig.AstroPi;
import com.ociweb.iot.hardware.impl.test.TestHardware;

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
        if(c instanceof TestHardware){
            byte[] dummy ={0};
            ((TestHardware) c).setI2CValueToRead((byte)28,dummy,1);
            ((TestHardware) c).setI2CValueToRead((byte)106,dummy,1);
        }
        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new IMUBehavior(runtime));

    }
          
}
