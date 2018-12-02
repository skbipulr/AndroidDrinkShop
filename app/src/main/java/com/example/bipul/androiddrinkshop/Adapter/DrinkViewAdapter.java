package com.example.bipul.androiddrinkshop.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bipul.androiddrinkshop.Interface.IItemClickListener;
import com.example.bipul.androiddrinkshop.R;

public class DrinkViewAdapter  extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView img_product;
    TextView txt_drink_name,txt_price;

    IItemClickListener itemClickListener;

    Button btn_add_to_cart;

    public void setiItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DrinkViewAdapter(View itemView) {
        super(itemView);

        img_product=itemView.findViewById(R.id.image_product);
        txt_drink_name=itemView.findViewById(R.id.txt_drink_name);
        txt_price=itemView.findViewById(R.id.txt_price);
        btn_add_to_cart=itemView.findViewById(R.id.btn_add_card);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v);

    }
}
