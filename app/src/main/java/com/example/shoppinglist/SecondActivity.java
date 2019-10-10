package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM =
            "com.example.android.shoppinglist.extra.ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void returnItem(View view) {
        Button button = (Button)view;
        String buttonText = button.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_ITEM, buttonText);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
