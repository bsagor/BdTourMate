package com.akhgupta.easylocation.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.NearbyPlacesResponse;
import com.akhgupta.easylocation.demo.PlacesCustomAdapter;
import com.akhgupta.easylocation.demo.PlacesServiceApi;
import com.akhgupta.easylocation.demo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.akhgupta.easylocation.demo.activity.Home.parameter;
import static com.akhgupta.easylocation.demo.activity.Home.parentparameter;

public class PlacesViewMaster extends AppCompatActivity {
    private ListView placesLV;
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/";
    private PlacesServiceApi placesServiceApi;
    private String searchString;
    private EditText searchET;
    private PlacesCustomAdapter placesCustomAdapter;
    private Unbinder unbinder;
    Context context = PlacesViewMaster.this;
    EditText txtAddress;
    ProgressBar progressBar;
    ImageView icon;
    float la,lo;
    String imageurl="https://maps.gstatic.com/mapfiles/place_api/icons/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_view_master);

        txtAddress= (EditText) findViewById(R.id.txtAddress);
        progressBar= (ProgressBar) findViewById(R.id.pb);
        placesLV = (ListView) findViewById(R.id.placesList);
        searchString=parameter+" in "+parentparameter;
        txtAddress.setText(searchString);
        networkLibraryIntialize();
        getData();
       progressBar.setVisibility(View.GONE);

    }
    private void getData() {
        String urlString = String.format("json?query=%s&key=%s",searchString,getString(R.string.place_api_key));
        Call<NearbyPlacesResponse> nearbyPlacesResponseCall = placesServiceApi.getAllResponse(urlString);
        nearbyPlacesResponseCall.enqueue(new Callback<NearbyPlacesResponse>() {
            @Override
            public void onResponse(Call<NearbyPlacesResponse> call, Response<NearbyPlacesResponse> response) {
                NearbyPlacesResponse nearbyPlacesResponse = response.body();
                final List<NearbyPlacesResponse.Result> results = nearbyPlacesResponse.getResults();
                placesCustomAdapter = new PlacesCustomAdapter(PlacesViewMaster.this,results);
                placesLV.setAdapter(placesCustomAdapter);

            }

            @Override
            public void onFailure(Call<NearbyPlacesResponse> call, Throwable t) {

            }
        });
    }

    private void networkLibraryIntialize() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placesServiceApi = retrofit.create(PlacesServiceApi.class);
    }
   /* public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mastermenu1,menu);
        return true;
    }



    public void logOut(MenuItem item) {
        Home.tourID="";
        Home.costID="0";
        Intent intent=new Intent(PlacesViewMaster.this,MainActivity2.class);
        startActivity(intent);
    }
*/
    public void showData(View view) {
        progressBar.setVisibility(View.VISIBLE);
        searchString =  txtAddress.getText().toString();
        networkLibraryIntialize();
        getData();
        progressBar.setVisibility(View.GONE);
    }
}
