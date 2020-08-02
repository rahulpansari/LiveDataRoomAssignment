package com.apna.techrahul.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apna.ViewModel.RecyclerViewModel;
import com.apna.techrahul.Database.RecyclerEntity;
import com.apna.techrahul.R;
import com.apna.techrahul.utils.RecyclerRepository;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    Context context;
    List<RecyclerEntity> entityList;
    RecyclerViewModel model;
    public RecyclerAdapter(Context c,RecyclerViewModel m) {
        super();
        context=c;
        model=m;
        entityList=new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.recycler_adapter,parent,false);
        return new RecyclerAdapter.RecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerHolder holder, final int position) {
            holder.title.setText("Title: "+entityList.get(position).getTitle());
            holder.body.setText("Body: "+entityList.get(position).getBody());
            holder.id.setText("Id: "+entityList.get(position).getId()+"");
        Log.e("size",entityList.size()+"rr");
            holder.userid.setText("Userid: "+entityList.get(position).getUserid()+"");
            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.delete(entityList.get(position));
                    Toast.makeText(context,"Succesfully Removed From Room Db",Toast.LENGTH_LONG).show();
                }
            });


    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtuserid)
        public TextView userid;
        @BindView(R.id.txtid)
                public TextView id;
        @BindView(R.id.txttitle)
                public TextView title;
        @BindView(R.id.txtbody)
                public TextView body;
        @BindView(R.id.remove)
        public Button remove;

        public RecyclerHolder(View v)
            {       super(v);
                    ButterKnife.bind(this,v);

            }

    }
    public void setEntity(List<RecyclerEntity>en)
    {
        entityList=en;
        notifyDataSetChanged();
    }
}
