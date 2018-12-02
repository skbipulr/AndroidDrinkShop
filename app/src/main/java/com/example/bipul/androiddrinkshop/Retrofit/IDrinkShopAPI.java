package com.example.bipul.androiddrinkshop.Retrofit;



import android.view.Menu;

import com.example.bipul.androiddrinkshop.Model.Banner;
import com.example.bipul.androiddrinkshop.Model.Category;
import com.example.bipul.androiddrinkshop.Model.CheckUserResponse;
import com.example.bipul.androiddrinkshop.Model.Drink;
import com.example.bipul.androiddrinkshop.Model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IDrinkShopAPI {
    @FormUrlEncoded
    @POST("checkuser.php")
    Call<CheckUserResponse> checkUserExists(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("register.php")
    Call<User> registerNewUser(@Field("phone") String phone,
                               @Field("name") String name,
                               @Field("address") String address,
                               @Field("birthdate") String birthdate);


    @FormUrlEncoded
    @POST("getdrink.php")
    Observable<List<Drink>> getDrink(@Field("menuid") String menuID);


    @FormUrlEncoded
    @POST("getuser.php")
    Call<User> getUserInformation(@Field("phone") String phone);

    @GET("getbanner.php")
    Observable<List<Banner>>getBanners();


    @GET("getmenu.php")
    Observable<List<Category>>getMenu();


}
