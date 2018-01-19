package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig;

import com.ociweb.iot.maker.*;

public class Project007 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        c.connect(AstroPiTwig.AstroPi.GetJoystick,100);
        c.setTimerPulseRate(300);
        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new SnakeBehavior(runtime));

    }
          
}
