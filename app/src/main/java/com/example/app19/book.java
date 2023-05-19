package com.example.app19;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class book implements Parcelable {


    private int imageRes;       // 本のイメージリソース
    private int num;            // 番号
    private String title;       // 自作タイトル
    private String why01;       // 事前準備１
    private String why02;       // 事前準備２
    private String why03;       // 事前準備３
    private String about;       // 内容

    //本情報初期化用コンストラクタ
    book(int imageRes, int num, String title, String why01, String why02, String why03, String about) {
        this.imageRes = imageRes;
        this.num = num;
        this.title = title;
        this.why01 = why01;
        this.why02 = why02;
        this.why03 = why03;
        this.about = about;
    }

    // 各種ゲッター

    public int getImageRes() {
        return imageRes;
    }
    public int getNum() {
        return num;
    }

    public String getTitle() {
        return title;
    }

    public String getWhy01() {
        return why01;
    }

    public String getWhy02() {
        return why02;
    }
    public String getWhy03() {
        return why03;
    }
    public String getAbout() {
        return about;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageRes);
        dest.writeInt(num);
        dest.writeString(title);
        dest.writeString(why01);
        dest.writeString(why02);
        dest.writeString(why03);
        dest.writeString(about);
    }

    //
    public static final Creator<book> CREATOR = new Creator<book>() {
        @Override
        public book createFromParcel(Parcel source) {
            int imageRes = source.readInt();
            int num = source.readInt();
            String title = source.readString();
            String why01 = source.readString();
            String why02 = source.readString();
            String why03 = source.readString();
            String about = source.readString();

            book book = new book (imageRes,num, title, why01,why02,why03, about);
            return book;
        }

        @Override
        public book[] newArray(int size) {
            return new book[0];
        }
    };

    // リスト1件分のViewをまとめるViewHolder


    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View rowView;         // 1件分のレイアウトView（クリック処理を実装する用）
        protected TextView title;       // 自作タイトルの表示用
        protected Button deleteBtn;     // 1件分のデータを削除するようボタン
        protected Button editBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowView = itemView;     // itemView自体を保存（実体は、layout_book_row の LinearLayoutとなる）
            title = itemView.findViewById(R.id.title);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

            editBtn = itemView.findViewById(R.id.edit_btn);
        }

        public View getRowView() {
            return rowView;
        }

        public TextView getTitle() {
            return title;
        }

        public Button getDeleteBtn() {
            return deleteBtn;
        }
        public Button getEditBtn() {
            return editBtn;
        }
    }

    // データとViewHolderを紐づけるAdapterクラス
    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<book> bookList;    // 本のリスト

        // 本リストを受け取る用のコンストラクタ
        public Adapter(List<book> bookList) {
            this.bookList = bookList;
        }

        // ViewHolder生成用メソッド
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // LayoutInflaterを使用し、layout_pan_rowを生成し、ViewHolderの生成も同時に行っている。
            return new ViewHolder(
                    LayoutInflater
                            .from(parent.getContext())
                            .inflate(R.layout.layout_book_row, parent, false));
        }

        // データとViewHolderの紐づけ用メソッド（リストがスクロールされ表示されるデータが切り替わるたびに呼ばれる）
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            final book book = bookList.get(position);  // 表示する本のデータを取得
            holder.getTitle().setText( book.getTitle());    // 本の名前をViewに設定

            // 削除ボタンを押したときの挙動を定義　notifyItemInserted()
            holder.getDeleteBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookList.remove(holder.getAdapterPosition());        // BOOKリストから本の情報を削除
                    notifyItemRemoved(holder.getAdapterPosition());     // リストの数が変更されたことを通知
                }
            });

            holder.getEditBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditDetail.class);    // BookDetailActivityに遷移するためのIntent作成
                    intent.putExtra(MainActivity.EXTRA_PAN, book );                           // Intentにクリックし本の情報を設定
                    v.getContext().startActivity(intent);                                   // アクティビティを起動
                }
            });

            // 1件分のデータがクリックされた時の挙動を定義

            
            holder.getRowView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), BookDetailActivity.class);    // PanDetailActivityに遷移するためのIntent作成
                    intent.putExtra(MainActivity.EXTRA_PAN, book );                           // Intentにクリックし本の情報を設定
                    v.getContext().startActivity(intent);                                   // アクティビティを起動
                }
            });

        }

        // リストのデータ数を返却するメソッド
        @Override
        public int getItemCount() {
            return bookList.size();
        }
    }

}

