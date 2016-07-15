package com.zhuyux.waterbubble.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zhuyux.waterbubble.utils.Buble;
import com.zhuyux.waterbubble.utils.BubleManager;

import java.util.List;

/**
 * 随手指移动显示泡泡的控件
 * Created by xiaozhu on 2016/7/15.
 */
public class BubleView extends View {
    /**
     * 控制是否去画
     */
    private boolean isDraw = false;
    /**
     * 指尖拖动的员
     */
    private Point finger;
    /**
     * 画笔
     */
    private Paint piant;
    /**上下文对象*/
    private Context context;
    /**闪烁的泡*/
    private List<Buble> twinkleBubles;
    /**持续的圆*/
    private List<Buble> continueBubles;

    public BubleView(Context context) {
        super(context);
        init(context);
    }

    public BubleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BubleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化控件
     *
     * @param context
     */
    private void init(Context context) {
        this.context = context;
        finger = new Point(0, 0);
        piant = new Paint();
        piant.setColor(Color.WHITE);
        piant.setAlpha(150);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                finger.x= (int) event.getX();
                finger.y= (int) event.getY();
                twinkleBubles = BubleManager.createTwinkleBubles(finger, context);
                continueBubles = BubleManager.createContinueBubles(finger, context);
                handler.sendEmptyMessageDelayed(0,200);
                isDraw=true;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                finger.x= (int) event.getX();
                finger.y= (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                finger.x= (int) event.getX();
                finger.y= (int) event.getY();
                isDraw=false;
                handler.removeCallbacksAndMessages(null);
                break;
        }

        return true;
    }

    /**
     * 设置图像的颜色
     * @param clolr
     */
    public void setColor(int clolr){
      piant.setColor(clolr);
    }

    /**
     * 设置图像的的透明度
     * @param alpha
     */
    public void setAlpha(int alpha){
        piant.setAlpha(alpha);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isDraw){
            canvas.drawCircle(finger.x,finger.y, BubleManager.dip2px(context,BubleManager.fingerR),piant);
            for (int i=0;i<continueBubles.size();i++){
                canvas.drawCircle(continueBubles.get(i).getLocationX(),continueBubles.get(i).getLocationY(),continueBubles.get(i).getBubleR(),piant);
            }
            for (int i=0;i<twinkleBubles.size();i++){
                canvas.drawCircle(twinkleBubles.get(i).getLocationX(),twinkleBubles.get(i).getLocationY(),twinkleBubles.get(i).getBubleR(),piant);
            }
            invalidate();
        }

    }

   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           continueBubles=BubleManager.updateContinueBuble(continueBubles,finger,context);
           twinkleBubles=BubleManager.updateTwinkleBuble(twinkleBubles,finger,context);
           handler.sendEmptyMessageDelayed(0,100);
       }
   };
}
