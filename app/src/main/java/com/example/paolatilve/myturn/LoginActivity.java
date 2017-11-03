package com.example.paolatilve.myturn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Model.User;
import Services.UserServiceManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String BASEURL = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        Button boton = findViewById(R.id.btLogin);
        boton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                TextView user = findViewById(R.id.txUser);
                TextView password =  findViewById(R.id.pwPassword);
                boolean valid = ( user.getText().toString() !=null && user.getText().toString().trim().length() > 0 )
                        && ( password.getText().toString() !=null && password.getText().toString().trim().length() > 0 );
                if(valid){
                    getServiceUser(user.getText().toString(),password.getText().toString());
                }else{
                    Toast.makeText(LoginActivity.this, "Por favor digíte usuario y password.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void getServiceUser(String user, String password){
        Retrofit r = new Retrofit.Builder()
                .baseUrl(BASEURL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserServiceManager service = r.create(UserServiceManager.class);
        Call<User> userInfo = service.GetUser();
        userInfo.enqueue(new Callback<User>() {
                             @Override
                             public void onResponse(Call<User> call, Response<User> response) {
                                 try {
                                     User serviceUser = response.body();
                                     if ( serviceUser != null ){
                                         Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                                         startActivity(intent);
                                     }
                                     Toast.makeText(LoginActivity.this,serviceUser.getName(),Toast.LENGTH_LONG).show();
                                 }catch (Exception e){
                                     Toast.makeText(LoginActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                                 }
                             }
                             @Override
                             public void onFailure(Call<User> call, Throwable t) {
                                 Toast.makeText(LoginActivity.this,t.toString(),Toast.LENGTH_LONG).show();
                             }
                         }
        );
    }

}

