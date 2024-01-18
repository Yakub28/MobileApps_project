package com.example.finalproject;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TemplateActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void HandleSum(View view){
        EditText nameEditText = findViewById(R.id.nameInput);
        EditText arg1EditText = findViewById(R.id.arg1Input);
        EditText arg2EditText = findViewById(R.id.arg2Input);

        String name = nameEditText.getText().toString();
        int sum = getSum(arg1EditText, arg2EditText);

        SetText(name, sum);
        AddToList(name, sum);
    }

    public void SetText(String name, int sum){
        TextView nameText = findViewById(R.id.nameText);
        TextView sumText = findViewById(R.id.sumText);

        nameText.setText(name);
        sumText.setText(String.valueOf(sum));
    }

    public void AddToList(String name, int sum){
        SharedPreferences sharedPref = this.getSharedPreferences("DataList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(name, sum);
        editor.apply();
    }

    public int getSum(EditText arg1EditText, EditText arg2EditText){
        int arg1 = 0;
        if(!isEmpty(arg1EditText)) arg1 = Integer.parseInt(arg1EditText.getText().toString());
        int arg2 = 0;
        if(!isEmpty(arg2EditText)) arg2 = Integer.parseInt(arg2EditText.getText().toString());
        return arg1 + arg2;
    }

    public void OpenDisplay(View view){
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }

    public void ClearData(View view) {
        SharedPreferences sharedPref = this.getSharedPreferences("DataList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        showToast("The list has been emptied!");
    }
}