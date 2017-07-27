/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.StartupListener;
import com.ociweb.gl.api.TimeListener;
import com.ociweb.iot.astropi.AstroPi;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import static com.ociweb.iot.maker.FogRuntime.I2C_WRITER;

/**
 *
 * @author huydo
 */
public class LEDMatrixBehavior implements StartupListener,TimeListener{
    
    private final FogCommandChannel ch;
    private final AstroPi sth;
    
    LEDMatrixBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel(I2C_WRITER,50000);
        sth = new AstroPi(ch);
    }
        int [] R = {63,0,0};
        int [] G = {0,63,0};
        int [] B = {0,0,63};
        int [] W = {34,34,34};
    
    @Override
    public void startup() {

        sth.clear();
//        for(int i=0;i<8;i++){
//
//                sth.setPixel(0,i,B);
//            
//        }
//        sth.setPixel(0,4,R);
//        sth.setRotation(90);
//        ch.i2cDelay(0x46, 1000000000);
//        sth.setRotation(90);
//        ch.i2cDelay(0x46, 1000000000);
//        sth.setRotation(90);
//        ch.i2cDelay(0x46, 1000000000);

//        ch.i2cDelay(0x46, 1000000000);
//        ch.i2cDelay(0x46, 1000000000);
//        sth.flip_h();
//        ch.i2cDelay(0x46, 1000000000);
//        sth.setRotation(90);
//        ch.i2cDelay(0x46, 1000000000);
//        sth.setRotation(180);
    int[][][] bitmap ={{{34, 34, 34}, {34, 34, 34}, {0, 19, 12}, {0, 19, 12}, {34, 34, 34}, {34, 34, 34}, {34, 34, 34}, {34, 34, 34}}, {{34, 34, 34}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {34, 34, 34}, {34, 34, 34}, {34, 34, 34}}, {{34, 5, 0}, {0, 19, 12}, {0, 0, 20}, {0, 19, 12}, {0, 19, 12}, {34, 34, 34}, {34, 34, 34}, {0, 14, 20}}, {{34, 34, 34}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {0, 14, 20}, {0, 14, 20}, {0, 14, 20}, {0, 14, 20}}, {{34, 34, 34}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {0, 14, 20}, {0, 14, 20}, {0, 14, 20}, {0, 14, 20}}, {{34, 34, 34}, {34, 34, 34}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {34, 34, 34}}, {{34, 34, 34}, {34, 34, 34}, {0, 19, 12}, {0, 19, 12}, {0, 19, 12}, {34, 34, 34}, {34, 34, 34}, {34, 34, 34}}, {{34, 34, 34}, {34, 34, 34}, {34, 5, 0}, {34, 34, 34}, {34, 5, 0}, {34, 34, 34}, {34, 34, 34}, {34, 34, 34}}};
       sth.setPixels(bitmap);
    }


private int blink = 0;
private int[] O = {34, 5, 0};
    @Override
    public void timeEvent(long l, int i) {
        if(blink == 1){
            sth.setPixel(7, 2, O);
            sth.setPixel(7, 3, W);
            sth.setPixel(7, 4, O);
            blink = 0;
        }else{
            sth.setPixel(7, 2, W);
            sth.setPixel(7, 3, O);
            sth.setPixel(7, 4, W);
            blink = 1;
        }
    }

    
    
    
    
}