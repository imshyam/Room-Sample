package com.shyam.roomsample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
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
    DataViewModel dataViewModel;

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

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        observeDataViewModel(dataViewModel);

    }

    private void observeDataViewModel(DataViewModel dataViewModel) {
        dataViewModel.getCustomDataObservable().observe(this, new Observer<List<CustomData>>() {
            @Override
            public void onChanged(@Nullable List<CustomData> data) {
                customViewAdapter = new CustomViewAdapter(data);
                recyclerView.setAdapter(customViewAdapter);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                String text = editText.getText().toString();
                CustomData data = new CustomData(text);
                dataViewModel.addData(data);
                customViewAdapter.addItem(data);
                recyclerView.scrollToPosition(customViewAdapter.getItemCount() - 1);
        }
    }
}
