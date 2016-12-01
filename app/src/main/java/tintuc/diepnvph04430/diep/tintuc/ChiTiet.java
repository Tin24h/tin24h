package tintuc.diepnvph04430.diep.tintuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChiTiet extends AppCompatActivity {
    TextView txttd, txtlt, txtnd, txtngay, txtgio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_chitiet);
        txtlt = (TextView) findViewById(R.id.cttloaitin);
        txttd = (TextView) findViewById(R.id.ccttieude);
        txtnd = (TextView) findViewById(R.id.cttnoidung);
        txtngay = (TextView) findViewById(R.id.cttngay);
        txtgio = (TextView) findViewById(R.id.cttgio);

        //Intent
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();


        String td = bd.getString("tieude");
        String lt = bd.getString("loaitin");
        String a = bd.getString("anh");
        String nd = bd.getString("noidung");
        String ng = bd.getString("ngay");
        String gi = bd.getString("gio");

        txttd.setText(td.toString());
        txtlt.setText(lt.toString());
        txtnd.setText(nd.toString());
        txtngay.setText(ng.toString());
        txtgio.setText(gi.toString());


    }
}
