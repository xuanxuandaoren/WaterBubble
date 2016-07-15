package com.zhuyux.waterbubble.utils;

import android.content.Context;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 圆的管理类
 * Created by xiaozhu on 2016/7/14.
 */
public class BubleManager {


    /**
     * 指尖按下去的园的半径
     */
    public static final int fingerR = 24;
    /**
     * 闪烁圆的数量
     */
    private static final int countTwinkle = 5;
    /**
     * 持续移动圆的数量
     */
    private static final int countContuine = 5;

    /**
     * 生产会持续在屏幕上显示的泡
     *
     * @param finger  手指摁的圆心所在
     * @param context 要导入的上下文对象
     * @return
     */
    public static List<Buble> createContinueBubles(Point finger, Context context) {
        List<Buble> bubles=new ArrayList<>();
        Buble buble=null;
        for (int i=0;i<countContuine;i++){
            buble=new Buble(finger,context,true);
            bubles.add(buble);

        }

        return bubles;
    }

    /**
     * 生产在屏幕上闪烁的泡
     *
     * @param finger  手指摁的圆心所在
     * @param context 要导入的上下文对象
     * @return
     */
    public static List<Buble> createTwinkleBubles(Point finger, Context context) {
        List<Buble> bubles=new ArrayList<>();
        Buble buble=null;
        for (int i=0;i<countTwinkle;i++){
            buble=new Buble(finger,context,false);
            bubles.add(buble);

        }

        return bubles;
    }

    /**
     * 更新闪烁泡的位置
     *
     * @param bubles 要更新的泡的集合
     * @return 返回更新后泡的集合
     */
    public static List<Buble> updateTwinkleBuble(List<Buble> bubles,Point finger, Context context) {
        List<Buble> newsBubles=new ArrayList<>();
        Buble buble=null;
        for (int i=0;i<countTwinkle/2;i++){
            buble=new Buble(finger,context,false);
            newsBubles.add(buble);
            bubles.remove(i);
        }
        bubles.addAll(newsBubles);
        return bubles;
    }

    /**
     * 更新移动泡的位置
     *
     * @param bubles 要更新的泡的集合
     * @return 返回更新后泡的集合
     */
    public static List<Buble> updateContinueBuble(List<Buble> bubles,Point finger, Context context) {
        List<Buble> newBubles=new ArrayList<>();
        Buble buble=null;
        for (int i=0;i<countContuine/2;i++){
            buble=new Buble(finger,context,true);
            newBubles.add(buble);

        }
        for (int i=0;i<countContuine;i++){
            buble=bubles.get(i);
            buble.updateBuble();
            if (buble.getBubleR()>0){
                newBubles.add(buble);
            }
        }


        return newBubles;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
