package com.gabriel.playball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    public int screenWidth;
    public int screenHeight;
    public BallPerformance ball;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get screen width&height
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth=displayMetrics.widthPixels;
        screenHeight=displayMetrics.heightPixels;

        ball=new BallPerformance();
        SharedPreferences sharedPreferences=getSharedPreferences("Settings",0);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        ball.directionX=sharedPreferences.getFloat("X",40);
        ball.directionY=sharedPreferences.getFloat("Y",40);
        ball.cicleR=sharedPreferences.getFloat("R",40);
        ball.controller=sharedPreferences.getInt("Control",1);
        ball.color=sharedPreferences.getInt("Color",Color.WHITE);



        setContentView(new drawBall(this));

    }

    public class drawBall extends View {



        private Paint paint=new Paint();
        private Paint buttonPaint=new Paint();
        private OnClickListener mClickListener;

        public drawBall(Context context){
            super(context);
        }
        public drawBall(Context context, AttributeSet attributes){
            super(context, attributes);

        }

        public void fixPosition(){
            if(ball.directionX+ball.cicleR>screenWidth){
                ball.directionX=screenWidth-ball.cicleR;
            }
            if(ball.directionY+ball.cicleR>screenHeight){
                ball.directionY=screenHeight-ball.cicleR;
            }
            if(ball.directionX-ball.cicleR<0){
                ball.directionX=ball.cicleR;
            }
            if(ball.directionY-ball.cicleR<0){
                ball.directionY=ball.cicleR;
            }
            if(isInClickButton()){
                double length =Math.sqrt(Math.pow(screenWidth-ball.directionX,2)+Math.pow(screenHeight-ball.directionY,2));
                double sinValue=(screenHeight-ball.directionY)/length;
                double cosValue=(screenWidth-ball.directionX)/length;
                ball.directionX=screenWidth- (float) ((150+ball.cicleR)*cosValue);
                ball.directionY= screenHeight-(float) ((150+ball.cicleR)*sinValue);
            }
        }

        public boolean isInClickButton(){
            if(Math.sqrt(Math.pow(screenWidth-ball.directionX,2)+Math.pow(screenHeight-ball.directionY,2))<150+ball.cicleR) {
                return true;
            }
            return false;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            this.fixPosition();
            paint.setColor(Color.WHITE);
            buttonPaint.setColor(Color.BLUE);
            canvas.drawCircle(ball.directionX, ball.directionY, ball.cicleR, paint);
            canvas.drawCircle(screenWidth,screenHeight,150,buttonPaint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            ball.directionX=event.getX();
            ball.directionY=event.getY();
            int action=event.getAction();
            if(action==MotionEvent.ACTION_UP&&isInClickButton()){
                Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
                intent.putExtra("performance", ball);
                startActivityForResult(intent,0);
            }
            else {
                invalidate();
            }
            return true;
        }

        @Override
        public void setOnClickListener(OnClickListener onClickListener) {
            mClickListener=onClickListener;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("X", ball.directionX);
        editor.putFloat("Y", ball.directionY);
        editor.putFloat("R", ball.cicleR);
        editor.putInt("Color",ball.color);
        editor.putInt("Control",ball.controller);
        editor.commit();
    }
}
