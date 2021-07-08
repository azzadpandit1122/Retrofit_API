package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.d(TAG, "onResponse: " + response.code());
                        ArrayList<Model.data> data = response.body().getData();
                        for (Model.data data1 : data) {
                            Log.e(TAG, "onResponse: " + data1.getEmail());
                            Log.e(TAG, "onResponse: " + data1.getFist_name());
                            Log.e(TAG, "onResponse: " + data1.id);
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }
}