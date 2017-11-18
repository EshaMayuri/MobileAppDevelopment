package com.sample.labassignment1;

/**
 * Created by Esha Mayuri on 11/15/2017.
 */

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Adapter extends BaseAdapter
{
    String[] result;
    Context context;
    String[] imageUrlList;
    private static LayoutInflater layoutInflater = null;

    public Adapter(BooksActivity booksActivity, String[] bookNameList, String[] imagesList)
    {

        result = bookNameList;
        context = booksActivity;
        imageUrlList = imagesList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount()
    {
        return result.length;
    }

    @Override
    public Object getItem(int position)
    {
        return  position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Data
    {
        TextView textView;
        ImageView imageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Data data = new Data();
        View rowView;

        rowView = layoutInflater.inflate(R.layout.programlist, null);
        data.textView = (TextView)rowView.findViewById(R.id.textView1);
        data.imageView=(ImageView) rowView.findViewById(R.id.imageView1);

        data.textView.setText(result[position]);

        Picasso.with(context).load(imageUrlList[position]).fit().centerCrop().into(data.imageView);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+imageUrlList[position], Toast.LENGTH_LONG).show();

                Intent intentEditJob = new Intent(context,BooksActivity.class);
                intentEditJob.putExtra("ImageUrl", imageUrlList[position]);
                intentEditJob.putExtra("BookName", result[position]);
                context.startActivity(intentEditJob);


            }
        });

        return rowView;
    }
}
