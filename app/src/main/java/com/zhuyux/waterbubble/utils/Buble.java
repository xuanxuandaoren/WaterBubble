package com.zhuyux.waterbubble.utils;

import android.content.Context;
import android.graphics.Point;

/**
 * 圆
 * Created by xiaozhu on 2016/7/14.
 */
public class Buble {
    /**圆心的位置的x坐标*/
    private int locationX;
    /**圆心位置的y坐标*/
    private int locationY;
    /**圆心的半径*/
    private int bubleR;

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getBubleR() {
        return bubleR;
    }

    /**是否是持续出现*/
    private boolean isContinue ;
    /**手指摁下圆心的位置*/
    private Point finger;
    /**上下文对象*/
    private Context context;
    /**手指摁下的半径*/
    private int fingerR;
    /**闪烁圆产生范围的上限*/
    private final int maxBubleDistance=18;
    /**闪烁圆产生范围的下限*/
    private final int minBubleDistance=10;
    /***
     * 闪烁圆的最大半径
     */
    private final int maxBunleR=5;
    /**闪烁圆的最小半径*/
    private final int minBunleR=2;
    /**移动圆的半径*/
    private final int bubleContuineR=2;
    /**相对于手指圆心的角度*/
    private double angle;
    /**每次移动的距离*/
    private int moveDistance;



    public Buble(Point finger, Context context,boolean isContinue){
        this.finger = finger;
        this.context = context;
        this.isContinue = isContinue;
        //先确定圆心
        int distance=BubleManager.dip2px(context,BubleManager.fingerR+ (float) (Math.random()*(maxBubleDistance-minBubleDistance)+minBubleDistance));
        angle= Math.random()*360;
        locationX= (int) (finger.x+distance*Math.cos(angle*Math.PI/180));
        locationY= (int) (finger.y+distance*Math.sin(angle*Math.PI/180));

        if(isContinue){
            //创建持续的小泡
            createContinueBuble();
        }else {
            //创建闪烁的小圆
            createTwinkleBuble();
        }
    }
    //创建闪烁的小圆
    private void createTwinkleBuble() {
        bubleR=BubleManager.dip2px(context, (float) (Math.random()*(maxBunleR-minBunleR)+minBunleR));
    }

    /**
     * 创建持续的小泡
     */
    private void createContinueBuble() {
        bubleR=BubleManager.dip2px(context,bubleContuineR);
        moveDistance=5*bubleR;
    }

    /**
     * 更新移动圆的位置
     */
    public void updateBuble(){
        if (isContinue){
            locationX+=moveDistance*Math.cos(angle*Math.PI/180);
            locationY+=moveDistance*Math.sin(angle*Math.PI/180);
            bubleR-=1;

        }

    }

    @Override
    public String toString() {
        return "Buble{" +
                "locationX=" + locationX +
                ", locationY=" + locationY +
                ", bubleR=" + bubleR +
                '}';
    }
}
