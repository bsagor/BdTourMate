package com.akhgupta.easylocation.demo.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.akhgupta.easylocation.demo.R;
import com.akhgupta.easylocation.demo.gallery.BitmapHelper;
import com.akhgupta.easylocation.demo.gallery.FolderGridAdapter;
import com.akhgupta.easylocation.demo.gallery.GalleryActivity;
import com.akhgupta.easylocation.demo.gallery.GridViewItem;
import com.akhgupta.easylocation.demo.gallery.ImageGridAdapter;
import com.google.android.gms.ads.formats.NativeAd;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import static android.util.JsonToken.NULL;

public class TravelMomentActivity extends AppCompatActivity implements View.OnClickListener{
    final String TAG = "TravelMomentActivity";
    Context context;

    EditText etAddMoment;
    Button btnSaveMoment;
    ImageView ivImage;
    GridView gridView;

    String momentTitle = "";
    String baseDir = Environment.getExternalStorageDirectory() + "/BDTOURMATE/";;

    List<GridViewItem> gridItems;
    ArrayList<String> aList = new ArrayList<>();
    ArrayList<String> dList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_moment);

        initialize();
    }

    private void initialize() {
        context = TravelMomentActivity.this;

        etAddMoment = (EditText) findViewById(R.id.etAddMoment);
        btnSaveMoment = (Button) findViewById(R.id.btnSaveMoment);
        btnSaveMoment.setOnClickListener(this);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        gridView = (GridView) findViewById(R.id.gridView);

        File file = new File(baseDir);
        if(!file.exists()) {
            file.mkdirs();
        }
        File[] files = new File(baseDir).listFiles();
        for (File fil : files) {
            Log.d(TAG, "Name " + fil.getName());

            aList.add(fil.getName());
            dList.add(fil.getPath());
        }

        FolderGridAdapter adapter = new FolderGridAdapter(context, aList);
        adapter.notifyDataSetChanged();

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), aList.get(position) + " Added", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TravelMomentActivity.this, GalleryActivity.class);
                intent.putExtra("moment", baseDir + aList.get(position));
                startActivity(intent);
            }
        });

        /*gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String moment = dList.get(i);
                File f = new File(moment);
                ArrayList<String> nList = new ArrayList<>();
                if(f.isDirectory()){
                    f.delete();

                    File[] files = new File(baseDir).listFiles();
                    for (File fil : files) {
                        Log.d(TAG, "delete Name " + fil.getName());

                        nList.add(fil.getName());
                    }
                    FolderGridAdapter adapter = new FolderGridAdapter(context, nList);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(),
                            aList.get(i) + " has deleted", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveMoment:
                momentTitle = etAddMoment.getText().toString();
                Log.d(TAG, "btnSaveMoment " + momentTitle);
//                String baseDir = Environment.getExternalStorageDirectory() + "/BDTOURMATE/";
//                ArrayList<String> aList = new ArrayList<>();
                File file = new File(baseDir + momentTitle + "/");
                    if(!file.exists()) {
                        file.mkdirs();
                        Log.d(TAG, "file::" + file.getAbsolutePath());
                        aList.add(momentTitle);

                        FolderGridAdapter adapter = new FolderGridAdapter(context, aList);
                        adapter.notifyDataSetChanged();

                        gridView.setAdapter(adapter);
                    }
                break;
        }
    }
}
