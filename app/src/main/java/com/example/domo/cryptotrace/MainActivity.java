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
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
private Button bOpenPref;
private RecyclerView recyclerView;
private List<Product> listing;
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

        recyclerView = findViewById(R.id.recycle);
//        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
//        ImageLoader.getInstance().init(config);
        listing = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aPiService service = retrofit.create(aPiService.class);
        Call<List<Product>> call=   service.getbookdetails();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                List<Product> list = response.body();
                Product product = null;
                for (int i = 0; i < list.size(); i++) {
                    product = new Product();
                    String name = list.get(i).getName();
                    String color = list.get(i).getRank();
                    String symbol = list.get(i).getSymbol();
                  //  String image = list.get(i).getImageurl();
                    String price = list.get(i).getPriceUsd();
                    product.setPriceUsd(price);
                    product.setSymbol(symbol);
                    product.setRank(color);
                    product.setName(name);
                  //  product.setImageurl(image);
                    listing.add(product);
                }


                Recycleradapter recyclerAdapter = new Recycleradapter(listing);
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this, 2);
                // RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
                recyclerView.setLayoutManager(recyce);
            //    recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);
            }



            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

            }



    //Ovo nam pomaže kako nebi morali gasiti aplikaciju da se Settings prikaže
    @Override
    protected void onResume() {
        super.onResume();
        getPreference();
    }
//Ovo nije radilo- pa sam ponovno napravio
//    @Override
//    protected void onReume() {
//        super.onResume();
//        getPreference();
//    }


    //Ovo je klasa koju smo sami gore kreirali kako bi mogli odrediti boje od pozadine kroz Settings
    //Tu još možeš i mjenjati text boju od texViewa sa metodom ||myTextView.setTextColor(0xAARRGGBB);||
    //Ili ovako ||ContextCompat.getColor(context, R.color.your_color);||
    private void getPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.contains("color")) {
            if (preferences.getString("color", "0").equals("1")) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
//ovo sranje->  setTheme(R.style.AppTheme); --> NE RADI!! --
            } else if (preferences.getString("color", "0").equals("2")) {
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);



            } else {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);

            }
        }
    }

    private void deepChangeTextColor(ViewGroup parentLayout){
        for (int count=0; count < parentLayout.getChildCount(); count++){
            View view = parentLayout.getChildAt(count);
            if(view instanceof TextView){
                ((TextView)view).setTextColor(Color.WHITE);
            } else if(view instanceof ViewGroup){
                deepChangeTextColor((ViewGroup)view);
            }
        }
    }


    //OLD ACTION BAR EDIT
//        ActionBar actionBar = getSupportActionBar();
////        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setTitle(“ActivityName”);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setTitle(“Edit Conference”);





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


    public class GridSpacingdecoration extends RecyclerView.ItemDecoration {

        private int span;
        private int space;
        private boolean include;

        private GridSpacingdecoration(int span, int space, boolean include) {
            this.span = span;
            this.space = space;
            this.include = include;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % span;

            if (include) {
                outRect.left = space - column * space / span;
                outRect.right = (column + 1) * space / span;

                if (position < span) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                outRect.left = column * space / span;
                outRect.right = space - (column + 1) * space / span;
                if (position >= span) {
                    outRect.top = space;
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     * +Ispravljen Warning za public u private u metodi dpToPx
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    //Metoda za crashanje aplikacije - test CRASHLYTICS plugina od FABRICA+button u activity_main
   // public void forceCrash(View view) {
    //    throw new RuntimeException("This is a crash");
   // }




}



