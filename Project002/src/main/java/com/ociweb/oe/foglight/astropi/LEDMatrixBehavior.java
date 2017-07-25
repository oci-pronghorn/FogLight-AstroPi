/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.StartupListener;
import com.ociweb.iot.astropi.AstroPi;
import com.ociweb.iot.astropi.JoyStickListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import static com.ociweb.iot.maker.FogRuntime.I2C_WRITER;

/**
 *
 * @author huydo
 */
public class LEDMatrixBehavior implements StartupListener{
    
    private final FogCommandChannel ch;
    private final AstroPi sth;
    
    LEDMatrixBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel(I2C_WRITER,50000);
        sth = new AstroPi(ch);
    }
        int [] R = {63,0,0};
        int [] G = {0,63,0};
        int [] B = {0,0,63};
        int [] W = {0,0,0};
    
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
 
//        int bitmap[][][] =  {{G,G,G,G,G,G,G,G},
//                             {G,G,R,G,G,G,G,G},
//                             {G,R,G,G,G,G,G,G},
//                             {R,R,R,R,R,R,R,G},
//                             {G,R,G,G,G,G,G,G},
//                             {G,G,R,G,G,G,G,G},
//                             {G,G,G,G,G,G,G,G},
//                             {G,G,G,G,G,G,G,G}};
//        sth.setPixels(bitmap);
//        ch.i2cDelay(0x46, 1000000000);
//        ch.i2cDelay(0x46, 1000000000);
//        sth.flip_h();
//        ch.i2cDelay(0x46, 1000000000);
//        sth.setRotation(90);
//        ch.i2cDelay(0x46, 1000000000);
//        sth.setRotation(180);
//    int bitmap[][][] = {{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {15, 63, 0}, {15, 63, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {0, 0, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {0, 0, 0}}, {{15, 63, 0}, {15, 63, 0}, {0, 0, 0}, {15, 63, 0}, {15, 63, 0}, {0, 0, 0}, {15, 63, 0}, {15, 63, 0}}, {{15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}, {15, 63, 0}}, {{0, 0, 0}, {0, 0, 0}, {15, 63, 0}, {0, 0, 0}, {0, 0, 0}, {15, 63, 0}, {0, 0, 0}, {0, 0, 0}}, {{0, 0, 0}, {15, 63, 0}, {0, 0, 0}, {15, 63, 0}, {15, 63, 0}, {0, 0, 0}, {15, 63, 0}, {0, 0, 0}}, {{15, 63, 0}, {0, 0, 0}, {15, 63, 0}, {0, 0, 0}, {0, 0, 0}, {15, 63, 0}, {0, 0, 0}, {15, 63, 0}}};
//    sth.setPixels(bitmap);
    }


//
//    @Override
//    public void timeEvent(long l, int i) {
//        if(blink == 1){
//            sth.setPixel(3, 2, R);
//            sth.setPixel(3, 5, R);
//            blink = 0;
//        }else{
//            sth.setPixel(3, 2, B);
//            sth.setPixel(3, 5, B);
//            blink = 1;
//        }
//    }

    
    
    
    
}