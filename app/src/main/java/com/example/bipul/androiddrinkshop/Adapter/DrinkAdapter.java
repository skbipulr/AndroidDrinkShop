package com.example.bipul.androiddrinkshop.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.bipul.androiddrinkshop.Interface.IItemClickListener;
import com.example.bipul.androiddrinkshop.Model.Drink;
import com.example.bipul.androiddrinkshop.R;
import com.example.bipul.androiddrinkshop.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.jar.Attributes;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewAdapter>{

    Context context;
    List<Drink> drinkList;

    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.drink_item_layout,null);
        return new DrinkViewAdapter(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewAdapter holder, final int position) {

        holder.txt_price.setText(new StringBuilder("$").append(drinkList.get(position).Price).toString());
        holder.txt_drink_name.setText(drinkList.get(position).Name);

        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartDialog(position);
            }
        });

        //load image
        Picasso.with(context)
                .load(drinkList.get(position).Link)
                .into(holder.img_product);


        holder.setiItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddToCartDialog(final int position) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout,null);

        //View
        ImageView img_product_dialog=itemView.findViewById(R.id.img_cart_prodect);
        final ElegantNumberButton txt_count=itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog=itemView.findViewById(R.id.txt_cart_product_name);

        EditText edt_comment=itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_sizeM=itemView.findViewById(R.id.rdi_sizeM);
        RadioButton rdi_sizeL=itemView.findViewById(R.id.rdi_sizeL);

        rdi_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                Common.sizeOfCup=0;
            }
        });

        rdi_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                Common.sizeOfCup=1;
            }
        });

        RadioButton rdi_sugar_100=itemView.findViewById(R.id.rdi_sugar_100);
        RadioButton rdi_sugar_70=itemView.findViewById(R.id.rdi_sugar_70);
        RadioButton rdi_sugar_50=itemView.findViewById(R.id.rdi_sugar_50);
        RadioButton rdi_sugar_30=itemView.findViewById(R.id.rdi_sugar_30);
        RadioButton rdi_sugar_free=itemView.findViewById(R.id.rdi_sugar_free);

        rdi_sugar_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sugar=30;
            }
        });

        rdi_sugar_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sugar=50;
            }
        });

        rdi_sugar_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sugar=70;
            }
        });

        rdi_sugar_100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sugar=100;
            }
        });


        rdi_sugar_free.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    Common.sugar=0;
            }
        });

        RadioButton rdi_ice_100=itemView.findViewById(R.id.rdi_ice_100);
        RadioButton rdi_ice_70=itemView.findViewById(R.id.rdi_ice_70);
        RadioButton rdi_ice_50=itemView.findViewById(R.id.rdi_ice_50);
        RadioButton rdi_ice_30=itemView.findViewById(R.id.rdi_ice_30);
        RadioButton rdi_ice_free=itemView.findViewById(R.id.rdi_ice_free);

        rdi_ice_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.ice=30;
            }
        });

        rdi_ice_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.ice=50;
            }
        });

        rdi_ice_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.ice=70;
            }
        });

        rdi_ice_100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.ice=100;
            }
        });

        rdi_ice_free.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Common.ice=0;
            }
        });

        RecyclerView recycler_topping=itemView.findViewById(R.id.recycler_topping);
        recycler_topping.setLayoutManager(new LinearLayoutManager(context));
        recycler_topping.setHasFixedSize(true);

      MultiChoiceAdapter adapter=new MultiChoiceAdapter(context,Common.toppingList);
      recycler_topping.setAdapter(adapter);

        //set Data

        Picasso.with(context)
                .load(drinkList.get(position).Link)
                .into(img_product_dialog);
            txt_product_dialog.setText(drinkList.get(position).Name);
            builder.setView(itemView);
            builder.setNegativeButton("ADD TO CART", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(Common.sizeOfCup == -1)
                    {
                        Toast.makeText(context, "Please choose size of cup", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Common.sugar == -1)
                    {
                        Toast.makeText(context, "Please choose suger", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(Common.ice==-1)
                    {
                        Toast.makeText(context, "Please choose ice", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    showConfirmDialog(position,txt_count.getNumber(),Common.sizeOfCup,Common.sugar,Common.ice);
                    dialog.dismiss();
                }
            });

            builder.show();
    }

    private void showConfirmDialog(int position, String number, int sizeOfCup, int sugar, int ice) {

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.confiem_add_to_cart_layout,null);


        //view
        ImageView img_product_dialog=itemView.findViewById(R.id.image_product);
        TextView txt_product_dialog=itemView.findViewById(R.id.txt_cart_product_name);
        TextView txt_product_price=itemView.findViewById(R.id.txt_cart_product_price);
        TextView txt_sugar=itemView.findViewById(R.id.txt_sugar);
        TextView txt_ice=itemView.findViewById(R.id.txt_ice);
        TextView txt_topping_extra=itemView.findViewById(R.id.txt_topping_extra);

        //set data
        Picasso.with(context).load(drinkList.get(position).Link).into(img_product_dialog);
        txt_product_dialog.setText(new StringBuilder(drinkList.get(position).Name).append( " x")
        .append(number)
        .append(Common.sizeOfCup == 0 ? " Size M" : "Size L").toString());

        txt_ice.setText(new StringBuilder("Ice: ").append(Common.ice).append("%").toString());
        txt_sugar.setText(new StringBuilder("Suger: ").append(Common.sugar).append("%").toString());


        double price = (Double.parseDouble(drinkList.get(position).Price)*Double.parseDouble(number)) + Common.toppingPrice;

        if(Common.sizeOfCup == 1)
            price+=3.0;

        txt_product_price.setText(new StringBuilder("$").append(price));
        StringBuilder topping_final_comment=new StringBuilder("");
        for(String line:Common.toppingAdded)
            topping_final_comment.append(line).append("\n");
        txt_topping_extra.setText(topping_final_comment);

        builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //Add to SQLite
                //Implement late in next part
                dialog.dismiss();
            }
        });
        builder.setView(itemView);
        builder.show();


    }

