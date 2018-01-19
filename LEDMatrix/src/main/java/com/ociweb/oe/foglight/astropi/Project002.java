package com.ociweb.oe.foglight.astropi;


import com.ociweb.iot.maker.*;

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
