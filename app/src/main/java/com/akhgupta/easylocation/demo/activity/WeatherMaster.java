package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.MainActivity;
import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.db.WeatherAdapter;
import com.akhgupta.easylocation.demo.api.WeatherApi;
import com.akhgupta.easylocation.demo.pojo.Example;
import com.akhgupta.easylocation.demo.pojo.Forecast;
import com.akhgupta.easylocation.demo.util.BdTourmatePreferenceManager;
import com.akhgupta.easylocation.demo.model.weatherResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.akhgupta.easylocation.demo.activity.Home.currentPlace;

public class WeatherMaster extends AppCompatActivity {
    String baseUrl="https://query.yahooapis.com/";
    String imageurl;
    WeatherApi weatherApi;
    TextView cityTV,maxtempTV,windTV;
    ImageView weatherImage;
    ListView weatherListview;
    EditText cityET;
    String parameterf;
    String a;
    Context context = WeatherMaster.this;
    BdTourmatePreferenceManager memory;
    WeatherAdapter weatherAdapter;
    ArrayList<weatherResult> weatherList;
    CheckBox chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_master);
        maxtempTV= (TextView) findViewById(R.id.maxtempTV);
        cityTV= (TextView) findViewById(R.id.cityTV);
        windTV= (TextView) findViewById(R.id.windTV);
        weatherImage= (ImageView) findViewById(R.id.weatherImg);
        weatherListview= (ListView) findViewById(R.id.weatherList);
        chk= (CheckBox) findViewById(R.id.chk);
        cityET= (EditText) findViewById(R.id.cityET);
        imageurl="http://l.yimg.com/a/i/us/we/52/";
        a=currentPlace;
        cityET.setText(currentPlace);
        networkLibraryInitializer();
        if(chk.isChecked()==true)
        {
            getDatac();
        }
        else
        {
            getDataf();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu,menu);
        return true;
    }

    public void home(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(WeatherMaster.this,Home.class);
        startActivity(intent);
    }

    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        memory.deletePreferences(memory.KEY_VISITOR_LOGIN);
        Intent intent=new Intent(WeatherMaster.this,MainActivity2.class);
        startActivity(intent);
    }
    private void networkLibraryInitializer() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi=retrofit.create(WeatherApi.class);
    }

    private void getDatac() {

        String url="v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+a+"%2C%20%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        Call<Example>allData=weatherApi.getAllData(url);
        allData.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example;
                try {
                    example=response.body();
                    int curentt;
                    curentt=Integer.parseInt(example.getQuery().getResults().getChannel().getItem().getCondition().getTemp());

                    curentt=(curentt-32)*5/9;

                    maxtempTV.setText("Temp: "+String.valueOf(curentt)+ Html.fromHtml("&deg;")+" C");

                    cityTV.setText("City: "+example.getQuery().getResults().getChannel().getLocation().getCity());
                    windTV.setText("Wind: "+example.getQuery().getResults().getChannel().getWind().getSpeed()+" "+example.getQuery().getResults().getChannel().getUnits().getSpeed());

                    String status=example.getQuery().getResults().getChannel().getItem().getCondition().getText().toString();

                    imageurl=imageurl+example.getQuery().getResults().getChannel().getItem().getCondition().getCode()+".gif";
                    /*Toast.makeText(MainActivity.this, imageurl, Toast.LENGTH_LONG).show();*/
                    Picasso.with(WeatherMaster.this).load(imageurl).error(R.drawable.clear).into(weatherImage);

                    List<Forecast> finalList=example.getQuery().getResults().getChannel().getItem().getForecast();
                    weatherList=new ArrayList<>();

                    List<Forecast> forcasrList= example.getQuery().getResults().getChannel().getItem().getForecast();

                    for(int i=0;i<forcasrList.size();i++)
                    {
                        int highc;
                        int lowc;
                        highc=Integer.parseInt(finalList.get(i).getHigh());
                        lowc=Integer.parseInt(finalList.get(i).getLow());
                        highc=(highc-32)*5/9;
                        lowc=(lowc-32)*5/9;
                        weatherList.add(new weatherResult(finalList.get(i).getCode(),finalList.get(i).getDate(),String.valueOf(highc)+ Html.fromHtml("&deg;")+" C",String.valueOf(lowc)+ Html.fromHtml("&deg;")+" C",finalList.get(i).getDay(),finalList.get(i).getText()));
                    }

                    weatherAdapter=new WeatherAdapter(WeatherMaster.this,weatherList);
                    weatherListview.setAdapter(weatherAdapter);
                }
                catch (Exception e)
                {
                    Toast.makeText(WeatherMaster.this, "Sorry no city found", Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
    private void getDataf() {

        String url="v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+a+"%2C%20%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        Call<Example>allData=weatherApi.getAllData(url);
        allData.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example;
                try {
                    example=response.body();
                    int curentt;
                    curentt=Integer.parseInt(example.getQuery().getResults().getChannel().getItem().getCondition().getTemp());

                    maxtempTV.setText("Temp: "+example.getQuery().getResults().getChannel().getItem().getCondition().getTemp()+ Html.fromHtml("&deg;")+" "+example.getQuery().getResults().getChannel().getUnits().getTemperature());

                    cityTV.setText("City: "+example.getQuery().getResults().getChannel().getLocation().getCity());
                    windTV.setText("Wind: "+example.getQuery().getResults().getChannel().getWind().getSpeed()+" "+example.getQuery().getResults().getChannel().getUnits().getSpeed());

                    String status=example.getQuery().getResults().getChannel().getItem().getCondition().getText().toString();

                    imageurl=imageurl+example.getQuery().getResults().getChannel().getItem().getCondition().getCode()+".gif";
                    /*Toast.makeText(MainActivity.this, imageurl, Toast.LENGTH_LONG).show();*/
                    Picasso.with(WeatherMaster.this).load(imageurl).error(R.drawable.clear).into(weatherImage);

                    List<Forecast> finalList=example.getQuery().getResults().getChannel().getItem().getForecast();
                    weatherList=new ArrayList<>();

                    List<Forecast> forcasrList= example.getQuery().getResults().getChannel().getItem().getForecast();

                    for(int i=0;i<forcasrList.size();i++)
                    {
                        int highc;
                        int lowc;
                        highc=Integer.parseInt(finalList.get(i).getHigh());
                        lowc=Integer.parseInt(finalList.get(i).getLow());
                        highc=(highc-32)*5/9;
                        lowc=(lowc-32)*5/9;
                        weatherList.add(new weatherResult(finalList.get(i).getCode(),finalList.get(i).getDate(),finalList.get(i).getHigh()+ Html.fromHtml("&deg;")+" "+example.getQuery().getResults().getChannel().getUnits().getTemperature(),finalList.get(i).getLow()+ Html.fromHtml("&deg;")+" "+example.getQuery().getResults().getChannel().getUnits().getTemperature(),finalList.get(i).getDay(),finalList.get(i).getText()));

                    }

                    weatherAdapter=new WeatherAdapter(WeatherMaster.this,weatherList);
                    weatherListview.setAdapter(weatherAdapter);
                }
                catch (Exception e)
                {
                    Toast.makeText(WeatherMaster.this, "Sorry no city found", Toast.LENGTH_SHORT).show();

                }



            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

    public void locationUpdate(MenuItem item) {
        Intent intent=new Intent(WeatherMaster.this,MainActivity.class);
        startActivity(intent);
    }
    public void showCity(View view) {
        imageurl="http://l.yimg.com/a/i/us/we/52/";
        a=cityET.getText().toString().trim();
        networkLibraryInitializer();

        if(chk.isChecked())
        {
            getDatac();
        }
        else
        {
            getDataf();
        }


    }

    public void change(View view) {
        imageurl="http://l.yimg.com/a/i/us/we/52/";
        a=cityET.getText().toString().trim();
        networkLibraryInitializer();

        if(chk.isChecked()==true)
        {
            getDatac();
        }
        else
        {
            getDataf();
        }
    }
}