/*
    private void showConfirmDialog(int position, String number, int sizeOfCup, int sugar, int ice) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.confiem_add_to_cart_layout,null);


   */
/* private void showConfirmDialog(int position, String number, int sizeOfCup, int sizeOfCup1) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.confiem_add_to_cart_layout,null);
*//*


        //view
        ImageView img_product_dialog=itemView.findViewById(R.id.image_product);
        TextView txt_product_dialog=itemView.findViewById(R.id.txt_cart_product_name);
        TextView txt_product_price=itemView.findViewById(R.id.txt_cart_product_price);
        TextView txt_sugar=itemView.findViewById(R.id.txt_sugar);
        TextView txt_ice=itemView.findViewById(R.id.txt_ice);
        TextView txt_topping_extra=itemView.findViewById(R.id.txt_topping_extra);

        Picasso.with(context).load(drinkList.get(position).Link).into(img_product_dialog);
        */
/*txt_product_dialog.setText(new StringBuilder(drinkList.get(position).Name).append(" x")
        .append(number)
        .append(Common.sizeOfCup==0 ? " Size M":" Size L").toString());
*//*

        txt_product_dialog.setText(new StringBuilder(drinkList.get(position).Name).append(" x")
        .append(number)
        .append(Common.sizeOfCup==0 ? "Size M" : "Size L").toString());

        txt_ice.setText(new StringBuilder("Ice: ").append(Common.ice).append("%").toString());
        txt_sugar.setText(new StringBuilder("Sugar: ").append(Common.sugar).append("%").toString());

        double price =(Double.parseDouble(drinkList.get(position).Price)*Double.parseDouble(number))+ Common.toppingPrice;

        if (Common.sizeOfCup==1) // size 1
            price+=3.0;

        txt_product_price.setText(new StringBuilder("$").append(price));
        StringBuilder topping_final_commnet=new StringBuilder("");
        for (String line: Common.toppingAdded)
            topping_final_commnet.append(line).append("\n");
        txt_topping_extra.setText(topping_final_commnet);

        builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Add to SqLite
                // Implement late in nex part
                dialog.dismiss();
            }
        });

        builder.setView(itemView);
        builder.show();
    }
*/

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
}
