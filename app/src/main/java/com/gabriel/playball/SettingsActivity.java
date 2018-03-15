package com.gabriel.playball;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends Activity {


    private final String[] itemColor={"White","Red","Blue","Green"};
    private final String[] itemControl={"Manual","Gravity"};
    private final String warningStr="1.可选白、红、蓝、绿四种颜色\n2.直径大小范围1-100，否则不能生效\n" +
            "3.设置后必须重启app设置才能生效";

    private BallPerformance ballSettings;

    private AlertDialog.Builder colorDialog;
    private String colorResult;
    private AlertDialog.Builder controlerDialog;
    private String controlerResult;

    private TextView colorView;
    private TextView controlView;
    private EditText diameterView;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ballSettings=(BallPerformance)getIntent().getSerializableExtra("performance");

        colorView=(TextView)findViewById(R.id.colorResult);
        controlView=(TextView)findViewById(R.id.controlResult);
        diameterView=(EditText)findViewById(R.id.diameterResult);
        warning=(TextView) findViewById(R.id.warning);
        warning.setText(warningStr);

        initSettings();

        colorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorSelect();
            }
        });

        controlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showControlerSelect();
                initSettings();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        float temp=Float.valueOf(diameterView.getText().toString());
        if(temp>=1||temp<=100){
            ballSettings.cicleR=temp;
        }else{
            Toast.makeText(SettingsActivity.this,"The diameter is wrong!",Toast.LENGTH_SHORT).show();
        }
        editor.putFloat("R", ballSettings.cicleR);
        editor.putInt("Color",ballSettings.color);
        editor.putInt("Control",ballSettings.controller);
        editor.commit();
        Toast.makeText(SettingsActivity.this,"Please restart this app for update the config file!!!",Toast.LENGTH_LONG).show();
        SettingsActivity.this.finish();

    }

    //初始化设置界面
    private void initSettings(){
        switch(ballSettings.color){
            case Color.WHITE:
                colorView.setText("White");
                break;
            case Color.RED:
                colorView.setText("Red");
                break;
            case Color.BLUE:
                colorView.setText("Blue");
                break;
            case Color.GREEN:
                colorView.setText("Green");
                break;
        }
        controlView.setText(itemControl[ballSettings.controller]);
        diameterView.setText(String.valueOf(ballSettings.cicleR));
    }

    //小Dialog
    private void showColorSelect() {
        colorDialog=new AlertDialog.Builder(SettingsActivity.this);
        colorDialog.setTitle("Select Color:").setItems(itemColor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                colorResult=itemColor[which];
                switch(which){
                    case 0:
                        ballSettings.color=Color.WHITE;
                        break;
                    case 1:
                        ballSettings.color=Color.RED;
                        break;
                    case 2:
                        ballSettings.color=Color.BLUE;
                        break;
                    case 3:
                        ballSettings.color=Color.GREEN;
                        break;
                }
            }
        });
        colorDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                initSettings();
            }
        });
        colorDialog.create().show();
    }

    private void showControlerSelect(){
        controlerDialog=new AlertDialog.Builder(SettingsActivity.this);
        controlerDialog.setTitle("Select Controller:").setItems(itemControl, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ballSettings.controller=which;
                controlerResult=itemControl[which];
            }
        });
        controlerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                initSettings();
            }
        });
        controlerDialog.create().show();
    }


}
