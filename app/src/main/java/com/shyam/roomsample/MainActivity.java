package com.shyam.roomsample;

import android.arch.persistence.room.Room;
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

    EditText editText;
    CustomViewAdapter customViewAdapter;
    CustomDataDao dataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = findViewById(R.id.button);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        editText = findViewById(R.id.editText);

        save.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database")
                .allowMainThreadQueries()
                .build();

        dataDao = database.customDataDao();

        List<CustomData> dataList = dataDao.getAll();

//        List<CustomData> customDataList = new ArrayList<>();
        customViewAdapter = new CustomViewAdapter(dataList);
        recyclerView.setAdapter(customViewAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String text = editText.getText().toString();
                int id = dataDao.getMaxId() == 0 ? 1 : dataDao.getMaxId() + 1;
                CustomData data = new CustomData(id, text);
                customViewAdapter.addItem(data);
                dataDao.insertData(data);
        }
    }
}
