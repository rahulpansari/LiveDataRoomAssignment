package com.apna.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.apna.techrahul.Database.RecyclerEntity;
import com.apna.techrahul.utils.RecyclerRepository;

import java.util.List;

public class RecyclerViewModel extends AndroidViewModel {
    RecyclerRepository recyclerRepository;
    LiveData<List<RecyclerEntity>> listLiveData;

    public RecyclerViewModel(Application application) {
        super(application);

        recyclerRepository=new RecyclerRepository(application);
        listLiveData=recyclerRepository.getRecycleList();
    }


    public void insert(RecyclerEntity entity)
    {

        recyclerRepository.insert(entity);
    }

    public void update(RecyclerEntity entity)
    {
        recyclerRepository.update(entity);
    }
    public void delete(RecyclerEntity entity)
    {
        recyclerRepository.delete(entity);
    }
    public LiveData<List<RecyclerEntity>> getList()
    {
        return listLiveData;
    }
}
