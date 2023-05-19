package com.example.app19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {

    private book book;    // 送信されてきたパン情報

    //編集画面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_book_detail );

        Intent intent = getIntent();    // アクティビティの軌道に使われたIntent取得
        book = (book)intent.getParcelableExtra(MainActivity.EXTRA_PAN); // IntentからPan情報を取得

        ImageView image = findViewById(R.id.image);
        TextView titleText = findViewById(R.id.title);
        TextView why01Text = findViewById(R.id.why01);
        TextView why02Text = findViewById(R.id.why02);
        TextView why03Text = findViewById(R.id.why03);
        TextView About = findViewById(R.id.about);

        TextView num = findViewById(R.id.num);

        // 本情報をViewに設定
        image.setImageResource( book.getImageRes());
        titleText.setText( book.getTitle());
        why01Text.setText( book.getWhy01());
        why02Text.setText( book.getWhy02());
        why03Text.setText( book.getWhy03());
        About.setText( book.getAbout());
        num.setText(Integer.toString( book.getNum()));
        //num.setText(toString(book.getNum()));

    }

}

