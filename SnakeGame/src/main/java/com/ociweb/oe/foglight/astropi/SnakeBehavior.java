/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ociweb.oe.foglight.astropi;

import com.ociweb.gl.api.StartupListener;
import com.ociweb.gl.api.TimeListener;
import com.ociweb.iot.astropi.LEDScreenTransducer;
import com.ociweb.iot.astropi.listeners.JoyStickListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import java.util.Random;

/**
 *
 * @author huydo
 */
public class SnakeBehavior implements StartupListener,JoyStickListener,TimeListener{
    
    private final FogCommandChannel ch;
    private final LEDScreenTransducer screen;
    
    SnakeBehavior(FogRuntime runtime){
        this.ch = runtime.newCommandChannel();
        screen = new LEDScreenTransducer(ch,this);
    }
    int [] H = {63,0,0};
    int [] R = {63,0,0};
    int [] F = {0,63,0};
    int [] B = {63,63,0};
    int [] W = {0,0,0};

    boolean inGame = true;
    int length = 3;
    int curDir = 4;
    int[] x = new int[64];
    int[] y = new int[64];
    int foodX;
    int foodY;
    @Override
    public void startup() {
        startGame();
    }
    
    public void startGame(){
        System.out.println("Game Started");
        screen.clear();
        inGame = true;
        for(int i = 0;i<64;i++){
            x = new int[64];
            y = new int[64];
        }
        x[0] = 2;
        x[1] = 1;
        x[2] = 0;
        y[0] = 0;
        y[1] = 0;
        y[2] = 0;
        length = 3;
        curDir = 4;
        drawSnake(x,y);
        makeFood();
    }
    
    @Override
    public void joystickEvent(int up, int down, int left, int right, int push) {
        if(inGame){
            if(up==1 && curDir != 2){
                curDir = 1;
                
            }else if(down == 1 && curDir != 1){
                curDir = 2;
                
            }else if(left == 1 && curDir != 4){
                curDir = 3;
                
            }else if(right ==1 && curDir != 3){
                curDir = 4;
            }
        }
    }

    /**
     * 
     * @param xPos
     * @param yPos
     * @param dir 1=up, 2=down, 3=left, 4=right
     */
    public void move(int[] xPos,int[] yPos,int dir){
        
        screen.setPixel(yPos[length-1],xPos[length-1],W);
        
        for(int i =length -1;i>0;i--){
            xPos[i] =xPos[i-1];
            yPos[i] =yPos[i-1];
        }
        
        switch(dir){
            case 1:
                yPos[0] = yPos[0] - 1;
                break;
            case 2:
                yPos[0] = yPos[0] + 1;
                break;
            case 3:
                xPos[0] = xPos[0] - 1;
                break;
            case 4:
                xPos[0] = xPos[0] + 1;
                break;
            default:
                break;
        }
        if(xPos[0] == foodX && yPos[0] == foodY){
            length += 1;
            xPos[length - 1] = xPos[length-2];
            yPos[length - 1] = yPos[length-2];
            makeFood();
        }        
        drawSnake(xPos,yPos);
        
        if((!isInRange(xPos[0],yPos[0])) || isSelfEating(xPos,yPos)){
            gameOver();
  
        }
                
    }
    
    public void gameOver(){
        inGame = false;
        System.out.println("Game Over. Your Score: "+length);
        for(int i = 1;i<length;i++){
            screen.setPixel(y[i],x[i],H);
        }
        startGame();
    }
    
    public boolean isInRange(int xPos,int yPos){
        return (xPos >= 0 && xPos <= 7) && (yPos >= 0 && yPos <= 7);
    }
    
    public boolean isSelfEating(int[] xPos,int[] yPos){
        for(int i = 1;i<length;i++){
            if(xPos[0] == xPos[i] && yPos[0] == yPos[i]){
                return true;
            }
        }
        return false;
    }
    
    
    public void makeFood(){
        Random rn = new Random();
        boolean food = true;
        while(food){
            food = false;
            foodX = rn.nextInt(8);
            foodY = rn.nextInt(8);
            for(int i = 0;i<length;i++){
                if(foodX == x[i] && foodY == y[i]){
                    food = true;
                }
            }
        }
        screen.setPixel(foodY, foodX, F);
    }
    
    public void drawSnake(int[] xPos,int[] yPos){
        screen.setPixel(yPos[0], xPos[0], H);
        for(int i = 1;i<length;i++){
            screen.setPixel(yPos[i],xPos[i],B);
        }
    }

    @Override
    public void timeEvent(long l, int i) {
        if(inGame){
            move(x,y,curDir);
        }
    }

 
    
    
}