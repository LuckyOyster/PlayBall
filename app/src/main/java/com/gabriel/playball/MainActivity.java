package com.gabriel.playball;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {

    public int screenWidth;
    public int screenHeight;
    public float directionX=40;
    public float directionY=40;
    public float cicleR=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get screen width&height
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth=displayMetrics.widthPixels;
        screenHeight=displayMetrics.heightPixels;

        setContentView(new drawBall(this));
    }

    public class drawBall extends View {



        private Paint paint=new Paint();

        public drawBall(Context context){
            super(context);
        }
        public drawBall(Context context, AttributeSet attributes){
            super(context, attributes);

        }

        public void fixPosition(){
            if(directionX+cicleR>screenWidth){
                directionX=screenWidth-cicleR;
            }
            if(directionY+cicleR>screenHeight){
                directionY=screenHeight-cicleR;
            }
            if(directionX-cicleR<0){
                directionX=cicleR;
            }
            if(directionY-cicleR<0){
                directionY=cicleR;
            }
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.fixPosition();
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

}
