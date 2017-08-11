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
        c.connect(AstroPi.GetMag,500);
        
    }


    @Override
    public void declareBehavior(FogRuntime runtime) {
        //////////////////////////////
        //Specify the desired behavior
        //////////////////////////////
        runtime.registerListener(new CompassBehavior(runtime));

    }
          
}

