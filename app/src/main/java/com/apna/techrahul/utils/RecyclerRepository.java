package com.apna.techrahul.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.apna.techrahul.Database.RecyclerDao;
import com.apna.techrahul.Database.RecyclerDatabase;
import com.apna.techrahul.Database.RecyclerEntity;
import com.apna.techrahul.network.NetworkInterface;
import com.apna.techrahul.network.RecycleNetwork;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerRepository {
    private RecyclerDao recyclerDao;
    private LiveData<List<RecyclerEntity>> alldata;
    List<RecyclerEntity> entity;
    Application application;
    public RecyclerRepository(Application application) {
        this.application=application;
        RecyclerDatabase database=RecyclerDatabase.getInstance(application);
        this.recyclerDao = database.getRecyclerDao();
        this.alldata = recyclerDao.getEntities();
        entity=recyclerDao.getEntities().getValue();
        checkifNetworkCallRequire();


    }

    public void insert(RecyclerEntity entity)
    {
        new InsertEntity(recyclerDao).execute(entity);
    }
    public void insertAll(List<RecyclerEntity> entities)
    {
        new InsertAllEntity(recyclerDao).execute(entities);
    }
    public void update(RecyclerEntity entity)
    {
        new UpdateEntity(recyclerDao).execute(entity);
    }
    public void delete(RecyclerEntity entity)
    {
        new DeleteEntity(recyclerDao).execute(entity);
    }
    public LiveData<List<RecyclerEntity>> getRecycleList()
    {Log.e("ho","hpll");
        return alldata;
    }

    public static class InsertEntity extends AsyncTask<RecyclerEntity,Void,Void>
    {   RecyclerDao dao;
        @Override
        protected Void doInBackground(RecyclerEntity... recyclerEntities) {
           // Log.e("hi",dao.getEntities().getValue().size()+"");
            dao.insert(recyclerEntities[0]);
            return null;
        }

        public InsertEntity(RecyclerDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public static class InsertAllEntity extends AsyncTask<List<RecyclerEntity>,Void,Void>
    {   RecyclerDao dao;
        @Override
        protected Void doInBackground(List<RecyclerEntity>... recyclerEntities) {
            // Log.e("hi",dao.getEntities().getValue().size()+"");
            dao.insertAll(recyclerEntities[0]);
            return null;
        }

        public InsertAllEntity(RecyclerDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public static class UpdateEntity extends AsyncTask<RecyclerEntity,Void,Void>
    {   RecyclerDao dao;
        @Override
        protected Void doInBackground(RecyclerEntity... recyclerEntities) {
            dao.update(recyclerEntities[0]);
            return null;
        }

        public UpdateEntity(RecyclerDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public static class DeleteEntity extends AsyncTask<RecyclerEntity,Void,Void>
    {   RecyclerDao dao;
        @Override
        protected Void doInBackground(RecyclerEntity... recyclerEntities) {
            dao.delete(recyclerEntities[0]);
            return null;
        }

        public DeleteEntity(RecyclerDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void makeNetworkCall()
    {
        Call<List<RecycleNetwork>> call;
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NetworkInterface.Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        NetworkInterface api=retrofit.create(NetworkInterface.class);
        call=api.getData();
        call.enqueue(new Callback<List<RecycleNetwork>>() {
            @Override
            public void onResponse(Call<List<RecycleNetwork>> call, Response<List<RecycleNetwork>> response) {
                Log.e("hipo",response+"");
                if(response.isSuccessful())
                {
                    Log.e("hipo","hopo");
                    List<RecycleNetwork> noti=response.body();
                    List<RecyclerEntity> daoent=new ArrayList<>();
                    for(int i=0;i<noti.size();i++)
                    {
                        long time= System.currentTimeMillis();
                        daoent.add(new RecyclerEntity(noti.get(i).getId(),noti.get(i).getUserId(),noti.get(i).getTitle(),noti.get(i).getBody(),time));
                    }

                    insertAll(daoent);

                }
            }

            @Override
            public void onFailure(Call<List<RecycleNetwork>> call, Throwable t) {
                Log.e("hhh",t.getMessage());
            }
        });
    }

    public void checkifNetworkCallRequire()
    {
        if(isNetworkAvailable(application.getApplicationContext()))
        {
            makeNetworkCall();
            Log.e("network","make network call");

        }
        else
        {
            this.alldata = recyclerDao.getEntities();
            Log.e("network","no  network call");
        }


    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
