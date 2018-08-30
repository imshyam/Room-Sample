package com.shyam.roomsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int textId = 0;
    EditText editText;
    CustomViewAdapter customViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = findViewById(R.id.button);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        editText = findViewById(R.id.editText);

        save.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<CustomData> customDataList = new ArrayList<>();
        customViewAdapter = new CustomViewAdapter(customDataList);
        recyclerView.setAdapter(customViewAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String text = editText.getText().toString();
                String id = String.valueOf(textId);
                CustomData data = new CustomData(id, text);
                customViewAdapter.addItem(data);
                textId++;
        }
    }
}
