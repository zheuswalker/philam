package redeye.ghostofwar.philam.ServicesOffered;

import android.app.AlertDialog;
import android.content.Context;
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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import redeye.ghostofwar.philam.Home.home_products_adapter;
import redeye.ghostofwar.philam.Home.home_services_content_constructors;
import redeye.ghostofwar.philam.R;

public class ProductsOffered extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public  static redeye.ghostofwar.philam.Home.home_products_adapter home_products_adapter;
    public  static List<redeye.ghostofwar.philam.Home.home_services_content_constructors> home_services_content_constructors =new ArrayList<>();
    home_services_content_constructors current1;


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_products_offered);

        context = ProductsOffered.this;
        new getNotifications(context).execute(getIntent().getStringExtra("ProductName"));
        recyclerView = findViewById(R.id.notificationrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
    public class getNotifications extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        getNotifications(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = "https://server.sympies.net/api/getProducts.php";

            //http:///192.168.100.69:8080/ecomSympies/public/mobilelogin

            String servicename = params[0];
            String data;
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                data = URLEncoder.encode("servicename","UTF-8")+"="+ URLEncoder.encode(servicename,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {


            Log.d("resultfromweb", result);
            home_services_content_constructors.clear();

            JSONObject feedcontentvalues = null;

            try {

                feedcontentvalues = new JSONObject(result);

                try {
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("products");

                    for (int i=0; i < feedvalues.length(); i++)
                    {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String pso_service_name = feedarray.getString("ppo_productname");
                        String pso_service_desc = "Issue Age : "+ feedarray.getString("ppo_issueage");
                        String pst_service_servicename = "Life Insurance Coverage " + feedarray.getString("ppo_coverage");

                        home_services_content_constructors current1 = new home_services_content_constructors(pso_service_name,pso_service_desc,pst_service_servicename);
                        home_services_content_constructors.add(current1);




                    }
                    home_products_adapter = new home_products_adapter(context, home_services_content_constructors);
                    recyclerView.setAdapter(null);
                    recyclerView.setAdapter(home_products_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }


        }



    }



}
