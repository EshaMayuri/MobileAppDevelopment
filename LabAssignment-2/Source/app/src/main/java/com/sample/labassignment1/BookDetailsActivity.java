package com.sample.labassignment1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class BookDetailsActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.imageView);

        Intent intentExtras = getIntent();
        String ImageUrl = intentExtras.getStringExtra("ImageUrl");
        String BookName = intentExtras.getStringExtra("BookName");
        URL url;
        Bitmap bmp;

        if(!"".equalsIgnoreCase(ImageUrl) && !"".equalsIgnoreCase(BookName) ) {
            try {
                url = new URL(ImageUrl.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            textView.setText(BookName);
            imageView.setImageBitmap(bmp);
        }

        Toast.makeText(getBaseContext(), ImageUrl + " " + BookName,Toast.LENGTH_SHORT).show();
    }
}
