package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.astropi.AstroPiTwig;

import com.ociweb.iot.maker.*;

public class Project006 implements FogApp
{
    ///////////////////////
    //Connection constants 
    ///////////////////////


    @Override
    public void declareConnections(Hardware c) {
        ////////////////////////////
        //Connection specifications
        ///////////////////////////
        c.connect(AstroPiTwig.AstroPi.GetAccel,300);

        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new RollingBallsBehavior(runtime));

    }
          
}
