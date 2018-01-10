package thingslab.batteryprotector_tl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
int percentage;
    ConstraintLayout ctl;
    RequestQueue ExampleRequestQueue;
    private BroadcastReceiver br=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*Network network=new BasicNetwork(new HurlStack());
            queue=new RequestQueue(cache,network);
            Cache cache=new DiskBasedCache(getCacheDir(),1024*1024);
            queue.start();
*/


            int lvl = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            percentage=lvl;
 //          Toast.makeText(getApplicationContext(), Integer.toString(percentage), Toast.LENGTH_LONG).show();
            String url ="http://192.168.4.1/battery" + Integer.toString(percentage);
             System.out.println(url);
// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                          }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
// Add the request to the RequestQueue.
           ExampleRequestQueue.add(stringRequest);



        }
    };

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
    public boolean checkCon() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssid = info.getSSID();
        if (ssid.contains("BatteryProtect" )) {
        return true;
        }
        else{
            return false;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//ctl=(ConstraintLayout) findViewById(R.id.main_layout);
        Button btn=(Button)findViewById(R.id.button);
        ExampleRequestQueue=Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkCon()){
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                    MainActivity.this.registerReceiver(MainActivity.this.br,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
  //                  ctl.setBackgroundColor(13101638);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Not Connected to BatteryProtect Network", Toast.LENGTH_LONG).show();

                }


            }
        });
                }
            }


 //
        //
        /*WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        String ssid = info.getSSID();
        if (checkCon() ) {
            Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
            this.registerReceiver(this.br,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            setActivityBackgroundColor(13101638);
            TextView t=(TextView) findViewById(R.id.textmsg);

            t.setText("Connected \n Keep this app running.");

        }
        else {

            Toast.makeText(getApplicationContext(), "Not Connected to BatteryProtect Network", Toast.LENGTH_LONG).show();
        }*/





