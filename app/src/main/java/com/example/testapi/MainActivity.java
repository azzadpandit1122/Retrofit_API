package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.text);
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getAllData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d(TAG, "onResponse: " + response.code());
                ArrayList<Model.data> data = response.body().getData();
                for (Model.data data1 : data) {
                    String Value_fatch_from_api = "";
                    Value_fatch_from_api += "Id" + data1.getId() + "\n";
                    Value_fatch_from_api += "Email" + data1.getEmail() + "\n";
                    Value_fatch_from_api += "Fist" + data1.getFirst_name() + "\n";
                    Value_fatch_from_api += "Last" + data1.getLast_name() + "\n\n";
                    Log.e(TAG, "onResponse: " + data1.getEmail());
                    Log.e(TAG, "onResponse: " + data1.getFirst_name());
                    Log.e(TAG, "onResponse: " + data1.id);
                    text.append(Value_fatch_from_api);
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}