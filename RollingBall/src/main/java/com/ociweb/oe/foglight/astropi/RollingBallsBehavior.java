package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.LEDScreenTransducer;
import com.ociweb.iot.astropi.IMUTransducer;
import com.ociweb.iot.astropi.listeners.AccelListener;

import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author huydo
 */

public class RollingBallsBehavior implements AccelListener,StartupListener {
    
    FogCommandChannel ch;
    IMUTransducer sensor;
    LEDScreenTransducer screen;
    public RollingBallsBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        sensor = new IMUTransducer(ch,this);
        screen = new LEDScreenTransducer(ch);
    }
    int[] R = {63,0,0};
    int[] W = {0,0,0};
    int curRow = 0;
    int curCol = 0;
    
    @Override
    public void startup() {
        sensor.setAccelScale(2);
        sensor.setGyroScale(245);
        sensor.setMagScale(2);
        sensor.begin(false, true, false);
        screen.clear();
        screen.setPixel(curRow, curCol, R);
        
    }
    
    @Override
    public void accelerationValues(double x, double y, double z) {
        
        int dir = rollingDir(x,y);
        String outString;
        switch (dir) {
            case 1:  outString = "left";
                    screen.setPixel(curRow, curCol, W);
                    curCol = curCol -1;
                    screen.setPixel(curRow,curCol,R);
                     break;
            case 2:  outString = "up left";
                    screen.setPixel(curRow, curCol, W);
                    curCol = curCol -1;
                    curRow = curRow -1;
                    screen.setPixel(curRow,curCol,R);
                     break;
            case 3:  outString = "up";
                    screen.setPixel(curRow, curCol, W);
                    curRow = curRow -1;
                    screen.setPixel(curRow,curCol,R);
                     break;
            case 4:  outString = "up right";
                    screen.setPixel(curRow, curCol, W);
                    curCol = curCol +1;
                    curRow = curRow -1;
                    screen.setPixel(curRow,curCol,R);
                     break;
            case 5:  outString = "right";
                    screen.setPixel(curRow, curCol, W);
                    curCol = curCol +1;
                    screen.setPixel(curRow,curCol,R);
                     break;
            case 6:  outString = "right down";
                    screen.setPixel(curRow, curCol, W);
                    curCol = curCol +1;
                    curRow = curRow +1;
                    screen.setPixel(curRow,curCol,R);
                     break;
            case 7:  outString = "down";
                    screen.setPixel(curRow, curCol, W);
                    curRow = curRow +1;
                    screen.setPixel(curRow,curCol,R);        
                     break;
            case 8:  outString = "down left";
                    screen.setPixel(curRow, curCol, W);
                    curRow = curRow +1;
                    curCol = curCol -1;
                    screen.setPixel(curRow,curCol,R); 
                     break;
            default: outString = "Stay";
                     break;
        }
        System.out.println(outString);
        
    }
    
    public int rollingDir(double x,double y){
        
        if(x*x+y*y < 0.3){
            return 0;
        }
        double angle = Math.atan2(y, x);
        if(isInDegRange(angle,-22.5,22.5)){
            return 1;
        }else if(isInDegRange(angle,22.5,67.5)){
            return 2;
        }else if(isInDegRange(angle,67.5,112.5)){
            return 3;
        }else if(isInDegRange(angle,112.5,157.5)){
            return 4;
        }else if(isInDegRange(angle,157.5,180) || isInDegRange(angle,-180,-157.5)){
            return 5;
        }else if(isInDegRange(angle,-157.5,-112.5)){
            return 6;
        }else if(isInDegRange(angle,-112.5,-67.5)){
            return 7;
        }else if(isInDegRange(angle,-67.5,-22.5)){
            return 8;
        }else{
            return 0;
        }
    }
    
    public boolean isInDegRange(double angle, double lo,double hi ){
        double deg = angle*180/Math.PI;
        return (deg > lo && deg < hi);
    }
    
}