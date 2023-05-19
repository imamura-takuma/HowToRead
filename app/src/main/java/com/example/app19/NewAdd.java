package com.example.app19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

public class NewAdd  extends AppCompatActivity {

    //新規登録画面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_add);



        ImageView image = findViewById(R.id.add_image);
        EditText AddTitleText = findViewById(R.id.add_title);
        EditText AddWhy01Text = findViewById(R.id.add_why01);
        EditText AddWhy02Text = findViewById(R.id.add_why02);
        EditText AddWhy03Text = findViewById(R.id.add_why03);
        EditText AddAbout = findViewById(R.id.add_about);

        Button AddBtn = findViewById(R.id.add_btn);


        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewAdd.this, MainActivity.class);  // RegisterActivityを作っていない場合エラー
                startActivity(intent);
            }
        });


    }
}

