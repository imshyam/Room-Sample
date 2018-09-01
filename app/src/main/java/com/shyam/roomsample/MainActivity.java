package com.shyam.roomsample;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;
    RecyclerView recyclerView;
    CustomViewAdapter customViewAdapter;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recycler_view);
        editText = findViewById(R.id.editText);

        save.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database").build();

        GetAllAsyncTask asyncTask = new GetAllAsyncTask();
        List<CustomData> dataList = new ArrayList<>();
        try {
            dataList = asyncTask.execute(database).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        customViewAdapter = new CustomViewAdapter(dataList);
        recyclerView.setAdapter(customViewAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String text = editText.getText().toString();
                SaveAsyncTask saveAsyncTask = new SaveAsyncTask(database);
                int id = 1;
                try {
                    id = saveAsyncTask.execute(text).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customViewAdapter.addItem(new CustomData(id, text));
                recyclerView.scrollToPosition(customViewAdapter.getItemCount() - 1);
        }
    }
}
