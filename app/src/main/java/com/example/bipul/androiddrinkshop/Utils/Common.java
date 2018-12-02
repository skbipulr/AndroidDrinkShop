package com.example.bipul.androiddrinkshop.Utils;

import com.example.bipul.androiddrinkshop.Model.Category;
import com.example.bipul.androiddrinkshop.Model.Drink;
import com.example.bipul.androiddrinkshop.Model.User;
import com.example.bipul.androiddrinkshop.Retrofit.IDrinkShopAPI;
import com.example.bipul.androiddrinkshop.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {
    //In Emulator localhost=10.0.2.2
    private static final String BASE_URL = "http://10.0.2.2:10080/drinkshop/";

    public static final String TOPPING_MENU_ID="7";

    public static User currentUser=null;
    public static Category currentCategory=null;

    public static List<Drink> toppingList=new ArrayList<>();

    public static double toppingPrice=0.0;
    public static List<String> toppingAdded=new ArrayList<>();

    //Hold field
    public static int sizeOfCup=-1;//-1 : no chose(error, O : M, 1 : L
    public static  int sugar=-1;// -1 : on choose (error)
    public static int ice=-1;



    public static IDrinkShopAPI getAPI()
    {
        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopAPI.class);
    }

}
