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
