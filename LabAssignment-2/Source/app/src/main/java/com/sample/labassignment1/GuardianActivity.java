package com.sample.labassignment1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GuardianActivity extends AppCompatActivity {

    ListView lv;
    EditText editTextView;
    ArrayList<String> list = new ArrayList<String>();
    HashMap<String, String> hash = new HashMap<String, String>();
    final String APIKey = "&api-key=84445c01-d18f-4cea-90dd-e9a9f443633e";
    String query = "";
    final String APIUrl = "https://content.guardianapis.com/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian);
        editTextView = (EditText)findViewById(R.id.editTextView);

        editTextView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Search(editTextView.getText().toString());
                }
                return true;
            }
        });


    }

    public void Search(String query)
    {
        //String query = editTextView.getText().toString();
        final String url = APIUrl + query + APIKey;

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                FetchDetails(url);
                return null;
            }
        }.execute();
    }

    private void Data(ArrayList<String> values)
    {
        lv = (ListView)findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textview, values);

        lv.setAdapter(adapter);

        // ListView Item Click Listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // ListView Clicked item index
                int itemPosition     = i;

                // ListView Clicked item value
                String  itemValue    = (String) lv.getItemAtPosition(i);
                itemValue = hash.get(itemValue);

                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("key",itemValue);
                startActivity(intent);

            }
        });
    }

    public ArrayList<String> FetchDetails(String url)
    {
        ArrayList<String> outputList = new ArrayList<String>();

        final OkHttpClient client=new OkHttpClient();
        Request newRequest=new Request.Builder()
                .url(url)
                .tag("data")
                .build();
        try
        {
            Response response=client.newCall(newRequest).execute();
            final JSONObject jsonResult;
            final String result = response.body().string();
            try
            {
                jsonResult = new JSONObject(result);
                JSONObject obj = jsonResult.getJSONObject("response");
                JSONArray jsonArray = obj.getJSONArray("results");
                for(int i = 0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String webTitle = jsonObject.getString("webTitle");
                    String webUrl = jsonObject.getString("webUrl");
                    list.add(webTitle);
                    hash.put(webTitle,webUrl);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        OkHttpUtils.cancelCallWithTag(client, "tag");
                        Data(list);
                    }});
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

        return outputList;
    }
}
