package tintuc.diepnvph04430.diep.tintuc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import tintuc.diepnvph04430.diep.tintuc.model.TinTuc;

/**
 * Created by Joker on 11/24/2016.
 */

public class ThoiSu2 extends android.support.v4.app.Fragment{
    View thoisu;
    final String API = "http://webtintuccc.esy.es/Thoisu.php";
    ListView listView;
    ArrayList<TinTuc> arrTT;

    public ThoiSu2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        thoisu = inflater.inflate(R.layout.activity_thoi_su, container, false);
        listView = (ListView) thoisu.findViewById(R.id.listViewThoisu);
        arrTT = new ArrayList<TinTuc>();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ThoiSu2.doGetTT().execute(API);
            }
        });
        return thoisu;
    }
    private  class doGetTT extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jsonArray = new JSONArray(s);
                int totallength = jsonArray.length();
                int loaded = 0;
                int progress;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject objecttt = jsonArray.getJSONObject(i);
//                    loaded++;
//                    progress =loaded*100/totallength;
//                    publishProgress(progress);

                    arrTT.add(new TinTuc(
                            objecttt.getInt("id"),
                            objecttt.getString("loaitin"),
                            objecttt.getString("tieude"),
                            objecttt.getString("anh"),
                            null,
                            objecttt.getString("ngay"),
                            objecttt.getString("gio")

                    ));
                }
                Custom_tonghop listAdapter = new Custom_tonghop(getActivity(), R.layout.custom_lisview, arrTT);
                listView.setAdapter(listAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    //dsdsdsojj
    private static String docNoiDung_Tu_URL(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
