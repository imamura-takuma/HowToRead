package com.example.app19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditDetail extends AppCompatActivity {

    private book book;    // 送信されてきた本情報
    private int number;

    //編集画面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_detail);

        Intent intent = getIntent();    // アクティビティの軌道に使われたIntent取得
        book = (book)intent.getParcelableExtra(MainActivity.EXTRA_PAN); // IntentからPan情報を取得

        ImageView image = findViewById(R.id.image);

        final EditText edit_titleText = findViewById(R.id.edit_title);
        final EditText edit_why01Text = findViewById(R.id.edit_why01);
        EditText edit_why02Text = findViewById(R.id.edit_why02);
        EditText edit_why03Text = findViewById(R.id.edit_why03);
        EditText edit_About = findViewById(R.id.edit_about);

        Button EditBtn = findViewById(R.id.edit_btn);


        //本情報をViewに設定
        image.setImageResource( book.getImageRes());
        edit_titleText.setText( book.getTitle());
        edit_why01Text.setText( book.getWhy01());
        edit_why02Text.setText( book.getWhy02());
        edit_why03Text.setText( book.getWhy03());
        edit_About.setText( book.getAbout());

        number=book.getNum();

        EditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_titleText.getText().toString().equals("")) {  // 未入力ならトーストを表示しonClick()を終了（return）
                    Toast.makeText(EditDetail.this, "titleを入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edit_why01Text.getText().toString().equals("")) {   // 未入力ならトーストを表示しonClick()を終了（return）
                    Toast.makeText(EditDetail.this, "01を入力してください", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();   // 空のインテント（行先の指定なし）を作成
                intent.putExtra(MainActivity.EXTRA_TITLE, edit_titleText.getText().toString());                    // MainActivityで定義したEXTRA_NAMEで、入力された名前を設定
                intent.putExtra(MainActivity.EXTRA_WHY01, edit_why01Text.getText().toString());    // MainActivityで定義したEXTRA_NAMEで、入力された年齢を設定
                //intent.putExtra(MainActivity.EXTRA_WHY01, Integer.parseInt(ageEdit.getText().toString()));    // MainActivityで定義したEXTRA_NAMEで、入力された年齢を設定
                setResult(RESULT_OK, intent);   // アクティビティの結果をRESULT_OK（成功）と返却値をintentとして登録
                finish();                       // アクティビティを終了（結果をMainActivityに返却）
            }
        });


    }

}

