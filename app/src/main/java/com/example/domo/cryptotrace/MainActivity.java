package com.example.domo.cryptotrace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
        setContentView(R.layout.activity_main);
        getUserList();
    }

//Kreiranje i dovlacenje Preferences screen-a
        //Ovo je zakomentirano jer više nema svrhu , prebačeno u ActionBar Vidi onOptionsItemSelected klasu
//        bOpenPref = findViewById(R.id.bOpenPreferences);
//
//        bOpenPref.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PreferencesScreenActivity.class);
//                startActivity(intent);
//            }
//        });





    //Ovo nam pomaže kako nebi morali gasiti aplikaciju da se Settings prikaže
    @Override
    protected void onResume() {
        super.onResume();
        getPreference();
    }



    //Ovo je klasa koju smo sami gore kreirali kako bi mogli odrediti boje od pozadine kroz Settings
    //Tu još možeš i mjenjati text boju od texViewa sa metodom ||myTextView.setTextColor(0xAARRGGBB);||
    //Ili ovako ||ContextCompat.getColor(context, R.color.your_color);||
    private void getPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.contains("color")) {
            if (preferences.getString("color", "0").equals("1")) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
//                findViewById(R.id.price)
//ovo sranje->  setTheme(R.style.AppTheme); --> NE RADI!! --
            } if (preferences.getString("color", "0").equals("2")) {
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
//                deepChangeTextColor();
            } else if (preferences.getString("color","0").equals("3")){
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }else{
                getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
            }
        }
    }

//} else if (preferences.getString("color", "0").equals("2")) {
//        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
////                deepChangeTextColor();
//        } else {
//        getWindow().getDecorView().setBackgroundColor(Color.RED);
//        }





            //Ovdje govorimo na koji main menu mislimo
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //Ovdje govorimo sto ce tocno klik na settings u action baru otvoriti
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.your_item_id) {
            //A ovo je metoda za pozivanje i startanje preferences activity-a
            Intent intent = new Intent(MainActivity.this, PreferencesScreenActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUserList() {
        try {
            APIService service = ApiClient.getRetrofit().create(APIService.class);
            Call<List<User>> call = service.getUserData();

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    //Log.d("onResponse", response.message());

                    List<User> userList = response.body();
                   LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                    RecyclerView recyclerView = (RecyclerView)
                            findViewById(R.id.recycler);
                    recyclerView.setLayoutManager(layoutManager);

                    RecyclerViewAdapter recyclerViewAdapter =
                            new RecyclerViewAdapter(userList);

                    recyclerView.setAdapter(recyclerViewAdapter);


                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
        }catch (Exception e) {}

    }
}

