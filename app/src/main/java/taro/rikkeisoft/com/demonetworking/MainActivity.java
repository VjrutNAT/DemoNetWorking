package taro.rikkeisoft.com.demonetworking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DATA_URL =  "https://jsonplaceholder.typicode.com/posts";
    private JObjectAdapter jObjectAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (isNetWorkingConnected()){
            new GetDataAsyncTask().execute();
        }
    }

    private class GetDataAsyncTask extends AsyncTask <String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            String json = null;
            try {
                URL url = new URL(DATA_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String lines;

                    while ((lines = br.readLine()) != null){
                        sb.append((lines + "\n"));
                    }
                    br.close();
                    Log.d("doInBackGround", sb.toString());
                    json = String.valueOf(sb);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                jObjectAdapter = new JObjectAdapter(readJson(s), MainActivity.this);
                recyclerView.setAdapter(jObjectAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isNetWorkingConnected(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private ArrayList<Object> readJson(String json) throws JSONException {
        ArrayList<Object> objects = new ArrayList<>();
        if (isNetWorkingConnected()){
            JSONArray array = new JSONArray(json);
            for (int i = 0; i <  array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                int userId = obj.getInt("userId");
                int id = obj.getInt("id");
                String title = obj.getString("title");
                String body = obj.getString("body");
                Object object = new Object(userId, id, title, body);
                objects.add(object);
            }
        }

        return objects;
    }
}
