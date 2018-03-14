package com.gabriel.playball;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        BallPerformance ballSettings=(BallPerformance)getIntent().getSerializableExtra("performance");

        SimpleAdapter simpleAdapter =new SimpleAdapter(this,getData(),R.layout.activity_settings,
                new String[]{"Title","Performance"},
                new int[]{R.id.settingTitle,R.id.settingResult});
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String,Object>();







        return list;
    }
}
