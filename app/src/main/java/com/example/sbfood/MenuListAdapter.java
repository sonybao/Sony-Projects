package com.example.sbfood;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class MenuListAdapter extends BaseAdapter {
    public MenuListAdapter(Context context, int layout, List<MenuList> menuList) {
        this.context = context;
        this.layout = layout;
        this.menuList = menuList;
    }

    private Context context;
    private int layout;
    private List<MenuList> menuList;

    public MenuListAdapter() {
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // ViewHolder kiểm tra những dữ liệu nào đã được in rồi thì sẽ không cần phải in lại
    private class Viewholder{
        ImageView picFood;
        TextView nameFood, priceFood, descriptionFood;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        // Kiểm tra nếu chưa có dữ liệu được in
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewholder = new Viewholder();
            viewholder.nameFood = (TextView)convertView.findViewById(R.id.nameFood);
            viewholder.priceFood = (TextView)convertView.findViewById(R.id.priceFood);
            viewholder.descriptionFood = (TextView)convertView.findViewById(R.id.descriptionFood);
            viewholder.picFood = (ImageView)convertView.findViewById(R.id.picFood);
            convertView.setTag(viewholder);


        }else {
            viewholder = (Viewholder) convertView.getTag();
        }

        MenuList menu = menuList.get(position);
        // ĐỔI CURRENCY CHO GIÁ MÓN
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(menu.getGiamon());
        // ÁNH XẠ
        viewholder.nameFood.setText(menu.getTenmon());
        viewholder.priceFood.setText(formattedNumber + " VND");
        viewholder.descriptionFood.setText(menu.getMota());
        viewholder.picFood.setImageResource(menu.getHinhanh());


        return convertView;
    }
        }

