package com.apna.techrahul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apna.ViewModel.RecyclerViewModel;
import com.apna.techrahul.Database.RecyclerEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertActivity extends AppCompatActivity {
    RecyclerViewModel viewModel;
@BindView(R.id.etuid)
    EditText etuid;
@BindView(R.id.ettitle)
EditText etTitle;
@BindView(R.id.etbody)
EditText etbody;
int  size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ButterKnife.bind(this);
        size=getIntent().getIntExtra("id",0);
        viewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);

    }

    @OnClick(R.id.savebttn) void SaveToRoom()
    {
        if(validate())
        {
            viewModel.insert(new RecyclerEntity(size, Integer.parseInt(etuid.getText().toString()), etTitle.getText().toString(), etbody.getText().toString(), System.currentTimeMillis()));
            Toast.makeText(getApplicationContext(),"Data Inserted To Room Db ",Toast.LENGTH_LONG ).show();
            finish();
        }

    }
    public boolean validate()
    {
        if(etuid.getText().toString().isEmpty())
        {
            etuid.setError("Set Userid");
            return false;
        }

        if(etTitle.getText().toString().isEmpty())
        {
            etTitle.setError("Set Title");
            return false;
        }

        if(etbody.getText().toString().isEmpty())
        {
            etbody.setError("Set Body");
            return false;
        }
        return  true;
    }
}