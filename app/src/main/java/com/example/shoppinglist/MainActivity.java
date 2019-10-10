package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final int ITEM_REQUEST = 1;
    private int itemCounter = 0;
    private EditText LocationEditText;
    @SuppressLint("UseSparseArrays")
    public HashMap<Integer, TextView> textViewHashMap = new HashMap<>();
    public ArrayList<TextView> textViewArrayList = new ArrayList<>();
    public ArrayList<String> textViewStringList = new ArrayList<>();
    public ArrayList<String> newStringArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationEditText = findViewById(R.id.location_edittext);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView6 = findViewById(R.id.textView6);
        textViewHashMap.put(1, textView1);
        textViewHashMap.put(2, textView2);
        textViewHashMap.put(3, textView3);
        textViewHashMap.put(4, textView4);
        textViewHashMap.put(5, textView5);
        textViewHashMap.put(6, textView6);

        if (savedInstanceState != null) {
            boolean isEmpty =
                    savedInstanceState.getBoolean("list_empty");
            int counter = savedInstanceState.getInt("counter");
            newStringArrayList = savedInstanceState.getStringArrayList("stringArrayList");
            textViewStringList = newStringArrayList;
            itemCounter = counter;
            if (!isEmpty) {
                for (int i = 0; i < counter; i++) {
                    textViewArrayList.add(textViewHashMap.get(i + 1));
                    String item = newStringArrayList.get(i);
                    textViewArrayList.get(i).setText(item);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!textViewArrayList.isEmpty()) {
            outState.putBoolean("list_empty", false);
            outState.putStringArrayList("stringArrayList", textViewStringList);
            outState.putInt("counter", itemCounter);
        } else {
            outState.putBoolean("list_empty", true);
        }
    }


    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, ITEM_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        itemCounter++;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                String item = data.getStringExtra(SecondActivity.EXTRA_ITEM);
                TextView textView = textViewHashMap.get(itemCounter);
                textViewStringList.add(item);
                textViewArrayList.add(textView);
                textViewArrayList.get(itemCounter - 1).setText(item);
            }
        }
    }

    public void openLocation(View view) {
        String loc = LocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" +loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            System.out.println("Can't handle this intent!");
        }
    }
}
