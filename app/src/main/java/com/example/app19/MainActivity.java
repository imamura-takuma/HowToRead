package com.example.app19;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_PAN = BuildConfig.APPLICATION_ID + ".PAN";     // 本情報を送るときに使用するEXTRA名用定数
    private List<book> bookList;        // BOOK（データ）
    private RecyclerView recycler;    // リサイクラービュー
    private book.Adapter adapter;      // アダプター

    public static final String EXTRA_TITLE = BuildConfig.APPLICATION_ID + ".TITLE";   // Extraに使う用の定数を用意
    public static final String EXTRA_WHY01 = BuildConfig.APPLICATION_ID + ".WHY01";     // Extraに使う用の定数を用意



    private ActivityResultLauncher<Intent> settingLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {  // 2つ目の引数で結果を受け取った時の処理（インタフェースを定義）
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {   // 結果がRESULT_OK（成功）の場合
                        Intent intent = result.getData();       // result.getData()により結果のインテント取得
                        p2_title = intent.getStringExtra(EXTRA_TITLE);   // intentから上で定義したEXTRA_NAME(com.example.app14.NAME)という名前のデータをStringで取得
                        p2_why01 = intent.getStringExtra(EXTRA_WHY01);

                    }
                }
            }
    );
    int p2_num;
    private String p2_title;
    private String p2_why01;
    private String p2_why02;
    private String p2_why03;
    private String p2_about;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookList = new ArrayList<>();                    // BOOKリストを初期化

//単語帳機能・・学習した単語を登録しテストできる機能（記憶の定着を高めるため一日、1週間、1か月のスパンでテストする）


        book p1 =  new book (R.drawable.book, 1,"　読書方法", "　効果的な読書の仕方をしりたっかた", "　本の読み方ノウハウを知りたいから", "今後、積極的に本を読むようになりたいまた人生の幸福度を上げたい", "　目次　\n" +
                "本を読む前の準備　\n" +
                "　・内容を予測する（間違っても大丈夫）\n" +
                "　・同じジャンルの本を複数読む（共通点がわかる）　\n" +
                "　・内容を予測する（間違っても大丈夫）　\n" +
                "読書中に意識すること　\n" +
                "　・読みたい章から読むこと　\n" +
                "　・すべてを読むのではなく、「しかし」「つまり」文の終わりつまり筆者が言いたいことを重点的に読む（全体の２０％）　\n" +
                "　・内容に質問しながら読む（例）なぜ筆者はそう思ったのか？言いたいことは何なのか？　\n" +
                "　・一旦本を閉じ学習した内容をまとめる　\n" +
                "　・知っている内容と知らない内容の差を意識する　\n" +
                "　・要約しながら読む（予測とのギャップを意識）　\n" +
                "読み終えた時にすること（アウトプット）　\n" +
                "　・忘れている事を思い出す訓練　\n" +
                "　・人に説明する　\n" +
                "　・学んだ事を体を使って実践してみる　\n" +
                "　違う方法で三回アウトプットしてみる");

                 p2_num=2;
                 p2_title="";
                 p2_why01="";
                 p2_why02="";
                 p2_why03="";
                 p2_about="";



            book p2 =  new book (R.drawable.book,p2_num,p2_title, p2_why01, p2_why02, p2_why03, p2_about);

        // 追加機能の編集


        bookList.add(p1);
        bookList.add(p2);




        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));   // リストの表示形式を縦並びに設定
        adapter = new book.Adapter( bookList );                                 // 本リストを基にアダプターを生成
        recycler.setAdapter(adapter);                                       // リサイクラーにアダプターをセット


//

        findViewById(R.id.new_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NewAdd.class);  // RegisterActivityを作っていない場合エラー
                startActivity(intent);
                }
        });
    }

}