package com.gabriel.playball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by gabriel on 18-3-13.
 */

public class drawBall extends View {

    public float directionX=40;
    public float directionY=40;
    public float cicleR=100;
    public int windowHeight;
    public int windowWidth;

    private Paint paint=new Paint();

    public drawBall(Context context){
        super(context);
    }
    public drawBall(Context context, AttributeSet attributes){
        super(context, attributes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(directionX, directionY, cicleR, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        directionX=event.getX();
        directionY=event.getY();
        invalidate();
        return true;

    }
}
