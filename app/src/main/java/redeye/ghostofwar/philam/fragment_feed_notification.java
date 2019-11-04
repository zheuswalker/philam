package redeye.ghostofwar.philam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Red Eye on 6/15/2018.
 */

public class fragment_feed_notification extends Fragment {


    public static RecyclerView recyclerView;
    public  static feed_notification_content_adapter feed_notification_content_adapter;
    public  static  List<feed_notification_required_settergetter> feed_notification_required_settergetter =new ArrayList<>();
    feed_notification_required_settergetter current1;


    Context context;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.layout_feed_notification_holder, container, false);
        context = getContext();
        new getNotifications(context).execute();
        recyclerView = rootView.findViewById(R.id.notificationrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return  rootView;


    }


    public class getNotifications extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context ctx;
        getNotifications(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected String doInBackground(String... params) {

            String reference = "https://server.sympies.net/api/getServices.php";

            //http:///192.168.100.69:8080/ecomSympies/public/mobilelogin

            String data ="";
            try {
                URL url = new URL(reference);
                HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
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

            feed_notification_required_settergetter.clear();

            Log.d("resultfromweb", result);

            JSONObject feedcontentvalues = null;

            try {

                feedcontentvalues = new JSONObject(result);

                try {
                    JSONArray feedvalues = feedcontentvalues.getJSONArray("services");

                    for (int i=0; i < feedvalues.length(); i++)
                    {
                        JSONObject feedarray = feedvalues.getJSONObject(i);
                        String pso_service_name = feedarray.getString("pso_service_name");
                        String pso_service_desc = feedarray.getString("pso_service_desc");
                        String pst_service_servicename = feedarray.getString("pst_service_servicename");

                        feed_notification_required_settergetter current1 = new feed_notification_required_settergetter(pso_service_name,pso_service_desc,pst_service_servicename);
                        feed_notification_required_settergetter.add(current1);




                    }
                    feed_notification_content_adapter = new feed_notification_content_adapter(context, feed_notification_required_settergetter);
                    recyclerView.setAdapter(null);
                    recyclerView.setAdapter(feed_notification_content_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }


        }



    }

}
