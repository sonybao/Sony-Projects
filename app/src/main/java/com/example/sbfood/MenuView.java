package com.example.sbfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class MenuView extends AppCompatActivity {
    TextView hello;
    Database database;
    Context context;
    ListView listView;
    ArrayList<MenuList> arrayMenu;
    MenuListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_view);
        hello = (TextView) findViewById(R.id.hello);
        listView = (ListView) findViewById(R.id.listView);

        arrayMenu = new ArrayList<>();
        String helloname = getIntent().getStringExtra("name");
        hello.setText("Hello " + helloname +"\n"+"Quý khách muốn ăn gì ?");
        arrayMenu.add(new MenuList("Cơm Tấm", 35000,
                "Nên thì một vợ một chồng\n" +
                "Một niêu cơm tấm, một đùm mắm nêm", R.drawable.comtam));
        arrayMenu.add(new MenuList("Phở", 50000, "Giữa trời mưa tầm tã húp ngay bát phở say mê ấm lòng", R.drawable.pho));
        arrayMenu.add(new MenuList("Hủ tíu", 20000, "Cứ đi đâu nhưng về tới Sài Gòn Tấp lề đường gọi một tô hủ tíu", R.drawable.hutiu));
        arrayMenu.add(new MenuList("Mì trộn", 25000, "Cứ còn cái gì là trộn ngay với mì", R.drawable.mitron));
        adapter = new MenuListAdapter(MenuView.this, R.layout.listview_line, arrayMenu);
        listView.setAdapter(adapter);

        AccList(); // SHOW LIST OF ACCOUNTS

    }

    private void AccList() {
        Database database = new Database(MenuView.this,"SonyFood.db",null,1);
        Cursor res = database.ViewList();
        while (res.moveToNext()) {
            String email = res.getString(1);
            String username = res.getString(2);
            String password = res.getString(3);
            String fullname = res.getString(4);
            Log.d("test", " " + email + " " + username + " " + password + " " + fullname);
        }

    }
}
