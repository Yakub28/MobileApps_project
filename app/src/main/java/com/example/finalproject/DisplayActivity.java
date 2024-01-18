package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;

public class DisplayActivity extends TemplateActivity {
    protected ListView lvDataList;
    protected Map<String, Integer> DataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        lvDataList = findViewById(R.id.lvDataList);
        DataList = getDataList();
        fillListView(DataList);
        lvDataList.setOnItemClickListener(
                (parent, v, pos, id) -> {
                    String key = parent.getItemAtPosition(pos).toString();
                    showToast(key + " " + String.valueOf(DataList.get(key)));
                }

        );
    }

    private Map<String, Integer> getDataList(){
        SharedPreferences sharedPref = this.getSharedPreferences("DataList", Context.MODE_PRIVATE);
        return (Map<String, Integer>) sharedPref.getAll();
    }

    protected void fillListView(Map<String, Integer> elements){
        Object[] keys = elements.keySet().toArray();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, keys) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                String key = keys[position].toString();

                text1.setText(key);
                text2.setText(String.valueOf(elements.get(key)));
                return view;
            }
        };
        lvDataList.setAdapter(adapter);
    }
}