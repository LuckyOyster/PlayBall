package com.gabriel.playball;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends Activity {


    private final String[] itemColor={"White","Red","Blue","Green"};
    private final String[] itemControl={"Userage","Gravity"};
    private ListView listView;
    private BallPerformance ballSettings;
    private AlertDialog.Builder colorDialog;
    private TextView selectResult;
    private String colorResult;
    private List<Map<String,Object>> adapterList;
    private Map<String,Object> flushMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ballSettings=(BallPerformance)getIntent().getSerializableExtra("performance");

        listView=(ListView)findViewById(android.R.id.list);
        selectResult=(TextView)findViewById(R.id.settingResult);

        adapterList=getData();
        final SimpleAdapter simpleAdapter =new SimpleAdapter(this,adapterList,R.layout.layout_adapter,
                new String[]{"Title","Performance"},
                new int[]{R.id.settingTitle,R.id.settingResult});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showColorSelect();
                        flushMap=adapterList.get(0);
                        flushMap.put("Performance",colorResult);
                        simpleAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
        Map<String,Object> mapColor=new HashMap<String,Object>();
        Map<String,Object> mapControl=new HashMap<String,Object>();
        Map<String,Object> mapSize=new HashMap<String,Object>();

        mapColor.put("Title","Select Color:");
        switch(ballSettings.color){
            case Color.WHITE:
                mapColor.put("Performance","White");
                break;
            case Color.RED:
                mapColor.put("Performance","Red");
                break;
            case Color.BLUE:
                mapColor.put("Performance","Blue");
                break;
            case Color.GREEN:
                mapColor.put("Performance","Green");
                break;
        }
        list.add(mapColor);

        mapControl.put("Title","Select Controller:");
        mapControl.put("Performance",itemControl[ballSettings.controller]);
        list.add(mapControl);

        mapSize.put("Title","Select Diameter:");
        mapSize.put("Performance",ballSettings.cicleR);
        list.add(mapSize);

        return list;
    }

    private void showColorSelect(){
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
                        ballSettings.color=Color.GREEN;
                        break;
                    case 3:
                        ballSettings.color=Color.BLUE;
                        break;
                }
            }
        });
        colorDialog.create().show();
    }
}
