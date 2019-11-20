package com.example.tringuyen_sydneyaraujo_comp304_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ChineseActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> restaurantList;
    private ListView lv;
    private String TAG = MainActivity.class.getSimpleName();
    String searchCriteria = "chinese%20restaurant";
    public static final String API_KEY = "AIzaSyDLBUjnJHO4IS6CdV0CMAaBqHXG3Qd8k8w";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);

        restaurantList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new getRestaurant().execute();
    }

    private class getRestaurant extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" +
                    searchCriteria + "&key=" + API_KEY + "";
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray res = jsonObj.getJSONArray("results");

                    // looping through All restaurant
                    for (int i = 0; i < res.length(); i++) {
                        JSONObject c = res.getJSONObject(i);
                        String name = c.getString("name");
                        String rating = c.getString("rating");
                        String address = c.getString("formatted_address");

                        // tmp hash map for single restaurant
                        HashMap<String, String> restaurant = new HashMap<>();

                        // adding each child node to HashMap key => value
                        restaurant.put("name", name);
                        restaurant.put("rating", rating);
                        restaurant.put("address", address);

                        // adding restaurant to restaurant list
                        restaurantList.add(restaurant);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ChineseActivity.this, "Retrieving restaurant list...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(ChineseActivity.this, restaurantList,
                    R.layout.restaurant_list_item, new String[]{ "name","address"},
                    new int[]{R.id.name, R.id.rating});
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    Intent intent = new Intent( getApplicationContext( ), MapsActivity.class );
                    TextView txtname = (TextView) v.findViewById(R.id.rating);
                    TextView txtname1 = (TextView) v.findViewById(R.id.name);
                    String tName1= txtname1.getText().toString();

                    String tName= txtname.getText().toString();
                    String itempos = lv.getItemAtPosition(position).toString();
                    intent.putExtra("value", tName);
                    intent.putExtra("name1", tName1);

                    startActivity(intent);

                }

            });
        }

    }

}
