package com.gabriel.playball;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by gabriel on 18-3-14.
 */

public class BallPerformance implements Serializable {
    public float directionX=40;
    public float directionY=40;
    public float cicleR=10;
    public int color= Color.WHITE;
    public int controller=0;
}
