package com.example.bipul.androiddrinkshop;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
/*import android.support.v7.app.AlertDialog;*/
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import com.example.bipul.androiddrinkshop.Model.CheckUserResponse;
import com.example.bipul.androiddrinkshop.Model.User;
import com.example.bipul.androiddrinkshop.Retrofit.IDrinkShopAPI;
import com.example.bipul.androiddrinkshop.Utils.Common;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import dmax.dialog.SpotsDialog;
//import android.app.AlertDialog;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE=1000;
    Button btn_continue;
    IDrinkShopAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService= Common.getAPI();

        btn_continue=(Button) findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLoginPage(LoginType.PHONE);
            }
        });

        //check session
        if (AccountKit.getCurrentAccessToken()!=null)
        {
            //Auto login
           // final AlertDialog alertDialog = new SpotsDialog(MainActivity.this);
            final AlertDialog alertDialog =new SpotsDialog(this);
           // final SpotsDialog alertDialog =new SpotsDialog(MainActivity.this);

            alertDialog.show();
            alertDialog.setMessage("please waiting...");

            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    mService.checkUserExists(account.getPhoneNumber().toString())
                            .enqueue(new Callback<CheckUserResponse>() {
                                @Override
                                public void onResponse(Call<CheckUserResponse> call, Response<CheckUserResponse> response) {
                                    CheckUserResponse userResponse=response.body();
                                    if (userResponse.isExists())
                                    {
                                        //Fetch information

                                        mService.getUserInformation(account.getPhoneNumber().toString())
                                                .enqueue(new Callback<User>() {
                                                    @Override
                                                    public void onResponse(Call<User> call, Response<User> response) {

                                                        //if User already exists just start new activity
                                                        alertDialog.dismiss();
                                                      //  Log.d("info"," alertDialog.setMessage(\"please waiting...\")1");

                                                        //fixed first time crash
                                                        Common.currentUser=response.body();  //Fix here

                                                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                                        finish(); //close MainActivity

                                                    }

                                                    @Override
                                                    public void onFailure(Call<User> call, Throwable t) {
                                                        Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                    }
                                    else
                                    {
                                        // Else need registation
                                        alertDialog.dismiss();
                                        showRegisterDialog(account.getPhoneNumber().toString());
                                    }
                                }

                                @Override
                                public void onFailure(Call<CheckUserResponse> call, Throwable t) {

                                }
                            });

                }

                @Override
                public void onError(AccountKitError accountKitError) {
                    Log.d("Error", accountKitError.getErrorType().getMessage());

                }
            });

        }

      //  printKeyHash();
    }

    private void startLoginPage(LoginType loginType) {
        Intent intent= new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder builder=
                new AccountKitConfiguration.AccountKitConfigurationBuilder(loginType,
                AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,builder.build());
        startActivityForResult(intent,REQUEST_CODE);
    }

    //Ctrl+o


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE)
        {
            AccountKitLoginResult result=data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            
            if (result.getError() !=null)
            {
                Toast.makeText(this, ""+result.getError().getErrorType().getMessage(), Toast.LENGTH_SHORT).show();
            }
            else if (result.wasCancelled())
            {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(result.getAccessToken()!=null)
                {
                  //AlertDialog alertDialog =new SpotsDialog(MainActivity.this);
                    final AlertDialog alertDialog = new SpotsDialog(MainActivity.this);
                   /* final SpotsDialog alertDialog =new SpotsDialog(MainActivity.this);*/
                    alertDialog.show();
                    alertDialog.setMessage("please waiting...");

                    //Get User phone and check exists on server
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                            mService.checkUserExists(account.getPhoneNumber().toString())
                                    .enqueue(new Callback<CheckUserResponse>() {
                                        @Override
                                        public void onResponse(Call<CheckUserResponse> call, Response<CheckUserResponse> response) {
                                            CheckUserResponse userResponse=response.body();
                                            if (userResponse.isExists())
                                            {
                                                //Fetch information

                                                mService.getUserInformation(account.getPhoneNumber().toString())
                                                        .enqueue(new Callback<User>() {
                                                            @Override
                                                            public void onResponse(Call<User> call, Response<User> response) {

                                                                //if User already exists just start new activity
                                                                alertDialog.dismiss();

                                                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                                                finish(); //close MainActivity

                                                            }

                                                            @Override
                                                            public void onFailure(Call<User> call, Throwable t) {
                                                                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        });


                                            }
                                            else
                                            {
                                                // Else need registation
                                                alertDialog.dismiss();
                                              //  Log.d("info"," alertDialog.setMessage(\"please waiting...\")2");
                                                showRegisterDialog(account.getPhoneNumber().toString());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CheckUserResponse> call, Throwable t) {

                                        }
                                    });

                        }

                        @Override
                        public void onError(AccountKitError accountKitError) {
                            Log.d("Error", accountKitError.getErrorType().getMessage());

                        }
                    });
                }
            }
        }
    }

    private void showRegisterDialog(final String phone) {
         AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("REGISTER");


        LayoutInflater inflater=this.getLayoutInflater();
        View register_layout=inflater.inflate(R.layout.register_layout,null);
        final MaterialEditText edt_name=(MaterialEditText) register_layout.findViewById(R.id.edt_name);
        final MaterialEditText edt_address=(MaterialEditText) register_layout.findViewById(R.id.edt_address);
        final MaterialEditText edt_birthdate=(MaterialEditText) register_layout.findViewById(R.id.edt_birthdate);

        Button btn_register=register_layout.findViewById(R.id.btn_register);

        edt_birthdate.addTextChangedListener(new PatternedTextWatcher("####-##-##"));

        builder.setView(register_layout);

       final AlertDialog dialog=builder.create();

        //event
//        final AlertDialog Dialog = dialog;
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            dialog.dismiss();


                if (TextUtils.isEmpty(edt_address.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_birthdate.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please enter your birthdate", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edt_name.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }


                final AlertDialog waitingDialog=new SpotsDialog(MainActivity.this);
                waitingDialog.show();
                waitingDialog.setMessage("please waiting...");

                mService.registerNewUser(phone,
                        edt_name.getText().toString(),
                        edt_address.getText().toString(),
                        edt_birthdate.getText().toString())
                       .enqueue(new Callback<User>() {
                           @Override
                           public void onResponse(Call<User> call, Response<User> response) {

                               waitingDialog.dismiss();
                             //  Log.d("info","waitingDialog.dismiss();");
                               User user=response.body();
                               if (TextUtils.isEmpty(user.getError_mag()))
                               {
                                   Toast.makeText(MainActivity.this, "User register successfully", Toast.LENGTH_SHORT).show();
                                  Common.currentUser=response.body();
                                   //start new activity
                                  startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                  finish();

                                   }
                           }
                           @Override
                           public void onFailure(Call<User> call, Throwable t) {
                               waitingDialog.dismiss();

                           }
                       });
            }

        });
            dialog.show();

    }

    private void printKeyHash() {
        try{
            PackageInfo info=getPackageManager().getPackageInfo("com.example.bipul.androiddrinkshop",
                    PackageManager.GET_SIGNATURES);
            for (Signature singnature:info.signatures)
            {
                MessageDigest md =MessageDigest.getInstance("SHA");
                md.update(singnature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
