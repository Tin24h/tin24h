package tintuc.diepnvph04430.diep.tintuc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GiaiTri extends Fragment implements SearchView.OnQueryTextListener {
    View giaitri;
    ListView lv;
    tintuc_adapter tintuc_adapter;
    ArrayList<TinTuc> mangdocbao;
    ArrayList<TinTuc> arrTT;
  boolean  abc = false;


    public GiaiTri() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        giaitri = inflater.inflate(R.layout.activity_giai_tri, container, false);
        mangdocbao=new  ArrayList<TinTuc>();
        lv=(ListView)giaitri.findViewById(R.id.lvtintuc);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new GiaiTri.Readdata().execute("http://vnexpress.net/rss/giai-tri.rss");
                abc= true;
                new GiaiTri.Readdata().execute("http://tuoitre.vn/rss/tt-van-hoa-giai-tri.rss");
            }
        });
        setHasOptionsMenu(true);
        return giaitri;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.menu_seach);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(this);
        sv.setIconifiedByDefault(false);



        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        newText = newText.toLowerCase();
        ArrayList<TinTuc> newlist = new ArrayList<>();
        for (TinTuc a : mangdocbao) {
            String name = a.getTitle().toLowerCase();
            if (name.contains(newText)) {
                newlist.add(a);


            }
            tintuc_adapter.setFilter(newlist);


        }

        return true;
    }

    class Readdata extends AsyncTask<String ,Integer , String> {

        ProgressDialog pbloading;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbloading = new ProgressDialog(getContext());
            pbloading.setMessage("Đang tải  chờ xíu nhé..");
            pbloading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pbloading.setCancelable(true);
            pbloading.setCanceledOnTouchOutside(false);
            pbloading.show();
        }


        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            pbloading.dismiss();
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListdescription= document.getElementsByTagName("description");
            String title = "";
            String hinhanh= "";
            String link ="";

            for(int i = 0 ; i<nodeList.getLength(); i++){
                String cdata=nodeListdescription.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);

                if(matcher.find()){
                    hinhanh=matcher.group(1);

                }

                Element element =(Element)nodeList.item(i);
                title = parser.getValue(element,"title");
                link= parser.getValue(element,"link");

                mangdocbao.add(new TinTuc(title,link,hinhanh));


            }
            if(abc) {
                tintuc_adapter = new tintuc_adapter(getContext(), R.layout.dong_layoutlistview, mangdocbao);
                lv.setAdapter(tintuc_adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getContext(), Tintucchinh.class);
                        intent.putExtra("link", mangdocbao.get(position).getLink());

                        startActivity(intent);

                    }
                });
            }


            super.onPostExecute(s);

        }
    }
    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);


            URLConnection urlConnection = url.openConnection();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }



}
