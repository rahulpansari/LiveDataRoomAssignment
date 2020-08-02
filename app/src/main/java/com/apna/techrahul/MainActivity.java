package com.apna.techrahul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.apna.ViewModel.RecyclerViewModel;
import com.apna.techrahul.Database.RecyclerEntity;
import com.apna.techrahul.adapter.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    RecyclerViewModel viewModel;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    LinearLayoutManager layoutManager;
    int size;
    public static Observer<List<RecyclerEntity>> dataobserver;
    FloatingActionButton floatbttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),viewModel);
        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        dataobserver=new Observer<List<RecyclerEntity>>() {
            @Override
            public void onChanged(List<RecyclerEntity> recyclerEntities) {

                size=recyclerEntities.size();
                Log.e("changes",size+"");
                recyclerAdapter.setEntity(recyclerEntities);

            }
        };
        viewModel.getList().observe(this,dataobserver );

    }

    @OnClick(R.id.floatingactionbttn)
    void insert() {
        Intent intent=new Intent(this,InsertActivity.class);
        intent.putExtra("id",size+1);
        startActivity(intent);
    }
}