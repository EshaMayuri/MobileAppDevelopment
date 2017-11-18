package com.sample.labassignment1;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BooksActivity extends AppCompatActivity {

    GridView gridView;
    EditText editTextView;
    final String APIUrl ="https://www.googleapis.com/books/v1/volumes?q=";
    String query = "";


    ArrayList<String> arrayListPrgmList = new ArrayList<String>();
    ArrayList<String> arrayListPrgmImages = new ArrayList<String>();

    public static String [] prgmNameList;
    public static String [] prgmImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        gridView = (GridView)findViewById(R.id.gridView);
        editTextView = (EditText)findViewById(R.id.editTextView);

//        editTextView.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    Search(editTextView.getText().toString());
//                }
//                return true;
//            }
//        });

        Search("Harry Potter");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gridView.setAdapter(new Adapter(this, prgmNameList,prgmImages));

        Intent intentExtras = getIntent();
        String ImageUrl = intentExtras.getStringExtra("ImageUrl");
        String BookName = intentExtras.getStringExtra("BookName");

        if(ImageUrl!= null && BookName != null) {
            Intent intentEditJob = new Intent(this, BookDetailsActivity.class);
            intentEditJob.putExtra("ImageUrl", ImageUrl);
            intentEditJob.putExtra("BookName", BookName);
            startActivity(intentEditJob);
        }


    }

    public void Search(String query)
    {
        //String query = editTextView.getText().toString();
        final String url = APIUrl + query;

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                FetchDetails(url);
                return null;
            }
        }.execute();
    }

    public void FetchDetails(String url)
    {
        final OkHttpClient client=new OkHttpClient();
        Request newRequest=new Request.Builder()
                .url(url)
                .tag("data")
                .build();
        try
        {
            Response response = client.newCall(newRequest).execute();
            final JSONObject jsonResult;
            final String result = response.body().string();
            try
            {
                jsonResult = new JSONObject(result);
                JSONArray array = jsonResult.getJSONArray("items");
                for(int i = 0; i<array.length(); i++)
                {
                    JSONObject obj = array.getJSONObject(i);
                    String selfLink = obj.getString("selfLink");
                    JSONObject volumeInfo = obj.getJSONObject("volumeInfo");
                    String icon = volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");
                    String title =  volumeInfo.getString("title");
//                    icons.add(Integer.parseInt(icon));
                    arrayListPrgmList.add(title);
                    arrayListPrgmImages.add(icon);
                }
                //Adapter.SetIcons(ConvertToArray(icons));
                prgmNameList = ConvertToArray(arrayListPrgmList);
                prgmImages = ConvertToArray(arrayListPrgmImages);
            }
            catch (JSONException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.d("Http3","ran into exception: "+e.getMessage());
        }
        //return outputList;

    }

    public String[] ConvertToArray(ArrayList<String> arrayList) {
        String[] result = new String[arrayList.size()];
        result = arrayList.toArray(result);
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu., menu);
        return true;
    }
}

