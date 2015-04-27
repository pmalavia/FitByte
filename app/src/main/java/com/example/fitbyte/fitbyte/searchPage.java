package com.example.fitbyte.fitbyte;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.Boolean;import java.lang.Exception;import java.lang.Override;import java.lang.String;import java.lang.StringBuilder;import java.lang.Void;import java.net.URLEncoder;
import java.util.ArrayList;


public class searchPage extends ActionBarActivity {
   public final static String SEARCHED_ITEMS = "com.example.amirsaifi.ITEMS";
   Button searchButton;
   Button listBut;
   EditText searchField;
   String parsedSearchText;
   String jsonResult = null;
   private ArrayList<foodItem> searchResults;
   private int maxResults = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        searchButton = (Button)findViewById(R.id.search_button);
        searchField = (EditText)findViewById(R.id.search_item);
    }


    /*
    public void setMaxResults(int num)
    {
        maxResults = num;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/
    public void buttonClicked(View view){


        //getJsonData();
        //createJSONObject();
        //Above Methods called in foodQueryAsyncTask()

        new foodQueryAsyncTask().execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class foodQueryAsyncTask extends AsyncTask<String, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params)
        {
            getJsonData();

            return null;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            super.onPostExecute(result);
            createJSONObject();

            if(searchResults.size() != 0) {
                Log.i("It Works! JsonResult: ", "\n" + searchResults.get(0).getItemName());
            }
            Intent intent = new Intent(searchPage.this, searchListActivity.class);
            intent.putParcelableArrayListExtra(SEARCHED_ITEMS, searchResults);
            startActivity(intent);
        }
    }

    //Requests info from Nutritionix Server and
    //Receives the Result in Json form in string called jsonResult
    public void getJsonData(){

        String encodedUrl = null;

        try {
            //formats text in text field to be URL friendly
            //encodedUrl is the food item typed in by user to be searched
            encodedUrl = URLEncoder.encode(searchField.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            // Can be safely ignored because UTF-8 is always supported
        }

        //Query that is sent to server to request food nutrition information
        String urlReq = "https://api.nutritionix.com/v1_1/search/" + encodedUrl +
                "?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2C" +
                "nf_calories%2Cnf_total_fat%2Cnf_total_carbohydrate%2Cnf_protein%2C" +
                "nf_serving_size_qty%2Cnf_serving_size_unit%2Cnf_serving_weight_grams" +
                "&appId=6f1a6b68&appKey=62ac2740a6ee4d065a8ec1dd0b30fb4b";
        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpGet request = new HttpGet(urlReq);

        InputStream inputStream = null;
        try {
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            inputStream = entity.getContent();
            // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            jsonResult = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
        }
    }

    //Parses the string (jsonResult) to be formatted and placed in class "foodItem"
    //stores all results in arrayList called "searchResults"
    public void createJSONObject()
    {
        try{
            JSONObject nutritionDatabaseHit = new JSONObject(jsonResult);
            JSONArray itemHits = nutritionDatabaseHit.getJSONArray("hits");
            searchResults = new ArrayList<foodItem>(itemHits.length());

            for(int i = 0 ; i < itemHits.length() ; i++)
            {
                JSONObject temp = itemHits.getJSONObject(i);
                JSONObject temp2 = temp.getJSONObject("fields");
                foodItem foodDataItem = new foodItem();

                foodDataItem.setItemName(temp2.getString("item_name"));
                foodDataItem.setBrandName(temp2.getString("brand_name"));
                foodDataItem.setCalories(temp2.getInt("nf_calories"));
                foodDataItem.setCarbs(temp2.getInt("nf_total_carbohydrate"));
                foodDataItem.setFat(temp2.getInt("nf_total_fat"));
                foodDataItem.setProtein(temp2.getInt("nf_protein"));
                foodDataItem.setServingSize(temp2.getInt("nf_serving_size_qty"));
                foodDataItem.setServingUnit(temp2.getString("nf_serving_size_unit"));
                //serving_weight_grams may be "null" for some items
                foodDataItem.setServingWeightinGrams(temp2.getInt("nf_serving_weight_grams"));

                searchResults.add(foodDataItem);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
