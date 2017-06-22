package tintuc.diepnvph04430.diep.tintuc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class Tintucchinh extends AppCompatActivity {
WebView wedview;
    ActionBar actionBar;
    ImageButton share;
    String duonglink;
    final Context context = this;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintucchinh);
        //actionBar.setHomeButtonEnabled(true);


        wedview=(WebView)findViewById(R.id.wedview);
        Intent intent = getIntent();
         duonglink = intent.getStringExtra("link");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (new CheckNet(Tintucchinh.this).isNetworkAvailable()) {
                    //todo có mạng
                    wedview.loadUrl(duonglink);
                    wedview.setWebViewClient(new WebViewClient());
                }else {
                    //todo ko có mạng
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    // Set the Alert Dialog Message
                    builder.setMessage("Internet Connection Required")
                            .setCancelable(false)
                            .setPositiveButton("Retry",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            // Restart the Activity
                                            Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_trangchu:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
