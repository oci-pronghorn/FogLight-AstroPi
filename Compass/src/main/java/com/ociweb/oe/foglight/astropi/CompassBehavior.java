/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.Behavior;
import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.IMUTransducer;
import com.ociweb.iot.astropi.LEDScreenTransducer;
import com.ociweb.iot.astropi.listeners.*;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/**
 *
 * @author huydo
 */
public class CompassBehavior implements MagListener,Behavior,StartupListener {
    
    FogCommandChannel ch;
    IMUTransducer sensor;
    LEDScreenTransducer screen;
    
    public CompassBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new IMUTransducer(ch,this);
        screen = new LEDScreenTransducer(ch);
    }
    int [] B = {63,0,0};
    int [] G = {0,63,0};
    int [] R = {0,0,63};
    int [] Y = {63,63,0}; //the arrow
    int [] W = {0,0,0};
    
    int[][][] bitmap = {{R,R,R,R,R,R,R,W},
                        {R,R,R,R,R,R,R,W},
                        {R,R,R,R,R,R,R,W},
                        {G,R,R,R,R,R,R,W},
                        {R,R,R,R,R,R,R,W},
                        {R,R,R,R,R,R,R,W},
                        {R,R,R,R,R,R,R,W},
                        {W,W,W,W,W,W,W,W}};
    
    long angle;
    
    int[][] ticks = new int[24][2];
    
    @Override
    public void startup() {
        
        screen.clear();
        screen.setPixels(bitmap);
        int row = 0;
        int col = -1;
        for(int i = 0;i<24;i++){
            if(row == 0 && col <6){
                ticks[i][0] = 0;
                ticks[i][1] = ++col;
                continue;
            }
            if(col ==6 && row<6){
                ticks[i][1] = 6;
                ticks[i][0] = ++row;
                continue;
            }
            if(row == 6 && col> 0){
                ticks[i][0] = 6;
                ticks[i][1] = --col;
                continue;
            }
            if(col == 0 && row >= 0){
                ticks[i][1] = 0;
                ticks[i][0] = --row;
            }
        }        
    }
    int rowIdx = 3;
    int colIdx = 0;
    
    @Override
    public void magneticValues(double x, double y, double z) {
        
        double heading = 180*Math.atan2(y, x)/3.14;
        heading = (heading<0)?(heading+360):heading;
        
        angle = Math.round(heading);
        System.out.println("heading: "+heading);
        int[] res = angleToIdx(angle);
        screen.setPixel(rowIdx, colIdx, R);
        screen.setPixel(3,3,G);
        rowIdx = res[0];
        colIdx = res[1];
        screen.setPixel(rowIdx, colIdx, Y);
        
    }
    
    private int[] angleToIdx(long angle){

        int a = (int) ((angle)/15);
        if(a<=12){
            a = 21-a;
        }else{
            a = 23-a;
            a = 21+a;
            if(a>23){
                a = a - 23;
            }
        }
        System.out.println(a);
        return ticks[a];
    }
    
    
}
