package com.akhgupta.easylocation.demo.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import  com.akhgupta.easylocation.demo.R;

import java.util.List;

/**
 * Created by medisys-java on 20-Nov-16.
 */
public class ImageGridAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<GridViewItem> items;


    public ImageGridAdapter(Context context, List<GridViewItem> items) {
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return items.size();
    }


    @Override
    public Object getItem(int position) {
        return items.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gallery_item, null);
        }

        TextView text = (TextView) convertView.findViewById(R.id.textView);
//        text.setText(items.get(position).getPath());
        text.setText(items.get(position).getPath());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Bitmap image = items.get(position).getImage();

        if (image != null){
            imageView.setImageBitmap(image);
        }
        else {
            // If no image is provided, display a folder icon.
            imageView.setImageResource(R.drawable.folder);
        }

        return convertView;
    }

}
