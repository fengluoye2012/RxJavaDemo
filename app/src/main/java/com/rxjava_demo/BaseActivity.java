package com.rxjava_demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG;
    private RecyclerView recyclerView;
    protected Activity act;
    protected MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = this;
        TAG = this.getClass().getSimpleName();

        setContentView(R.layout.activity_create);
        recyclerView = findViewById(R.id.recyclerView);

        initAdapter();
        iniListeners();
    }

    protected abstract List<String> initData();

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(initData());
        recyclerView.setAdapter(mainAdapter);
    }

    protected abstract void iniListeners();
}
