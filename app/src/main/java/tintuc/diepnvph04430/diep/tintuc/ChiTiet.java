package tintuc.diepnvph04430.diep.tintuc;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChiTiet extends AppCompatActivity {
    TextView txttd, txtlt, txtnd, txtngay, txtgio;
    ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_chitiet);
        txtlt = (TextView) findViewById(R.id.cttloaitin);
        txttd = (TextView) findViewById(R.id.ccttieude);
        txtnd = (TextView) findViewById(R.id.cttnoidung);
        txtngay = (TextView) findViewById(R.id.cttngay);
        txtgio = (TextView) findViewById(R.id.cttgio);
        share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String textToShare = "Xin chào các bạn!!";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                // intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
                intent.putExtra(Intent.EXTRA_TEXT, textToShare);

                // See if official Facebook app is found
                boolean facebookAppFound = false;
                List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                        intent.setPackage(info.activityInfo.packageName);
                        facebookAppFound = true;
                        break;
                    }
                }
                startActivity(intent);
            }
        });

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
